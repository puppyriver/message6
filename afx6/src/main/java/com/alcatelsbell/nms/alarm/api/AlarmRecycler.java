package com.alcatelsbell.nms.alarm.api;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie.Chen
 * Date: 11-7-27
 */
public interface AlarmRecycler {
    public Object recycleAlarm(Curralarm alarm);
}
