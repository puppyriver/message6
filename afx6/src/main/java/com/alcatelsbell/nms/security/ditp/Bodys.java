package com.alcatelsbell.nms.security.ditp;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Author: Ronnie.Chen
 * Date: 13-3-1
 * Time: 下午12:41
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class Bodys {
    static HashMap<Integer,Class> commands = new HashMap();
    static {
        commands.put(0x00000001,DITP_CONNECT.class);
        commands.put(0x80000001,DITP_CONNECT_RESP.class);
        commands.put(0x00000002,DITP_DISCONNECT.class);
        commands.put(0x80000002,DITP_DISCONNECT_RESP.class);
        commands.put(0x00000003,DITP_ACTIVE_TEST.class);
        commands.put(0x80000003,DITP_ACTIVE_TEST_RESP.class);
        commands.put(0x00000101,DITP_ADD_DEVICE.class);
        commands.put(0x80000101,DITP_ADD_DEVICE_RESP.class);
        commands.put(0x00000102,DITP_REMOVE_DEVICE.class);
        commands.put(0x80000102,DITP_REMOVE_DEVICE_RESP.class);

        commands.put(0x00000103,DITP_LIST_DEVICE.class);
        commands.put(0x80000103,DITP_LIST_DEVICE_RESP.class);

        commands.put(0x00000201,DITP_DEVREG.class);
        commands.put(0x80000201,DITP_DEVREG_RESP.class);

        commands.put(0x00000202,DITP_DEVAUTH.class);
        commands.put(0x80000202,DITP_DEVAUTH_RESP.class);

        commands.put(0x00000203,DITP_DESTROY_DATA.class);
        commands.put(0x80000203,DITP_DESTROY_DATA_RESP.class);

        commands.put(0x00000301,DITP_RSA_KEYGEN.class);
        commands.put(0x80000301,DITP_RSA_KEYGEN_RESP.class);

        commands.put(0x00000302,DITP_RANDGEN.class);
        commands.put(0x80000302,DITP_RANDGEN_RESP.class);
    }

    public static int getCmd(Class cls) {
        Iterator<Integer> iterator = commands.keySet().iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            Class cc = commands.get(next);
            if (cc.equals(cls))
                return next;
        }
        return -1;
    }

    public final static class DITP_CONNECT extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int version;

        @MessageField(index = 2,byteSize = 8)
        public int timestamp;

        @MessageField(index = 3,byteSize = 16)
        public byte[] authenticator;
    }

    public final static class DITP_CONNECT_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;

        @MessageField(index = 2,byteSize = 16)
        public byte[] authenticatorDMS;
    }

    public final static class DITP_DISCONNECT  extends SocketMessageBody{
    }

    public final static class DITP_DISCONNECT_RESP  extends SocketMessageBody{
    }

    public final static class DITP_ACTIVE_TEST  extends SocketMessageBody{
    }

    public final static class DITP_ACTIVE_TEST_RESP  extends SocketMessageBody{
    }

    public final static class DITP_ADD_DEVICE  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_ADD_DEVICE_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }

    public final static class DITP_REMOVE_DEVICE  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_REMOVE_DEVICE_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }

    public final static class DITP_LIST_DEVICE  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 128)
        public byte[] reserved;
    }

    public final static class DITP_LIST_DEVICE_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
        @MessageField(index = 2,byteSize = 4)
        public int count;
        @MessageField(index = 3,byteSize = -1,sizeFunction = "size_DeviceList")
        public byte[] deviceList;

        public int size_DeviceList() {
            return getSocketMessage().getBodyLength() - 4 - 4;
        }
    }

    public final static class DITP_DEVREG  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_DEVREG_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }


    public final static class DITP_DEVLOGIN  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_DEVLOGIN_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }

    public final static class DITP_DEVAUTH  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_DEVAUTH_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }

    public final static class DITP_DESTROY_DATA  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 64)
        public byte[] deviceId;
    }

    public final static class DITP_DESTROY_DATA_RESP extends SocketMessageBody {
        @MessageField(index = 1,byteSize = 4)
        public int result;
    }

    public final static class DITP_RSA_KEYGEN extends SocketMessageBody {
        @MessageField(index = 1,byteSize = 4)
        public int length;
    }

    public final static class DITP_RSA_KEYGEN_RESP  extends SocketMessageBody{
        @MessageField(index = 1,byteSize = 4)
        public int result;
        @MessageField(index = 2,byteSize = 4)
        public int pubKeyLen;
        @MessageField(index = 3,byteSize = 4)
        public int priKeyLen;
        @MessageField(index = 4,byteSize = -1,sizeFunction = "size_publicKey")
        public int publicKey;
        @MessageField(index = 5,byteSize = -1,sizeFunction = "size_privateKey")
        public int privateKey;

        public int size_publicKey() {
            return pubKeyLen;
        }

        public int size_privateKey() {
            return priKeyLen;
        }
    }

    public final static class DITP_RANDGEN extends SocketMessageBody {
        @MessageField(index = 1,byteSize = 4)
        public int length;
    }

    public final static class DITP_RANDGEN_RESP extends SocketMessageBody {
        @MessageField(index = 1,byteSize = 4)
        public int result;
        @MessageField(index = 2,byteSize = 4)
        public int dataLen;
        @MessageField(index = 3,byteSize = -1,sizeFunction = "size_data")
        public int data;

        public int size_data() {
            return getSocketMessage().getBodyLength() - 4 - 4;
        }

    }

}
