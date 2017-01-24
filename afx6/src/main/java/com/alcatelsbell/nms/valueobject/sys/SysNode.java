package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User: Ronnie
 * Date: 11-11-14
 * Time: 下午4:49
 */
@Entity
@Table(name = "S_SYSNODE")
public class SysNode extends BObject{
    private String ipaddress;
    private String name;
    private Integer httpport;
    private Integer jmxport;
    private Integer rmiport;
    private String manageurl;
    private String status;
    private Integer state;

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHttpport() {
        return httpport;
    }

    public void setHttpport(Integer httpport) {
        this.httpport = httpport;
    }

    public Integer getJmxport() {
        return jmxport;
    }

    public void setJmxport(Integer jmxport) {
        this.jmxport = jmxport;
    }

    public Integer getRmiport() {
        return rmiport;
    }

    public void setRmiport(Integer rmiport) {
        this.rmiport = rmiport;
    }

    public String getManageurl() {
        return manageurl;
    }

    public void setManageurl(String manageurl) {
        this.manageurl = manageurl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
