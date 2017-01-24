package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;
@Table(name = "ROLEASSIGN")
@Entity
public class RoleAssign extends BObject implements Serializable{
	private long operatorid;
	private long roleid;
	public long getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(long operatorid) {
		this.operatorid = operatorid;
	}
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

}
