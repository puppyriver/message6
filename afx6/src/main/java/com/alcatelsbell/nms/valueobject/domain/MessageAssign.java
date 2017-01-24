package com.alcatelsbell.nms.valueobject.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alcatelsbell.nms.valueobject.BObject;


@Table(name = "R_MessageAssign")
@Entity
public class MessageAssign extends BObject implements Serializable{
	private Integer messageId;
	private Integer readerId;
    private Integer readerType;
    private Integer readStatus;
    private Integer writeStatus;

    @Transient
    private Message message;

	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getReaderId() {
		return readerId;
	}
	public void setReaderId(Integer readerId) {
		this.readerId = readerId;
	}

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Integer getWriteStatus() {
        return writeStatus;
    }

    public void setWriteStatus(Integer writeStatus) {
        this.writeStatus = writeStatus;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Integer getReaderType() {
        return readerType;
    }

    public void setReaderType(Integer readerType) {
        this.readerType = readerType;
    }
}
