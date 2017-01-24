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
@Table(name = "T_SHELFCONFIG")
@NamedQueries({
	@NamedQuery(
			name="ShelfConfig.getAllchildrenConfigList",
			query="SELECT cc FROM CardConfig cc WHERE cc.dn IN (SELECT ca.childConfigDn FROM ConfigAssign ca WHERE ca.parentConfigDn =:shelfConfigDn)"	
	)
	,
	@NamedQuery(
			name="ShelfConfig.getShelfTypeEntity",
			query="SELECT rt FROM ShelfType rt WHERE rt.dn=:shelfTypeDn"	
	)
})


public class ShelfConfig extends BObject{

    @BField(description = "名称",searchType = BField.SearchType.REQUIRED)
    private String name;


    @BField(description = "机框类型",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.ShelfType",
            dnReferenceTransietField = "shelfTypeName",
            dnReferenceEntityField = "name")
    private String shelfTypeDn;
    
    @Transient
    private List childrenConfigList;

    @Transient
    private String shelfTypeName;
    
    @Transient
    private ShelfType shelfTypeEntity;
    
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

    public String getShelfTypeDn() {
        return shelfTypeDn;
    }

    public void setShelfTypeDn(String shelfTypeDn) {
        this.shelfTypeDn = shelfTypeDn;
    }

	public List getChildrenConfigList() {
		return childrenConfigList;
	}

	public void setChildrenConfigList(List childrenConfigList) {
		this.childrenConfigList = childrenConfigList;
	}

	public String getShelfTypeName() {
		return shelfTypeName;
	}

	public void setShelfTypeName(String shelfTypeName) {
		this.shelfTypeName = shelfTypeName;
	}

	public ShelfType getShelfTypeEntity() {
		return shelfTypeEntity;
	}

	public void setShelfTypeEntity(ShelfType shelfTypeEntity) {
		this.shelfTypeEntity = shelfTypeEntity;
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
