/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.alcatelsbell.nms.util.protocol.tl1;


import com.alcatelsbell.nms.util.protocol.TelnetSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Ronnie.Chen
 */
public class TL1Session {
    private Log logger = LogFactory.getLog(TL1Session.class);
    private String seperator = " ";
    private String encoding = "iso8859-1";
    private String prompt = ";";
    
    public static boolean isFullOf(int length,String cr,String sp) {
        return cr.length() >=length && cr.replaceAll(sp, "").length() == 0;
    }
    public static boolean isFullOf( String cr,String sp) {
        return isFullOf(1,cr,sp);
    }    
 
    private static int commandIndex = 0;

    private static synchronized String getCommandIndex() {
        return "" + (++commandIndex);
    }
    // 连接服务器并登陆

    private TelnetSession telnetSession = null;
    public void connectAndLogin(String host,int port,String username,String password) throws Exception {
        try {
            if (this.telnetSession == null) {
                this.telnetSession = new TelnetSession(host,port);
                this.telnetSession.setEncoding(getEncoding());
                this.telnetSession.setPrompt(getPrompt());
//                String str = this.telnetSession.sendCommand("LOGIN:::1::UN=" + username + ",PWD=" + password + ";");
//                logger.debug("login : " + str);
                String str = this.telnetSession.sendCommand(";\n");

                str = this.telnetSession.sendCommand("LOGIN:::1::UN=" + username + ",PWD=" + password + ";");
                logger.debug("login : " + str);
            }
        } catch (Exception e) {
            logger.error(e);
            disconnect();
            throw e;
        }

    }

    public void disconnect() throws Exception {
        if (this.telnetSession != null) {
            this.telnetSession.disconnect();
            this.telnetSession = null;
        }
//        if (this.snmpTrapReceiver != null) {
//            this.snmpTrapReceiver.removeMessageListener(this.ems.getSendHost(), this);
//            this.snmpTrapReceiver = null;
//        }
//        clearFilter();
    }
    
    private TL1Result stringToTL1Result(String info) throws TL1ParseFailedException {
        String[] lines = info.split("\n");
        TL1Result result = new TL1Result();
        result.setDatas(new ArrayList());
        boolean start = false;
        
        boolean column = true;
        for (String line : lines) {
            line = line.trim();
            if (this.isFullOf(3,line,"-")) {
                start = !start;
                continue;
            }
            
            if (start) {
                if (column) {                    
                    // read columns
                    
                    String[] array = line.split(seperator);
                    if (array == null || array.length == 0)
                        throw new TL1ParseFailedException("Failed to parse column,  array is null, line=" + line);
                    if (array != null && array.length > 0)
                        result.setColumns(Arrays.asList(array));
                    column = false;
                } else {
                    // read row
                    String[] array = line.split(seperator);
                    if (array == null || array.length == 0)
                        throw new TL1ParseFailedException("Failed to parse row,  array is null, line="+line);
                    if (array.length > result.getColumns().size()) 
                        throw new TL1ParseFailedException("Failed to parse row, expected data column size = "+result.getColumns().size() +" actual size = "+array.length+", line="+line);
                    if (array.length < result.getColumns().size()) {
                        logger.error("Warning:Parsing row,expected data column size = " + result.getColumns().size() + " actual size = " + array.length + ", line=" + line);
                        String[] temp = new String[result.getColumns().size()];
                        System.arraycopy(array, 0, temp, 0, array.length);
                        array = temp;
                    }
                    result.getDatas().add(Arrays.asList(array));
                }
            }
        }
        
        return result;
    }
    
    public TL1Result executeCommand(String command) throws ErrorReturnException, IOException, TL1ParseFailedException {
        return executeCommand(command, false);
    }

 
    
    public TL1Result executeCommand( String command, boolean ctag) throws ErrorReturnException, IOException, TL1ParseFailedException {
        logger.debug("Execute command:" + command + ":" + ctag);
        String originCommand = command;
        String cmdIndex = getCommandIndex();
        if (!ctag) {
            command = command + ":" + cmdIndex + "::;";
        } else {
            command = command + ";";
        }
        String info = telnetSession.sendCommand(command, ";");
        int count = 0;
        while (true) {
            if (info == null) {
                throw new IOException("Communication Interrupted, Maybe connection timeout");
            }

            if (info.indexOf("EN=") < 0) {
                info = telnetSession.readUntil(";");
                continue;
            }
            int midx = info.indexOf("M");
            int denyIdx = info.indexOf("DENY", midx);
            if (midx > 0 && denyIdx > 0) {
                throw new ErrorReturnException("Command:"+ command+" is denied ,midx>0 and denyIdx>0, return= "+info);
            }
            if (midx > 0) {
                String echo = info.substring(midx + 1, info.indexOf(" ", midx + 3)).trim();
                if (echo.equals(cmdIndex)) {
                    logger.debug(info);
                    return stringToTL1Result(info);
                } else if (ctag && echo.equals("CTAG")) {
                    logger.debug(info);
                    return stringToTL1Result(info);
                } else {
                    info = telnetSession.readUntil(";");
                    continue;
                }
            }



            if (count++ == 100) {
                return null;
            }
        }

    }
    
    public static void main(String[] args) throws Exception {
        TL1Session session = new TL1Session();
        session.connectAndLogin("135.254.125.132",12015,"manager","123456");
        System.out.println(isFullOf(10,"--------------------------","-"));
        System.out.println(isFullOf(2, "----", "-"));
        System.out.println(isFullOf(3, "----", "-"));
        System.out.println(isFullOf(2, "------dsafadsf-------------", "-"));
        System.out.println(isFullOf(10, "-dsaf---------------------", "-"));
        System.out.println(isFullOf(10, "123----------------------", "-"));
    }

    /**
     * @return the seperator
     */
    public String getSeperator() {
        return seperator;
    }

    /**
     * @param seperator the seperator to set
     */
    public void setSeperator(String seperator) {
        this.seperator = seperator;
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    /**
     * @param prompt the prompt to set
     */
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    
}
