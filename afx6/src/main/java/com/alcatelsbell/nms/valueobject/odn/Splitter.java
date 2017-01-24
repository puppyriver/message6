package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;
import com.alcatelsbell.nms.valueobject.physical.config.ShelfType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Author: Ronnie.Chen
 * Date: 12-8-27
 * Time: 下午2:04
 * rongrong.chen@alcatel-sbell.com.cn
 */
@XmlRootElement(name = "Splitter")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Splitter")
@Entity
@Table(name = "O_SPLITTER")
public class Splitter extends AbstractOdn{

	@DicGroupMapping(groupName = "SPLITEERWAYNUMBER", definitionClass = OdnDictionary.class)
    @BField(description = "分光比",
    		createType = BField.CreateType.REQUIRED,
    		searchType = BField.SearchType.NULLABLE)
    private Integer wayNumber;
    private String inPortDn;

    @BField(description = "编号")
    private Integer sequence;

    @BField(description = "标识",searchType = BField.SearchType.NULLABLE)
    private String userlabel;

    @BField(description = "所属光交",
            createType = BField.CreateType.NULLABLE,
            editType = BField.EditType.NULLABLE,searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Rack",
            dnReferenceTransietField = "rackName",
            dnReferenceEntityField = "rackname")
    private String rackDn;

    @BField(description = "分光器类型",
            createType = BField.CreateType.HIDE,
            editType = BField.EditType.HIDE,
            mergeType = BField.MergeType.RESERVED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.ShelfType",
            dnReferenceTransietField = "shelfTypeName",
            dnReferenceEntityField = "name")
    private String shelfTypeDn;

    @Transient
    private String shelfTypeName;

    @Transient
    private ShelfType shelfTypeEntity;

    private String rackName;

    @BField(description = "厂商",searchType = BField.SearchType.NULLABLE)
    private String vendorDn;

    public Integer getWayNumber() {
        return wayNumber;
    }

    public void setWayNumber(Integer wayNumber) {
        this.wayNumber = wayNumber;
    }

    public String getInPortDn() {
        return inPortDn;
    }

    public void setInPortDn(String inPortDn) {
        this.inPortDn = inPortDn;
    }

    public String getUserlabel() {
        return userlabel;
    }

    public void setUserlabel(String userlabel) {
        this.userlabel = userlabel;
    }

    public String getRackDn() {
        return rackDn;
    }

    public void setRackDn(String rackDn) {
        this.rackDn = rackDn;
    }

    public String getVendorDn() {
        return vendorDn;
    }

    public void setVendorDn(String vendorDn) {
        this.vendorDn = vendorDn;
    }

    public String getRackName() {
        return rackName;
    }

    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getShelfTypeDn() {
        return shelfTypeDn;
    }

    public void setShelfTypeDn(String shelfTypeDn) {
        this.shelfTypeDn = shelfTypeDn;
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
}
