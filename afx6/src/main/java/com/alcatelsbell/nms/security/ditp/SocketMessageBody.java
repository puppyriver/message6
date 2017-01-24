package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.util.ByteUtil;
import com.alcatelsbell.nms.util.LogUtil;

import java.lang.reflect.Field;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-28
 * Time: 下午3:09
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SocketMessageBody {

    private SocketMessage socketMessage;
    private byte[] socketBytes;
    public byte[] getSocketBytes() {
        return socketBytes;
    }

    public void setSocketBytes(byte[] socketBytes) {
        this.socketBytes = socketBytes;
    }

    void appendBytes(byte[] bs) {
        if (socketBytes == null) socketBytes = new byte[0];
        socketBytes = ByteUtil.joint(socketBytes,bs);
    }

    public SocketMessage getSocketMessage() {
        return socketMessage;
    }

    public void setSocketMessage(SocketMessage socketMessage) {
        this.socketMessage = socketMessage;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Field[] declaredFields = getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            MessageField annotation = declaredField.getAnnotation(MessageField.class);
            if (annotation != null) {
                try {
                    Object filedValue = CommonUtil.getInstance().getFiledValue(this, declaredField);
                    if (filedValue != null && filedValue.getClass().equals(new byte[0].getClass())) {
                        filedValue = ByteUtil.byteArrayToString((byte[])filedValue);
                    }
                    sb.append(declaredField.getName()+"="+ filedValue).append(" ");

                } catch (Exception e) {
                    LogUtil.error(getClass(), e, e);
                }
            }
        }

        return sb.toString();
    }


}
