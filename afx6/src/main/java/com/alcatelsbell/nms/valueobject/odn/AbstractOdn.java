package com.alcatelsbell.nms.valueobject.odn;

import java.util.Date;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.ExtBObject;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@MappedSuperclass
public class AbstractOdn extends ExtBObject{
	private static final long serialVersionUID = -2336746345873281161L;
	/*拼装名字*/
	@BField(description = "名称",searchType = BField.SearchType.NULLABLE,mergeType = BField.MergeType.RESERVED)
	private String name;
	/*编码*/
	private String code;
	/*创建者*/
	private String creator;
	/*更新者*/
	private String updater;
	/*MEMO*/
	private String memo;
	/*资产编号*/
	private String assetbarcode;
	/*位置序号*/
	private int locationIndex;
	/*出厂物理位置序号*/
	private int physicalIndex;
	/*变更日期*/
	@BField(description = "更新日期",searchType = BField.SearchType.NULLABLE,editType = BField.EditType.HIDE,createType = BField.CreateType.HIDE)
	@Temporal (TemporalType.TIMESTAMP)
	private Date modifyDate;
	
//	@BField(description = "变更记录",editType = BField.EditType.HIDE, createType = BField.CreateType.HIDE)
	@Column(length=1024)
	@XmlTransient
	private String modifyLog;
	
	/*对应的操作add,merge,delete*/
	private String operation;
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getUpdater() {
		return updater;
	}
	public void setUpdater(String updater) {
		this.updater = updater;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAssetbarcode() {
		return assetbarcode;
	}
	public void setAssetbarcode(String assetbarcode) {
		this.assetbarcode = assetbarcode;
	}
	public int getLocationIndex() {
		return locationIndex;
	}
	public void setLocationIndex(int locationIndex) {
		this.locationIndex = locationIndex;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getModifyLog() {
		return modifyLog;
	}
	public void setModifyLog(String modifyLog) {
		this.modifyLog = modifyLog;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return (locationIndex<((AbstractOdn)o).getLocationIndex()?-1:(locationIndex==((AbstractOdn) o).getLocationIndex()? 0 : 1));
	}
	public int getPhysicalIndex() {
		return physicalIndex;
	}
	public void setPhysicalIndex(int physicalIndex) {
		this.physicalIndex = physicalIndex;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
}
