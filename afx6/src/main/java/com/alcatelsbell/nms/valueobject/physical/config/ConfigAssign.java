package com.alcatelsbell.nms.valueobject.physical.config;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-5-22
 * Time: 上午10:51
 */
@Entity
@Table(name = "T_CONFIGASSIGN")
public class ConfigAssign extends BObject{
    private String parentConfigType;
    private String parentConfigDn;
    private String childConfigType;
    private String childConfigDn;

    private Double childX;
    private Double childY;
    private Integer locationIndex;

    public String getParentConfigType() {
        return parentConfigType;
    }

    public void setParentConfigType(String parentConfigType) {
        this.parentConfigType = parentConfigType;
    }

    public String getParentConfigDn() {
        return parentConfigDn;
    }

    public void setParentConfigDn(String parentConfigDn) {
        this.parentConfigDn = parentConfigDn;
    }

    public String getChildConfigType() {
        return childConfigType;
    }

    public void setChildConfigType(String childConfigType) {
        this.childConfigType = childConfigType;
    }

    public String getChildConfigDn() {
        return childConfigDn;
    }

    public void setChildConfigDn(String childConfigDn) {
        this.childConfigDn = childConfigDn;
    }

    public Double getChildX() {
        return childX;
    }

    public void setChildX(Double childX) {
        this.childX = childX;
    }

    public Double getChildY() {
        return childY;
    }

    public void setChildY(Double childY) {
        this.childY = childY;
    }

    public Integer getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(Integer index) {
        this.locationIndex = index;
    }
}
