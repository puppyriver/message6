package com.alcatelsbell.nms.valueobject.alarm;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-7-14
 */
@Table(name = "F_SLARECORD")
@Entity
public class SlaRecord  extends BObject implements java.io.Serializable {
    private String productdn;
    private double availability;
    private int faultcount;
    private long faulttime;
    private long faultrepeatcount;

    public String getProductdn() {
        return productdn;
    }

    public void setProductdn(String productdn) {
        this.productdn = productdn;
    }

    public double getAvailability() {
        return availability;
    }

    public void setAvailability(double availability) {
        this.availability = availability;
    }

    public int getFaultcount() {
        return faultcount;
    }

    public void setFaultcount(int faultcount) {
        this.faultcount = faultcount;
    }

    public long getFaulttime() {
        return faulttime;
    }

    public void setFaulttime(long faulttime) {
        this.faulttime = faulttime;
    }

    public long getFaultrepeatcount() {
        return faultrepeatcount;
    }

    public void setFaultrepeatcount(long faultrepeatcount) {
        this.faultrepeatcount = faultrepeatcount;
    }
}
