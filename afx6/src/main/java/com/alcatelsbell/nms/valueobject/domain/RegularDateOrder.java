package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "R_RegularDateOrder")
public class RegularDateOrder extends BObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;//例行告警工单名称
	private String creatorDn;//例行告警工单创建人
	private String creatorName;
	private String reviewerDn;//例行告警工单审核人
	private String reviewerName;
	private Date reviewDate;
	private String updatePersonDn;//例行告警工单更新人
	private String updatePersonName;
	private String orderStatus;//例行告警工单状态
	private String orderRemarks;//例行告警工单备注
	@Transient
	private String checkStatus;//审批
	private String orderContent;//工单内容
	
	public String getOrderContent() {
		return orderContent;
	}
	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}
	public String getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	public String getUpdatePersonDn() {
		return updatePersonDn;
	}
	public void setUpdatePersonDn(String updatePersonDn) {
		this.updatePersonDn = updatePersonDn;
	}
	public String getUpdatePersonName() {
		return updatePersonName;
	}
	public void setUpdatePersonName(String updatePersonName) {
		this.updatePersonName = updatePersonName;
	}
	public String getOrderRemarks() {
		return orderRemarks;
	}
	public void setOrderRemarks(String orderRemarks) {
		this.orderRemarks = orderRemarks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatorDn() {
		return creatorDn;
	}
	public void setCreatorDn(String creatorDn) {
		this.creatorDn = creatorDn;
	}
	public String getReviewerDn() {
		return reviewerDn;
	}
	public void setReviewerDn(String reviewerDn) {
		this.reviewerDn = reviewerDn;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
}
