package com.alcatelsbell.nms.valueobject.alarm;

/**
 * User: Ronnie.Chen
 * Date: 11-5-13
 * Time: 上午10:52
 */


import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * FProductalarmStatus entity. @author MyEclipse Persistence Tools
 */
@Table(name = "F_PRODUCTALARM_STATUS")
@Entity
public class FProductalarmStatus extends BObject implements java.io.Serializable {

	// Fields

	private Long statusid;
	private Long productalarmid;
	private Short severity;

    @Deprecated
    public Long getProductid() {
        return productid;
    }

    @Deprecated
    public void setProductid(Long productid) {
        this.productid = productid;
    }

    @Model
    private Long productid;

    @Model
    private Long customerid;

	// Constructors

	/** default constructor */
	public FProductalarmStatus() {
	}

	/** full constructor */
	public FProductalarmStatus(Long productalarmid, Short severity) {
		this.productalarmid = productalarmid;
		this.severity = severity;
	}

	// Property accessors

	public Long getStatusid() {
		return this.statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}


	public Long getProductalarmid() {
		return this.productalarmid;
	}

	public void setProductalarmid(Long productalarmid) {
		this.productalarmid = productalarmid;
	}

	public Short getSeverity() {
		return this.severity;
	}

	public void setSeverity(Short severity) {
		this.severity = severity;
	}

    @Deprecated
    public Long getCustomerid() {
        return customerid;
    }

    @Deprecated
    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }
}