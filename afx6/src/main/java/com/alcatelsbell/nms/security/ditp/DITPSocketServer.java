package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.ByteUtil;
import com.alcatelsbell.nms.util.LogUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 上午10:37
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class DITPSocketServer extends Thread{
    Selector selector = null;
    ServerSocketChannel serverSocketChannel = null;
    @Override
    public void run() {

        processMessageThread.start();

        try {
            startListening();
        } catch (IOException e) {
            LogUtil.error(getClass(), e, e);
        }

        while (!Thread.interrupted()) {
            try {
                selector.select();
            } catch (IOException e) {
                LogUtil.error(getClass(), e, e);
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
        //    System.out.println("get key size = "+selectionKeys.size());
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable())
                    accept(selectionKey);
                if (selectionKey.isReadable())
                    read(selectionKey);

            }

            selectionKeys.clear();
        }
    }

    public static void main(String[] args) {

        System.out.println((byte)0xA5);
        new DITPInterface().start();
    }

    private void accept (SelectionKey selectionKey) {

        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("accept:"+socketChannel);
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ,socketChannel);
//            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//            int read = socketChannel.read(byteBuffer);
//            System.out.println(read);
            //    selector.wakeup();

        } catch (IOException e) {
            LogUtil.error(getClass(), e, e);
        }

    }
    private SocketChannel liveChannel = null;
    private void read(SelectionKey selectionKey) {
      //  System.out.println("read:"+selectionKey.interestOps());
        SocketChannel channel = (SocketChannel) selectionKey.attachment();
        liveChannel = channel;
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            if ( channel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                queue.read(byteBuffer);
            }

        } catch (IOException e) {
            LogUtil.error(getClass(), e, e);
            try {
                channel.close();
            } catch (IOException e1) {
                LogUtil.error(getClass(), e1, e1);
            }
            return;
        }



    }

    private final  MessageBufferQueue queue = new MessageBufferQueue();

    private Thread processMessageThread = new Thread(){
        public void run() {
            while (true) {
                try {
                    SocketMessage message = (SocketMessage) queue.take();
                    int sequence = ((DITPSocketMessage)message).getSequence();
                    int cmd = ((DITPSocketMessage) message).getCmd();
                    DITPSocketMessageRequest ditpSocketMessageRequest = requests.get(sequence);
                    if (ditpSocketMessageRequest != null) {
                        DITPSocketMessageHandler handler = ditpSocketMessageRequest.getHandler();
                        handler.handleSocketMessage((DITPSocketMessage)message);

                    } else {
                        DITPSocketMessageHandler handler = handlers.get(cmd);
                        if (handler != null) {
                            SocketMessage replyMessage = handler.handleSocketMessage((DITPSocketMessage)message);
                            if (replyMessage != null) {
                                response((DITPSocketMessage) replyMessage);
                                handler.afterReponse();
                            }
                        }
                    }
                } catch (Throwable e) {
                    LogUtil.error(getClass(), e, e);
                }
            }
        }
    };



    /**
     * @todo
     * @param message
     */
    public DITPSocketMessageRequest request(DITPSocketMessage message, DITPSocketMessageHandler handler) throws Exception {
        DITPSocketMessageRequest request = new DITPSocketMessageRequest(message, handler);
        requests.put(message.getSequence(), request);
        System.out.println(new Date()+">>>>>>>>>> "+message);
        byte[] array = message.encode();
        ByteUtil.logBytes(array,"ditpmessage_post."+System.currentTimeMillis());
        liveChannel.write(ByteBuffer.wrap(array));
        return request;
    }

    public DITPSocketMessage blockingRequest(DITPSocketMessage message,long timeOutSeconds) throws Exception{
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        final List<DITPSocketMessage> list = new ArrayList();
        DITPSocketMessageRequest request = new DITPSocketMessageRequest(message, new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
            //    while (!lock.tryLock());
                lock.lock();
                try {
                    list.add(inMessagee);
                    condition.signalAll();
                } catch (Exception e) {
                    LogUtil.error(getClass(), e, e);
                } finally {
                    lock.unlock();
                }


                return null;
            }
        });
        requests.put(message.getSequence(), request);
        System.out.println(new Date()+">>>>>>>>>> "+message);
        byte[] array = message.encode();
        ByteUtil.logBytes(array,"ditpmessage_post."+System.currentTimeMillis());

        lock.lock();

        try {
            liveChannel.write(ByteBuffer.wrap(array));
            condition.await(timeOutSeconds, TimeUnit.SECONDS);
            if (list.size() > 0)
                return list.get(0);

        } catch (IOException e) {
            LogUtil.error(getClass(), e, e);
        } catch (InterruptedException e) {
            LogUtil.error(getClass(), e, e);
        } finally {
            lock.unlock();
        }

        throw new RequestTimeOutException("Request:"+message.getSequence()+" timeout");

    }


    /**
     * @todo
     * @param message
     */
    public void response(DITPSocketMessage message) throws Exception {
        System.out.println(new Date()+">>>>>>>>>> "+message);
        byte[] array = message.encode();
        ByteUtil.logBytes(array,"ditpmessage_post."+System.currentTimeMillis());
        liveChannel.write(ByteBuffer.wrap(array));
    }


    private HashMap<Integer,DITPSocketMessageHandler> handlers = new HashMap<Integer, DITPSocketMessageHandler>();
    private ConcurrentHashMap<Integer,DITPSocketMessageRequest> requests = new ConcurrentHashMap<Integer, DITPSocketMessageRequest>();
    public void registerMessageHandler(Integer cmd,DITPSocketMessageHandler handler) {
        handlers.put(cmd,handler);
    }





    private void startListening() throws IOException {
        selector = Selector.open();
        String a = null;
        String hostname = InetAddress.getLocalHost().getHostName();
        hostname = InetAddress.getLocalHost().getHostAddress();
        System.out.println(hostname);
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress("0.0.0.0",6900));
        serverSocketChannel.configureBlocking(false);
        System.out.println("Start listening on 0.0.0.0:6900");
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //  serverSocketChannel.register(selector, SelectionKey.OP_CONNECT);
        //   serverSocketChannel.register(selector, SelectionKey.OP_READ);
        //  serverSocketChannel.register(selector, SelectionKey.OP_WRITE);

    }




}
