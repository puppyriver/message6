package com.alcatelsbell.nms.modules.task.model;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import java.util.Date;

@Table(name = "T_TASK")
@Entity
public class Task extends BObject{
	
	@BField(description = "关联任务",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType=BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.modules.task.model.Schedule",
            dnReferenceTransietField = "scheduleDn", dnField="id",
            dnReferenceEntityField = "dn")
    private Long scheduleId;
	@Transient
	private String scheduleDn;
	
	//@DicGroupMapping(groupName = "TASKSTATUS", definitionClass = NMSDictionary.class)
	@BField(description = "状态",viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
    private Integer status;

    @Temporal(TemporalType.TIMESTAMP)
    @BField(description = "开始时间",viewType=BField.ViewType.SHOW,editType = BField.EditType.NULLABLE)
    private Date startTime;
    @BField(description = "名称",viewType=BField.ViewType.SHOW,editType = BField.EditType.NULLABLE)
    private String name;
    @BField(description = "描述",viewType=BField.ViewType.SHOW,editType = BField.EditType.NULLABLE)
    private String description;
    
    
    @BField(description = "EMS",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType=BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Ems",
            dnReferenceTransietField = "emsDn",
            dnReferenceEntityField = "dn")
	private String taskObject;
	@Transient
	private String emsDn;
	
    private Integer percentage = 0;
    
   

	@Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
	

    public String getTaskObject() {
        return taskObject;
    }

    public void setTaskObject(String taskObject) {
        this.taskObject = taskObject;
    }

    

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getScheduleDn() {
		return scheduleDn;
	}

	public void setScheduleDn(String scheduleDn) {
		this.scheduleDn = scheduleDn;
	}

	public String getEmsDn() {
		return emsDn;
	}

	public void setEmsDn(String emsDn) {
		this.emsDn = emsDn;
	}
	
	 public Integer getPercentage() {
	        return percentage;
	    }

	    public void setPercentage(Integer percentage) {
	        this.percentage = percentage;
	    }
}
