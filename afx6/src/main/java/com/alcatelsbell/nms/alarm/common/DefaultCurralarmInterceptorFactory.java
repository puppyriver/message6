package com.alcatelsbell.nms.alarm.common;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie
 * Date: 12-6-13
 * Time: 下午8:53
 */
public class DefaultCurralarmInterceptorFactory implements CurralarmInterceptorFactory {
    @Override
    public CurralarmInterceptor createCurralarmInterceptor(Curralarm alarm) {
        return new CurralarmInterceptor() {
            @Override
            public String getHandleCode() {
                return null;
            }

            @Override
            public boolean beforeAlarmCreate(AlarmWrapper alarm) {
                return true;
            }

            @Override
            public boolean beforeAlarmUpdate(AlarmWrapper alarm) {
                return true;
            }

            @Override
            public boolean beforeAlarmDelete(AlarmWrapper alarm) {
                return true;
            }

            @Override
            public void afterAlarmCreate(AlarmWrapper alarm) {

            }

            @Override
            public void afterAlarmUpdate(AlarmWrapper alarm) {

            }

            @Override
            public void afterAlarmDelete(AlarmWrapper alarm) {

            }
        };
    }
}
