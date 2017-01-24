package com.alcatelsbell.nms.valueobject.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

/**
 * @author:LiXiaoBing
 * @date:2011-8-23
 * @time:上午09:46:37
 */
@Table(name = "REPORTSCHEDULE")
@Entity
public class ReportSchedule extends BObject implements java.io.Serializable{

    private String name;
    private int category;
    private int type;
    private int periodType;
    private int needCustomer;
    private Date startTime;
    private Date endTime;
    private String value1;
    private String value2;
    private String value3;
    private int autoDeploy;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPeriodType() {
		return periodType;
	}
	public void setPeriodType(int periodType) {
		this.periodType = periodType;
	}
	public int getNeedCustomer() {
		return needCustomer;
	}
	public void setNeedCustomer(int needCustomer) {
		this.needCustomer = needCustomer;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public int getAutoDeploy() {
		return autoDeploy;
	}
	public void setAutoDeploy(int autoDeploy) {
		this.autoDeploy = autoDeploy;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	private long userId ;
}
