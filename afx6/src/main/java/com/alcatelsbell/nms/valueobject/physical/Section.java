package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-3-31
 * Time: 下午3:13
 */

@Entity
@Table(name = "P_SECTION")
public class Section extends BObject{


    private String productDn;

    private String ownerDn;
    private String ownerType;

    private String entityType;
    private String entityDn;
    private String entityCode;
    private Long entityId;

    private Integer sequence;


    public String getEntityCode() {
        return entityCode;
    }

    public void setEntityCode(String entityCode) {
        this.entityCode = entityCode;
    }

    public String getProductDn() {
        return productDn;
    }

    public void setProductDn(String productDn) {
        this.productDn = productDn;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerDn() {
        return ownerDn;
    }

    public void setOwnerDn(String ownerDn) {
        this.ownerDn = ownerDn;
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

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
