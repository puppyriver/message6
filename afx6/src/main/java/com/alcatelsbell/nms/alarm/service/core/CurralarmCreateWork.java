package com.alcatelsbell.nms.alarm.service.core;

/**
 * User: Ronnie
 * Date: 12-6-13
 * Time: 下午4:37
 */
public interface CurralarmCreateWork {
    /**
     * 对处理完的告警后发出的告警变更事件进行二次处理，一般情况下可以不作处理
     * @param event
     */
    public void prepareCurralarmChangeEvent(CurralarmChangeEvent event);
}
