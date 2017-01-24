package com.alcatelsbell.nms.security.ditp;


import com.alcatelsbell.nms.util.LogUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-16
 * Time: 下午2:26
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class TestClient   {



    public void disconnect() {
        try {
            if (socketChannel != null)
                socketChannel.finishConnect();
        } catch (IOException e) {
            LogUtil.error(getClass(), e, e);
        }
        System.out.println("disconnected");
    }
    SocketChannel socketChannel = null;

    public void connect() throws Exception{
        if (socketChannel != null)
            socketChannel.finishConnect();
        socketChannel =  SocketChannel.open(new InetSocketAddress("127.0.0.1",6900));
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.putChar('a');
        byteBuffer.putChar('b');
        byteBuffer.putChar('c');
        byteBuffer.putChar('d');
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        Thread.sleep(1000l);
        byteBuffer.clear();
        byteBuffer.putChar('e');
        byteBuffer.putChar('f');
        byteBuffer.putChar('g');
        byteBuffer.putChar('h');
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

        Thread.sleep(1000l);
    //    byteBuffer.clear();
        byteBuffer = ByteBuffer.wrap("ronnie".getBytes());

        socketChannel.write(byteBuffer);

    }

    public static void main(String[] args) throws Exception {
        new TestClient().connect();
    }

}
