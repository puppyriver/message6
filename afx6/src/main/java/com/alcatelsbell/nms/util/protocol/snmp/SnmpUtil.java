/**
 *
 */

package com.alcatelsbell.nms.util.protocol.snmp;

import org.snmp4j.PDU;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import java.io.IOException;
import java.util.List;

/**
 * author litongjie
 */
public interface SnmpUtil {

    public  boolean isBaseOidHasValue(String baseOidStr);

    public  void init() throws IOException;

    public  int snmpGetInt(String strOID) throws IOException;

    public  long snmpGetLong(String strOID) throws IOException;

    public  String snmpGetString(String strOID) throws IOException;

    public  VariableBinding snmpGet(String strOID) throws IOException;

    public void snmpSet(String strOID, String value) throws IOException;
    public List<VariableBinding> snmpGet(List<String> oids) throws IOException;

    public  List snmpGetTable(OID[] columnOIDs, OID lowerBoundIndex,
                              OID upperBoundIndex) throws IOException;

    public  void close();
    public  String getAddress();
    public  PDU createPDU();
}