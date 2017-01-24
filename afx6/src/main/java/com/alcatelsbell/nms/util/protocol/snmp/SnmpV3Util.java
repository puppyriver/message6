package com.alcatelsbell.nms.util.protocol.snmp;


import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.CounterSupport;
import org.snmp4j.mp.DefaultCounterListener;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.*;
import org.snmp4j.smi.*;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.*;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: </p>
 *
 * @version 1.0
 */
public class SnmpV3Util implements SnmpUtil {

    private String strAddress = "127.0.0.1";
    private int port = 161;

    private UserTarget usertarget;
    private Snmp snmp;
    private int _timeout = 10000;
    private int _retries = 1;
    private int maxRepetitions = 4;
    private OID _authProtocol;
    private OID _privProtocol;
    private OctetString _privPassphrase;
    private OctetString _authPassphrase;
    private OctetString _securityName = new OctetString();
    private OctetString _contextEngineID;
    private OctetString _contextName;
    public String getAddress() {
        return strAddress;
    }

 
    public SnmpV3Util(String _strAddress, String user, String authProtocol,
		  String authPasshrase, String privProtocol, String privPassphrase,String contextName,int _retries) throws IOException {
        this.strAddress = _strAddress;
	_securityName = new OctetString(user);
	initAuth(authProtocol,authPasshrase);
	initPriv(privProtocol,privPassphrase);
	if(_retries>1){
        	this._retries=_retries;
        }
        init();
    }
  private void initPriv(String privProtocol, String privPassphrase){
      if(privProtocol==null||privPassphrase==null){
	   return ;
      }
      if (privProtocol.equals("DES")) {
		_privProtocol = PrivDES.ID;
		_privPassphrase = new OctetString(privPassphrase);
	} else if ((privProtocol.equals("AES128")) || (privProtocol.equals("AES"))) {
		_privProtocol = PrivAES128.ID;
		_privPassphrase = new OctetString(privPassphrase);
	} else if (privProtocol.equals("AES192")) {
		_privProtocol = PrivAES192.ID;
		_privPassphrase = new OctetString(privPassphrase);
	} else if (privProtocol.equals("AES256")) {
		_privProtocol = PrivAES256.ID;
		_privPassphrase = new OctetString(privPassphrase);
	}
  }
   private void initAuth(String authProtocol,
		  String authPasshrase){
       if(authProtocol==null||authPasshrase==null){
	   return ;
       }
       if (authProtocol.equals("MD5")) {
		_authProtocol = AuthMD5.ID;
		 _authPassphrase = new OctetString(authPasshrase);
	} else if (authProtocol.equals("SHA")) {
		_authProtocol = AuthSHA.ID;
		 _authPassphrase = new OctetString(authPasshrase);
	}
      
   }
    public PDU createPDU(){
	PDU pdu = new ScopedPDU();
	ScopedPDU scopedPDU = (ScopedPDU)pdu;
	if (_contextEngineID != null) {
		scopedPDU.setContextEngineID(_contextEngineID);
	}
	if (_contextName != null) {
		scopedPDU.setContextName(_contextName);
	}
	return pdu;
	
    }
    
	/** {@inheritDoc}*/
	public boolean isBaseOidHasValue(String baseOidStr)  {
	//	PDU pdu =createPDU();
		OID oid = new OID(baseOidStr);
		try {
			TreeUtils treeUtils = new TreeUtils(snmp, new DefaultPDUFactory());
			List events = treeUtils.getSubtree(usertarget, oid);
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
	CounterSupport.getInstance().addCounterListener(new DefaultCounterListener());
        String address = strAddress + "/" + port;
        Address targetaddress = new UdpAddress(address);
        TransportMapping transport = new DefaultUdpTransportMapping();
        usertarget = new UserTarget();
        usertarget.setVersion(SnmpConstants.version3);
        usertarget.setAddress(targetaddress);
        usertarget.setRetries(_retries);
        usertarget.setTimeout(_timeout);
	if (_authPassphrase != null) {
		if (_privPassphrase != null) {
		    usertarget.setSecurityLevel(SecurityLevel.AUTH_PRIV);
		} else {
		    usertarget.setSecurityLevel(SecurityLevel.AUTH_NOPRIV);
		}
	} else {
	    usertarget.setSecurityLevel(SecurityLevel.NOAUTH_NOPRIV);
	}
	usertarget.setSecurityName(_securityName);
	snmp = new Snmp(transport);
	USM usm = new USM(SecurityProtocols.getInstance(),
			new OctetString(MPv3.createLocalEngineID()), 0);
	SecurityModels.getInstance().addSecurityModel(usm);
	addUsmUser(snmp);
	snmp.listen();
    }
	
    
    private void addUsmUser(Snmp snmp) {
		snmp.getUSM().addUser(_securityName, new UsmUser(_securityName,
			_authProtocol,
			_authPassphrase,
			_privProtocol,
			_privPassphrase));
	}
    /** {@inheritDoc}*/
    public int snmpGetInt(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID);
        return vb.getVariable().toInt();
    }

