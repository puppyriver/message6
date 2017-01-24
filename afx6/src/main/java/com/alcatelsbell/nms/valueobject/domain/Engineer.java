package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * User: Change Via
 * Date: 12-10-23
 * Time: 下午3:34
 * Enginner Manager Database
 */
@Entity
@Table(name = "TM_ENGINEER")
public class Engineer extends BObject implements Serializable {

    @Column(name = "ENGINEERNAME")
    private String engineerName;

    @Column(name = "ENGINEERDESC")
    private String engineerDesc;

    @javax.persistence.Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_TIME")
    private Date startTime;

    @javax.persistence.Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_TIME")
    private Date endTime;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "CONTACTWAY")
    private String contactWay;

    @Column(name = "ENGINEERSTATE")//0:审批通过、1：待审批、2：新建、3：驳回
    private Integer engineerState;
    
    @Column(name = "ENGINEERDEALSTATE")//0：激活、1：挂起、2：结束
    private Integer engineerDealState;

	@Column(name = "OPERATORID")
    private Long operatorId;

    @Column(name = "CREATEOR")
    private String creator;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = EngineerAssign.class,
            fetch = FetchType.EAGER, mappedBy = "engineer")
    private List engineerAssigns = new ArrayList();

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
    }

    public List getEngineerAssigns() {
        return engineerAssigns;
    }

    public void setEngineerAssigns(List engineerAssigns) {
        this.engineerAssigns = engineerAssigns;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public Integer getEngineerState() {
        return engineerState;
    }

    public void setEngineerState(Integer engineerState) {
        this.engineerState = engineerState;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "Engineer{" +
                "engineerName='" + engineerName + '\'' +
                ", engineerDesc='" + engineerDesc + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", contact='" + contact + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", engineerState=" + engineerState +
                ", operatorId=" + operatorId +
                ", creator='" + creator;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getEngineerDesc() {
        return engineerDesc;
    }

    public void setEngineerDesc(String engineerDesc) {
        this.engineerDesc = engineerDesc;
    }
    public Integer getEngineerDealState() {
		return engineerDealState;
	}

	public void setEngineerDealState(Integer engineerDealState) {
		this.engineerDealState = engineerDealState;
	}
}
