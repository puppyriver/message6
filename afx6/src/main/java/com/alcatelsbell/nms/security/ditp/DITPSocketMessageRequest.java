package com.alcatelsbell.nms.security.ditp;

/**
 * Author: Ronnie.Chen
 * Date: 13-3-1
 * Time: 下午2:37
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class DITPSocketMessageRequest {
    private DITPSocketMessage requestMessage;
    private DITPSocketMessageHandler handler;

    public DITPSocketMessageRequest(DITPSocketMessage requestMessage, DITPSocketMessageHandler handler) {
        this.requestMessage = requestMessage;
        this.handler = handler;
    }

    public DITPSocketMessage getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(DITPSocketMessage requestMessage) {
        this.requestMessage = requestMessage;
    }

    public DITPSocketMessageHandler getHandler() {
        return handler;
    }

    public void setHandler(DITPSocketMessageHandler handler) {
        this.handler = handler;
    }
}
