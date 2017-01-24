package com.alcatelsbell.nms.util.protocol;

/**
 * Author: Ronnie.Chen
 * Date: 13-1-4
 * Time: 下午4:42
 * rongrong.chen@alcatel-sbell.com.cn
 */


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    Ping command is platforms indifferent, here i listed the santax
    on 3 normal platforms:

    For HP Unix :
    ping 172.16.3.35 64 -n 4

    For Sun Solaris :
    ping -s 172.16.3.35 64 4

    For Windows :
    Ping -n 4 -l 64 172.16.3.35
*/

public class IcmpPing { //Windows平台
    private String url = null;
    int count = 0; //Default: 4
    int packetSize = 0; // Default: 64Byte
    int delays = 0; //
    int times = 0; // 0  mean invalid ping
    int loss = 100; // by default, the loss = 100%, normally, it should be 0(no loss)
    int packetSent = 4; /* The packet has been sent, normally,
           packetSent = count ( when count != 0),
           otherwise, packetSent = 4(When count = 0) */
    private boolean flag = false;
    private boolean meatLessToken = false;
    private StringBuffer outputBuffer = new StringBuffer();

    public IcmpPing(String url, int count, int packetSize) {
        this.url = url;
        this.count = count;
        this.packetSize = packetSize;
    }

    public IcmpPing(String url) {
        this(url, 4, 64);

    }

    public boolean ping() {
        delays = 0;
        times = 0;
        // Construct the ping command
        if (url == null) {
            flag = false;
        }
        StringBuffer pingCmd = new StringBuffer(50);
        pingCmd.append("Ping");
        packetSent = count;
        if (count > 0) {
            pingCmd.append(" -n ");
            pingCmd.append(count);
        } else {
            pingCmd.append(" -n ");
            packetSent = 4;
            pingCmd.append(packetSent); // No count means 4 times
        }
        if (packetSize > 0) {
            pingCmd.append(" -l ");
            pingCmd.append(packetSize);
        }
        pingCmd.append(" " + url);

        Runtime rt = Runtime.getRuntime();
        try {
            /*
             * 以下代码使用Runtime类在一个subprocess中运行所在平台
             * 的native命令。
             */
            Process ps = rt.exec(pingCmd.toString());
            /*
             * ping命令的output会定向到subprocess的InputStream。
             * 得到此InputStream, 然后输出到你希望的地方。
             */
            InputStream is = ps.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = br.readLine().trim();
            int theDelay;
            while (str != null) {

                if (str.compareTo("") != 0 &&
                        str.compareTo("\r") != 0 &&
                        str.compareTo("\n") != 0) {
                    outputBuffer.append(str + "\r\n");
                    //System.out.println(str + "\r\n");
                    theDelay = parseDelay(str);
                    if (meatLessToken || theDelay > 0) {
                        times++;
                        //    System.out.println("times:" + times);
                        delays += theDelay;
                        //   System.out.println("delays:" + delays);
                    }
                }
                str = br.readLine();
            }
        } catch (Exception se) {
            if (times == 0) {
                flag = false;
            } else {
                flag = false;
            }
        }
        if (times == 0) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    public int getDelay() {

        if (flag) {
            return delays / times;

        } else {
            return 0;
        }
    }

    public int getLossPercent() {

        if (flag) {
            if (times > packetSent) {
                // impossible case, return 100% loss
                return 100;
            } else {
                int count = ((packetSent - times) / packetSent) * 100;
                return count;
            }
        } else {
            return 100; // 100% loss
        }
    }

    public String getOutput() {
        return outputBuffer.toString();
    }

    private int parseDelay(String str) {
        int delay = 0;
        StringTokenizer st = new StringTokenizer(str);
        String strTemp;
        StringBuffer strBuff = new StringBuffer(50);
        while (st.hasMoreTokens()) {
            strTemp = st.nextToken();
            if (strTemp.indexOf("ms") >= 0
                    && strTemp.indexOf("time") >= 0
                    ) {
                char ch;
                for (int i = 0; i < strTemp.length(); i++) {
                    ch = strTemp.charAt(i);
                    if (ch == '<') {
                        meatLessToken = true;
                    }
                    if (ch >= '0' && ch <= '9') {
                        strBuff.append(ch);
                    }
                }
                if (strBuff.length() <= 0) {
                    return 0;
                }
                delay = (new Integer(strBuff.toString())).intValue();
                if (meatLessToken) {
                    delay = delay / 2;
                }
                break;
            }
        }
        return delay;
    }

    public static void main(String[] args) {
        // Usage of Ping
        //Ping ping = new Ping("www.yahoo.com", 10, 4096);
        IcmpPing icmpPing = new IcmpPing("192.168.1.101"); //Count= 4, packet size =64Byte
        boolean ok = icmpPing.ping();
        System.out.println(icmpPing.getOutput());

        int delay = icmpPing.getDelay();
        int loss = icmpPing.getLossPercent();

        System.out.println("Average Delay:" + delay + "ms");
        System.out.println("Average Loss:" + loss + "%");
    }
}
