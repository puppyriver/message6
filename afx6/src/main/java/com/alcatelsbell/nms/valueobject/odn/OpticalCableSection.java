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
 * Time: 下午02:12:28
 */
@Entity
@Table(name = "O_OPTICALCABLESECTION")
public class OpticalCableSection extends AbstractOdn {
	
	private static final long serialVersionUID = 1L;
	
	@BField(description = "所属光缆",searchType = BField.SearchType.NULLABLE,
			dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.OpticalCable",
            dnReferenceTransietField = "opticalCableName",
            dnReferenceEntityField = "opticalCableName")
	private String opticalCable_dn;
	
	@BField(description = "光缆段名称",searchType = BField.SearchType.NULLABLE)
	private String sectionName;
	
	@BField(description = "所属区域",editType = BField.EditType.REQUIRED,
			searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
            dnReferenceTransietField = "regionName",
            dnReferenceEntityField = "name")
	private String region_dn;
	
	@BField(description = "A端机房",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Room",
            searchType = BField.SearchType.NULLABLE,
            dnReferenceTransietField = "a_room_name",
            dnReferenceEntityField = "roomname")
	private String a_room_dn;
	
	@BField(description = "A端设备",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Rack",
            searchType = BField.SearchType.NULLABLE,
            dnReferenceTransietField = "a_device_name",
            dnReferenceEntityField = "rackname")
	private String a_device_dn;
	
	@BField(description = "Z端机房",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Room",
            searchType = BField.SearchType.NULLABLE,
            dnReferenceTransietField = "z_room_name",
            dnReferenceEntityField = "roomname")
	private String z_room_dn;
	
	@BField(description = "Z端设备",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Rack",
            searchType = BField.SearchType.NULLABLE,
            dnReferenceTransietField = "z_device_name",
            dnReferenceEntityField = "rackname")
	private String z_device_dn;
	
	@BField(description = "纤芯类别",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName = "FIBERCATEGORY", definitionClass = OdnDictionary.class)
	private short fiberCategory;
	
	@BField(description = "纤芯数量",searchType = BField.SearchType.NULLABLE)
	private short fiberNumber;
	
	@BField(description = "光缆段长度(m)")
	private int length;
	
	@BField(description = "光缆段计算长度")
	private int calculateLength;
	
	@BField(description = "光缆模式",searchType = BField.SearchType.NULLABLE)
	@DicGroupMapping(groupName = "OPTICALCABLEMODE", definitionClass = OdnDictionary.class)
	private short opticalCableMode;
	
	@Transient
	private String regionName;
	
	@Transient
	private String opticalCableName;
	
	@Transient
	private String a_room_name;
	@Transient
	private String z_room_name;
	@Transient
	private String a_device_name;
	@Transient
	private String z_device_name;
//	@Transient
//	private OpticalCable opticalCableEntity;
//	@Transient
//	private Room aRoomEntity;
//	@Transient
//	private Room zRoomEntity;
//	@Transient
//	private Rack aDeviceEntity;
//	@Transient
//	private Rack zDeviceEntity;
	

	public String getOpticalCable_dn() {
		return opticalCable_dn;
	}

	public void setOpticalCable_dn(String opticalCable_dn) {
		this.opticalCable_dn = opticalCable_dn;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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

	public short getFiberCategory() {
		return fiberCategory;
	}

	public void setFiberCategory(short fiberCategory) {
		this.fiberCategory = fiberCategory;
	}

	public short getFiberNumber() {
		return fiberNumber;
	}

	public void setFiberNumber(short fiberNumber) {
		this.fiberNumber = fiberNumber;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCalculateLength() {
		return calculateLength;
	}

	public void setCalculateLength(int calculateLength) {
		this.calculateLength = calculateLength;
	}

	public short getOpticalCableMode() {
		return opticalCableMode;
	}

	public void setOpticalCableMode(short opticalCableMode) {
		this.opticalCableMode = opticalCableMode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getOpticalCableName() {
		return opticalCableName;
	}

	public void setOpticalCableName(String opticalCableName) {
		this.opticalCableName = opticalCableName;
	}

	public String getA_room_name() {
		return a_room_name;
	}

	public void setA_room_name(String aRoomName) {
		a_room_name = aRoomName;
	}

	public String getA_device_name() {
		return a_device_name;
	}

	public void setA_device_name(String aDeviceName) {
		a_device_name = aDeviceName;
	}

	public String getZ_room_name() {
		return z_room_name;
	}

	public void setZ_room_name(String zRoomName) {
		z_room_name = zRoomName;
	}

	public String getZ_device_name() {
		return z_device_name;
	}

	public void setZ_device_name(String zDeviceName) {
		z_device_name = zDeviceName;
	}
	
}