    /** {@inheritDoc}*/
    public long snmpGetLong(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID); //caution when noSuchInstance is returned get 129 !!!
        return vb.getVariable().toLong();
    }

    /** {@inheritDoc}*/
    public String snmpGetString(String strOID) throws IOException {
        VariableBinding vb = snmpGet(strOID);
        return vb.getVariable().toString();
    }

   
    /** {@inheritDoc}*/
    public VariableBinding snmpGet(String strOID) throws IOException {
        VariableBinding result = null;
        PDU pdu = createPDU();
        ResponseEvent response;

        pdu.add(new VariableBinding(new OID(strOID)));
        pdu.setType(PDU.GET);

        response = snmp.get(pdu, usertarget);
        if (response != null) {
            if (response.getResponse() == null) {
                throw new IOException("Snmp failed connect to "+this.getAddress());
            }
            if (response.getResponse().getErrorStatus() == PDU.noError||response.getResponse().getErrorStatusText().
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

    @Override
    public void snmpSet(String strOID, String value) throws IOException {
        throw new IOException("not implemented");
    }


    public List<VariableBinding> snmpGet(List<String> oids) throws IOException{
	List<VariableBinding> result=null;
	  PDU pdu = createPDU();
	  ResponseEvent response;
	   for (String strOID : oids) {
	            pdu.add(new VariableBinding(new OID(strOID)));
	            pdu.setType(PDU.GET);
	        }
	   response = snmp.get(pdu, usertarget);
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
    public List snmpGetTable(OID[] columnOIDs, OID lowerBoundIndex, OID upperBoundIndex) throws IOException {
        List result = new LinkedList();
        TableUtils tableUtils = new TableUtils(this.snmp, new DefaultPDUFactory(PDU.GETBULK));
        tableUtils.setMaxNumRowsPerPDU(maxRepetitions);
	OID[] columns=new OID[1];
   	OID root = getRootOid(columnOIDs);
   	if(root==null){
   	    return result;
   	}
   	columns[0]=root;
        result = tableUtils.getTable(this.usertarget, columns, lowerBoundIndex, upperBoundIndex);
        
        return snmp4jtransfer(columnOIDs,result,root);
    }

    private OID getRootOid(OID[] columnOIDs){
	
	OID first=columnOIDs[0];
	int[] values=getRoot(first.getValue());
	if(values==null){
	    return null;
	}
	OID column=new OID(values);
	return column;
    }
    
    private int[] getRoot(int[] values){
	  int[] newValue=null;
	  if (values.length == 0) {
	      return newValue;
	    }
	  newValue = new int[values.length-2];
	  System.arraycopy(values, 0, newValue, 0, values.length-2);
	  return newValue;
    }
    
    /*
     * transfer snmpv3 value into snmpv2 model in order to keep old function works!!
     * 
     */
    
    
  
    private List snmp4jtransfer(OID[] columnOIDs,List result,OID root){
	 List<TableEvent> tresult = new LinkedList<TableEvent>();
	 OID _index= getIndex(root);
	 int _size=_index.size();
	 Map<OID,List<VariableBinding>> m=new LinkedHashMap<OID,List<VariableBinding>>();
	    for (int i = 0; i < result.size(); i++) {
		 TableEvent event = (TableEvent)result.get(i);
		
		 VariableBinding vb=   event.getColumns()[0];
		 OID r_index= vb.getOid();
		// use colums to split r_index
		OID[] para= getInterlIndex(r_index,_size);
		OID aindex=para[0];
		OID column=para[1];
		List<VariableBinding> nresult=m.get(aindex);
		if(nresult==null){
		    nresult=  new LinkedList<VariableBinding>();
		    if(isInColums(columnOIDs,column)){
			nresult.add(vb);
		    }
		    m.put(aindex, nresult)  ;
		} else{
		    if(isInColums(columnOIDs,column)){
			nresult.add(vb);
		    }
		}		
	    }
	    
	Set<Entry<OID,List<VariableBinding>>> entrys= m.entrySet();
	for (Entry<OID, List<VariableBinding>> entry : entrys) {
	    List<VariableBinding> vblist=entry.getValue();
	    VariableBinding[] vbs=new VariableBinding[vblist.size()];
	    vbs=vblist.toArray(vbs);
	    tresult.add(new TableEvent(new Object(),null,entry.getKey(),vbs));
	}
	 return tresult;
   }
    
    private OID[] getInterlIndex(OID r_index,int r_size){
	 OID[] para=new OID[2];
	  int[] value=r_index.getValue();
	  int[] newValue = new int[r_size];
	  int indexsize=value.length-r_size;
	  int[] indexvalue=new int[indexsize];
	  System.arraycopy(value, 0, newValue, 0,r_size);
	  System.arraycopy(value, r_size, indexvalue, 0,indexsize);
	  para[0]=new OID(indexvalue);
	  para[1]=new OID(newValue);
	return para;
    }
    
//    private List snmp4jtransfer(OID[] columnOIDs,List result,OID root){
//	 List<TableEvent> tresult = new LinkedList<TableEvent>();
//	 OID _index= getIndex(root);
//	 Set<OID> indexs=new HashSet<OID>();
//	 Map<OID,List<VariableBinding>> m=new LinkedHashMap<OID,List<VariableBinding>>();
//	    for (int i = 0; i < result.size(); i++) {
//		 TableEvent event = (TableEvent)result.get(i);
//		
//		 VariableBinding vb=   event.getColumns()[0];
//		 OID r_index= vb.getOid();
//		
//		if( r_index.startsWith(_index)){
//		    OID index=new OID( vb.getVariable().toString());
//		    LinkedList<VariableBinding> newresult = new LinkedList<VariableBinding>();
//		    if(isInColums(columnOIDs,r_index)){
//			newresult.add(vb);
//		    }
//		    indexs.add(index);
//		    m.put(index, newresult)  ;
//		}else{
//		    if(isInColums(columnOIDs,r_index)){
//			for (OID index : indexs) {
//				if(endwith(r_index,index)){
//				    m.get(index).add(vb);
//				}
//				
//			    }
//		    }
//		    
//		}
//		
//	    }
//	    
//	Set<Entry<OID,List<VariableBinding>>> entrys= m.entrySet();
//	for (Entry<OID, List<VariableBinding>> entry : entrys) {
//	    List<VariableBinding> vblist=entry.getValue();
//	    VariableBinding[] vbs=new VariableBinding[vblist.size()];
//	    vbs=vblist.toArray(vbs);
//	    tresult.add(new TableEvent(new Object(),null,entry.getKey(),vbs));
//	}
//	 return tresult;
//    }
//    
    private boolean isInColums(OID[] columnOIDs,OID r_index){
	for (OID oid : columnOIDs) {
	 if( r_index.equals(oid)){
	     return true;
	 }
	}
	
	return false;
	
    }
    private boolean endwith(OID r_index,OID index){
	int size=index.size();
	if(r_index.size()<size){
	    return false;
	}
	int result=r_index.rightMostCompare(size, index);
	if(result==0){
	    return true;
	}
	return false;
    }
    
    public final OID getIndex(OID root) {
	    int[] value=root.getValue();
	    int[] newValue = new int[value.length+2];
	    System.arraycopy(value, 0, newValue, 0, value.length);
	    newValue[value.length] = 1;
	    newValue[value.length+1] = 1;
	    return new OID(newValue);
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
	SnmpV3Util snmpV3Util =new SnmpV3Util("172.16.7.91","local","MD5","fijiinms","DES","12345678",null,1);
	//snmpV3Util =new SnmpV3Util("172.16.7.91","local","MD5","fijiinms",null,null,null,1);
	System.out.println( snmpV3Util.snmpGetString(".1.3.6.1.2.1.1.1.0"));
//	snmpV3Util.close();
	//SnmpV3Util snmpV3Util =new SnmpV3Util("192.168.59.132","litongjie","MD5","setup_passphrase",null,null,null,1);
	SnmpV3Util snmpV3Util1 =new SnmpV3Util("172.16.7.91","local","MD5","fijiinms","DES","12345678",null,1);
	System.out.println( snmpV3Util1.snmpGetLong(".1.3.6.1.2.1.1.7.0"));
	System.out.println( snmpV3Util.snmpGetLong(".1.3.6.1.2.1.1.7.0"));
	snmpV3Util.close();
//	SnmpV3Util snmpV3Util2 =new SnmpV3Util("192.168.59.132","litongjie","MD5","litongjie",null,null,null,1);
//	//SnmpV3Util snmpV3Util =new SnmpV3Util("172.16.119.111","local","MD5","wangshim",null,null,null,1);
//
//	System.out.println( snmpV3Util2.snmpGetString(".1.3.6.1.2.1.1.1.0"));
//	snmpV3Util2.close();
    }

}
