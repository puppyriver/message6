package com.alcatelsbell.nms.util.protocol.tl1;

/**
 * User: Ronnie
 * Date: 12-6-7
 * Time: 下午3:14
 */
public interface TL1EventListener {
    public void handleTL1Event(TL1Event event)  throws Exception;
}
