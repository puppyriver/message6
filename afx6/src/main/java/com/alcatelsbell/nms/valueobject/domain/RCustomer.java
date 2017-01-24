package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.Model;

import javax.persistence.*;
import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
import java.util.Vector;

/**
 * customer客户信息实体类
 */
@Table(name = "R_CUSTOMER")
@Entity
public class RCustomer  extends BObject implements java.io.Serializable {

	// Fields
	private String name;                //客户名称
	private String code;				//客户编号
	private String memo;				//备忘录
	private String creator;				//创建人
	private String updator;				//更新人
	private String city;				//所属城市
	private String region;				//所属地区/区域
	private Short trade;				//所属行业
	private Short companytype;			//公司类型
	private Short companyscale;			//
	private Short staffcount;			//员工数量
	private String address;				//地址
	private String postcode;			//邮政编码
	private String web;					//网址
	private String email;				//邮箱
	private String contactor;			//联系人
	private Short customerlevel;		//客户等级
	private Short customertype;			//客户种类
	private Double xcoordinate;			//X坐标
	private Double ycoordinate;			//Y坐标
	private String customermgr;			//客户经理
	private String customermgrphone;	//客户经理电话
	private String customermgremail;	//客户经理email
	private String customermgrdepartment;	//客户经理部门
	private String groupcontactor;		//组织联系人
	private String groupcontactorphone;	//组织联系人电话
	private String businesscontactor;	//客户联系人
	private String businesscontactorphone;	//客户联系人电话
	private String businesscontactoremail;  //企业联系人email
	private String techcontactor;		//技术联系人
	private String techcontactorphone;	//技术联系人电话
	private Short datasource;			//数据源
	private String datachangeinfo;		//数据改变信息
	private String cusPost;				//
	//  @Temporal(TemporalType.TIMESTAMP)
	//	private Date createdate;

	@Model
	private String parentcustomerdn;

	public String getParentcustomerdn() {
		return parentcustomerdn;
	}

	public void setParentcustomerdn(String parentcustomerdn) {
		this.parentcustomerdn = parentcustomerdn;
	}


	public String getCusPost() {
		return cusPost;
	}

	public void setCusPost(String cusPost) {
		this.cusPost = cusPost;
	}

	@Column(name="m_regionid")
	private Long regionId;	//地区Id

	@Model(type="model")
	private String regiondn;

	@Transient
	private Vector<RContact> vcontact;

	// Constructors

	public Vector<RContact> getVcontact() {
		return vcontact;
	}

	public void setVcontact(Vector<RContact> vcontact) {
		this.vcontact = vcontact;
	}

	/** default constructor */
	public RCustomer() {
	}

	/** minimal constructor */
	public RCustomer(String name) {
		this.name = name;
	}

