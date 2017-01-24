package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "O_CONNECT")
public class Connect extends AbstractOdn {

	private static final long serialVersionUID = -2407046071902964242L;
	
	@BField(description="跳接名称",
			editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED)
	private String connectname;
	
	private String aendroom_dn;
	@BField(description="A端dn",
			editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
            dnReferenceTransietField = "aendport_name",
            dnReferenceEntityField = "name")
	private String aendport_dn;
	
	@BField(description="A端RFID")
	private String aRfid;
	
	private String zendroom_dn;
	@BField(description="Z端dn",
			editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
            dnReferenceTransietField = "zendport_name",
            dnReferenceEntityField = "name")
	private String zendport_dn;
	
	@BField(description="Z端RFID")
	private String zRfid;
	
	@DicGroupMapping(groupName = "CONNECTTYPE", definitionClass = OdnDictionary.class)
	@BField(description = "连接类型")
	private int connecttype;

	@DicGroupMapping(groupName = "PHYSICALSTATUS", definitionClass = OdnDictionary.class)
	@BField(description = "物理状态")
	private int physicalstatus;

	@Transient
	private String aendport_name;
	
	@Transient
	private String zendport_name;
	
	public String getConnectname() {
		return connectname;
	}

	public void setConnectname(String connectname) {
		this.connectname = connectname;
	}

	public String getAendroom_dn() {
		return aendroom_dn;
	}

	public void setAendroom_dn(String aendroom_dn) {
		this.aendroom_dn = aendroom_dn;
	}

	public String getAendport_dn() {
		return aendport_dn;
	}

	public void setAendport_dn(String aendport_dn) {
		this.aendport_dn = aendport_dn;
	}

	public String getZendroom_dn() {
		return zendroom_dn;
	}

	public void setZendroom_dn(String zendroom_dn) {
		this.zendroom_dn = zendroom_dn;
	}

	public String getZendport_dn() {
		return zendport_dn;
	}

	public void setZendport_dn(String zendport_dn) {
		this.zendport_dn = zendport_dn;
	}

	public int getConnecttype() {
		return connecttype;
	}

	public void setConnecttype(int connecttype) {
		this.connecttype = connecttype;
	}

	public int getPhysicalstatus() {
		return physicalstatus;
	}

	public void setPhysicalstatus(int physicalstatus) {
		this.physicalstatus = physicalstatus;
	}

	public String getAendport_name() {
		return aendport_name;
	}

	public void setAendport_name(String aendport_name) {
		this.aendport_name = aendport_name;
	}

	public String getZendport_name() {
		return zendport_name;
	}

	public void setZendport_name(String zendport_name) {
		this.zendport_name = zendport_name;
	}

	public String getaRfid() {
		return aRfid;
	}

	public void setaRfid(String aRfid) {
		this.aRfid = aRfid;
	}

	public String getzRfid() {
		return zRfid;
	}

	public void setzRfid(String zRfid) {
		this.zRfid = zRfid;
	}
	
	

}
