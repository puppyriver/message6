package com.alcatelsbell.nms.common.message;

import java.io.Serializable;

/**
 * User: Ronnie.Chen
 * Date: 11-5-26
 * Time:
 */
public class InterfaceMessage implements Serializable{
    public String cmdName;
    public static final int TYPE_REQUEST_DATA = 0;
    public static final int TYPE_RESPONSE_DATA = 1;

    private int type;

    public InterfaceMessage(int type,Serializable obj) {

    }
}
