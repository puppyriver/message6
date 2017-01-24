package com.alcatelsbell.nms.util.protocol;

/**
 *
 * @author Ronnie.Chen
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.telnet.*;

import java.io.*;
import java.net.SocketException;
import java.util.Date;


public class TelnetSession {

    private static Log logger = LogFactory.getLog(TelnetSession.class.getName());
    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;
    private String prompt;
    private String currentString;
    private TemplateUtil templetUtil;
    private byte[] msgBytes;
    private String encoding = "gb2312";
    
    public TelnetSession(String host, int port)
            throws SocketException, IOException {
        this(host, port, "$");
    }

    public TelnetSession(String host, int port, String _prompt)
            throws SocketException, IOException {
        this.telnet = null;

        this.prompt = "$";

        this.currentString = null;

        this.templetUtil = new TemplateUtil();

        this.msgBytes = null;

        this.prompt = _prompt;
        connect(host, port);
    }

    public void login(String user, String password)
            throws IOException {
        login(user, password, "login: ", "Password: ");
    }

    public void login(String user, String password, String loginPrompt, String passwdPrompt) throws IOException {
        readUntil(loginPrompt);
        write(user);
        readUntil(passwdPrompt);
        write(password);

        readUntil(this.prompt);
    }

    public void su(String password) throws IOException {
        write("su");
        readUntil("Password: ");
        write(password);
        this.prompt = "#";
        readUntil(new StringBuilder().append(this.prompt).append(" ").toString());
    }

    public String getPrompt() {
        return this.prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    private boolean connect(String host, int port) throws SocketException, IOException {
        this.telnet = new TelnetClient();
        SuppressGAOptionHandler sgaOpt = new SuppressGAOptionHandler(true, true, true, true);
        TerminalTypeOptionHandler ttOpt = new TerminalTypeOptionHandler("VT320", false, false, true, false);
        EchoOptionHandler eOPt = new EchoOptionHandler(true, false, true, false);
        WindowSizeOptionHandler wsOpt = new WindowSizeOptionHandler(100, 30);
        try {
            this.telnet.addOptionHandler(ttOpt);
            this.telnet.addOptionHandler(sgaOpt);
            this.telnet.addOptionHandler(eOPt);
            this.telnet.addOptionHandler(wsOpt);
        } catch (InvalidTelnetOptionException e) {
            throw new IllegalStateException("invalid telnet options", e);
        }
        this.telnet.connect(host, port);

        this.in = this.telnet.getInputStream();
        this.out = new PrintStream(this.telnet.getOutputStream());
        return true;
    }

    public synchronized  String readUntil(String pattern) throws IOException {
        if (pattern.length() == 0) {
            return readUntil();
        }

        char lastChar = pattern.charAt(pattern.length() - 1);
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        char ch = (char) this.in.read();

        Date startTime = new Date();
        while (true) {
            sb.append(ch);
            if ((ch == lastChar)
                    && (sb.toString().endsWith(pattern))) {
                this.currentString = sb.toString();
                return new String(sb.toString().getBytes("iso8859-1"),encoding);
            }
//            if(this.in.available()<=0){
//                throw new IOException("the server may be closed");
//            }
            int ich = this.in.read();
            ch = (char) ich;
            if (ich < 0) {
                throw new IOException("the end of the  stream is reached");
            }

            if (sb.length() > 2097152 * 10) {
                sb = sb.delete(0, sb.length());
                logger.error("Please check your script. read too many characters");
            }

            Date endTime = new Date();
            if (endTime.getTime() - startTime.getTime() > 3600000L) {
                logger.error("Please check your script. Spent too much time to read characters. The program quit this reading process.");
                throw new IOException("Spent too much time to read characters. The program quit this reading process.");
            }
        }
    }

    public String readUntil() throws IOException {
        String result = "";
        if (this.msgBytes == null) {
            this.msgBytes = new byte[2097152];
        }

        int byteNum = this.in.read(this.msgBytes);
        if (byteNum < 0) {
            throw new IOException("the end of the  stream is reached");
        }

        result = new String(this.msgBytes, 0, byteNum,encoding);
        this.currentString = result;
     //   logger.info("read : {0}"+ this.currentString);

        return result;
    }

    public synchronized void write(String value) throws IOException {
        this.out.println(value);
        this.out.flush();
    }

    public void writeChar(char ch) throws IOException {
        this.telnet.getOutputStream().write(ch);
        this.telnet.getOutputStream().flush();
    }

    public String sendCommand(String command)
            throws IOException {
        logger.debug("send command:"+command);
        write(command);
        return readUntil(this.prompt) ;
    }
    public String sendCommand(String command,String prompt)
            throws IOException {
        logger.info("send command:" + command);
        write(command);

        return  readUntil(prompt);
    }
    public void processMinPattern(String pString) {
        this.templetUtil.process(this.currentString, pString, "(.+?)");
    }

    public void processMaxPattern(String pString) {
        this.templetUtil.process(this.currentString, pString, "(.*)");
    }

    public String replace(String oString) {
        return this.templetUtil.replace(oString);
    }

    public void disconnect() throws IOException {
        try {
            this.telnet.disconnect();
            this.in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public static void main(String[] args) throws IOException {
        TelnetSession session = new TelnetSession("135.251.23.27",23,"#");
        session.login("root","root123");
        String ls = session.sendCommand("ls");
        BufferedReader br = new BufferedReader(new StringReader(ls));
        String s = null;
        while ((s = br.readLine()) != null)  {
            System.out.println(s);
        }

    }
}