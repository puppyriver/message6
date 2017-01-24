package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.ByteUtil;
import com.alcatelsbell.nms.util.LogUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-16
 * Time: 上午11:00
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class DITPInterface extends Thread{
   private  DITPSocketServer server = null;
    @Override
    public void run() {
        server = new DITPSocketServer();
        register();
        server.start();

    }
    final ReentrantLock lock = new ReentrantLock();
    final Condition newConnect = lock.newCondition();
    public boolean isReady = false;
    public boolean waitForNewConnect() {

        lock.lock();
        try {
            newConnect.await(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LogUtil.error(getClass(), e, e);
        } finally {
        }
        lock.unlock();

        return isReady;

    }

    private void register() {
        server.registerMessageHandler(Bodys.getCmd(Bodys.DITP_CONNECT.class)
                ,new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
                DITPSocketMessage respMessage = DITPSocketMessage.prepareEncoding
                        (Bodys.getCmd(Bodys.DITP_CONNECT_RESP.class), inMessagee.getSequence());
                Bodys.DITP_CONNECT_RESP respMessageBody = new Bodys.DITP_CONNECT_RESP();
                respMessage.setMessageBody(respMessageBody);
                respMessageBody.result = 0;
                respMessageBody.authenticatorDMS = ((Bodys.DITP_CONNECT)inMessagee.getMessageBody()).authenticator;


                return respMessage;
            }

            public void afterReponse() {
                lock.lock();
                try {
                    isReady = true;
                    newConnect.signalAll();
                } catch (Exception e) {
                    LogUtil.error(getClass(), e, e);
                } finally {
                }
                lock.unlock();
            }
        });

        server.registerMessageHandler(Bodys.getCmd(Bodys.DITP_DEVREG.class)
                ,new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
                DITPSocketMessage respMessage = DITPSocketMessage.prepareEncoding
                        (Bodys.getCmd(Bodys.DITP_DEVREG_RESP.class), inMessagee.getSequence());
                Bodys.DITP_DEVREG_RESP respMessageBody = new Bodys.DITP_DEVREG_RESP();
                respMessage.setMessageBody(respMessageBody);
                respMessageBody.result = getDeviceRegisterResult(((Bodys.DITP_DEVREG)inMessagee.getMessageBody()).deviceId);
                return respMessage;
            }
        });


        server.registerMessageHandler(Bodys.getCmd(Bodys.DITP_DEVLOGIN.class)
                ,new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
                DITPSocketMessage respMessage = DITPSocketMessage.prepareEncoding
                        (Bodys.getCmd(Bodys.DITP_DEVLOGIN_RESP.class), inMessagee.getSequence());
                Bodys.DITP_DEVLOGIN_RESP respMessageBody = new Bodys.DITP_DEVLOGIN_RESP();
                respMessage.setMessageBody(respMessageBody);
                respMessageBody.result = getDeviceLoginResult(((Bodys.DITP_DEVLOGIN)inMessagee.getMessageBody()).deviceId);
                return respMessage;
            }
        });

        server.registerMessageHandler(Bodys.getCmd(Bodys.DITP_DEVAUTH.class)
                ,new DITPSocketMessageHandler() {
            @Override
            public DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) {
                DITPSocketMessage respMessage = DITPSocketMessage.prepareEncoding
                        (Bodys.getCmd(Bodys.DITP_DEVAUTH_RESP.class), inMessagee.getSequence());
                Bodys.DITP_DEVAUTH_RESP respMessageBody = new Bodys.DITP_DEVAUTH_RESP();
                respMessage.setMessageBody(respMessageBody);
                respMessageBody.result = getDeviceAuthResult(((Bodys.DITP_DEVAUTH) inMessagee.getMessageBody()).deviceId);
                return respMessage;
            }
        });



    }


    protected int getDeviceRegisterResult(byte[] deviceId) {
        return 0;
    }
    protected int getDeviceLoginResult(byte[] deviceId) {
        return 0;
    }
    protected int getDeviceAuthResult(byte[] deviceId) {
        return 0;
    }


    public int notifyAddDevice(byte[] deviceId) throws Exception {
        Bodys.DITP_ADD_DEVICE body = new Bodys.DITP_ADD_DEVICE();
        body.deviceId = deviceId;
        DITPSocketMessage message = server.blockingRequest(buildMessage(body, nextSequence()),60);
        return ((Bodys.DITP_ADD_DEVICE_RESP) message.getMessageBody()).result;

    }


    public int notifyRemoveDevice(byte[] deviceId) throws Exception {
        Bodys.DITP_REMOVE_DEVICE body = new Bodys.DITP_REMOVE_DEVICE();
        body.deviceId = deviceId;
        DITPSocketMessage message = server.blockingRequest(buildMessage(body, nextSequence()),60);
        return ((Bodys.DITP_REMOVE_DEVICE_RESP) message.getMessageBody()).result;
    }

    public List<byte[]> listDevice() throws Exception {
        List result = new ArrayList();
        Bodys.DITP_LIST_DEVICE body = new Bodys.DITP_LIST_DEVICE();
       // body.reserved = new byte[64];
        DITPSocketMessage message = server.blockingRequest(buildMessage(body, nextSequence()),60);
        byte [] list =  ((Bodys.DITP_LIST_DEVICE_RESP) message.getMessageBody()).deviceList;
        int count = ((Bodys.DITP_LIST_DEVICE_RESP) message.getMessageBody()).count;
        int length = list.length / count;
        for (int i = 0; i < count; i++) {
            byte[] deviceId = ByteUtil.subByteArray(list,i * length, length);
            result.add(deviceId);
        }
        return result;
    }

    public int notifyDestoryData(byte[] deviceId) throws Exception {
        Bodys.DITP_DESTROY_DATA body = new Bodys.DITP_DESTROY_DATA();
        body.deviceId = deviceId;
        DITPSocketMessage message = server.blockingRequest(buildMessage(body, nextSequence()),60);
        return ((Bodys.DITP_DESTROY_DATA_RESP) message.getMessageBody()).result;
    }


    private AtomicInteger sequence = new AtomicInteger(10000);
    private int nextSequence() {
        return sequence.incrementAndGet();
    }

    private DITPSocketMessage buildMessage(SocketMessageBody body,int sequence) {
        int cmd = Bodys.getCmd(body.getClass());
        DITPSocketMessage message = DITPSocketMessage.prepareEncoding(cmd, sequence);
        message.setMessageBody(body);
        return message;
    }

    public static void main(String[] args) throws Exception {
        final DITPInterface ditpInterface = new DITPInterface();
        ditpInterface.start();
        ditpInterface.waitForNewConnect();
        assert ditpInterface.isReady;
        Thread.sleep(2000l);
        ditpInterface.notifyAddDevice(ByteUtil.objectToBytes("ronnie1",64));
        Thread.sleep(2000l);
        ditpInterface.notifyAddDevice(ByteUtil.objectToBytes("ronnie2",64));

        Thread.sleep(2000l);
        ditpInterface.notifyRemoveDevice(ByteUtil.objectToBytes("ronnie2",64));

        List<byte[]> bytes = ditpInterface.listDevice();
        System.out.println(bytes.get(0));

       // ditpInterface.notifyRemoveDevice(ByteUtil.objectToBytes("ronnie2",64));
        Thread.sleep(10000000l);
    }


}
