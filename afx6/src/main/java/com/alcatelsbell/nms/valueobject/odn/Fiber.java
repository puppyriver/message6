/**
 * 
 */
package com.alcatelsbell.nms.valueobject.odn;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

/**
 * @author: Aaron
 * Date: 2012-9-11
 * Time: 下午02:13:04
 */
@Entity
@Table(name = "O_FIBER")
public class Fiber extends AbstractOdn {

	private static final long serialVersionUID = 1L;
	
	@BField(description = "所属光缆段",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.OpticalCableSection",
            dnReferenceTransietField = "opticalCableSectionName",
            dnReferenceEntityField = "sectionName")
	private String opticalCableSection_dn;

	@BField(description = "所属区域",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
            dnReferenceTransietField = "regionName",
            dnReferenceEntityField = "name")
	private String region_dn;
	
	@BField(description = "纤芯编号",searchType = BField.SearchType.NULLABLE)
	private String no;  //编号
	
	@BField(description = "A端机房",searchType = BField.SearchType.NULLABLE)
	private String a_room_dn;
	
	@BField(description = "A端设备",searchType = BField.SearchType.NULLABLE)
	private String a_device_dn;
	
	@BField(description = "A端端口",searchType = BField.SearchType.NULLABLE)
	private String a_port_dn;
	
	@BField(description = "Z端机房",searchType = BField.SearchType.NULLABLE)
	private String z_room_dn;
	
	@BField(description = "Z端设备",searchType = BField.SearchType.NULLABLE)
	private String z_device_dn;
	
	@BField(description = "Z端端口",searchType = BField.SearchType.NULLABLE)
	private String z_port_dn;
	
	@BField(description = "纤芯类别",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName = "FIBERCATEGORY", definitionClass = OdnDictionary.class)
	private int fiberCategory;
	
	@BField(description = "物理状态")
	@DicGroupMapping(groupName = "PHYSICALSTATUS", definitionClass = OdnDictionary.class)
	private int physicalstatus;
	
	@BField(description = "OTDR长度",searchType = BField.SearchType.NULLABLE)
	private float otdrlength;
	
	@BField(description = "OTDR损耗",searchType = BField.SearchType.NULLABLE)
	private float otdrloss;
	
	@BField(description = "线芯关联状态",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName = "FIBERASSOCIATIONSTATUS", definitionClass = OdnDictionary.class)
	private String fiberAssociationStatus;
	
	@Transient
	private String regionName;
	
	@Transient
	private String opticalCableSectionName;
	
	@Transient
	private String a_portname;
	
	@Transient
	private String z_portname;

	public String getOpticalCableSection_dn() {
		return opticalCableSection_dn;
	}

	public void setOpticalCableSection_dn(String opticalCableSection_dn) {
		this.opticalCableSection_dn = opticalCableSection_dn;
	}

	public String getRegion_dn() {
		return region_dn;
	}

	public void setRegion_dn(String region_dn) {
		this.region_dn = region_dn;
	}

	public String getA_room_dn() {
		return a_room_dn;
	}

	public void setA_room_dn(String a_room_dn) {
		this.a_room_dn = a_room_dn;
	}

	public String getA_device_dn() {
		return a_device_dn;
	}

	public void setA_device_dn(String a_device_dn) {
		this.a_device_dn = a_device_dn;
	}

	public String getA_port_dn() {
		return a_port_dn;
	}

	public void setA_port_dn(String a_port_dn) {
		this.a_port_dn = a_port_dn;
	}

	public String getZ_room_dn() {
		return z_room_dn;
	}

	public void setZ_room_dn(String z_room_dn) {
		this.z_room_dn = z_room_dn;
	}

	public String getZ_device_dn() {
		return z_device_dn;
	}

	public void setZ_device_dn(String z_device_dn) {
		this.z_device_dn = z_device_dn;
	}

	public String getZ_port_dn() {
		return z_port_dn;
	}

	public void setZ_port_dn(String z_port_dn) {
		this.z_port_dn = z_port_dn;
	}

	public int getFiberCategory() {
		return fiberCategory;
	}

	public void setFiberCategory(int fiberCategory) {
		this.fiberCategory = fiberCategory;
	}

	public int getPhysicalstatus() {
		return physicalstatus;
	}

	public void setPhysicalstatus(int physicalstatus) {
		this.physicalstatus = physicalstatus;
	}

	public float getOtdrlength() {
		return otdrlength;
	}

	public void setOtdrlength(float otdrlength) {
		this.otdrlength = otdrlength;
	}

	public float getOtdrloss() {
		return otdrloss;
	}

	public void setOtdrloss(float otdrloss) {
		this.otdrloss = otdrloss;
	}

	public String getFiberAssociationStatus() {
		return fiberAssociationStatus;
	}

	public void setFiberAssociationStatus(String fiberAssociationStatus) {
		this.fiberAssociationStatus = fiberAssociationStatus;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getOpticalCableSectionName() {
		return opticalCableSectionName;
	}

	public void setOpticalCableSectionName(String opticalCableSectionName) {
		this.opticalCableSectionName = opticalCableSectionName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getA_portname() {
		return a_portname;
	}

	public void setA_portname(String aPortname) {
		a_portname = aPortname;
	}

	public String getZ_portname() {
		return z_portname;
	}

	public void setZ_portname(String zPortname) {
		z_portname = zPortname;
	}
	
}
