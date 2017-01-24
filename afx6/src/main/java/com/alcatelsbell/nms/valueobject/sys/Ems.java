package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.common.annotation.DicGroupMapping;
import com.alcatelsbell.nms.common.crud.annotation.BField;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.CdcpDictionary;
import com.alcatelsbell.nms.valueobject.odn.dictionarys.OdnDictionary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.Date;

/**
 * User: Ronnie
 * Date: 11-11-15
 * Time: 下午1:07
 * @see
 */
@Entity
@Table(name = "S_EMS")
public class Ems extends BObject {
    private static final long serialVersionUID = -1l;
	@BField(description = "名称",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String name;
	@BField(description = "用户标签",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String userlabel;
    private String sysNodeDn;
    
    @DicGroupMapping(groupName = "EMSTYPE", definitionClass = CdcpDictionary.class)
    @BField(description = "类型",searchType = BField.SearchType.HIDE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String type;
    private String owner;
    @BField(description = "本地EMS名称",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String nativeemsname;
    @Column(length = 1024)
    private String additionalinfo;
    private String emsversion;
    /*@BField(description = "厂商名称",
            createType = BField.CreateType.REQUIRED,
            editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.sys.Vendor",
            dnReferenceTransietField = "vendorName",
            dnReferenceEntityField = "name")*/
    @DicGroupMapping(groupName = "EMSVENDOR", definitionClass = CdcpDictionary.class)
    @BField(description = "厂商名称",createType = BField.CreateType.REQUIRED,
            editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.NULLABLE)
    private String vendordn;

    @Transient
    private String vendorName;
    @DicGroupMapping(groupName = "EMSISMONITORED", definitionClass = CdcpDictionary.class)
    @BField(description = "管理状态",searchType = BField.SearchType.HIDE,createType = BField.CreateType.HIDE,editType = BField.EditType.HIDE,viewType = BField.ViewType.SHOW)
    private Integer isMonitored;
    @DicGroupMapping(groupName = "EMSISSYNCOK", definitionClass = CdcpDictionary.class)
    @BField(description = "是否同步完成",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private Integer synIsOk;
    @BField(description = "中文全名",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String full_cn_name;
    @BField(description = "所属区域",
            createType = BField.CreateType.REQUIRED,
            editType = BField.EditType.REQUIRED,
            searchType = BField.SearchType.NULLABLE,
            dnReferenceEntityName = "com.alcatelsbell.nms.valueobject.domain.RRegion",
            dnReferenceTransietField = "regionName",dnField="id",
            dnReferenceEntityField = "regionname")
    private Long regionId;
    @Transient
    private String regionName;
	@BField(description = "同步结束时间",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private Date synEndTime;
	 @BField(description = "重连接次数",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private Integer reconnectCountTimes;
	@BField(description = "下次重连接时间",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private Date reconnectNextTime;
	@DicGroupMapping(groupName = "EMSSTATUS", definitionClass = CdcpDictionary.class)
	@BField(description = "状态",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private Integer status;
	@BField(description = "控制名称",searchType = BField.SearchType.NULLABLE,createType = BField.CreateType.NULLABLE,editType = BField.EditType.NULLABLE,viewType = BField.ViewType.SHOW)
    private String controlName;

    @DicGroupMapping(groupName = "PROTOCALTYPE", definitionClass = CdcpDictionary.class)
    @BField(description = "传输协议类型")
    private Integer protocalType;

    @BField(description = "接口异常编码")
    private String exceptionCode;

    @BField(description = "接口异常描述")
    private String exceptionDetail;

    @BField(description = "备注")
    private String comments;

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getProtocalType() {
        return protocalType;
    }

    public void setProtocalType(Integer protocalType) {
        this.protocalType = protocalType;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

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

    public String getSysNodeDn() {
        return sysNodeDn;
    }

    public void setSysNodeDn(String sysNodeDn) {
        this.sysNodeDn = sysNodeDn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getNativeemsname() {
        return nativeemsname;
    }

    public void setNativeemsname(String nativeemsname) {
        this.nativeemsname = nativeemsname;
    }

    public String getAdditionalinfo() {
        return additionalinfo;
    }

    public void setAdditionalinfo(String additionalinfo) {
        this.additionalinfo = additionalinfo;
    }

    public String getEmsversion() {
        return emsversion;
    }

    public void setEmsversion(String emsversion) {
        this.emsversion = emsversion;
    }

    public String getVendordn() {
        return vendordn;
    }

    public void setVendordn(String vendordn) {
        this.vendordn = vendordn;
    }



    public Integer getMonitored() {
        return isMonitored;
    }

    public void setMonitored(Integer monitored) {
        isMonitored = monitored;
    }

    public Integer getIsMonitored() {
        return isMonitored;
    }

    public void setIsMonitored(Integer monitored) {
        isMonitored = monitored;
    }


    public Integer getSynIsOk() {
        return synIsOk;
    }

    public void setSynIsOk(Integer synIsOk) {
        this.synIsOk = synIsOk;
    }

    public String getFull_cn_name() {
        return full_cn_name;
    }

    public void setFull_cn_name(String full_cn_name) {
        this.full_cn_name = full_cn_name;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Date getSynEndTime() {
        return synEndTime;
    }

    public void setSynEndTime(Date synEndTime) {
        this.synEndTime = synEndTime;
    }

    public Integer getReconnectCountTimes() {
        return reconnectCountTimes;
    }

    public void setReconnectCountTimes(Integer reconnectCountTimes) {
        this.reconnectCountTimes = reconnectCountTimes;
    }

    public Date getReconnectNextTime() {
        return reconnectNextTime;
    }

    public void setReconnectNextTime(Date reconnectNextTime) {
        this.reconnectNextTime = reconnectNextTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public void copyShallow(BObject bObject) {
        Ems b = (Ems) bObject;
        this.id = b.getId();
        this.name = b.getName();
        this.type = b.getType();
        this.userlabel = b.getUserlabel();
        this.owner = b.getOwner();
        this.nativeemsname = b.getNativeemsname();
        this.additionalinfo = b.getAdditionalinfo();
        this.emsversion = b.getEmsversion();
        this.dn = b.getDn();
        this.controlName = b.getControlName();
        this.createDate = b.getCreateDate();
        this.fromWhere = b.getFromWhere();
        this.full_cn_name = b.getFull_cn_name();
        this.isMonitored = b.getMonitored();
        this.oid = b.getOid();
        this.reconnectCountTimes = b.getReconnectCountTimes();
        this.reconnectNextTime = b.getReconnectNextTime();
        this.regionId = b.getRegionId();
        this.status = b.getStatus();
        this.synEndTime = b.getSynEndTime();
        this.synIsOk = b.getSynIsOk();
        this.sysNodeDn = b.getSysNodeDn();
        this.updateDate = b.getUpdateDate();
        this.userObject = b.getUserObject();
        this.vendordn = b.getVendordn();
      }
}

