package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User: Ronnie
 * Date: 12-2-15
 * Time: 上午10:02
 */
@Table(name = "S_MessageAssign")
@Entity
public class SMessageAssign extends BObject{
    private Long messageId;
    private Long assignerId;
    private Integer assignerType;

    private Integer readStatus;
    private Integer writeStatus;

    @Transient
    private SMessage message;


    public SMessage getMessage() {
        return message;
    }

    public void setMessage(SMessage message) {
        this.message = message;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Long assignerId) {
        this.assignerId = assignerId;
    }

    public Integer getAssignerType() {
        return assignerType;
    }

    public void setAssignerType(Integer assignerType) {
        this.assignerType = assignerType;
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
}
