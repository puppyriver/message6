package com.alcatelsbell.nms.alarm.common;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie
 * Date: 12-6-13
 * Time: 下午8:47
 */
public interface CurralarmInterceptorFactory {
    public CurralarmInterceptor createCurralarmInterceptor(Curralarm alarm) ;
}
