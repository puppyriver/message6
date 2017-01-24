package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;
import com.alcatelsbell.nms.valueobject.physical.config.RackType;
import com.alcatelsbell.nms.valueobject.physical.config.ShelfType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Aaron
 * Date 12.05.11
 * */

@XmlRootElement(name = "Shelf")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Shelf")
@Entity
@Table(name = "O_SHELF")
public class Shelf extends AbstractOdn {
	private static final long serialVersionUID = 5082958438018773778L;

  //  @BField(description = "机框名称",searchType = BField.SearchType.NULLABLE)
    private String shelfname;

    @BField(description = "序列号",searchType = BField.SearchType.NULLABLE)
    private String serialNumber;

	@BField(description = "机架名字",
			createType = BField.CreateType.REQUIRED,
			editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Rack",
            dnReferenceTransietField = "rackName",
            dnReferenceEntityField = "name")
	private String rack_dn;
	
	@DicGroupMapping(groupName = "ORDEROFARRANGE", definitionClass = OdnDictionary.class)
	@BField(description = "板卡排列",searchType = BField.SearchType.NULLABLE)
	private int cardrannkmethod;

	@DicGroupMapping(groupName = "RACKINSTALLATIONSIDE", definitionClass = OdnDictionary.class)
	@BField(description = "机架属性",searchType = BField.SearchType.NULLABLE,editType=BField.EditType.REQUIRED,createType = BField.CreateType.REQUIRED)
	private int orientation;
	
	private String room_dn;
	private int cardamount;

	@DicGroupMapping(groupName = "SHELFTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "机框类型",searchType = BField.SearchType.NULLABLE)
	private int shelftype;

    @BField(description = "机框模板类型",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
    		mergeType = BField.MergeType.RESERVED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.ShelfType",
            dnReferenceTransietField = "shelfTypeName",
            dnReferenceEntityField = "name")
    private String shelfTypeDn;

    @Transient
    private String shelfTypeName;

    @XmlTransient
    @Transient
    private ShelfType shelfTypeEntity;
    
    @Transient
    private String rackName;

    private String componentId;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getShelfTypeName() {
        return shelfTypeName;
    }

    public void setShelfTypeName(String shelfTypeName) {
        this.shelfTypeName = shelfTypeName;
    }

    public String getShelfTypeDn() {
        return shelfTypeDn;
    }

    public void setShelfTypeDn(String shelfTypeDn) {
        this.shelfTypeDn = shelfTypeDn;
    }

    public String getShelfname() {
		return shelfname;
	}
	public void setShelfname(String shelfname) {
		this.shelfname = shelfname;
	}
	public String getRack_dn() {
		return rack_dn;
	}
	public void setRack_dn(String rack_dn) {
		this.rack_dn = rack_dn;
	}
	public int getCardrannkmethod() {
		return cardrannkmethod;
	}
	public void setCardrannkmethod(int cardrannkmethod) {
		this.cardrannkmethod = cardrannkmethod;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public String getRoom_dn() {
		return room_dn;
	}
	public void setRoom_dn(String room_dn) {
		this.room_dn = room_dn;
	}
	public int getCardamount() {
		return cardamount;
	}
	public void setCardamount(int cardamount) {
		this.cardamount = cardamount;
	}
	public int getShelftype() {
		return shelftype;
	}
	public void setShelftype(int shelftype) {
		this.shelftype = shelftype;
	}

	public ShelfType getShelfTypeEntity() {
		return shelfTypeEntity;
	}

	public void setShelfTypeEntity(ShelfType shelfTypeEntity) {
		this.shelfTypeEntity = shelfTypeEntity;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
