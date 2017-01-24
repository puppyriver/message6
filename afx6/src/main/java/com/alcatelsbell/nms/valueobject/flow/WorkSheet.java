package com.alcatelsbell.nms.valueobject.flow;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.ExtBObject;
import com.alcatelsbell.nms.valueobject.odn.WorkItem;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

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
 * Time: 下午3:27
 * 工作单
 */
@XmlRootElement(name = "WorkSheet")
@XmlType(name = "WorkSheet")  
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "W_WORKSHEET")
public class WorkSheet extends ExtBObject{

	private static final long serialVersionUID = 1L;

	//可以为空,表示无关联工单
//	@BField(description = "关联工单",
//    		createType = BField.CreateType.REQUIRED,
//    		searchType = BField.SearchType.NULLABLE)
    private String workOrderDn;
    
    /*任务单状态：未下发 2，已下发 1，已挂起 3*/
    @DicGroupMapping(groupName = "WORKSHEETSTATUS", definitionClass = OdnDictionary.class)
    @BField(description = "任务单状态",
    		searchType = BField.SearchType.NULLABLE)
    private Integer status;
    
    /*施工状态：未施工1，正在施工2，施工完毕3，施工异常4，待核查5*/
    @DicGroupMapping(groupName = "IMPLEMENTSTATUS", definitionClass = OdnDictionary.class)
    @BField(description="施工状态",
    		searchType = BField.SearchType.NULLABLE)
    private Integer implementStatus;
    
    /*任务步骤数*/
    @BField(description="任务步骤数")
    private Integer itemNumber;
    
    /*创建时间*/
    @BField(description="创建时间")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /*截止时间*/
	@BField(description="截止时间",
			searchType=BField.SearchType.NULLABLE)
    @Temporal (TemporalType.TIMESTAMP)
    private Date deadline;
    
    /*下发时间*/
    @BField(description="下发时间")
    @Temporal (TemporalType.TIMESTAMP)
    private Date deliverTime;

    /*完成时间*/
    @BField(description="完成时间")
    @Temporal (TemporalType.TIMESTAMP)
    private Date finishTime;

    //关联到Operator
    /*创建者*/
//    @BField(description = "任务单创建人",
//    		createType = BField.CreateType.REQUIRED,
//    		editType = BField.EditType.REQUIRED,
//            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
//            dnReferenceTransietField = "creatorName",
//            dnReferenceEntityField = "name")
    private String creatorDn;
    
    @Transient
    private String creatorName;
    
    /*指派人*/
    @BField(description = "任务单创建人",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "assignerName",
            dnReferenceEntityField = "name")
    private String assignerDn;

    @Transient
    private String assignerName;
    
    @BField(description = "施工人",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "implementerName",
            dnReferenceEntityField = "name")
    private String implementerDn;
    
    @Transient
    private String implementerName;
    
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
    
    /*任务操作内容：创建跳纤，更改跳纤，删除跳纤，巡检设备*/
    @DicGroupMapping(groupName = "WORKSHEETTYPE", definitionClass = OdnDictionary.class)
    @BField(description="操作内容")
    private Integer sheetContent;
    
    /*机房信息*/
    @BField(description = "机房名称",
    		createType = BField.CreateType.REQUIRED,
    		editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.odn.Room",
            dnReferenceTransietField = "roomName",
            dnReferenceEntityField = "roomname")
    private String roomDn;
    
    @Transient
    private String roomName;
    
    /*描述信息*/
    private String description;
    
    /*所属光路*/
  //  @BField(description = "所属光路")
    private String optical;
    
    //字符串形式:"(dn1:name1);(dn2:name2)"
  //  @BField(description = "巡检设备信息")
    private String deviceInfos;
    
    //设备信息（在那些设备上创建删除修改跳纤）
    @BField(description = "设备信息")
    private String deviceInfo;
    
	public String getWorkOrderDn() {
		return workOrderDn;
	}

	public void setWorkOrderDn(String workOrderDn) {
		this.workOrderDn = workOrderDn;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getImplementStatus() {
		return implementStatus;
	}

	public void setImplementStatus(Integer implementStatus) {
		this.implementStatus = implementStatus;
	}

	public Integer getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDeliverTime() {
		return deliverTime;
	}

	public void setDeliverTime(Date deliverTime) {
		this.deliverTime = deliverTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public String getCreatorDn() {
		return creatorDn;
	}

	public void setCreatorDn(String creatorDn) {
		this.creatorDn = creatorDn;
	}

	public String getAssignerDn() {
		return assignerDn;
	}

	public void setAssignerDn(String assignerDn) {
		this.assignerDn = assignerDn;
	}

	public Integer getSheetContent() {
		return sheetContent;
	}

	public void setSheetContent(Integer sheetContent) {
		this.sheetContent = sheetContent;
	}

	public String getRoomDn() {
		return roomDn;
	}

	public void setRoomDn(String roomDn) {
		this.roomDn = roomDn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getAssignerName() {
		return assignerName;
	}

	public void setAssignerName(String assignerName) {
		this.assignerName = assignerName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getOptical() {
		return optical;
	}

	public void setOptical(String optical) {
		this.optical = optical;
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

	public String getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(String deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

}
