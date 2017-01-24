package com.alcatelsbell.nms.valueobject.domain;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie.Chen
 * Date: 11-5-28
 * Time:
 */
@Entity
@Table(name = "R_MEGROUPASSIGN")
public class MeGroupAssign extends BObject{
    private String medn;
    private String megroupdn;

    public String getMedn() {
        return medn;
    }

    public void setMedn(String medn) {
        this.medn = medn;
    }

    public String getMegroupdn() {
        return megroupdn;
    }

    public void setMegroupdn(String megroupdn) {
        this.megroupdn = megroupdn;
    }
}
