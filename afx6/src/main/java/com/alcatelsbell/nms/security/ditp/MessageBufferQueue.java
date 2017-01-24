package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.ByteUtil;
import com.alcatelsbell.nms.util.LogUtil;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午4:21
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class MessageBufferQueue extends LinkedBlockingQueue {
    static enum READ_STATE {
        READ_HEAD,
        READ_BODY;
    }
    private SocketMessage currentSocketMessage;
    private SocketMessageFactory socketMessageFactory = new DITPSocketMessageFacotory();

    public void read(ByteBuffer byteBuffer) {
        if (currentSocketMessage == null)
            currentSocketMessage = socketMessageFactory.createMessageForDecoding();
        try {
            if (byteBuffer.hasRemaining() && currentSocketMessage.read(byteBuffer)) {
                offer(currentSocketMessage);
                System.out.println(new Date()+"<<<<<<<<<< "+currentSocketMessage);
                System.out.println();
                logMessage(currentSocketMessage);
                currentSocketMessage = socketMessageFactory.createMessageForDecoding();
            }
        } catch (MessageException e) {
            LogUtil.error(getClass(), e, e);
        }
    }

    private void logMessage(SocketMessage currentSocketMessage) {
        ByteUtil.logBytes(currentSocketMessage.getSocketBytes(),"ditpmessage."+System.currentTimeMillis());
    }

}
