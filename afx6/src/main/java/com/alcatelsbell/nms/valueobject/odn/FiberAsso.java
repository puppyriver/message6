package com.alcatelsbell.nms.valueobject.odn;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.Entity;

/**
 * Author: Ronnie.Chen
 * Date: 12-10-8
 * Time: 下午2:32
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
public class FiberAsso extends AbstractOdn{
    private String fiber1Dn;
    private String fiber2Dn;
    private String opticalConnectorDn;

    public String getFiber1Dn() {
        return fiber1Dn;
    }

    public void setFiber1Dn(String fiber1Dn) {
        this.fiber1Dn = fiber1Dn;
    }

    public String getFiber2Dn() {
        return fiber2Dn;
    }

    public void setFiber2Dn(String fiber2Dn) {
        this.fiber2Dn = fiber2Dn;
    }

    public String getOpticalConnectorDn() {
        return opticalConnectorDn;
    }

    public void setOpticalConnectorDn(String opticalConnectorDn) {
        this.opticalConnectorDn = opticalConnectorDn;
    }
}
