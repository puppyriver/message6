package com.alcatelsbell.nms.modules.task.model;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Author: Ronnie.Chen Date: 13-5-7 Time: 上午10:25
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Table(name = "T_SCHEDULE")
@Entity
public class Schedule extends BObject {
	public static final int TIME_TYPE_FIX = 0;
	public static final int TIME_TYPE_CRON = 1;
	public static final int TIME_TYPE_CYCLE = 2;
	public static final int TIME_TYPE_MANUAL = 3;
	private Integer timeType;
	
	@BField(description = "计划名称",viewType=BField.ViewType.SHOW,editType = BField.EditType.NULLABLE,createType=BField.CreateType.NULLABLE)

	private String jobName;
	
//	@DicGroupMapping(groupName = "SCHEDULEJOBTYPE", definitionClass = NMSDictionary.class)
	@BField(description = "计划类型",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED)

	private String jobType;
	
	/**
	 * fix: 2012-12-31 11:12:23 cron 0 15 10 ? * * cycle: 2012-12-31 12:13:24 |
	 * 1440
	 */
	private String timeExpression;
	
	@BField(description = "EMS",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType=BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Ems",
            dnReferenceTransietField = "emsDn",
            dnReferenceEntityField = "dn")
	private String taskObjects;
	@Transient
	private String emsDn;
	
	private String arguments;
	
	//@DicGroupMapping(groupName = "SCHEDULESTATUS", definitionClass = NMSDictionary.class)
	@BField(description = "状态",viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private Integer status;
	
	

	public static final int STATUS_INACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

	public String getTimeExpression() {
		return timeExpression;
	}

	public void setTimeExpression(String timeExpression) {
		this.timeExpression = timeExpression;
	}

	public String getTaskObjects() {
		return taskObjects;
	}

	public void setTaskObjects(String taskObjects) {
		this.taskObjects = taskObjects;
	}

	public String getArguments() {
		return arguments;
	}

	public void setArguments(String arguments) {
		this.arguments = arguments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	@Override
	public String toString() {
		return "Schedule{" + "id=" + getId() + ", timeType=" + timeType
				+ ", timeExpression='" + timeExpression + '\''
				+ ", taskObjects='" + taskObjects + '\'' + ", arguments='"
				+ arguments + '\'' + ", jobName='" + jobName + '\''
				+ ", status=" + status + '}';
	}

	public String getEmsDn() {
		return emsDn;
	}

	public void setEmsDn(String emsDn) {
		this.emsDn = emsDn;
	}
}
