package com.alcatelsbell.nms.common.message;

import java.io.Serializable;

/**
 * User: Ronnie.Chen
 * Date: 11-5-24
 * Time:
 */
public class GeneralMessage implements Serializable{
    public static final int ADD = 1;
    public static final int DELETE = 2;
    public static final int UPDATE = 3;

    private Object msgObj;
    private int operationType;
    private long sessionKey;

    public GeneralMessage()
    {}

    public GeneralMessage( Object _msgObj, int _operationType )
    {
        msgObj = _msgObj;
        operationType = _operationType;
    }

    public GeneralMessage( Object _msgObj, int _operationType, int _sessionKey )
    {
        this( _msgObj, _operationType );
        sessionKey = _sessionKey;
    }

    public Object getMsgObj()
    {
        return this.msgObj;
    }

    public void setMsgObj( Object msgObj )
    {
        this.msgObj = msgObj;
    }

    public int getOperationType()
    {
        return this.operationType;
    }

    public void setOperationType( int operationType )
    {
        this.operationType = operationType;
    }

    public long getSessionKey()
    {
        return sessionKey;
    }

    public void setSessionKey( long aSessionKey )
    {
        sessionKey = aSessionKey;
    }
}
