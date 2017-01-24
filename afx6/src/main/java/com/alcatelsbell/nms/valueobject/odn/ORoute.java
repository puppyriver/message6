package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Ronnie.Chen
 * Date: 12-9-25
 * Time: 下午1:59
 * rongrong.chen@alcatel-sbell.com.cn
 */

@Entity
@Table(name = "O_ROUTE")
public class ORoute extends AbstractOdn{

    private String aEndInfo;
    private String zEndInfo;
    private String entityType;
    private String entityDn;
    private int sequence;
    private String businessDn;
    private String businessType;
    private String endSide;

    public String getEndSide() {
        return endSide;
    }

    public void setEndSide(String endSide) {
        this.endSide = endSide;
    }

    public String getaEndInfo() {
        return aEndInfo;
    }

    public void setaEndInfo(String aEndInfo) {
        this.aEndInfo = aEndInfo;
    }

    public String getzEndInfo() {
        return zEndInfo;
    }

    public void setzEndInfo(String zEndInfo) {
        this.zEndInfo = zEndInfo;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityDn() {
        return entityDn;
    }

    public void setEntityDn(String entityDn) {
        this.entityDn = entityDn;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getBusinessDn() {
        return businessDn;
    }

    public void setBusinessDn(String businessDn) {
        this.businessDn = businessDn;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
