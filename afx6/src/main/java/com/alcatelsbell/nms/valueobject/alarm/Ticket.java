package com.alcatelsbell.nms.valueobject.alarm;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Ronnie
 * Date: 11-12-28
 * Time: 下午3:40
 */
@Table(name = "F_TICKET")
@Entity
public class Ticket extends BObject{
    private Long curralarmId;
    private Long hisalarmId;
    private String alarmDn;
    private String alarmName;
    private String alarmIdentifier;
    private String ticketNo;
    private Integer ticketStatus;
    private String statusDesc;
    private String url;
    private Integer customerLevel;
    private String customerinfo;
    private String productinfo;
    private String regioninfo;
    private String groupinfo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timeLimit;

    @Temporal(TemporalType.TIMESTAMP)
    private Date alarmTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date replyTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date closeTime;
    
    private String comments;

    private Integer closed;


    public Long getCurralarmId() {
        return curralarmId;
    }

    public void setCurralarmId(Long curralarmId) {
        this.curralarmId = curralarmId;
    }

    public Long getHisalarmId() {
        return hisalarmId;
    }

    public void setHisalarmId(Long hisalarmId) {
        this.hisalarmId = hisalarmId;
    }

    public String getAlarmDn() {
        return alarmDn;
    }

    public void setAlarmDn(String alarmDn) {
        this.alarmDn = alarmDn;
    }

    public String getAlarmIdentifier() {
        return alarmIdentifier;
    }

    public void setAlarmIdentifier(String alarmIdentifier) {
        this.alarmIdentifier = alarmIdentifier;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCustomerinfo() {
        return customerinfo;
    }

    public void setCustomerinfo(String customerinfo) {
        this.customerinfo = customerinfo;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getRegioninfo() {
        return regioninfo;
    }

    public void setRegioninfo(String regioninfo) {
        this.regioninfo = regioninfo;
    }

    public String getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(String groupinfo) {
        this.groupinfo = groupinfo;
    }

    public Date getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Date timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(Integer customerLevel) {
        this.customerLevel = customerLevel;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }
}
