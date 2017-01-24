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
@Table(name = "P_CARD")
public class Card extends BObject {
    private String slotdn;

    @Index(name = "CARD_INDEX_MEDN")
    private String medn;
    private String name;
    private String nativeemsname;
    private String type;
    private String vendor;
    private String emsdn;
    private String equipmenttypedn;

    public String getEmsdn() {
        return emsdn;
    }

    public void setEmsdn(String emsdn) {
        this.emsdn = emsdn;
    }

    public String getSlotdn() {
        return slotdn;
    }

    public void setSlotdn(String slotdn) {
        this.slotdn = slotdn;
    }

    public String getMedn() {
        return medn;
    }

    public void setMedn(String medn) {
        this.medn = medn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }


    public String getEquipmenttypedn() {
        return equipmenttypedn;
    }

    public void setEquipmenttypedn(String equipmenttypedn) {
        this.equipmenttypedn = equipmenttypedn;
    }
}
