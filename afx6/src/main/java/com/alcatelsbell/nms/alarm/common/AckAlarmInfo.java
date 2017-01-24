package com.alcatelsbell.nms.alarm.common;

import java.io.*;
import java.util.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class AckAlarmInfo implements Serializable {
    private String alarmReason = null;
    private int faultType = -1;
    private Date reportTime = null;
    private Date repairTime = null;
    private Date occurTime = null;
    private String affirmAlarmReason = null;
    
    public AckAlarmInfo() {
    }

    public static void main(String[] args) {
        AckAlarmInfo ackalarminfo = new AckAlarmInfo();
    }

    public void setAlarmReason(String alarmCause) {
        this.alarmReason = alarmCause;
    }

    public String getAlarmReason() {
        return this.alarmReason;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public void setRepairTime(Date repairTime) {
        this.repairTime = repairTime;
    }

    public void setFaultType(int faultType) {
        this.faultType = faultType;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public Date getRepairTime() {
        return repairTime;
    }

    public int getFaultType() {
        return faultType;
    }

    public Date getOccurTime() {
        return occurTime;
    }

	public String getAffirmAlarmReason() {
		return affirmAlarmReason;
	}

	public void setAffirmAlarmReason(String affirmAlarmReason) {
		this.affirmAlarmReason = affirmAlarmReason;
	}
    
}
