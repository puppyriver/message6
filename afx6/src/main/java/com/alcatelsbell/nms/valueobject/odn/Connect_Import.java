package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Author: Ronnie.Chen
 * Date: 12-10-18
 * Time: 下午8:49
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
@Table(name = "CONNECT_IMPORT")
public class Connect_Import extends BObject {
    private String aendInfo;
    private String zendInfo;
    private String adeviceInfo;
    private String zdeviceInfo;
    private String type;

    @Temporal (TemporalType.TIMESTAMP)
    private Date importTime;

    private Integer status;
    private String xlsFileName;
    private Integer sequence;
    private String batchVersion;

    public String getAendInfo() {
        return aendInfo;
    }

    public void setAendInfo(String aendInfo) {
        this.aendInfo = aendInfo;
    }

    public String getZendInfo() {
        return zendInfo;
    }

    public void setZendInfo(String zendInfo) {
        this.zendInfo = zendInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getXlsFileName() {
        return xlsFileName;
    }

    public void setXlsFileName(String xlsFileName) {
        this.xlsFileName = xlsFileName;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getBatchVersion() {
        return batchVersion;
    }

    public void setBatchVersion(String batchVersion) {
        this.batchVersion = batchVersion;
    }

    public String getAdeviceInfo() {
        return adeviceInfo;
    }

    public void setAdeviceInfo(String adeviceInfo) {
        this.adeviceInfo = adeviceInfo;
    }

    public String getZdeviceInfo() {
        return zdeviceInfo;
    }

    public void setZdeviceInfo(String zdeviceInfo) {
        this.zdeviceInfo = zdeviceInfo;
    }
}
