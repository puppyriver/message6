package com.alcatelsbell.nms.valueobject.meta;

import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 12-1-6
 * Time: 下午12:55
 */

@Entity
@Table(name = "META_CONFIG")
public class MetaConfig extends BObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3517311438978090435L;
	/**
	 * 
	 */
	private String operator; //关联用户名，系统配置则为空
    private String permission;  //关联的权限
    private String type;    // 配置类型
    @BField(description = "名称",viewType=BField.ViewType.SHOW,editType = BField.EditType.REQUIRED,createType=BField.CreateType.REQUIRED,searchType = BField.SearchType.NULLABLE,sequence = 0)
    private String name;     //名称
    private String value;    //值
    private String value2;
    private Integer sequenceNo;
    private String description;
    private Integer valid;   //启用 = 1 ，不启用 = 0
    private Integer priority; // 优先级   越大优先级越高
    private Double weight;   //权重
    private String parentMetaConfigDn;


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public Integer getSequenceNo() {
        return sequenceNo;
    }

    public void setSequenceNo(Integer sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer on) {
        this.valid = on;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getParentMetaConfigDn() {
        return parentMetaConfigDn;
    }

    public void setParentMetaConfigDn(String parentMetaConfigDn) {
        this.parentMetaConfigDn = parentMetaConfigDn;
    }
}
