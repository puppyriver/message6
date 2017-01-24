package com.alcatelsbell.nms.security.ditp;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-28
 * Time: 上午11:42
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class MessageException extends Exception {
    public MessageException(String message) {
        super(message);
    }
    public MessageException(Throwable message) {
        super(message);
    }
}
