package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-26
 * Time:
 */
@Entity
@Table(name = "P_TOPOLOGICALLINK")
public class Topologicallink extends BObject {
    private String nativeemsname;
    private String amedn;
    private String amename;
    private String aportdn;
    private String aportname;

    private String zmedn;
    private String zmename;
    private String zportdn;
    private String zportname;

    private int direction;

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
