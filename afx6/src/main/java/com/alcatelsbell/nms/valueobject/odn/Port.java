package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Port")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Port")
@Entity
@Table(name = "O_PORT")
public class Port extends AbstractOdn {

	private static final long serialVersionUID = 6701172641346307281L;

	//@BField(description = "端口名称",searchType = BField.SearchType.NULLABLE)
	private String portname;
	
	private String shelf_dn;

	@DicGroupMapping(groupName = "PORTTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "端口类型",searchType = BField.SearchType.NULLABLE)
	private int porttype;
	
	@BField(description="板卡名字",
			createType = BField.CreateType.REQUIRED,
			editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Card",
            dnReferenceTransietField = "cardName",
            dnReferenceEntityField = "name")
	private String card_dn;
	
	//"可用":1,"故障":2
	@DicGroupMapping(groupName = "PHYSICALSTATUS", definitionClass = OdnDictionary.class)
	@BField(description = "物理状态")
	private int physicalstatus;
	
	//"空闲":1,"占用":2,"保留":3
	@DicGroupMapping(groupName = "SERVICESTATUS", definitionClass = OdnDictionary.class)
	@BField(description = "业务状态",viewType = BField.ViewType.HIDE,editType = BField.EditType.HIDE)
	private int servicestatus;
	
	//"已连接":1,"未连接":2,"已直连":3,"预占用":4
	@DicGroupMapping(groupName = "CONNECTSTATUS", definitionClass = OdnDictionary.class)
	@BField(description = "连接状态",searchType = BField.SearchType.NULLABLE)
	private int connectstatus;
	
	@BField(description ="本地RFID",searchType = BField.SearchType.NULLABLE)
	private String localRfid;
	
	//@BField(description ="对端RFID",searchType = BField.SearchType.NULLABLE)
	private String remoteRfid;
		
	@Transient
	private String cardName;

    private String componentId;

    private String meDn;

    @BField(description ="业务信息",searchType = BField.SearchType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String businessInfo;
	
	@Transient
	private String fiberNo;

    @BField(description = "对端端口",viewType = BField.ViewType.DETAIL,lazyLoadExp = "jpql://select c.name from Port c where c.localRfid = :localRfid and c.dn != :dn")
    @Transient
    private String jumpPortName;

    @BField(description = "远端端口",viewType = BField.ViewType.DETAIL,lazyLoadExp = "jpql://select c .name from Port c where c.dn  = (select aportDn from LogicalConnection where  zportDn = :dn) or c.dn = (select zportDn from LogicalConnection where  aportDn = :dn)")
    @Transient
    private String remotePortName;
	
	public String getPortname() {
		return portname;
	}
	public void setPortname(String portname) {
		this.portname = portname;
	}
	public String getShelf_dn() {
		return shelf_dn;
	}
	public void setShelf_id(String shelf_dn) {
		this.shelf_dn = shelf_dn;
	}
	public int getPorttype() {
		return porttype;
	}
	public void setPorttype(int porttype) {
		this.porttype = porttype;
	}
	public int getPhysicalstatus() {
		return physicalstatus;
	}
	public void setPhysicalstatus(int physicalstatus) {
		this.physicalstatus = physicalstatus;
	}
	public String getCard_dn() {
		return card_dn;
	}
	public void setCard_dn(String card_dn) {
		this.card_dn = card_dn;
	}
	public int getServicestatus() {
		return servicestatus;
	}
	public void setServicestatus(int servicestatus) {
		this.servicestatus = servicestatus;
	}
	public int getConnectstatus() {
		return connectstatus;
	}
	public void setConnectstatus(int connectstatus) {
		this.connectstatus = connectstatus;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public void setShelf_dn(String shelf_dn) {
		this.shelf_dn = shelf_dn;
	}
	public String getLocalRfid() {
		return localRfid;
	}
	public void setLocalRfid(String localRfid) {
		this.localRfid = localRfid;
	}
	public String getRemoteRfid() {
		return remoteRfid;
	}
	public void setRemoteRfid(String remoteRfid) {
		this.remoteRfid = remoteRfid;
	}
	public String getFiberNo() {
		return fiberNo;
	}
	public void setFiberNo(String fiberNo) {
		this.fiberNo = fiberNo;
	}

    public String getMeDn() {
        return meDn;
    }

    public void setMeDn(String meDn) {
        this.meDn = meDn;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getJumpPortName() {
        return jumpPortName;
    }

    public void setJumpPortName(String jumpPortName) {
        this.jumpPortName = jumpPortName;
    }

    public String getRemotePortName() {
        return remotePortName;
    }

    public void setRemotePortName(String remotePortName) {
        this.remotePortName = remotePortName;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }
}
