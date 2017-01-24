package com.alcatelsbell.nms.security.ditp;

import com.alcatelsbell.nms.util.LogUtil;

/**
 * Author: Ronnie.Chen
 * Date: 13-2-25
 * Time: 下午1:56
 * rongrong.chen@alcatel-sbell.com.cn
 */

public class DITPSocketMessage extends SocketMessage {

    @MessageField(index = 1,byteSize = 2,value = { (byte)0xA5,0x5A})
    private byte[] startFlag1;


    @MessageField(index = 2,byteSize = 2)
    private int length;

    @MessageField(index = 3,byteSize = 4)
    private int cmd;

    @MessageField(index = 4,byteSize = 4)
    private int sequence;

    private DITPSocketMessage(SocketMessageFactory factory) {
        super(factory);
    }

    public static DITPSocketMessage prepareEncoding(int cmd,int sequence) {
        DITPSocketMessage message = new DITPSocketMessage(null);
        message.setCmd(cmd);
        message.setSequence(sequence);
        message.setStartFlag1(new byte[]{(byte) 0xA5, 0x5A});
        message.setLength(0);
        message.setMode(MODE.ENCODE);
        return message;
    }

    public static DITPSocketMessage prepareDecoding(SocketMessageFactory factory) {
        DITPSocketMessage message = new DITPSocketMessage(factory);
        message.setMode(MODE.DECODE);
        return message;
    }

    @Override
    protected int getBodyLength() {
        if (length > 0)
            return length - 2 - 2 - 4 - 4;
        return -1;
    }

    public byte[] getStartFlag1() {
        return startFlag1;
    }

    public void setStartFlag1(byte[] startFlag1) {
        this.startFlag1 = startFlag1;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public byte[] encode() throws Exception{

        if (length == 0) {
            length = super.encode().length;
        }
        return super.encode();

    }
}
