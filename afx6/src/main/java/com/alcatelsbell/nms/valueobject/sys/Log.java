package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Ronnie.Chen
 * Date: 11-8-23
 */

@Entity
@Table(name = "S_LOG")
public class  Log  extends BObject implements java.io.Serializable{

	@Temporal(TemporalType.TIMESTAMP)
	@BField(description = "日志时间",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)

	private Date time;      //日志时间
	@BField(description = "日志操作用户",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String username;          //日志操作用户   
	
	@BField(description = "用户登录的IP地址",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String ipaddress;         //用户登录的IP地址
	@BField(description = "系统名称",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String sysname;           //系统名称  @ 
	
	@BField(description = "模块",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
    @DicGroupMapping(groupName = "Log_Module")
    private String module;            //模块
	
	@BField(description = "日志来源",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String source; 			  //日志来源
	
	@BField(description = "日志类别",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
    @DicGroupMapping(groupName = "Log_Category")
	private String category;          //日志大类  @  用户操作日志  任务执行日志 安全日志 模块日志  系统 日志
	
	@BField(description = "日志操作类型",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String operation;         //日志小类，操作类型  @__
	
	@BField(description = "日志对象标识",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
    @DicGroupMapping(groupName = "Log_Object")
    private String object;            //日志对象标识
	
	@BField(description = "日志内容",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String content;           //日志内容  @  _
	
	
	private Long parentid;            //父日志ID
	@BField(description = "备注",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String comments;          //备注
	@BField(description = "备注2",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String comments2;         //备注2
	@BField(description = "备注3",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private String comments3;         //备注3

	@BField(description = "日志级别",searchType=BField.SearchType.NULLABLE,viewType=BField.ViewType.SHOW,editType = BField.EditType.HIDE)
	private Integer loglevel;         //日志级别
	private Integer childcount;


	public Integer getChildcount() {
		return childcount;
	}

	public void setChildcount(Integer childcount) {
		this.childcount = childcount;
	}

	public Integer getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(Integer loglevel) {
		this.loglevel = loglevel;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}



	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getParentid() {
		return parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments2() {
		return comments2;
	}

	public void setComments2(String comments2) {
		this.comments2 = comments2;
	}

	public String getComments3() {
		return comments3;
	}

	public void setComments3(String comments3) {
		this.comments3 = comments3;
	}


	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
