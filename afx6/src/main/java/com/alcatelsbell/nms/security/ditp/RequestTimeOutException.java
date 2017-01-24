package com.alcatelsbell.nms.security.ditp;

/**
 * Author: Ronnie.Chen
 * Date: 13-3-1
 * Time: 下午3:35
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class RequestTimeOutException extends MessageException {
    public RequestTimeOutException(String message) {
        super(message);
    }

    public RequestTimeOutException(Throwable message) {
        super(message);
    }
}
