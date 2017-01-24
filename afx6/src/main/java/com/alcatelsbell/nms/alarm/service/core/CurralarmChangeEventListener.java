package com.alcatelsbell.nms.alarm.service.core;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 上午11:29
 */
public interface CurralarmChangeEventListener {
    /**
     * This method is blocking ,please realize it in short time!
     * @param event
     */
    public void fireOnCurralarmChange(CurralarmChangeEvent event);
}
