package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;

import java.util.Date;

/**
 * User: Ronnie
 * Date: 12-2-15
 * Time: 上午10:02
 */
@Table(name = "S_Message")
@Entity
public class SMessage extends BObject{
    private Integer type ;
    private Integer fileKey;
    private String title;

    @Column(length = 1024)
    @BField(description = "内容",editType = BField.EditType.REQUIRED,createType = BField.CreateType.REQUIRED)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @BField(description = "起始时间",editType = BField.EditType.NULLABLE,createType = BField.CreateType.NULLABLE)
    private Date startTime;

    @Temporal (TemporalType.TIMESTAMP)
    @BField(description = "结束时间",editType = BField.EditType.NULLABLE,createType = BField.CreateType.NULLABLE)
    private Date endTime;
    private Long parentId;

    @BField(description = "发布者",
    		editType = BField.EditType.HIDE,
    		searchType=BField.SearchType.NULLABLE,
    		viewType=BField.ViewType.SHOW,
    		createType=BField.CreateType.HIDE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "user_name",
            dnField = "id",
            dnReferenceEntityField = "name", mergeType = BField.MergeType.RESERVED)
    private Long fromUserId;
    
    @Transient
    private String user_name;
    
    public SMessage() {

    }
    public SMessage(Long fromUserId,String title,String text) {
        this.fromUserId = fromUserId;
        this.title = title;
        this.text = text;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFileKey() {
        return fileKey;
    }

    public void setFileKey(Integer fileKey) {
        this.fileKey = fileKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
