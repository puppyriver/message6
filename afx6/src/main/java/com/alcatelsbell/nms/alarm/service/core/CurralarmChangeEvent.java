package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 上午11:19
 */
public class CurralarmChangeEvent extends HashMap implements Serializable {
    public static final int ALARM_EVENT_TYPE_ADD = 1;
    public static final int ALARM_EVENT_TYPE_UPDATE = 3;
    public static final int ALARM_EVENT_TYPE_DELETE = 2;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    private Date timeStamp = new Date();

    private Curralarm curralarm;


    private Curralarm oldalarm;
    private int eventType;


    public void setProperty(String key,Object value) {
        put(key,value);
    }

    public void setProperties(HashMap map) {
        putAll(map);
    }

    public Object getProperty(String key) {
        return get(key);
    }

    public CurralarmChangeEvent(Curralarm oldalarm,Curralarm curralarm, int eventType) {
        this.oldalarm = oldalarm;
        this.curralarm = curralarm;
        this.eventType = eventType;
        timeStamp = new Date();
    }


    public Curralarm getCurralarm() {
        return curralarm;
    }

    public void setCurralarm(Curralarm curralarm) {
        this.curralarm = curralarm;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }


    public Curralarm getOldalarm() {
        return oldalarm;
    }

    public void setOldalarm(Curralarm oldalarm) {
        this.oldalarm = oldalarm;
    }

    @Override
    public String toString() {
        return "CurralarmChangeEvent{" +
                "curralarm=" + toString(curralarm) +
                ", oldalarm=" + toString(oldalarm) +
                ", eventType=" + eventType +
                '}';
    }

    private String toString(Curralarm alarm) {
        if (alarm == null) return "NULL";
        StringBuffer sb = new StringBuffer();
        sb.append("id=").append(alarm.getId()).append("dn=").append(alarm.getDn()).append(",status=").append(alarm.getStatus()).append(",severity=").append(alarm.getSeverity()).append(",add=").append(alarm.getAdditionalinfo())
                .append("customer=").append(alarm.getCustomer());
        return sb.toString();
    }
}
