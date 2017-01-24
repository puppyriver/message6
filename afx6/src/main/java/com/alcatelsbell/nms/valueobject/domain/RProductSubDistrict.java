package com.alcatelsbell.nms.valueobject.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-12
 * Time: 下午6:09
 */

@Entity
@Table(name = "R_PRODUCTSUBDISTRICT")
@Inheritance(strategy = InheritanceType.JOINED)
public class RProductSubDistrict extends RProduct implements java.io.Serializable {



	private String customername;
	private String customerdn;
	private String onudn;
	private String onuname;
	private String oltdn;
	private String oltname;
	private String commnets;
	private String circuitcode;
    private String regiondn;
    private String regionname;


    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getCustomerdn() {
        return customerdn;
    }

    public void setCustomerdn(String customerdn) {
        this.customerdn = customerdn;
    }

    public String getOnudn() {
        return onudn;
    }

    public void setOnudn(String onudn) {
        this.onudn = onudn;
    }

    public String getOnuname() {
        return onuname;
    }

    public void setOnuname(String onuname) {
        this.onuname = onuname;
    }

    public String getOltdn() {
        return oltdn;
    }

    public void setOltdn(String oltdn) {
        this.oltdn = oltdn;
    }

    public String getOltname() {
        return oltname;
    }

    public void setOltname(String oltname) {
        this.oltname = oltname;
    }

    public String getCommnets() {
        return commnets;
    }

    public void setCommnets(String commnets) {
        this.commnets = commnets;
    }

    public String getCircuitcode() {
        return circuitcode;
    }

    public void setCircuitcode(String circuitcode) {
        this.circuitcode = circuitcode;
    }

    public String getRegiondn() {
        return regiondn;
    }

    public void setRegiondn(String regiondn) {
        this.regiondn = regiondn;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }
}