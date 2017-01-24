package com.alcatelsbell.nms.alarm.common;

/**
 * User: Ronnie.Chen
 * Date: 11-5-24
 * Time:
 */
public class AlarmProcessException extends  Exception{
    public AlarmProcessException(String dn,Exception e){
        super("Failed to process alarm :"+dn+e,e);
    }
}
