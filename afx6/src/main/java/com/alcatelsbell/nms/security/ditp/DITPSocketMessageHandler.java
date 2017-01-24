package com.alcatelsbell.nms.security.ditp;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-28
 * Time: 下午4:37
 * rongrong.chen@alcatel-sbell.com.cn
 */
public abstract class DITPSocketMessageHandler {
    public abstract DITPSocketMessage handleSocketMessage(DITPSocketMessage inMessagee) ;
    public void afterReponse() {

    }
}
