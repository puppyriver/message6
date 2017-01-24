package com.alcatelsbell.nms.alarm.service.core;

/**
 * User: Ronnie
 * Date: 11-12-7
 * Time: 下午2:16
 */
public interface CurralarmDelayNotifierPolicy {
    /**
     *
     * @param event
     * @return
     */
    public boolean arbiteCurralarmCreate(CurralarmChangeEvent event);

    /**
     *
     * @param event the event take from the delayed queue, also the object which processed in the  arbiteCurralarmCreate
     * @return
     */
    public boolean arbiteCurralarmRemained(CurralarmChangeEvent event);
}
