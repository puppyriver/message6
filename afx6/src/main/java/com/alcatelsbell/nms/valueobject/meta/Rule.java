package com.alcatelsbell.nms.valueobject.meta;


import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-1-31
 * Time: 上午9:44
 */
@Entity
@Table(name = "META_RULE")
public class Rule extends BObject
{
    public static final int CONDITIONTYPE_AND = 0;
    public static final int CONDITIONTYPE_OR = 1;

    public final static int VALID = 1;
    public final static int INVALID = 0;

    public final static int EXCCODE_POSITIVE = 1;
    public final static int EXCCODE_NEGATIVE = 0;

    private String userdn;

    @BField(description = "策略名称",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType = BField.SearchType.NULLABLE)
    private String name;

    private Integer category;
    private Integer type;
    private Integer conditionType;
    private Integer processType;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

  //  @DicGroupMapping(groupName = "RULEVALIED", definitionClass = NMSDictionary.class)
    @BField(description = "是否启用",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType = BField.SearchType.NULLABLE)
    private Integer isValid;

    private String comments;

    private String m_className;

    @DicGroupMapping(groupName = "RULE_EXE")
    @BField(description = "允许/屏蔽",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType = BField.SearchType.NULLABLE)
    private Integer excCode;

    @Transient
    private List<RuleCondition> ruleConditions = null;
    @Transient
    private List<RuleEvent> ruleEvents = null;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

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

    public Integer getProcessType() {
        return processType;
    }

    public void setProcessType(Integer processType) {
        this.processType = processType;
    }



    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getM_className() {
        return m_className;
    }

    public void setM_className(String m_className) {
        this.m_className = m_className;
    }

    public Integer getExcCode() {
        return excCode;
    }

    public void setExcCode(Integer excCode) {
        this.excCode = excCode;
    }

    public String getUserdn() {
        return userdn;
    }

    public void setUserdn(String userdn) {
        this.userdn = userdn;
    }

    public List<RuleCondition> getRuleConditions() {
        return ruleConditions;
    }

    public void setRuleConditions(List<RuleCondition> ruleConditions) {
        this.ruleConditions = ruleConditions;
    }

    public List<RuleEvent> getRuleEvents() {
        return ruleEvents;
    }

    public void setRuleEvents(List<RuleEvent> ruleEvents) {
        this.ruleEvents = ruleEvents;
    }
}