	/** full constructor */
	public RCustomer(String name, String code,
			String memo, Date createdate, String creator, Date updatedate,
			String updator, String city, String region, Short trade,
			Short companytype, Short companyscale, Short staffcount,
			String address, String postcode, String web, String email,
			String contactor, Short customerlevel, Short customertype,
			Double xcoordinate, Double ycoordinate, String customermgr,
			String customermgrphone, String customermgremail,
			String customermgrdepartment, String groupcontactor,
			String groupcontactorphone, String businesscontactor,
			String businesscontactorphone, String businesscontactoremail,
			String techcontactor, String techcontactorphone, Short datasource,
			String datachangeinfo ) {

		this.dn = dn;
		this.name = name;
		this.code = code;
		this.memo = memo;
		//this.createdate = createdate;
		this.creator = creator;
		this.updator = updator;
		this.city = city;
		this.region = region;
		this.trade = trade;
		this.companytype = companytype;
		this.companyscale = companyscale;
		this.staffcount = staffcount;
		this.address = address;
		this.postcode = postcode;
		this.web = web;
		this.email = email;
		this.contactor = contactor;
		this.customerlevel = customerlevel;
		this.customertype = customertype;
		this.xcoordinate = xcoordinate;
		this.ycoordinate = ycoordinate;
		this.customermgr = customermgr;
		this.customermgrphone = customermgrphone;
		this.customermgremail = customermgremail;
		this.customermgrdepartment = customermgrdepartment;
		this.groupcontactor = groupcontactor;
		this.groupcontactorphone = groupcontactorphone;
		this.businesscontactor = businesscontactor;
		this.businesscontactorphone = businesscontactorphone;
		this.businesscontactoremail = businesscontactoremail;
		this.techcontactor = techcontactor;
		this.techcontactorphone = techcontactorphone;
		this.datasource = datasource;
		this.datachangeinfo = datachangeinfo;

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	//	public Date getCreatedate() {
	//		return this.createdate;
	//	}
	//
	//	public void setCreatedate(Date createdate) {
	//		this.createdate = createdate;
	//	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return this.updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
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

	public Short getTrade() {
		return this.trade;
	}

	public void setTrade(Short trade) {
		this.trade = trade;
	}

	public Short getCompanytype() {
		return this.companytype;
	}

	public void setCompanytype(Short companytype) {
		this.companytype = companytype;
	}

	public Short getCompanyscale() {
		return this.companyscale;
	}

	public void setCompanyscale(Short companyscale) {
		this.companyscale = companyscale;
	}

	public Short getStaffcount() {
		return this.staffcount;
	}

	public void setStaffcount(Short staffcount) {
		this.staffcount = staffcount;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public Short getCustomerlevel() {
		return this.customerlevel;
	}

	public void setCustomerlevel(Short customerlevel) {
		this.customerlevel = customerlevel;
	}

	public Short getCustomertype() {
		return this.customertype;
	}

	public void setCustomertype(Short customertype) {
		this.customertype = customertype;
	}

	public Double getXcoordinate() {
		return this.xcoordinate;
	}

	public void setXcoordinate(Double xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public Double getYcoordinate() {
		return this.ycoordinate;
	}

	public void setYcoordinate(Double ycoordinate) {
		this.ycoordinate = ycoordinate;
	}

	public String getCustomermgr() {
		return this.customermgr;
	}

	public void setCustomermgr(String customermgr) {
		this.customermgr = customermgr;
	}

	public String getCustomermgrphone() {
		return this.customermgrphone;
	}

	public void setCustomermgrphone(String customermgrphone) {
		this.customermgrphone = customermgrphone;
	}

	public String getCustomermgremail() {
		return this.customermgremail;
	}

	public void setCustomermgremail(String customermgremail) {
		this.customermgremail = customermgremail;
	}

	public String getCustomermgrdepartment() {
		return this.customermgrdepartment;
	}

	public void setCustomermgrdepartment(String customermgrdepartment) {
		this.customermgrdepartment = customermgrdepartment;
	}

	public String getGroupcontactor() {
		return this.groupcontactor;
	}

	public void setGroupcontactor(String groupcontactor) {
		this.groupcontactor = groupcontactor;
	}

	public String getGroupcontactorphone() {
		return this.groupcontactorphone;
	}

	public void setGroupcontactorphone(String groupcontactorphone) {
		this.groupcontactorphone = groupcontactorphone;
	}

	public String getBusinesscontactor() {
		return this.businesscontactor;
	}

	public void setBusinesscontactor(String businesscontactor) {
		this.businesscontactor = businesscontactor;
	}

	public String getBusinesscontactorphone() {
		return this.businesscontactorphone;
	}

	public void setBusinesscontactorphone(String businesscontactorphone) {
		this.businesscontactorphone = businesscontactorphone;
	}

	public String getBusinesscontactoremail() {
		return this.businesscontactoremail;
	}

	public void setBusinesscontactoremail(String businesscontactoremail) {
		this.businesscontactoremail = businesscontactoremail;
	}

	public String getTechcontactor() {
		return this.techcontactor;
	}

	public void setTechcontactor(String techcontactor) {
		this.techcontactor = techcontactor;
	}

	public String getTechcontactorphone() {
		return this.techcontactorphone;
	}

	public void setTechcontactorphone(String techcontactorphone) {
		this.techcontactorphone = techcontactorphone;
	}

	public Short getDatasource() {
		return this.datasource;
	}

	public void setDatasource(Short datasource) {
		this.datasource = datasource;
	}

	public String getDatachangeinfo() {
		return this.datachangeinfo;
	}

	public void setDatachangeinfo(String datachangeinfo) {
		this.datachangeinfo = datachangeinfo;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegiondn() {
		return regiondn;
	}

	public void setRegiondn(String regiondn) {
		this.regiondn = regiondn;
	}
}