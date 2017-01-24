package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * User: Ronnie
 * Date: 12-7-2
 * Time: 下午3:48
 * ODN施工步骤
 */
@XmlType(name = "WorkItem")  
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "O_WORKITEM")
public class WorkItem extends ExtBObject{
    private static final long serialVersionUID = -1l;
    
    /*步骤号*/
    @BField(description = "步骤号")
    private Integer sequence;
    
    /*关联任务单dn*/
    @BField(description = "关联任务单",
    		createType = BField.CreateType.REQUIRED,
    		searchType = BField.SearchType.NULLABLE)
    private String workSheetDn;
    
    //冗余
    private String workOrderDn;

    @BField(description="A端dn",
			editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
            dnReferenceTransietField = "aendport_name",
            dnReferenceEntityField = "name")
    private String aEndPortDn;

    @Transient
	private String aendport_name;
    
    @Transient
    private String aEndRfid;
    
    @BField(description="Z端dn",
			editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Port",
            dnReferenceTransietField = "zendport_name",
            dnReferenceEntityField = "name")
    private String zEndPortDn;
    
    @Transient
	private String zendport_name;
    
    @Transient
    private String zEndRfid;

    /*连接状态：已连接 1，未连接 2，已直连 3*/
    @DicGroupMapping(groupName = "CONNECTSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "A端原状态")
    private Integer aEndPortSourceStatus;

    @DicGroupMapping(groupName = "CONNECTSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "A端目标状态")
    private Integer aEndPortDestStatus;


    @DicGroupMapping(groupName = "CONNECTSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "Z端原状态")
    private Integer zEndPortSourceStatus;

    @DicGroupMapping(groupName = "CONNECTSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "Z端目标状态")
    private Integer zEndPortDestStatus;
    
    /*关联跳纤DN*/
    @BField(description = "跳纤名字",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Connect",
            dnReferenceTransietField = "connectName",
            dnReferenceEntityField = "name")
    private String connectDn;
    
    @Transient
    private String connectName;
    
    /*描述信息*/
    private String description;

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getWorkSheetDn() {
		return workSheetDn;
	}

	public void setWorkSheetDn(String workSheetDn) {
		this.workSheetDn = workSheetDn;
	}

	public String getWorkOrderDn() {
		return workOrderDn;
	}

	public void setWorkOrderDn(String workOrderDn) {
		this.workOrderDn = workOrderDn;
	}

	public String getaEndPortDn() {
		return aEndPortDn;
	}

	public void setaEndPortDn(String aEndPortDn) {
		this.aEndPortDn = aEndPortDn;
	}

	public String getzEndPortDn() {
		return zEndPortDn;
	}

	public void setzEndPortDn(String zEndPortDn) {
		this.zEndPortDn = zEndPortDn;
	}

	public Integer getaEndPortSourceStatus() {
		return aEndPortSourceStatus;
	}

	public void setaEndPortSourceStatus(Integer aEndPortSourceStatus) {
		this.aEndPortSourceStatus = aEndPortSourceStatus;
	}

	public Integer getaEndPortDestStatus() {
		return aEndPortDestStatus;
	}

	public void setaEndPortDestStatus(Integer aEndPortDestStatus) {
		this.aEndPortDestStatus = aEndPortDestStatus;
	}

	public Integer getzEndPortSourceStatus() {
		return zEndPortSourceStatus;
	}

	public void setzEndPortSourceStatus(Integer zEndPortSourceStatus) {
		this.zEndPortSourceStatus = zEndPortSourceStatus;
	}

	public Integer getzEndPortDestStatus() {
		return zEndPortDestStatus;
	}

	public void setzEndPortDestStatus(Integer zEndPortDestStatus) {
		this.zEndPortDestStatus = zEndPortDestStatus;
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

	public String getConnectDn() {
		return connectDn;
	}

	public void setConnectDn(String connectDn) {
		this.connectDn = connectDn;
	}

	public String getConnectName() {
		return connectName;
	}

	public void setConnectName(String connectName) {
		this.connectName = connectName;
	}

	public String getaEndRfid() {
		return aEndRfid;
	}

	public void setaEndRfid(String aEndRfid) {
		this.aEndRfid = aEndRfid;
	}

	public String getzEndRfid() {
		return zEndRfid;
	}

	public void setzEndRfid(String zEndRfid) {
		this.zEndRfid = zEndRfid;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		return (sequence<((WorkItem)o).getSequence()?-1:(sequence==((WorkItem) o).getSequence()? 0 : 1));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
