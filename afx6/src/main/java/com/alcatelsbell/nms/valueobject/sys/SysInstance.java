package com.alcatelsbell.nms.valueobject.sys;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Author: Ronnie.Chen
 * Date: 12-7-29
 * Time: 下午8:52
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
@Table(name = "S_SYSINSTANCE")
public class SysInstance extends BObject{
    private String sysNodeDn;
    private String name;
    private String type;
     private Integer status;
    private String processKey;
    private String instanceVersion;

    public String getInstanceVersion() {
        return instanceVersion;
    }

    public void setInstanceVersion(String instanceVersion) {
        this.instanceVersion = instanceVersion;
    }

    public String getSysNodeDn() {
        return sysNodeDn;
    }

    public void setSysNodeDn(String sysNodeDn) {
        this.sysNodeDn = sysNodeDn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }
}
