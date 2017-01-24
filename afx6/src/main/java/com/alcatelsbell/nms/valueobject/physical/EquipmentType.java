package com.alcatelsbell.nms.valueobject.physical;

/**
 * User: Ronnie.Chen
 * Date: 11-6-21
 */

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name = "P_EQUIPMENTYPE")
public class EquipmentType extends BObject {
    private String name;
    private String description;
    private String vendor;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    private String field;
}
