package com.alcatelsbell.nms.valueobject.alarm;

/**
 * User: Ronnie.Chen
 * Date: 11-5-13
 * Time: 上午10:50
 */


import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * FProductalarm entity. @author MyEclipse Persistence Tools
 */
@Table(name = "F_PRODUCTALARM")
@Entity
public class FProductalarm extends BObject implements java.io.Serializable {

	// Fields


	private String name;
	private Long productid;
	private String comments;
	private Short maxseverity;
	private Long productspecid;
	private String productspecname;
	private Short producttype;
	private String city;
	private String region;
	private Long customerid;


	private String customername;
	private Short customerlevel;
	private String servicelinecode;
	private String customermanager;
	private String serviceinfo;

    @Temporal(TemporalType.TIMESTAMP)
	private Date alarmstarttime;

    @Temporal (TemporalType.TIMESTAMP)
	private Date alarmendtime;
	private Short slalevel;
	private Short networkoperlevel;

    @Model
    private String customerdn;

	// Constructors

	/** default constructor */
	public FProductalarm() {
	}

	/** minimal constructor */
	public FProductalarm(String name, Long productid) {
		this.name = name;
		this.productid = productid;
	}

	/** full constructor */
	public FProductalarm(String name, Long productid, String comments,
			Short maxseverity, Long productspecid, String productspecname,
			Short producttype, String city, String region, Long customerid,
			String customername, Short customerlevel, String servicelinecode,
			String customermanager, String serviceinfo, Date alarmstarttime,
			Date alarmendtime, Short slalevel, Short networkoperlevel) {
		this.name = name;
		this.productid = productid;
		this.comments = comments;
		this.maxseverity = maxseverity;
		this.productspecid = productspecid;
		this.productspecname = productspecname;
		this.producttype = producttype;
		this.city = city;
		this.region = region;
		this.customerid = customerid;
		this.customername = customername;
		this.customerlevel = customerlevel;
		this.servicelinecode = servicelinecode;
		this.customermanager = customermanager;
		this.serviceinfo = serviceinfo;
		this.alarmstarttime = alarmstarttime;
		this.alarmendtime = alarmendtime;
		this.slalevel = slalevel;
		this.networkoperlevel = networkoperlevel;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProductid() {
		return this.productid;
	}

	public void setProductid(Long productid) {
		this.productid = productid;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Short getMaxseverity() {
		return this.maxseverity;
	}

	public void setMaxseverity(Short maxseverity) {
		this.maxseverity = maxseverity;
	}

	public Long getProductspecid() {
		return this.productspecid;
	}

	public void setProductspecid(Long productspecid) {
		this.productspecid = productspecid;
	}

	public String getProductspecname() {
		return this.productspecname;
	}

	public void setProductspecname(String productspecname) {
		this.productspecname = productspecname;
	}

	public Short getProducttype() {
		return this.producttype;
	}

	public void setProducttype(Short producttype) {
		this.producttype = producttype;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}

	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public Short getCustomerlevel() {
		return this.customerlevel;
	}

	public void setCustomerlevel(Short customerlevel) {
		this.customerlevel = customerlevel;
	}

	public String getServicelinecode() {
		return this.servicelinecode;
	}

	public void setServicelinecode(String servicelinecode) {
		this.servicelinecode = servicelinecode;
	}

	public String getCustomermanager() {
		return this.customermanager;
	}

	public void setCustomermanager(String customermanager) {
		this.customermanager = customermanager;
	}

	public String getServiceinfo() {
		return this.serviceinfo;
	}

	public void setServiceinfo(String serviceinfo) {
		this.serviceinfo = serviceinfo;
	}

	public Date getAlarmstarttime() {
		return this.alarmstarttime;
	}

	public void setAlarmstarttime(Date alarmstarttime) {
		this.alarmstarttime = alarmstarttime;
	}

	public Date getAlarmendtime() {
		return this.alarmendtime;
	}

	public void setAlarmendtime(Date alarmendtime) {
		this.alarmendtime = alarmendtime;
	}

	public Short getSlalevel() {
		return this.slalevel;
	}

	public void setSlalevel(Short slalevel) {
		this.slalevel = slalevel;
	}

	public Short getNetworkoperlevel() {
		return this.networkoperlevel;
	}

	public void setNetworkoperlevel(Short networkoperlevel) {
		this.networkoperlevel = networkoperlevel;
	}

    public String getCustomerdn() {
        return customerdn;
    }

    public void setCustomerdn(String customerdn) {
        this.customerdn = customerdn;
    }
}