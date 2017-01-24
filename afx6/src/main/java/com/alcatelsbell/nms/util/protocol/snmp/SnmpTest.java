package com.alcatelsbell.nms.util.protocol.snmp;


//import com.sun.xml.internal.bind.v2.model.core.ElementInfo;

import org.snmp4j.mp.SnmpConstants;

/**
 * Author: Ronnie.Chen
 * Date: 12-10-21
 * Time: 下午5:51
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class SnmpTest {

    public static void main(String[] args) throws Exception {
        int version = SnmpConstants.version1;
        String ipaddress = "192.168.1.103";
        String community = "sunseaopmex";

        SnmpUtil util = new SnmpV2Util(ipaddress,community,1,version);

//        Object o = SnmpClient.getInstance().snmpGetObject(util, ElementInfo.class);
//        System.out.println(o);
    }
}
