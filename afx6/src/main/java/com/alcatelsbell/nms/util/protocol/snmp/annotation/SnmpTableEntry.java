package com.alcatelsbell.nms.util.protocol.snmp.annotation;

import java.io.Serializable;

/**
 * Author: Ronnie.Chen
 * Date: 14-9-12
 * Time: 上午10:18
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SnmpTableEntry implements Serializable {
    private String index;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
