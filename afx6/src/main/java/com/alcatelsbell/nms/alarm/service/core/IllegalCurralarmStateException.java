package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午12:52
 */
public class IllegalCurralarmStateException extends Exception{
   public IllegalCurralarmStateException(Curralarm alarm,String msg) {
         super(alarm.getDn()+" has illegal state: "+msg);
   }
   public IllegalCurralarmStateException(String alarmDn,String msg) {
         super(alarmDn+" has illegal state: "+msg);
   }
}
