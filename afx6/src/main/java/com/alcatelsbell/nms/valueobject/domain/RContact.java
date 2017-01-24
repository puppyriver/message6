package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.alcatelsbell.nms.valueobject.BObject;


@Table(name = "R_CONTACT")
@Entity
public class RContact extends BObject
implements Serializable{
	private String customerDn;
	private String name;
	private String position;
	private String address;
	private String phone;
	private String email;
	private String postalCode;
	private String company;
	private String phone2;
	private String phone3;
	private String phone4;
//	private String manager;
//    private String manaContact;
//    private String managEmail;
//	public String getManager() {
//		return manager;
//	}
//	public void setManager(String manager) {
//		this.manager = manager;
//	}
//	public String getManaContact() {
//		return manaContact;
//	}
//	public void setManaContact(String manaContact) {
//		this.manaContact = manaContact;
//	}
//	public String getManagEmail() {
//		return managEmail;
//	}
//	public void setManagEmail(String managEmail) {
//		this.managEmail = managEmail;
//	}
	private long customerId;
	private int type;
	private int vpnContactLevel;
	private String chiefManagerName;
	protected byte persistType;
	public int oid;
	public RContact(){
		
	}
	public RContact(String name, String position, String address, String phone,
			String email, String postalCode, String company, String phone2,
			String phone3, String phone4, long customerId, int type,
			int vpnContactLevel, String chiefManagerName,
			byte persistType, int oid, String customerDn) {
		super();
		this.name = name;
		this.position = position;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.postalCode = postalCode;
		this.company = company;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.phone4 = phone4;
		this.customerId = customerId;
		this.type = type;
		this.vpnContactLevel = vpnContactLevel;
		this.chiefManagerName = chiefManagerName;
		this.persistType = persistType;
		this.oid = oid;
		this.customerDn = customerDn;
	}
	public String getCustomerDn() {
		return customerDn;
	}
	public void setCustomerDn(String customerDn) {
		this.customerDn = customerDn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getPhone4() {
		return phone4;
	}
	public void setPhone4(String phone4) {
		this.phone4 = phone4;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getVpnContactLevel() {
		return vpnContactLevel;
	}
	public void setVpnContactLevel(int vpnContactLevel) {
		this.vpnContactLevel = vpnContactLevel;
	}
	public String getChiefManagerName() {
		return chiefManagerName;
	}
	public void setChiefManagerName(String chiefManagerName) {
		this.chiefManagerName = chiefManagerName;
	}
	public byte getPersistType() {
		return persistType;
	}
	public void setPersistType(byte persistType) {
		this.persistType = persistType;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
}
