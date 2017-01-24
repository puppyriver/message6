package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.valueobject.BObject;

/**
 * @author:LiXiaoBing
 * @date:2011-11-9
 * @time:下午02:01:26
 */

@Entity
@Table(name = "R_PmData")
public class PmData extends BObject implements Serializable{
	
    private double value;
    private String category;
    private String stringValue;
    private Date timePoint;
    private long pmGatherPointId;
    private String unit;
    private Integer status;
    @Transient
    private Object valueObject;
    public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	public Date getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(Date timePoint) {
		this.timePoint = timePoint;
	}
	public long getPmGatherPointId() {
		return pmGatherPointId;
	}
	public void setPmGatherPointId(long pmGatherPointId) {
		this.pmGatherPointId = pmGatherPointId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getValueObject() {
		return valueObject;
	}
	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}
}
