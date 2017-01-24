package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "R_PmGatherPoint")
public class PmGatherPoint extends BObject implements Serializable{
	
	 private String entity;
     private long pmTypeId;
     private long pmParameterId;
     private String dataStorageInfo;

     private int rate;
     private int location;
     private int granularity;
     private Date pmDataLastRetrieveTime;
     private int status;

     private String ipAddr;
     private long neId;
     
     private String mn;
     private String trunkNo;
     private String subRouteNo;
     
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public long getPmTypeId() {
		return pmTypeId;
	}
	public void setPmTypeId(long pmTypeId) {
		this.pmTypeId = pmTypeId;
	}
	public long getPmParameterId() {
		return pmParameterId;
	}
	public void setPmParameterId(long pmParameterId) {
		this.pmParameterId = pmParameterId;
	}
	public String getDataStorageInfo() {
		return dataStorageInfo;
	}
	public void setDataStorageInfo(String dataStorageInfo) {
		this.dataStorageInfo = dataStorageInfo;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public int getGranularity() {
		return granularity;
	}
	public void setGranularity(int granularity) {
		this.granularity = granularity;
	}
	public Date getPmDataLastRetrieveTime() {
		return pmDataLastRetrieveTime;
	}
	public void setPmDataLastRetrieveTime(Date pmDataLastRetrieveTime) {
		this.pmDataLastRetrieveTime = pmDataLastRetrieveTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public long getNeId() {
		return neId;
	}
	public void setNeId(long neId) {
		this.neId = neId;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public String getTrunkNo() {
		return trunkNo;
	}
	public void setTrunkNo(String trunkNo) {
		this.trunkNo = trunkNo;
	}
	public String getSubRouteNo() {
		return subRouteNo;
	}
	public void setSubRouteNo(String subRouteNo) {
		this.subRouteNo = subRouteNo;
	}
     
     

}
