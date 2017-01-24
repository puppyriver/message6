package com.alcatelsbell.nms.alarm.common;

/**
 * User: Ronnie
 * Date: 12-6-13
 * Time: 下午6:25
 */
public interface CurralarmInterceptor {
    public String getHandleCode();
    public boolean beforeAlarmCreate(AlarmWrapper alarm);
    public boolean beforeAlarmUpdate(AlarmWrapper alarm);
    public boolean beforeAlarmDelete(AlarmWrapper alarm);
    public void afterAlarmCreate(AlarmWrapper alarm);
    public void afterAlarmUpdate(AlarmWrapper alarm);
    public void afterAlarmDelete(AlarmWrapper alarm);
}
