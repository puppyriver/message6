package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午12:29
 */
public interface CurralarmReadWork {
    /**
     * Read a alarm with read-lock ,which means the alarm will not be change by any other thread during the method
     * @param curralarm
     * @return
     * @throws Exception
     */
    public void readAlarmSafty(Curralarm curralarm) throws Exception;
}
