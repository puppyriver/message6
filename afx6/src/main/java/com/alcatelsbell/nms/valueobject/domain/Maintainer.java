package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

@Entity
@Table(name = "MAINTAINER")
public class Maintainer extends BObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5459644253171176250L;

	private String subRoleId;
	
	private String subRoleName;
	
	private String auserName;
	
	private String auserId;
	
	@Column(name = "buserNmae")
	private String buserName;
	
	private String buserId;
	
	private String zhuanYe;
	
	private String diShi;
	
	public String getBuserName() {
		return buserName;
	}
	public void setBuserName(String buserName) {
		this.buserName = buserName;
	}
	public String getSubRoleId() {
		return subRoleId;
	}

	public void setSubRoleId(String subRoleId) {
		this.subRoleId = subRoleId;
	}

	public String getSubRoleName() {
		return subRoleName;
	}

	public void setSubRoleName(String subRoleName) {
		this.subRoleName = subRoleName;
	}

	public String getAuserName() {
		return auserName;
	}

	public void setAuserName(String auserName) {
		this.auserName = auserName;
	}

	public String getAuserId() {
		return auserId;
	}

	public void setAuserId(String auserId) {
		this.auserId = auserId;
	}

	public String getBuserId() {
		return buserId;
	}

	public void setBuserId(String buserId) {
		this.buserId = buserId;
	}

	public String getZhuanYe() {
		return zhuanYe;
	}

	public void setZhuanYe(String zhuanYe) {
		this.zhuanYe = zhuanYe;
	}

	public String getDiShi() {
		return diShi;
	}

	public void setDiShi(String diShi) {
		this.diShi = diShi;
	}

	public String getQuXian() {
		return quXian;
	}

	public void setQuXian(String quXian) {
		this.quXian = quXian;
	}

	private String quXian;
	
}
