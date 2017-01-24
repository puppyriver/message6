package com.alcatelsbell.nms.valueobject.domain;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;

@Table(name = "R_Message")
@Entity
public class Message extends BObject implements Serializable{
	private Integer authorId;
	private String Title;
	@Lob
	@Column(name="Content")
	private String Content;
	private Integer parentMessageId;
    private Integer type;


	public Message()
	{
	}
	public Message(Integer authorId, String title, String content,
			Integer parentMessageId) {
		this.authorId = authorId;
		Title = title;
		Content = content;
		this.parentMessageId = parentMessageId;
	}
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Integer getParentMessageId() {
		return parentMessageId;
	}
	
	public void setParentMessageId(Integer parentMessageId) {
		this.parentMessageId = parentMessageId;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
