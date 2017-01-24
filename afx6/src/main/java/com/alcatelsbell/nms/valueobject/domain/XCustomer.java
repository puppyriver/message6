/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;

import java.util.List;
import java.util.Vector;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author g
 */
@Entity
public class XCustomer extends BObject {
    @Column (unique = true, nullable = false)
    private String name;

    private String contactorname;

    private String companyname;

    private String telephone;

    private String fax;

    private String email;

    private String address;

    private String comments;

    private int category;

   // @XYField (description = "客户等级", type = "enum")
    private int customerlevel;

    //@XYField (description = "是否容许派单", type = "enum")
    private int isDispatch; //是否容许派单

//    @Column(insertable = false, updatable = false)
//    protected Long regionId;
//
//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn (name = "regionId")
//    protected Region region = null;
    
    private String chiefManagerName;

    @Column (unique = true)
    private String no;

    private int customerProperty;

//    @OneToMany (mappedBy = "customer")
//    protected List<Business> businesses = null;

    public XCustomer() {
    }

//
//    @Column (unique = true, nullable = false)
//    protected String dn;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public List<Business> getBusinesses() {
//        return businesses;
//    }
//
//    public void setBusinesses(List<Business> businesses) {
//        this.businesses = businesses;
//    }
//
//    public void addBusiness(Business business) {
//        if (businesses == null) {
//            businesses = new Vector<Business>();
//        }
//
//        business.setCustomer(this);
//        businesses.add(business);
//    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getContactorname() {
        return contactorname;
    }

    public void setContactorname(String contactorname) {
        this.contactorname = contactorname;
    }

    public int getCustomerlevel() {
        return customerlevel;
    }

    public void setCustomerlevel(int customerlevel) {
        this.customerlevel = customerlevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getChiefManagerName() {
        return chiefManagerName;
    }

    public void setChiefManagerName(String chiefManagerName) {
        this.chiefManagerName = chiefManagerName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public int getCustomerProperty() {
        return customerProperty;
    }

    public void setCustomerProperty(int customerProperty) {
        this.customerProperty = customerProperty;
    }

    public int getIsDispatch() {
        return isDispatch;
    }

    public void setIsDispatch(int isDispatch) {
        this.isDispatch = isDispatch;
    }


//    public Region getRegion() {
//        return region;
//    }
//
//    public void setRegion(Region region) {
//        this.region = region;
//    }
//
//    public Long getRegionId() {
//        return regionId;
//    }


    @Override
    public String toString() {
        return this.name;
    }

}
