package com.alcatelsbell.nms.valueobject.flow;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.util.Date;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-7-2
 * Time: 下午3:26
 *  一般记录来自外部系统的工单
 */
@XmlRootElement(name = "WorkOrder")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkOrder")
@Entity
@Table(name = "W_WORKORDER")
public class WorkOrder extends ExtBObject{
	
	private static final long serialVersionUID = 1L;

	/*创建时间*/
	@BField(description="创建时间")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	/*截止时间*/
	@BField(description="截止时间",
			searchType=BField.SearchType.NULLABLE)
    @Temporal (TemporalType.TIMESTAMP)
    private Date deadline;
   
    /*状态：已创建任务单 1，未创建任务单 2*/
    @DicGroupMapping(groupName = "WORKORDERSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "工单状态",searchType = BField.SearchType.NULLABLE)
    private Integer status;
    
    /*指派人*/
    @BField(description = "指派人",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "assignerName",
            dnReferenceEntityField = "name")
    private String assignerDn;
    
    /*指派人名字*/
    @Transient
    private String assignerName;
   
    /*施工组*/
    @BField(description = "施工组",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.UsersGroup",
            dnReferenceTransietField = "department",
            dnReferenceEntityField = "groupname")
    private String departmentDn;
    
    /*施工组*/
    @Transient
    private String department;
    
    @BField(description = "施工人",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "implementerName",
            dnReferenceEntityField = "name")
    private String implementerDn;
    
    @Transient
    private String implementerName;
    
    /*工单标题*/
    private String title;
    
    /*工单内容*/
    @Column(length=1024)
    private String content;
    
    /*任务单数量*/
    @BField(description = "任务单数量")
    private Integer sheetNumber;
    
    /*任务单完成数量*/
    @BField(description = "任务单完成数量")
    private Integer finishedSheetNumber;
    
    private Integer opticalLinkNumber;
    
    /*A端设备*/
    private String aEndDevice;
    
    /*A端端口*/
    private String aEndPort;
    
    /*Z端设备*/
    private String zEndDevice;
    
    /*Z端端口*/
    private String zEndPort;
    
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAssignerDn() {
		return assignerDn;
	}

	public void setAssignerDn(String assignerDn) {
		this.assignerDn = assignerDn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSheetNumber() {
		return sheetNumber;
	}

	public void setSheetNumber(Integer sheetNumber) {
		this.sheetNumber = sheetNumber;
	}

	public Integer getFinishedSheetNumber() {
		return finishedSheetNumber;
	}

	public void setFinishedSheetNumber(Integer finishedSheetNumber) {
		this.finishedSheetNumber = finishedSheetNumber;
	}

	public String getAssignerName() {
		return assignerName;
	}

	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getaEndDevice() {
		return aEndDevice;
	}

	public void setaEndDevice(String aEndDevice) {
		this.aEndDevice = aEndDevice;
	}

	public String getaEndPort() {
		return aEndPort;
	}

	public void setaEndPort(String aEndPort) {
		this.aEndPort = aEndPort;
	}

	public String getzEndDevice() {
		return zEndDevice;
	}

	public void setzEndDevice(String zEndDevice) {
		this.zEndDevice = zEndDevice;
	}

	public String getzEndPort() {
		return zEndPort;
	}

	public void setzEndPort(String zEndPort) {
		this.zEndPort = zEndPort;
	}

	public Integer getOpticalLinkNumber() {
		return opticalLinkNumber;
	}

	public void setOpticalLinkNumber(Integer opticalLinkNumber) {
		this.opticalLinkNumber = opticalLinkNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentDn() {
		return departmentDn;
	}

	public void setDepartmentDn(String departmentDn) {
		this.departmentDn = departmentDn;
	}

	public String getImplementerDn() {
		return implementerDn;
	}

	public void setImplementerDn(String implementerDn) {
		this.implementerDn = implementerDn;
	}

	public String getImplementerName() {
		return implementerName;
	}

	public void setImplementerName(String implementerName) {
		this.implementerName = implementerName;
	}

}
