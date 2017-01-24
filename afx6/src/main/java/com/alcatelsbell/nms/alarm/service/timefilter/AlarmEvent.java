package com.alcatelsbell.nms.alarm.service.timefilter;




import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;

import java.util.Date;
import java.io.Serializable;

public class AlarmEvent implements Serializable{

	private Date createTime;

	private Alarminformation alarmInformation;

	public AlarmEvent(Date createTime, Alarminformation alarmInformation) {
		this.createTime = createTime;
		this.alarmInformation = alarmInformation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Alarminformation getAlarmInformation() {
		return alarmInformation;
	}

	public void setAlarmInformation(Alarminformation alarmInformation) {
		this.alarmInformation = alarmInformation;
	}
}
