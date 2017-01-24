package com.alcatelsbell.nms.valueobject.task;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "TASK")
@Entity
public class Task extends BObject{
	
	private String title;

	private String content;
	
	private String assigner;
	
	private String handler;
	
	private Long parentTicketId;
	
	private String status;
	
	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAssigner() {
		return assigner;
	}

	public void setAssigner(String assigner) {
		this.assigner = assigner;
	}

	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	public Long getParentTicketId() {
		return parentTicketId;
	}

	public void setParentTicketId(Long parentTicketId) {
		this.parentTicketId = parentTicketId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
