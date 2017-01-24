package com.alcatelsbell.nms.valueobject.physical.config;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:12
 */
@Entity
@Table(name = "T_RACKTYPE")
public class RackType extends EntityType{
    @DicGroupMapping(groupName = "SIDEDCATEGORY",definitionClass = OdnDictionary.class)
    @BField(description = "规格")
    private Integer sideType;

    @DicGroupMapping(groupName = "RACKTYPE",definitionClass = OdnDictionary.class)
    @BField(description = "机架类型", searchType = BField.SearchType.NULLABLE
    ,createType = BField.CreateType.REQUIRED)
    private Integer rackCategory;
    
    @BField(description = "机框数")
    private Integer shelfNumber ;
    
    //存一个字符串 ，例如 "1:(10,100);2:(20,100);3:(20,100)"
    @Column(length=2048)
    private String shelfCoordinate;
    
    //机框规格,存一个字符串 ，例如 "(10,100)" (如果所有框规格都一样的话)
    @Column(length = 2048)
    private String shelfSpec;
    
    public Integer getSideType() {
        return sideType;
    }

    public void setSideType(Integer sideType) {
        this.sideType = sideType;
    }

	public String getShelfCoordinate() {
		return shelfCoordinate;
	}

	public void setShelfCoordinate(String shelfCoordinate) {
		this.shelfCoordinate = shelfCoordinate;
	}

	public String getShelfSpec() {
		return shelfSpec;
	}

	public void setShelfSpec(String shelfSpec) {
		this.shelfSpec = shelfSpec;
	}

	public Integer getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(Integer shelfNumber) {
		this.shelfNumber = shelfNumber;
	}

    public Integer getRackCategory() {
        return rackCategory;
    }

    public void setRackCategory(Integer rackCategory) {
        this.rackCategory = rackCategory;
    }
}
