
package com.alcatelsbell.nms.alarm.service;

import com.alcatelsbell.nms.common.ServiceException;
import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import com.alcatelsbell.nms.valueobject.alarm.HisAlarm;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface FaultRemoteIFC extends Remote
{
    public Curralarm ackCurrAlarm(long sessionKey, Curralarm currAlarm, String userName) throws RemoteException, ServiceException;

    public void clearCurrAlarm(long sessionKey, Curralarm currAlarm) throws RemoteException, ServiceException;

    public void ackAndClearCurrAlarm(long sessionKey, Curralarm currAlarm) throws RemoteException, ServiceException;
    public void shieldCurrAlarm(long sessionKey, Curralarm currAlarm, Date startTime, Date endTime, long userId, int ifNeedEnd) throws RemoteException, ServiceException;
    public void customShield(long sessionKey, String alarmName, String neName, String faultCause,
                             String sourceObjectNameCN, Date startTime, Date endTime, long emsId, long userId)
                        throws ServiceException, RemoteException;

//    public void unchainedCurrAlarm( long sessionKey, FilteredAlarm currAlarm ) throws RemoteException, ServiceException;
//    public Vector getCurralarmsByCustomer( long sessionKey, XCustomer _customer ) throws RemoteException, ServiceException;
    public Vector getCustomersByCurralarm(long sessionKey, Curralarm _curralarm) throws RemoteException, ServiceException;
    public Vector getCustomersByCurralarm(long sessionKey, long _curralarmid) throws RemoteException, ServiceException;

 //   public Vector getHisalarmsByCustomer( long sessionKey, XCustomer _customer ) throws RemoteException, ServiceException;
    public Vector getCustomersByHisalarm(long sessionKey, HisAlarm _hisalarm) throws RemoteException, ServiceException;
    public Vector getCustomersByHisalarm(long sessionKey, long _hisalarmid) throws RemoteException, ServiceException;


    public void setNeedToAckedBeforeBecomingHisAlarm(boolean[] _needToAck) throws RemoteException, ServiceException;
    
    public void setCurAcked(Integer curAck) throws ServiceException, RemoteException ;
    
    public void setSendTicket(Integer sendTicket) throws ServiceException, RemoteException ;
    
 //   public Vector getCurralarmsByMe( long _sessionKey, Managedelement _me )throws RemoteException, ServiceException;
    public Vector getCurralarmsByCond(long _sessionKey, Map _cond)throws RemoteException, ServiceException;

    public Vector getCustomersAssociatedCurralarm() throws RemoteException, ServiceException;

    public Object syncAlarms(String emsdn, int fieldtype, List<Alarminformation> _events) throws RemoteException,ServiceException;

    public List<GeneralMessage> readGeneralMessages(long serial) throws RemoteException,ServiceException;
    public HashMap readCurralarmStat()  throws RemoteException,ServiceException;
    public Curralarm updateCurralarm(String dn, HashMap fieldValueMap) throws RemoteException,ServiceException;
    public Curralarm createCurralarm(Curralarm alarm) throws RemoteException,ServiceException;
    public Curralarm deleteCurralarm(String alarmDn) throws RemoteException,ServiceException;
    public Curralarm readCurralarm(String dn) throws RemoteException,ServiceException;

}
