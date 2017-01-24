package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

import java.util.concurrent.atomic.AtomicLong;


/**
 * User: Ronnie
 * Date: 12-7-20
 * Time: 上午9:47
 */
public class CurralarmServerStatistics {
    private  static CurralarmServerStatistics inst = new CurralarmServerStatistics();

    public static CurralarmServerStatistics getInstance() {
        return inst;
    }

    private AtomicLong total = new AtomicLong(0);
    /**
     *     public static final int ALARM_SEVERITY_CRITICAL = 0; // 严重  一级告警
             public static final int ALARM_SEVERITY_MAJOR = 1; // 主要     二级告警
             public static final int ALARM_SEVERITY_MINOR = 2; // 次要
             public static final int ALARM_SEVERITY_WARNING = 3; // 一般
             public static final int ALARM_SEVERITY_INTERMEDIATE = 4; // 一般
             public static final int ALARM_SEVERITY_CLEAR = 5; // 一般
             public static final int ALARM_SEVERITY_INFO = 6; // 信息
             public static final int ALARM_SEVERITY_UNKNOWN_SEVERITY = 7; // 信息
     */
    private AtomicLong[] severityNumbers = new AtomicLong[] {new AtomicLong(0),new AtomicLong(0),new AtomicLong(0),
            new AtomicLong(0),new AtomicLong(0),new AtomicLong(0),new AtomicLong(0),new AtomicLong(0)};

    public void addCurralarm(Curralarm alarm) throws Exception{
        severityNumbers[alarm.getSeverity()].incrementAndGet();
        total.incrementAndGet();
    }
    public void updateCurralarm(Curralarm oldAlarm,Curralarm alarm)  throws Exception{
        if (oldAlarm != null && alarm != null) {
            if (oldAlarm.getSeverity() != alarm.getSeverity()) {
                severityNumbers[oldAlarm.getSeverity()].decrementAndGet();
                severityNumbers[alarm.getSeverity()].incrementAndGet();
            }
        }
    }
    public void removeCurralarm(Curralarm alarm)  throws Exception{
        severityNumbers[alarm.getSeverity()].decrementAndGet();

    }

    public AtomicLong getTotal() {
        return total;
    }

    public void setTotal(AtomicLong total) {
        this.total = total;
    }

    public AtomicLong[] getSeverityNumbers() {
        return severityNumbers;
    }

    public void setSeverityNumbers(AtomicLong[] severityNumbers) {
        this.severityNumbers = severityNumbers;
    }
}
