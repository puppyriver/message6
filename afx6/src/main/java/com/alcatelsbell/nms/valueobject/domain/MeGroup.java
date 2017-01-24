package com.alcatelsbell.nms.valueobject.domain;

import java.util.Vector;

import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.physical.Managedelement ;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User: Ronnie.Chen
 * Date: 11-5-28
 * Time:
 */
@Entity
@Table(name = "R_MEGROUP")
public class MeGroup extends BObject{
    private String name;
    private String parentmegroupdn;
    private String nativeemsname;
    
    @Transient
    private MeGroup[] subGroups;
    @Transient
	private Managedelement[] mes;
    @Transient
    private String userLabel;
    @Transient
    private long imageId=-1;

	public long getImageId() {
		return imageId;
	}

	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public MeGroup[] getSubGroups() {
		return subGroups;
	}

	public void setSubGroups(MeGroup[] subGroups) {
		this.subGroups = subGroups;
	}

	public Managedelement[] getMes() {
		return mes;
	}

	public void setMes(Managedelement[] mes) {
		this.mes = mes;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentmegroupdn() {
        return parentmegroupdn;
    }

    public void setParentmegroupdn(String parentmegroupdn) {
        this.parentmegroupdn = parentmegroupdn;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }
}
