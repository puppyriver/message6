package com.alcatelsbell.nms.valueobject.physical.config;


import java.util.List;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:12
 */
@Entity
@Table(name = "T_RACKCONFIG")
@NamedQueries({
	@NamedQuery(
			name="RackConfig.getAllchildrenConfigList",
			query="SELECT sc FROM ShelfConfig sc WHERE sc.dn IN (SELECT ca.childConfigDn FROM ConfigAssign ca WHERE ca.parentConfigDn =:rackConfigDn)"	
	),

	@NamedQuery(
			name="RackConfig.getRackTypeEntity",
			query="SELECT rt FROM RackType rt WHERE rt.dn=:rackTypeDn"	
	)
})


public class RackConfig extends BObject{

    /**
	 * 
	 */

	@BField(description = "名称",searchType = BField.SearchType.REQUIRED)
    private String name;

    @BField(description = "机架类型",
    		createType=BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.RackType",
            dnReferenceTransietField = "rackTypeName",
            dnReferenceEntityField = "name")
    private String rackTypeDn;
    
    @Transient
    private List childrenConfigList;

    @Transient
    private String rackTypeName;
    
    @Transient
    private RackType rackTypeEntity;
    
    @Transient
    private Double selfX;
    
    @Transient
    private Double selfY;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRackTypeDn() {
        return rackTypeDn;
    }

    public void setRackTypeDn(String rackTypeDn) {
        this.rackTypeDn = rackTypeDn;
    }

	public List getChildrenConfigList() {
		return childrenConfigList;
	}

	public void setChildrenConfigList(List childrenConfigList) {
		this.childrenConfigList = childrenConfigList;
	}

	public String getRackTypeName() {
		return rackTypeName;
	}

	public void setRackTypeName(String rackTypeName) {
		this.rackTypeName = rackTypeName;
	}

	public RackType getRackTypeEntity() {
		return rackTypeEntity;
	}

	public void setRackTypeEntity(RackType rackTypeEntity) {
		this.rackTypeEntity = rackTypeEntity;
	}

	public Double getSelfX() {
		return selfX;
	}

	public void setSelfX(Double selfX) {
		this.selfX = selfX;
	}

	public Double getSelfY() {
		return selfY;
	}

	public void setSelfY(Double selfY) {
		this.selfY = selfY;
	}
}
