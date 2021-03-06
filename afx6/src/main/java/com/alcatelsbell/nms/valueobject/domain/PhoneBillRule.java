package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;


@Table(name="PHONEBILLRULE")
@Entity
public class PhoneBillRule extends BObject implements Serializable {
	    public Integer getPmGatherPointId() {
		return pmGatherPointId;
	}
	public void setPmGatherPointId(Integer pmGatherPointId) {
		this.pmGatherPointId = pmGatherPointId;
	}
	public Integer getPmParameterId() {
		return pmParameterId;
	}
	public void setPmParameterId(Integer pmParameterId) {
		this.pmParameterId = pmParameterId;
	}
	public String getPmParameterInfo() {
		return pmParameterInfo;
	}
	public void setPmParameterInfo(String pmParameterInfo) {
		this.pmParameterInfo = pmParameterInfo;
	}
	public Integer getGatherPointEntityRelation() {
		return gatherPointEntityRelation;
	}
	public void setGatherPointEntityRelation(Integer gatherPointEntityRelation) {
		this.gatherPointEntityRelation = gatherPointEntityRelation;
	}
	public String getPmGatherPointCond() {
		return pmGatherPointCond;
	}
	public void setPmGatherPointCond(String pmGatherPointCond) {
		this.pmGatherPointCond = pmGatherPointCond;
	}
	public String getMeCond() {
		return meCond;
	}
	public void setMeCond(String meCond) {
		this.meCond = meCond;
	}
	public String getPtpCond() {
		return ptpCond;
	}
	public void setPtpCond(String ptpCond) {
		this.ptpCond = ptpCond;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		private static final long serialVersionUID = 1L;
	    private Integer pmGatherPointId; //另外加二个属性
        private Integer pmParameterId;   //采集点的ID
		private String pmParameterInfo;//采集点的信息
	    private Integer gatherPointEntityRelation;
	    private String pmGatherPointCond;
	    private String meCond;
	    private String ptpCond;

}
