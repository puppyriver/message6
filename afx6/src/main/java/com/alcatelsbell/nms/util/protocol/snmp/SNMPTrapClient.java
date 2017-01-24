package com.alcatelsbell.nms.util.protocol.snmp;


import com.alcatelsbell.nms.util.ObjectUtil;
import org.snmp4j.*;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SNMPTrapClient implements Runnable {
    public final static Queue<PDU> pduQueue = new ConcurrentLinkedQueue<PDU>();
    public final static Queue<CommandResponderEvent> eventQueue = new ConcurrentLinkedQueue<CommandResponderEvent>();
    private String udpAddress = null;

    public static int size = 0;

    public SNMPTrapClient(String ipAddress, String port) {
        udpAddress = ipAddress + "/" + port;

    }
    private List traps = new ArrayList();
    public void processTrap() {
        try {
            TransportMapping transport = new DefaultUdpTransportMapping(
                    new UdpAddress(udpAddress));
            Snmp snmp = new Snmp(transport);
            USM usm = new USM(SecurityProtocols.getInstance(),
                                 new OctetString(MPv3.createLocalEngineID()), 0);
               SecurityModels.getInstance().addSecurityModel(usm);

            CommandResponder testTrapResponder = new CommandResponder() {
                public synchronized void processPdu(CommandResponderEvent event) {
                    String security= new String(event.getSecurityName());
                     System.out.println(security);
                     System.out.println("receive trap :"+event.toString());
                    traps.add(event);
                    if (traps.size() > 0)
                    ObjectUtil.saveObject("traps",traps);
                     eventQueue.add(event);
//                    PDU pdu = event.getPDU();
//                     if (pdu != null) {
//                        pduQueue.add(pdu);
//                    }
               }
            };
            snmp.addCommandResponder(testTrapResponder);
            transport.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        processTrap();
    }

    public static void main(String[] strings) {
        //本机IP地址
        String ipaddress="192.168.1.106";
        String port="162";


        if(strings.length>0&&strings.length<2){
            ipaddress=strings[0];
        }
        if(strings.length>1&&strings.length<3){
           ipaddress=strings[0];
           port=strings[1];
       }


        try {

          Thread thread = new Thread(new SNMPTrapClient(ipaddress, port));
      thread.start();
            System.out.println("listening for traps on port 162...");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            Thread.sleep(10000000l);
        } catch (InterruptedException e) {

        }


    }
}
