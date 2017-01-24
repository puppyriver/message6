package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Ronnie.Chen
 * Date: 11-8-24
 */

@Entity
@Table(name = "S_SYSTASK")
public class SysTask  extends BObject implements java.io.Serializable{
    private String sysName;
    private String taskType;          //任务类型    exp 1.报表任务  2激活任务  3归档任务
    private String taskName;         //任务名称
    private String taskSequence;

    private String creator;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;         //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date executeTime;          //开始执行时间

    private String taskObject;
    private String taskParameter;

    private Integer status;             //状态  1.未开始  2. 执行中 3.暂停 3.执行失败 4 执行成功 5已归档

    private Integer taskStatus;         // 根据不同的taskType动态定义

    private String executeRule;
    private String quartzRule;

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskSequence() {
        return taskSequence;
    }

    public void setTaskSequence(String taskSequence) {
        this.taskSequence = taskSequence;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getTaskObject() {
        return taskObject;
    }

    public void setTaskObject(String taskObject) {
        this.taskObject = taskObject;
    }

    public String getTaskParameter() {
        return taskParameter;
    }

    public void setTaskParameter(String taskParameter) {
        this.taskParameter = taskParameter;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExecuteRule() {
        return executeRule;
    }

    public void setExecuteRule(String executeRule) {
        this.executeRule = executeRule;
    }

    public String getQuartzRule() {
        return quartzRule;
    }

    public void setQuartzRule(String quartzRule) {
        this.quartzRule = quartzRule;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

}
