package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午11:55
 */
public interface CurralarmWriteWork {
    public static final int WORK_TYPE_NONE = 0;
    public static final int WORK_TYPE_DELETE = 1;
    public static final int WORK_TYPE_UPDATE = 2;
    public static final int WORK_TYPE_INVALID = 3;
    /**
     * 对告警的所有赋值计算,本方法中获取到的告警对象为写安全
     * @param curralarm
     * @throws Exception
     * @return int 返回WORK_TYPE
     */
    public int prepareBeforeOperation(Curralarm curralarm) throws Exception;

    /**
     * 对处理完的告警后发出的告警变更事件进行二次处理，一般情况下可以不作处理
     * @param event
     */
    public void prepareCurralarmChangeEvent(CurralarmChangeEvent event);
}
