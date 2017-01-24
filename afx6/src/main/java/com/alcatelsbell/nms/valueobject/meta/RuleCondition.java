package com.alcatelsbell.nms.valueobject.meta;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.*;
import java.util.Date;

/**
 * User: Ronnie
 * Date: 12-1-31
 * Time: 上午9:45
 */
@Entity
@Table(name = "META_RULECONDITION")
public class RuleCondition extends BObject{
    public static final int TYPE_EXPRESSION = 0;
    public static final int TYPE_JAVASCRIPT = 1;
    public static final int CONDITIONTYPE_AND = 0;
    public static final int CONDITIONTYPE_OR = 1;

    private Integer type;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal (TemporalType.TIMESTAMP)
    private Date endTime;

    private String ruleDn;
    private Integer conditionType;



    @Column(length = 2048)
    private String value1;
    private String value2;
    private String value3;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getConditionType() {
        return conditionType;
    }

    public void setConditionType(Integer conditionType) {
        this.conditionType = conditionType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRuleDn() {
        return ruleDn;
    }

    public void setRuleDn(String ruleDn) {
        this.ruleDn = ruleDn;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
