package com.alcatelsbell.nms.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.alcatelsbell.nms.valueobject.BObject;

public class Operator extends BObject implements Serializable {

 	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6803236132549346782L;
	
	
	private Long id;
	private String loginName;
	private String passWD;
	private Date expDate;
	private int accountStatus;
	private Date pwdExpdate;
	private String creator;
	private Date createDate;
	private String department;
	private String departmentdn;

	private String telephone;
	//private int version;
	private String no;
	private String name;
	private int predefined;
	private String description;
	private String appmodule;
	private String email;
	private int isteamleader;
	private int usergroupId;
	private int logoutTime;
	private String synchroflowPWD;
	private String sex;
	private Date birthday;
	private String worktype;
	private String dn;
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getWorktype() {
		return worktype;
	}
	public void setWorktype(String worktype) {
		this.worktype = worktype;
	}
	public String getDepartmentdn() {
		return departmentdn;
	}
	public void setDepartmentdn(String departmentdn) {
		this.departmentdn = departmentdn;
	}
	private Date entryDate;
	private String IDCard;
	private String allowip;
	private String reJectip;
	private int parentoperatorid;
	private String mobilephone;
	private boolean isweb;
	private ArrayList roleses;
	
	public ArrayList getRoleses() {
		return roleses;
	}
	public void setRoleses(ArrayList roleses) {
		this.roleses = roleses;
	}
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassWD() {
		return passWD;
	}
	public void setPassWD(String passWD) {
		this.passWD = passWD;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public int getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Date getPwdExpdate() {
		return pwdExpdate;
	}
	public void setPwdExpdate(Date pwdExpdate) {
		this.pwdExpdate = pwdExpdate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Override
	public Date getCreateDate() {
		return createDate;
	}
	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPredefined() {
		return predefined;
	}
	public void setPredefined(int predefined) {
		this.predefined = predefined;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppmodule() {
		return appmodule;
	}
	public void setAppmodule(String appmodule) {
		this.appmodule = appmodule;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIsteamleader() {
		return isteamleader;
	}
	public void setIsteamleader(int isteamleader) {
		this.isteamleader = isteamleader;
	}
	public int getUsergroupId() {
		return usergroupId;
	}
	public void setUsergroupId(int usergroupId) {
		this.usergroupId = usergroupId;
	}
	public int getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(int logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getSynchroflowPWD() {
		return synchroflowPWD;
	}
	public void setSynchroflowPWD(String synchroflowPWD) {
		this.synchroflowPWD = synchroflowPWD;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getIDCard() {
		return IDCard;
	}
	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}
	public String getAllowip() {
		return allowip;
	}
	public void setAllowip(String allowip) {
		this.allowip = allowip;
	}
	public String getReJectip() {
		return reJectip;
	}
	public void setReJectip(String reJectip) {
		this.reJectip = reJectip;
	}
	public int getParentoperatorid() {
		return parentoperatorid;
	}
	public void setParentoperatorid(int parentoperatorid) {
		this.parentoperatorid = parentoperatorid;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public boolean isIsweb() {
		return isweb;
	}
	public void setIsweb(boolean isweb) {
		this.isweb = isweb;
	}
	
}
