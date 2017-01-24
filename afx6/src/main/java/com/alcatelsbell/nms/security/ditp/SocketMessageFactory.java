package com.alcatelsbell.nms.security.ditp;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午4:02
 * rongrong.chen@alcatel-sbell.com.cn
 */
public abstract class SocketMessageFactory {
    abstract public Object createMessageBodyForDecoding(SocketMessage message) ;
    abstract public SocketMessage createMessageForDecoding();
}

