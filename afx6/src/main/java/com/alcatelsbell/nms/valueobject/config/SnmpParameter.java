package com.alcatelsbell.nms.valueobject.config;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Ronnie.Chen
 * Date: 12-8-27
 * Time: 下午1:59
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
@Table(name = "C_SNMPPARAMETER")
public class SnmpParameter extends BObject {
    private Integer snmpVersion;
    private String readCommunity;
    private String writeCommunity;
    private Integer port;
    private String ipAddress;
    private String v3UserName;
    private String v3ContextName;
    private String v3AuthProtocal;
    private String v3AuthPassword;
    private String v3PrivacyProtocal;
    private String v3PrivacyPassword;
    private String meDn;
    private Integer intervalSec;
    private String sysNodeDn;


    public String getSysNodeDn() {
        return sysNodeDn;
    }

    public void setSysNodeDn(String sysNodeDn) {
        this.sysNodeDn = sysNodeDn;
    }

    public Integer getIntervalSec() {
        return intervalSec;
    }

    public void setIntervalSec(Integer intervalSec) {
        this.intervalSec = intervalSec;
    }

    public Integer getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(Integer snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public String getReadCommunity() {
        return readCommunity;
    }

    public void setReadCommunity(String readCommunity) {
        this.readCommunity = readCommunity;
    }

    public String getWriteCommunity() {
        return writeCommunity;
    }

    public void setWriteCommunity(String writeCommunity) {
        this.writeCommunity = writeCommunity;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getV3UserName() {
        return v3UserName;
    }

    public void setV3UserName(String v3UserName) {
        this.v3UserName = v3UserName;
    }

    public String getV3ContextName() {
        return v3ContextName;
    }

    public void setV3ContextName(String v3ContextName) {
        this.v3ContextName = v3ContextName;
    }

    public String getV3AuthProtocal() {
        return v3AuthProtocal;
    }

    public void setV3AuthProtocal(String v3AuthProtocal) {
        this.v3AuthProtocal = v3AuthProtocal;
    }

    public String getV3AuthPassword() {
        return v3AuthPassword;
    }

    public void setV3AuthPassword(String v3AuthPassword) {
        this.v3AuthPassword = v3AuthPassword;
    }

    public String getV3PrivacyProtocal() {
        return v3PrivacyProtocal;
    }

    public void setV3PrivacyProtocal(String v3PrivacyProtocal) {
        this.v3PrivacyProtocal = v3PrivacyProtocal;
    }

    public String getV3PrivacyPassword() {
        return v3PrivacyPassword;
    }

    public void setV3PrivacyPassword(String v3PrivacyPassword) {
        this.v3PrivacyPassword = v3PrivacyPassword;
    }

    public String getMeDn() {
        return meDn;
    }

    public void setMeDn(String meDn) {
        this.meDn = meDn;
    }
}
