package com.alcatelsbell.nms.valueobject.physical;

import com.alcatelsbell.nms.valueobject.BObject;
import org.hibernate.annotations.Index;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Ronnie.Chen
 * Date: 11-6-21
 */

@Entity
@Table(name = "P_EQUIPMENT")
public class Equipment extends BObject {
    private String name;
    private String userlabel;
    private String equipmenttypedn;

    private String nativeemsname;
    private String equipmenttype;

	@Index(name = "EQUIPMENT_INDEX_PARENTDN")
    private String parentequipmentdn;

    private String info;
    private String description;
    private int childrensize;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    private String vendor;


    public String getIndexno() {
        return indexno;
    }

    public void setIndexno(String index) {
        this.indexno = index;
    }

    private String indexno;
    private String field;

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }

    private String emsdn;

    @Transient
    private Equipment parent;

    @Transient
    private List<Equipment> children = new ArrayList<Equipment>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserlabel() {
        return userlabel;
    }

    public void setUserlabel(String userlabel) {
        this.userlabel = userlabel;
    }

    public String getEquipmenttypedn() {
        return equipmenttypedn;
    }

    public void setEquipmenttypedn(String equipmenttypedn) {
        this.equipmenttypedn = equipmenttypedn;
    }

    public String getEquipmenttype() {
        return equipmenttype;
    }

    public void setEquipmenttype(String type) {
        this.equipmenttype = type;
    }

    public String getParentequipmentdn() {
        return parentequipmentdn;
    }

    public void setParentequipmentdn(String parentequipmentdn) {
        this.parentequipmentdn = parentequipmentdn;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChildrensize() {
        return childrensize;
    }

    public void setChildrensize(int childrensize) {
        this.childrensize = childrensize;
    }

    public Equipment getParent() {
        return parent;
    }

    public void setParent(Equipment parent) {
        this.parent = parent;
    }

    public List<Equipment> getChildren() {
        return children;
    }

    public void setChildren(List<Equipment> children) {
        this.children = children;
    }

    public void addChild(Equipment child) {
        if (children == null)
            children = new ArrayList<Equipment>();
        children.add(child);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }


    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }
    

}
