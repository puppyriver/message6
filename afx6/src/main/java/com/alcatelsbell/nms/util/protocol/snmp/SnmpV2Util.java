package com.alcatelsbell.nms.util.protocol.snmp;


import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SnmpV2Util implements SnmpUtil {
    private String community = "public";
    private String strAddress = "127.0.0.1";
    private int port = 161;

    private CommunityTarget comtarget;
    private CommunityTarget setTarget;
    private Snmp snmp;
    private int _timeout = 10000;
    private int _retries = 1;
    private int maxRepetitions = 4;
    private int version = SnmpConstants.version2c;
    public String getAddress() {
        return strAddress;
    }


    public SnmpV2Util(String _strAddress, String _community,int _retries) throws IOException {
        this.strAddress = _strAddress;
        if (_community != null && _community.trim().length() > 0) {
            this.community = _community;
        }
        if(_retries>1){
        	this._retries=_retries;
        }
        init();
    }

    public SnmpV2Util(String _strAddress, String _community,int _retries,int _version) throws IOException {
        this.strAddress = _strAddress;
        version = _version;
        if (_community != null && _community.trim().length() > 0) {
            this.community = _community;
        }
        if(_retries>1){
        	this._retries=_retries;
        }
        init();
    }


	/** {@inheritDoc}*/
	public boolean isBaseOidHasValue(String baseOidStr)  {
		PDU pdu = new PDU();
		OID oid = new OID(baseOidStr);
		try {
			TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
			List events = treeUtils.getSubtree(comtarget, oid);
			if (!events.isEmpty()&&events.size()>1) {
					return true;
				}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

    /** {@inheritDoc}*/
    public void init() throws IOException {
        OctetString osCommunity = new OctetString(this.community);
        String address = strAddress + "/" + port;
        Address targetaddress = new UdpAddress(address);
        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        snmp.listen();
        comtarget = new CommunityTarget();
        comtarget.setCommunity(osCommunity);
      //  comtarget.setVersion(SnmpConstants.version2c);
        comtarget.setVersion(version);
        comtarget.setAddress(targetaddress);
        comtarget.setRetries(_retries);
        comtarget.setTimeout(_timeout);

    }

    /** {@inheritDoc}*/
    public int snmpGetInt(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID);
        if (vb.getVariable() instanceof org.snmp4j.smi.Null) return -1;
        return vb.getVariable().toInt();
    }

    /** {@inheritDoc}*/
    public long snmpGetLong(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID);
        if (vb.getVariable() instanceof org.snmp4j.smi.Null) return -1;
        return vb.getVariable().toLong();
    }

    /** {@inheritDoc}*/
    public String snmpGetString(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID);
        return vb.getVariable().toString();
    }

    public void snmpSet(String strOID,String value) throws IOException {
        PDU pdu = createPDU();
        pdu.add(new VariableBinding(new OID(strOID), new OctetString(value)));
        pdu.setType(PDU.SET);
        if (setTarget == null) {
            sendPDU(pdu);
        }

    }

    /** {@inheritDoc}*/
    public VariableBinding snmpGet(String strOID) throws IOException {
        VariableBinding result = null;
        PDU pdu = createPDU();
        ResponseEvent response;

        pdu.add(new VariableBinding(new OID(strOID)));
        pdu.setType(PDU.GET);

        response = snmp.get(pdu, comtarget);
        if (response != null) {
            if (response.getResponse() == null) {
                throw new IOException("Snmp failed connect to "+this.getAddress());
            }
            if (response.getResponse().getErrorStatusText().
                equalsIgnoreCase("Success")) {
                PDU pduresponse = response.getResponse();
                result = (VariableBinding) pduresponse.getVariableBindings().
                         firstElement();
            }else{
            	System.err.println("response is "+response.getResponse().getErrorStatusText());
            	throw new IOException("response error is "+response.getResponse().getErrorStatusText());
            }
        } else {
            System.out.println("Feeling like a TimeOut occured ");
            throw new IOException("time out! Snmp failed connect to "+this.getAddress());
        }

        return result;
    }

    public ResponseEvent sendPDU(PDU pdu) throws IOException
    {
        if (setTarget == null) {
        // 设置 target
             setTarget = new CommunityTarget();
            String address = strAddress + "/" + port;
            Address targetaddress = new UdpAddress(address);
            setTarget.setCommunity(new OctetString(community));
            setTarget.setAddress(targetaddress);
            // 通信不成功时的重试次数
            setTarget.setRetries(2);
            // 超时时间
            setTarget.setTimeout(1500);
            setTarget.setVersion(SnmpConstants.version2c);
        }
        // 向Agent发送PDU，并返回Response
        return snmp.send(pdu, setTarget);
    }

    public List<VariableBinding> snmpGet(List<String> oids) throws IOException{
	List<VariableBinding> result=null;
	  PDU pdu = createPDU();
	  ResponseEvent response;
	   for (String strOID : oids) {
	            pdu.add(new VariableBinding(new OID(strOID)));
	            pdu.setType(PDU.GET);
	        }
	   response = snmp.get(pdu, comtarget);
	   if (response != null) {
	            if (response.getResponse() == null) {
	                throw new IOException("Snmp failed connect to "+this.getAddress());
	            }
	            if (response.getResponse().getErrorStatusText().
	                equalsIgnoreCase("Success")) {
	                PDU pduresponse = response.getResponse();
	                result=new ArrayList<VariableBinding>(pduresponse.getVariableBindings());
	            }else{
	            	System.err.println("response is "+response.getResponse().getErrorStatusText());
	            	throw new IOException("response error is "+response.getResponse().getErrorStatusText());
	            }
	        } else {
	            System.out.println("Feeling like a TimeOut occured ");
	            throw new IOException("time out! Snmp failed connect to "+this.getAddress());
	        }
	return result;
    }

    /** {@inheritDoc}*/
    public List snmpGet(Vector<String> strOIDs) throws IOException {
        Vector result = null;
        PDU pdu = createPDU();
        ResponseEvent response;

        for (String strOID : strOIDs) {
            pdu.add(new VariableBinding(new OID(strOID)));
            pdu.setType(PDU.GET);
        }
        response = snmp.get(pdu, comtarget);
        if (response != null) {
            if (response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
                PDU pduresponse = response.getResponse();
                result = pduresponse.getVariableBindings();
            }
        } else {
            System.out.println("Feeling like a TimeOut occured ");
        }

        return result;
    }

    /** {@inheritDoc}*/
    public List snmpGetTable(OID[] columnOIDs, OID lowerBoundIndex, OID upperBoundIndex) throws IOException {
        List result = null;
        TableUtils tableUtils = new TableUtils(this.snmp, new DefaultPDUFactory(PDU.GETBULK));
        tableUtils.setMaxNumRowsPerPDU(maxRepetitions);
        result = tableUtils.getTable(this.comtarget, columnOIDs, lowerBoundIndex, upperBoundIndex);
        return result;
    }


    /** {@inheritDoc}*/
    public void close()  {
        if (snmp == null) return;
        try {
            snmp.close();
            snmp = null;
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            snmp = null;
        }
    }
    public static void main(String[] args) throws IOException {
	SnmpUtil snmpUtil=new SnmpV2Util("127.0.0.1","public",1);
	long numbers =snmpUtil.snmpGetLong(".1.3.6.1.2.1.1.7.0");
	System.out.println(numbers);
    }

    /** {@inheritDoc}*/
    public PDU createPDU() {
	return  new PDU();
    }

}
