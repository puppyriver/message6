package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.util.ByteUtil;
import com.alcatelsbell.nms.util.LogUtil;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午4:49
 * rongrong.chen@alcatel-sbell.com.cn
 */
public abstract class SocketMessage implements Serializable {

    private MessageInterpreter interpreter = null;
    /**
     *  the index of the head element.
     */
    private int currentIndex = 0;

    static enum READ_STATE {
        READ_HEAD,
        READ_BODY;
    }

    public static enum MODE {
        ENCODE,
        DECODE;
    }
    private READ_STATE state = READ_STATE.READ_HEAD;
    private MODE mode = MODE.ENCODE;

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public SocketMessage(SocketMessageFactory factory) {
        this.socketMessageFactory = factory;
    }

    protected abstract int getBodyLength();
    /**
     * buffered bytes for head element.
     */
    private byte[] buffer = new byte[0];

    private Object messageBody = null;
    private SocketMessageFactory socketMessageFactory = null;

    public Object getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Object messageBody) {
        this.messageBody = messageBody;
    }

    public SocketMessageFactory getMessageBodyFactory() {
        return socketMessageFactory;
    }

    public void setSocketMessageFactory(SocketMessageFactory socketMessageFactory) {
        this.socketMessageFactory = socketMessageFactory;
    }

    private byte[] socketBytes;
    public byte[] getSocketBytes() {
        return socketBytes;
    }

    void appendSocketBytes(byte[] bs) {
        if (socketBytes == null) socketBytes = new byte[0];
        socketBytes = ByteUtil.joint(socketBytes,bs);
    }


    public void resetForRead() {
       state = READ_STATE.READ_HEAD;
       currentIndex = 0;
        socketBytes = null;
   }

    /**
     *
     * @param byteBuffer
     * @return whether the message head read finished.
     */
    public boolean read(ByteBuffer byteBuffer) throws MessageException {
        appendSocketBytes(byteBuffer.array());
        Object readObject  = this;
        if (state == READ_STATE.READ_BODY)
            readObject = getMessageBody();

        if (interpreter == null) {
            interpreter = MessageInterpreter.wrap(readObject.getClass());
        }

        if (currentIndex == 0) {
            currentIndex = 1;
        }

        Field objectField = interpreter.getMessageFields().get(currentIndex-1); // index start from 1 but list start from 0


        MessageField messageField = objectField.getAnnotation(MessageField.class);
        byte[]  definedValue = messageField.value();
        int fieldByteSize = messageField.byteSize();
        if (!messageField.sizeFunction().isEmpty()) {
            try {
                fieldByteSize = (Integer)readObject.getClass().getMethod(messageField.sizeFunction()).invoke(readObject);
            } catch (Exception e) {
                LogUtil.error(getClass(), e, e);
                throw new MessageException(e);
            }
        }

        if (byteBuffer.remaining() >= fieldByteSize - buffer.length) {
            byte[] rest = new byte[fieldByteSize -buffer.length];
            byteBuffer.get(rest);
            byte[] fieldBytes = ByteUtil.joint(buffer, rest);
            buffer = new byte[0];

            if (definedValue.length > 0) {
                if (!ByteUtil.byteArrayEquals(definedValue,fieldBytes)) {
                    resetForRead();
                    return false;
                }

            }
            Object fieldValue = null;
            try {
                fieldValue = ByteUtil.bytesToObject(objectField, fieldBytes);


                if (readObject instanceof SocketMessageBody)
                    ((SocketMessageBody) readObject).appendBytes(fieldBytes);
            } catch (Exception e) {
                LogUtil.error(getClass(), e, e);
                throw new MessageException(e);
            }


            try {
                CommonUtil.getInstance().setFiledValue(readObject,objectField,fieldValue);
            } catch (Exception e) {
                throw new MessageException("Failed to set value : value = "+fieldValue+" field = "+objectField);
            }

            if (currentIndex == interpreter.getMessageFields().size()) {
                if (state == READ_STATE.READ_HEAD) {
                    state = READ_STATE.READ_BODY;
                    this.getMessageBodyFactory().createMessageBodyForDecoding(this);
                    interpreter = MessageInterpreter.wrap(getMessageBody().getClass());
                    currentIndex = 1;
                    return read(byteBuffer);
                }  else if (state == READ_STATE.READ_BODY) {
                    return true;
                }
            } else {
                currentIndex ++;
                return read(byteBuffer);
            }

        } else {
            byte[] rest = new byte[byteBuffer.remaining()];
            byteBuffer.get(rest);
            buffer = ByteUtil.joint(buffer,rest);
            return false;
        }

        return false;

    }
    private byte[] encode(Object o) throws Exception {
        MessageInterpreter headInterpreter = MessageInterpreter.wrap(o.getClass());
        List<Field> messageFields = headInterpreter.getMessageFields();

        byte[] bs = new byte[0];
        for (int i = 0; i < messageFields.size(); i++) {
            Field field = messageFields.get(i);
            MessageField annotation = field.getAnnotation(MessageField.class);
            Object value = CommonUtil.getInstance().getFiledValue(o, field);
            byte[] fieldBytes = annotation.value();
            if (value != null)
                fieldBytes = ByteUtil.objectToBytes(value,annotation.byteSize());

            if (fieldBytes.length == 0)
                fieldBytes = new byte[annotation.byteSize()];
            bs = ByteUtil.joint(bs,fieldBytes);
        }
        return bs;
    }
    public byte[] encode() throws Exception {
        return ByteUtil.joint(encode(this), encode(getMessageBody()));
    }

    public byte[] encodeHead() throws Exception {
        return encode(this);
    }

    public byte[] encodeBody() throws Exception {
        return encode(getMessageBody());
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
                    if (filedValue.getClass().equals(new byte[0].getClass())) {
                        filedValue = ByteUtil.byteArrayToString((byte[])filedValue);
                    }
                    sb.append(declaredField.getName()+"="+ filedValue).append(" ");
                } catch (Exception e) {
                    LogUtil.error(getClass(), e, e);
                }
            }
        }
        return sb.toString()+"\n"+"           [messagebody] : "+getMessageBody();
    }

}
