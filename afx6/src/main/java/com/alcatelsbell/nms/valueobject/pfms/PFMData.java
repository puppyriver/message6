package com.alcatelsbell.nms.valueobject.pfms;

import com.alcatelsbell.nms.valueobject.BObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.Index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * Author: Ronnie.Chen
 * Date: 2014/11/8
 * Time: 15:16
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
@Table(name = "E_PFMDATA")
public class PFMData extends BObject implements Serializable {
    private Double value;

    @Column(length = 1024)
    private String stringValue;

    @Index(name = "INDEX_PMDATA_TIMESTAMP")
    private Date timestamp = new Date();

    @Transient
    private Object entityObject;

    @Index(name = "INDEX_PMDATA_DEVICEID")
    private Long deviceId;

    @Index(name = "INDEX_PMDATA_ENTITYID")
    private Long entityId;

    @Index(name = "INDEX_PMDATA_DEVICEDN")
    private String deviceDn;

    @Index(name = "INDEX_PMDATA_ENTITYDN")
    private String entityDn;

    @Index(name = "INDEX_PMDATA_ENTITYTYPE")
    private int entityType;

    private String unit;

    @Index(name = "INDEX_PMDATA_CATEGORY")
    private String category;


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }




    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Object getEntityObject() {
        return entityObject;
    }

    public void setEntityObject(Object entityObject) {
        this.entityObject = entityObject;
    }

    public String getDeviceDn() {
        return deviceDn;
    }

    public void setDeviceDn(String deviceDn) {
        this.deviceDn = deviceDn;
    }

    public String getEntityDn() {
        return entityDn;
    }

    public void setEntityDn(String entityDn) {
        this.entityDn = entityDn;
    }
}
