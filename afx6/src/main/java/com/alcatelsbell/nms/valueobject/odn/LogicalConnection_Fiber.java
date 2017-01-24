package com.alcatelsbell.nms.valueobject.odn;

import javax.persistence.Entity;

/**
 * Author: Ronnie.Chen
 * Date: 12-9-28
 * Time: 下午1:17
 * rongrong.chen@alcatel-sbell.com.cn
 */
@Entity
public class LogicalConnection_Fiber extends AbstractOdn {
    private String logicalConnectionDn;
    private String fiberDn;
    private Integer fiberSequence;

    public String getLogicalConnectionDn() {
        return logicalConnectionDn;
    }

    public void setLogicalConnectionDn(String logicalConnectionDn) {
        this.logicalConnectionDn = logicalConnectionDn;
    }

    public String getFiberDn() {
        return fiberDn;
    }

    public void setFiberDn(String fiberDn) {
        this.fiberDn = fiberDn;
    }

    public Integer getFiberSequence() {
        return fiberSequence;
    }

    public void setFiberSequence(Integer fiberSequence) {
        this.fiberSequence = fiberSequence;
    }
}
