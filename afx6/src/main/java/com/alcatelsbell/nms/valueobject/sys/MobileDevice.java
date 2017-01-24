package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * User: Ronnie
 * Date: 12-6-15
 * Time: 下午4:05
 * 管理接入系统的移动设备，手机，平板等，用于鉴权等
 */

@Entity
@Table(name = "S_MOBILEDEVICE")
public class MobileDevice extends BObject {

    @BField(description = "MAC地址", searchType = BField.SearchType.NULLABLE)
    private String macAddress;

    @DicGroupMapping(groupName = "MOBILETYPE", definitionClass = SysDictionarys.class)
    @BField(description = "设备类型",searchType = BField.SearchType.NULLABLE)
    private int mobileType;

    @DicGroupMapping(groupName = "ACCOUNTSTATUS", definitionClass = SysDictionarys.class)
    @BField(description = "状态",searchType = BField.SearchType.NULLABLE)
    private int status;

    @BField(description = "手机号码", searchType = BField.SearchType.NULLABLE)
    private String phoneNumber;

    @BField(description = "序列号", searchType = BField.SearchType.NULLABLE)
    private String serialNumber;

    @BField(description = "备注", searchType = BField.SearchType.NULLABLE)
    private String comments;

    @BField(description = "关联用户",editType = BField.EditType.REQUIRED,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.Operator",
            dnReferenceTransietField = "operatorName",
            dnReferenceEntityField = "name")
    private String operatorDn;

    @Transient
    private String operatorName;


    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getMobileType() {
        return mobileType;
    }

    public void setMobileType(int mobileType) {
        this.mobileType = mobileType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOperatorDn() {
        return operatorDn;
    }

    public void setOperatorDn(String operatorDn) {
        this.operatorDn = operatorDn;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
