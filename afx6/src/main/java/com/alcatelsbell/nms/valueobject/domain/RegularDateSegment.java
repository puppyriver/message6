package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "R_RegularDateSegment")
public class RegularDateSegment extends BObject implements Serializable {

	private static final long serialVersionUID = -5951617865603461729L;

	@Temporal (TemporalType.TIMESTAMP)
	private Date startDate; //开始日期
	@Temporal (TemporalType.TIMESTAMP)
	private Date endDate;//结束如期
	private String weekDay; // 星期
	private Integer startHour;// 开始时间（小时）
	private Integer startMin;//开始时间（分钟）
	private Integer endHour;//结束时间（小时）
	private Integer endMin;//结束时间（分钟）
	private Long productId;//所属业务
	private String productDn; //所属业务DN
	private String alarmStatus;//状态为0:无效；1:有效
	private String orderDn;
	private Double matchLevel;//匹配度
	private String rationalLevel;//是否合理
	private String confirmUsable;//初始化为0，当这个例行告警时间段合理且对应的工单状态为已确认时设置为1.
	
	public String getConfirmUsable() {
		return confirmUsable;
	}
	public void setConfirmUsable(String confirmUsable) {
		this.confirmUsable = confirmUsable;
	}
	public String getRationalLevel() {
		return rationalLevel;
	}
	public void setRationalLevel(String rationalLevel) {
		this.rationalLevel = rationalLevel;
	}
	public Double getMatchLevel() {
		return matchLevel;
	}
	public void setMatchLevel(Double matchLevel) {
		this.matchLevel = matchLevel;
	}
	public String getOrderDn() {
		return orderDn;
	}
	public void setOrderDn(String orderDn) {
		this.orderDn = orderDn;
	}
	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	public Integer getStartHour() {
		return startHour;
	}
	public void setStartHour(Integer startHour) {
		this.startHour = startHour;
	}
	public Integer getStartMin() {
		return startMin;
	}
	public void setStartMin(Integer startMin) {
		this.startMin = startMin;
	}
	public Integer getEndHour() {
		return endHour;
	}
	public void setEndHour(Integer endHour) {
		this.endHour = endHour;
	}
	public Integer getEndMin() {
		return endMin;
	}
	public void setEndMin(Integer endMin) {
		this.endMin = endMin;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductDn() {
		return productDn;
	}
	public void setProductDn(String productDn) {
		this.productDn = productDn;
	}
}
