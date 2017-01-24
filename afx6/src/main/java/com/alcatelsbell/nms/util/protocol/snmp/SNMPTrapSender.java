package com.alcatelsbell.nms.util.protocol.snmp;

/**
 * Author: Ronnie.Chen
 * Date: 13-8-21
 * Time: 下午3:53
 * rongrong.chen@alcatel-sbell.com.cn
 */

import org.apache.log4j.BasicConfigurator;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.asn1.BER;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.log.Log4jLogFactory;
import org.snmp4j.log.LogFactory;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.AbstractTransportMapping;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.Vector;

/**
 * @author ltj
 *
 */
public class SNMPTrapSender {
    private Vector _vbs = new Vector();
    private int _pduType = 0;
    private int _port = 0;
    private int _version = 0;
    private Address _address;
    private static Snmp _snmp = null;
    private OctetString _community = new OctetString("public");
    private Target _target;
    private int _retries = 2;
    private int _timeout = 1500;
    private TimeTicks _sysUpTime = new TimeTicks(0);
    private OID _trapOID = new OID("1.3.6.1.4.1.2789.2005");
    public SNMPTrapSender(String host, Vector vbs){
        _version = SnmpConstants.version2c;
        _pduType = PDU.TRAP;
        _port=162;
        this._vbs = vbs;
        _address = new UdpAddress(host+"/"+_port);
        checkTrapVariables(_vbs);

        LogFactory.setLogFactory(new Log4jLogFactory());
        BER.setCheckSequenceLength(false);
    }
    public SNMPTrapSender(String host, String varbind){
        _version = SnmpConstants.version2c;
        _pduType = PDU.TRAP;
        _port=162;
        this._vbs = getVariableBinding(varbind);
        _address = new UdpAddress(host+"/"+_port);
        checkTrapVariables(_vbs);

        LogFactory.setLogFactory(new Log4jLogFactory());
        BER.setCheckSequenceLength(false);
    }

    static {
        if (System.getProperty("log4j.configuration") == null) {
            BasicConfigurator.configure();
        }
    }
    private void sendAndProcessResponse() {
        try {
            PDU response = this.send();

            System.out.println(PDU.getTypeString(getPduType()) +
                    " sent successfully");

        } catch (IOException ex) {
            System.err.println("Error while trying to send request: " +
                    ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void sendTrap(){
        sendAndProcessResponse();
    }
    private PDU send() throws IOException {
        _snmp = createSnmpSession();
        this._target = createTarget();
        _target.setVersion(_version);
        _target.setAddress(_address);
        _target.setRetries(_retries);
        _target.setTimeout(_timeout);
        _snmp.listen();

        PDU request = createPDU(_target);
        for (int i=0; i<_vbs.size(); i++) {
            request.add((VariableBinding)_vbs.get(i));
        }

        PDU response = null;
        ResponseEvent responseEvent;
        long startTime = System.currentTimeMillis();
        responseEvent = _snmp.send(request, _target);
        if (responseEvent != null) {
            response = responseEvent.getResponse();
            System.out.println("Received response after "+
                    (System.currentTimeMillis()-startTime)+" millis");
        }
        _snmp.close();
        return response;
    }
    private Snmp createSnmpSession() throws IOException {
        AbstractTransportMapping transport;
        if (_address instanceof TcpAddress) {
            transport = new DefaultTcpTransportMapping();
        } else {
            transport = new DefaultUdpTransportMapping();
        }
        // Could save some CPU cycles:
        // transport.setAsyncMsgProcessingSupported(false);
        Snmp snmp =  new Snmp(transport);

        return snmp;
    }
    private Target createTarget() {

        CommunityTarget target = new CommunityTarget();
        target.setCommunity(_community);
        return target;

    }

    public PDU createPDU(Target target) {
        PDU request = new PDU();

        request.setType(_pduType);
        return request;
    }

    public int getPduType() {
        return _pduType;
    }
    private void checkTrapVariables(Vector vbs) {
        if ((_pduType == PDU.INFORM) || (_pduType == PDU.TRAP)) {
            if ((vbs.size() == 0) ||
                    ((vbs.size() > 1) &&
                            (!((VariableBinding) vbs.get(0)).getOid().equals(SnmpConstants.
                                    sysUpTime)))) {
                vbs.add(0, new VariableBinding(SnmpConstants.sysUpTime, _sysUpTime));
            }
            if ((vbs.size() == 1) || ((vbs.size() > 2) &&
                    (!((VariableBinding) vbs.get(1)).getOid().equals(SnmpConstants.
                            snmpTrapOID)))) {
                vbs.add(1, new VariableBinding(SnmpConstants.snmpTrapOID, _trapOID));
            }
        }
    }
    private Vector getVariableBinding(String varbind) {
        Vector v = new Vector(varbind.length());
        String oid = null;
        char type = 'i';
        String value = null;
        int equal = varbind.indexOf("={");
        if (equal > 0) {
            oid = varbind.substring(0, equal);
            type = varbind.charAt(equal+2);
            value = varbind.substring(varbind.indexOf('}')+1);
        }else{
            v.add(new VariableBinding(new OID(varbind)));
            return v;
        }

        VariableBinding vb = new VariableBinding(new OID(oid));
        if (value != null) {
            Variable variable;
            switch (type) {
                case 'i':
                    variable = new Integer32(Integer.parseInt(value));
                    break;
                case 'u':
                    variable = new UnsignedInteger32(Long.parseLong(value));
                    break;
                case 's':
                    variable = new OctetString(value);
                    break;
                case 'x':
                    variable = OctetString.fromString(value, ':', 16);
                    break;
                case 'd':
                    variable = OctetString.fromString(value, '.', 10);
                    break;
                case 'b':
                    variable = OctetString.fromString(value, ' ', 2);
                    break;
                case 'n':
                    variable = new Null();
                    break;
                case 'o':
                    variable = new OID(value);
                    break;
                case 't':
                    variable = new TimeTicks(Long.parseLong(value));
                    break;
                case 'a':
                    variable = new IpAddress(value);
                    break;
                default:
                    throw new IllegalArgumentException("Variable type "+type+
                            " not supported");
            }
            vb.setVariable(variable);
        }
        v.add(vb);
        System.out.println( org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(v,
                org.apache.commons.lang.builder.ToStringStyle.MULTI_LINE_STYLE));
        return v;
    }

    public static void main(String[] args) {
        SNMPTrapSender trap = new SNMPTrapSender("192.168.1.100",
                "1.3.6.1.4.1.2789.2005.1={s}WWW Server Has Been Restarted");
        trap.sendAndProcessResponse();
    }
}