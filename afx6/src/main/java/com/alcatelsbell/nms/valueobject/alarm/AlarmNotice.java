package com.alcatelsbell.nms.valueobject.alarm;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "F_AlarmNotice")
@Entity
public class AlarmNotice extends BObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recipients;
	
	private Integer status;
	
	private Integer monitorType;
	
	private String deviceName;
	
	private String paramName;
	
	private String severitys;
	
	private Integer alarmType;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	private Long tally;
	
	private Integer firstMotivate;
	
	private String comments;

	public String getSeveritys() {
		return severitys;
	}

	public void setSeveritys(String severitys) {
		this.severitys = severitys;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public Integer getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(Integer alarmType) {
		this.alarmType = alarmType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Long getTally() {
		return tally;
	}

	public void setTally(Long tally) {
		this.tally = tally;
	}

	public Integer getFirstMotivate() {
		return firstMotivate;
	}

	public void setFirstMotivate(Integer firstMotivate) {
		this.firstMotivate = firstMotivate;
	}
	
}
