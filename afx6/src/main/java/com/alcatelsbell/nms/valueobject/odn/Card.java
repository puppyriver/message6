package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;
import com.alcatelsbell.nms.valueobject.physical.config.CardType;
import com.alcatelsbell.nms.valueobject.physical.config.RackType;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Card")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Card")
@Entity
@Table(name = "O_CARD")
public class Card extends AbstractOdn {
	private static final long serialVersionUID = -4542871751971025724L;

   // @BField(description = "板卡名称",searchType = BField.SearchType.NULLABLE)
	private String cardname;
		
    private String rack_dn;
    
	/*板卡类型：业务版1，控制板2，分光器板3*/
	@DicGroupMapping(groupName = "CARDTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "板卡类型",searchType = BField.SearchType.NULLABLE)
	private int cardtype;

	@DicGroupMapping(groupName = "PHYSICALSTATUS", definitionClass = OdnDictionary.class)
	@BField(description = "物理状态",searchType = BField.SearchType.NULLABLE)
	private int physicalstatus;
	
	@BField(description = "机框名字",
			createType = BField.CreateType.REQUIRED,
			editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Shelf",
            dnReferenceTransietField = "shelfName",
            dnReferenceEntityField = "name")
	private String shelf_dn;

	@DicGroupMapping(groupName = "ORDEROFARRANGE", definitionClass = OdnDictionary.class)
	@BField(description = "端口排列",searchType = BField.SearchType.NULLABLE)
	private int portranktype;
	private int portamount;
	
	@BField(description = "分光比",
    		createType = BField.CreateType.REQUIRED,
    		searchType = BField.SearchType.NULLABLE)
    private Integer wayNumber;
	
	/*输入端口dn，默认是第9个端口*/
	private String inPortDn;
	
	@BField(description = "板卡模板类型",
			createType = BField.CreateType.REQUIRED,
			editType = BField.EditType.REQUIRED,
			mergeType = BField.MergeType.RESERVED,
	        dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.physical.config.CardType",
	        dnReferenceTransietField = "cardTypeName",
	        dnReferenceEntityField = "name")
	private String cardTypeDn;
	
	@XmlTransient
	@Transient
    private CardType cardTypeEntity;
	
	@Transient
    private String cardTypeName;
	
	@Transient
	private String shelfName;

    private String componentId;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getCardTypeDn() {
        return cardTypeDn;
    }

    public void setCardTypeDn(String cardTypeDn) {
        this.cardTypeDn = cardTypeDn;
    }

    public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	public String getRack_dn() {
		return rack_dn;
	}
	public void setRack_dn(String rack_dn) {
		this.rack_dn = rack_dn;
	}
	public int getCardtype() {
		return cardtype;
	}
	public void setCardtype(int cardtype) {
		this.cardtype = cardtype;
	}
	public int getPhysicalstatus() {
		return physicalstatus;
	}
	public void setPhysicalstatus(int physicalstatus) {
		this.physicalstatus = physicalstatus;
	}
	public String getShelf_dn() {
		return shelf_dn;
	}
	public void setShelf_dn(String shelf_dn) {
		this.shelf_dn = shelf_dn;
	}
	public int getPortranktype() {
		return portranktype;
	}
	public void setPortranktype(int portranktype) {
		this.portranktype = portranktype;
	}
	public int getPortamount() {
		return portamount;
	}
	public void setPortamount(int portamount) {
		this.portamount = portamount;
	}

	public CardType getCardTypeEntity() {
		return cardTypeEntity;
	}

	public void setCardTypeEntity(CardType cardTypeEntity) {
		this.cardTypeEntity = cardTypeEntity;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public String getShelfName() {
		return shelfName;
	}

	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}

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

}
