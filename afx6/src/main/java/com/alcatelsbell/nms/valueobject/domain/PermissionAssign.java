package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;
@Table(name = "PERMISSIONASSIGN")
@Entity
public class PermissionAssign extends BObject implements Serializable{
	private long roleid;
	private long permissionid;
	public long getRoleid() {
		return roleid;
	}
	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}
	public long getPermissionid() {
		return permissionid;
	}
	public void setPermissionid(long permissionid) {
		this.permissionid = permissionid;
	}

}
