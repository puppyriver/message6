package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.valueobject.BObject;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-26
 * Time:
 */
@Entity
@Table(name = "P_ROUTE")
public class Route extends BObject {
    private String nativeemsname;

    @Index(name = "ROUTE_INDEX_CIRCUITDN")
    private String circuitdn;
    private String circuitno;

    private String amedn;
    private String amename;

    private String zmedn;
    private String zmename;

    @Index(name = "ROUTE_INDEX_APORTDN")
    private String aportdn;
    private String aportname;

    @Index(name = "ROUTE_INDEX_ZPORTDN")
    private String zportdn;
    private String zportname;

    private String actpname;
    private String zctpname;


    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public String getCircuitdn() {
        return circuitdn;
    }

    public void setCircuitdn(String circuitdn) {
        this.circuitdn = circuitdn;
    }

    public String getCircuitno() {
        return circuitno;
    }

    public void setCircuitno(String circuitno) {
        this.circuitno = circuitno;
    }

    public String getAmedn() {
        return amedn;
    }

    public void setAmedn(String amedn) {
        this.amedn = amedn;
    }

    public String getAmename() {
        return amename;
    }

    public void setAmename(String amename) {
        this.amename = amename;
    }

    public String getZmedn() {
        return zmedn;
    }

    public void setZmedn(String zmedn) {
        this.zmedn = zmedn;
    }

    public String getZmename() {
        return zmename;
    }

    public void setZmename(String zmename) {
        this.zmename = zmename;
    }

    public String getAportdn() {
        return aportdn;
    }

    public void setAportdn(String aportdn) {
        this.aportdn = aportdn;
    }

    public String getAportname() {
        return aportname;
    }

    public void setAportname(String aportname) {
        this.aportname = aportname;
    }

    public String getZportdn() {
        return zportdn;
    }

    public void setZportdn(String zportdn) {
        this.zportdn = zportdn;
    }

    public String getZportname() {
        return zportname;
    }

    public void setZportname(String zportname) {
        this.zportname = zportname;
    }

    public String getActpname() {
        return actpname;
    }

    public void setActpname(String actpname) {
        this.actpname = actpname;
    }

    public String getZctpname() {
        return zctpname;
    }

    public void setZctpname(String zctpname) {
        this.zctpname = zctpname;
    }
}
