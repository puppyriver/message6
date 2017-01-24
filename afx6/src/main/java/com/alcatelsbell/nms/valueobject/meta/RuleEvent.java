package com.alcatelsbell.nms.valueobject.meta;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-1-31
 * Time: 上午9:45
 */
@Entity
@Table(name = "META_RULEEVENT")
public class RuleEvent extends BObject{
    private String value;
    private String type;
    private String ruleDn;

    public String getRuleDn() {
        return ruleDn;
    }

    public void setRuleDn(String ruleDn) {
        this.ruleDn = ruleDn;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
