package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.common.SysConst;
//import com.alcatelsbell.nms.util.TimeLogger;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午9:11
 */
public class CurralarmOperations {
    static class InstHolder {
        public static CurralarmOperations inst = new CurralarmOperations();
    }
    private volatile boolean[] m_needToAckedBeforeBecomingHisAlarm;
    
    private volatile Integer curAck;
    
    private volatile Integer sendTicket;

    public static CurralarmOperations getInstance() {
        return InstHolder.inst;
    }
    private CurralarmOperations() {
        m_needToAckedBeforeBecomingHisAlarm = new boolean[7];
        for(int i=0;i<=6;i++)
            m_needToAckedBeforeBecomingHisAlarm[i]=false;
        
        curAck = 0;
        sendTicket = 0;
    }

    private Log logger = LogFactory.getLog(getClass());

    public void autoClearCurrAlarm(String alarmDn, Alarminformation alarmEvent) throws Exception {
         if ( alarmDn == null )
        {
            return;
        }
        if (alarmEvent != null && alarmEvent.getEmstime() != null)
            clearCurrAlarm(   -1, alarmDn, false, alarmEvent.getEmstime(),  SysConst.CURRALARM_CLEARTYPE_AUTO,null);
        else
            clearCurrAlarm(  -1, alarmDn, false, new Date(),  SysConst.CURRALARM_CLEARTYPE_AUTO,null );
        
        this.ackCurAlarm(-1,alarmDn , "自动确认", null) ;
    }

    public void manuClearCurrAlarm(String alarmDn) throws Exception {
       manuClearCurrAlarm(alarmDn,null);
    }
    //添加告警清除人
    public void manuClearCurrAlarm(String alarmDn,String clearerInfo,String clear) throws Exception {
        if ( alarmDn == null )
        {
            return;
        }
        Date time = new Date();
        clearCurrAlarm(   -1, alarmDn, true, time, SysConst.CURRALARM_CLEARTYPE_USER ,clearerInfo,clear);
    }

    public void manuClearCurrAlarm(String alarmDn,String clearerInfo) throws Exception {
        if ( alarmDn == null )
        {
            return;
        }
        Date time = new Date();
      //  TimeLogger.start("manuClearCurrAlarm");
        clearCurrAlarm(   -1, alarmDn, true, time, SysConst.CURRALARM_CLEARTYPE_USER ,clearerInfo );
      //  TimeLogger.end("manuClearCurrAlarm",logger,TimeLogger.LEVEL_INFO,10);
    }
    
    protected   void clearCurrAlarm( long _sessionKey, String alarmDn, boolean _writeLog,
                                     Date _clearTime, int _resolveStatus,String clearerInfo) throws
               Exception {
        clearCurrAlarm(  _sessionKey,  alarmDn,  _writeLog,
                         _clearTime,  _resolveStatus, clearerInfo,null);
    }
    //添加告警清除人
    protected   void clearCurrAlarm( long _sessionKey, String alarmDn, boolean _writeLog,
                                   Date _clearTime, int _resolveStatus,String clearerInfo,String _clearer) throws
             Exception {
        logger.info("clearCurrAlarm:"+alarmDn);
        final Date clearTime = _clearTime;
        final int resolveStatus = _resolveStatus;
        final String _clearerInfo = clearerInfo;
        final String clearer = _clearer;
        CurralarmServer.getInstance().curralarmWrite(alarmDn,new CurralarmWriteWork() {
            @Override
            public int prepareBeforeOperation(Curralarm currAlarm) throws Exception {
//                boolean createHis = toCreateHisAlarmWhenClearing(currAlarm);
                if(currAlarm.getStatus() == SysConst.CURRALARM_STATUS_CLEARED||
                    currAlarm.getStatus() == SysConst.CURRALARM_STATUS_ACKEDCLEARED){
                    return WORK_TYPE_NONE;
                }
                    
                if(currAlarm.getTicketStatus()!=null&&currAlarm.getTicketStatus() == com.alcatelsbell.nms.common.SysConst.TICKET_STATUS_DEFAULT){
                    currAlarm.setTicketStatus(com.alcatelsbell.nms.common.SysConst.TICKET_STATUS_DISCARD);
                    currAlarm.setAdditionalinfo("在派单时延内被清除，不派单");
                    currAlarm.setStd_billresult(SysConst.BILL_RESULT_TYPE_HOLD_CLEAR);       //在派单延时内清除
                }
                
                boolean createHis = currAlarm.getStatus() == SysConst.CURRALARM_STATUS_ACKED ;
                logger.info("createHis:"+createHis);

                currAlarm.setAgent(_clearerInfo);
                currAlarm.setPrevseverity( currAlarm.getSeverity() );
                currAlarm.setLastmodified( clearTime );
                currAlarm.setResolveTime(clearTime);
                currAlarm.setResolveStatus(resolveStatus);
                currAlarm.setAlertkey(clearer);
             //   currAlarm.setSeverity(SysConst.ALARM_SEVERITY_CLEAR);
                currAlarm.setDeletedat(new Date());
                
                if(currAlarm.getFirstoccurrence()!=null&&currAlarm.getResolveTime()!=null){
                    long duration = currAlarm.getResolveTime().getTime() - currAlarm.getFirstoccurrence().getTime();
                    logger.info("alarmDn:"+currAlarm.getDn());
                    logger.info("duration:"+duration+"(ms)");
                    Long durationl = duration / (60*1000);
                    logger.info("duration:"+duration+"(min)");
                    currAlarm.setStd_duration(durationl.intValue());
                    logger.info("currAlarm.getStd_duration():"+currAlarm.getStd_duration()+"(min)");
                }
                
                if (currAlarm.getStatus() == SysConst.CURRALARM_STATUS_ACKED)
                    currAlarm.setStatus(SysConst.CURRALARM_STATUS_ACKEDCLEARED);
                else
                    currAlarm.setStatus(SysConst.CURRALARM_STATUS_CLEARED);

                if (createHis) return WORK_TYPE_DELETE;
                return WORK_TYPE_UPDATE;
            }

            @Override
            public void prepareCurralarmChangeEvent(CurralarmChangeEvent event) {
                event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_SEVERITY_CHANGE,"true");
            }
        });






//         if (createHis) {
//
//             if ( true )
//            {
//                //the currAlarm has been acknowledeged
//                currAlarm.setStatus( SysConst.CURRALARM_STATUS_ACKEDCLEARED );
//                currAlarm.setSeverity(SysConst.ALARM_SEVERITY_CLEAR);
//                currAlarm.setResolveStatus(SysConst.CURRALARM_CLEARTYPE_AUTO);
//
//                logger.debug(
//                        "The current alarm:" + currAlarm.getDn() + " status is acked and cleared, so create historcal alarm");
//                try {
//                    this.createHisAlarm( _context, _sessionKey, currAlarm, _writeLog );
//                } catch (ServiceException e) {
//                    logger.error(e, e);
//                }
//                return;
//            }
//         }


     }
    public void ackCurAlarm(long _sessionKey, String alarmDn ,String _memo,String _faultCause) throws
            Exception
    {
        ackCurAlarm(_sessionKey,alarmDn,_memo,_faultCause,SysConst.CURRALARM_AUTO_ACKNOWLEDGED,"系统确认");
    }
        public void ackCurAlarm(long _sessionKey, String alarmDn ,String _memo,String _faultCause,final short ackType,String _userName) throws
                 Exception
        {

            final String memo = _memo;
            final String faultCause = _faultCause;
            final String userName = _userName;
            CurralarmServer.getInstance().curralarmWrite(alarmDn,new CurralarmWriteWork() {
                @Override
                public int prepareBeforeOperation(Curralarm curralarm) throws Exception {
                    if(curralarm.getStatus() == SysConst.CURRALARM_STATUS_ACKED||
                        curralarm.getStatus() == SysConst.CURRALARM_STATUS_ACKEDCLEARED){
                        return WORK_TYPE_NONE;
                    }
                    boolean createHis = false;
                    if (curralarm.getStatus() == SysConst.CURRALARM_STATUS_CLEARED) {
                        createHis = true;
                    }

                    if (null != memo && !memo.trim().isEmpty())
                        curralarm.setMemo((curralarm.getMemo() == null ? "":(curralarm.getMemo()+"; "))+memo);

                    if (null != faultCause && !faultCause.trim().isEmpty())
                        curralarm.setFaultCause(faultCause);
                    logger.info("Ack curralarm : " + curralarm.getId());
                    curralarm.setLastmodified(new Date());
                    curralarm.setStatechange(new Date());  //告警确认时间
                    curralarm.setAcknowledged(ackType);
                    curralarm.setStd_confirmaccount(userName);
                    curralarm.setStd_confirmtime(new Date());
                    if (curralarm.getStatus() == SysConst.CURRALARM_STATUS_CLEARED)
                        curralarm.setStatus(SysConst.CURRALARM_STATUS_ACKEDCLEARED);
                    else
                        curralarm.setStatus(SysConst.CURRALARM_STATUS_ACKED);


                    if (createHis)
                        return WORK_TYPE_DELETE;
                    return WORK_TYPE_UPDATE;
                }

                @Override
                public void prepareCurralarmChangeEvent(CurralarmChangeEvent event) {
                    if (event.getEventType() == CurralarmChangeEvent.ALARM_EVENT_TYPE_UPDATE)
                         event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_SEVERITY_CHANGE,"true");
                }
            });
        }



    public void ackAndClearCurrAlarm( long _sessionKey, String alarmDn,String _clearer) throws
             Exception
    {
        if ( alarmDn == null )
        {
            return;
        }
        final String clearer = _clearer;
        CurralarmServer.getInstance().curralarmWrite(alarmDn,new CurralarmWriteWork() {
            @Override
            public int prepareBeforeOperation(Curralarm _currAlarm) throws Exception {
                Date clearTime = new Date();
                _currAlarm.setPrevseverity( _currAlarm.getSeverity() );
                _currAlarm.setResolveTime( clearTime );
                _currAlarm.setLastmodified( clearTime );
                _currAlarm.setDeletedat(clearTime);
                _currAlarm.setStatechange(clearTime);
                _currAlarm.setAcknowledged(SysConst.CURRALARM_MANU_ACKNOWLEDGED);
                _currAlarm.setStatus( SysConst.CURRALARM_STATUS_ACKEDCLEARED );
                _currAlarm.setResolveStatus( SysConst.CURRALARM_CLEARTYPE_USER );
                _currAlarm.setAlertkey(clearer);
                return WORK_TYPE_UPDATE;
            }

            @Override
            public void prepareCurralarmChangeEvent(CurralarmChangeEvent event) {
            }
        });

        CurralarmServer.getInstance().curralarmWrite(alarmDn, new CurralarmWriteWork() {
            @Override
            public int prepareBeforeOperation(Curralarm _currAlarm) throws Exception {
                return WORK_TYPE_DELETE;
            }

            @Override
            public void prepareCurralarmChangeEvent(CurralarmChangeEvent event) {
            }
        });

    }


    private boolean toCreateHisAlarmWhenClearing( Curralarm _currAlarm )
    {
        return ( _currAlarm.getStatus() == SysConst.CURRALARM_STATUS_ACKED ) ||
                this.getNeedToAckedBeforeBecomingHisAlarm()[_currAlarm.getSeverity()];
    }

    public void setNeedToAckedBeforeBecomingHisAlarm( boolean[] _yesOrNo )
    {
        logger.info("setNeedToAckedBeforeBecomingHisAlarm");
        if (_yesOrNo != null) {
            for (int i = 0; i < _yesOrNo.length; i++) {
                boolean b = _yesOrNo[i];
                logger.info(i+"="+b);
            }
        }
        m_needToAckedBeforeBecomingHisAlarm = _yesOrNo;
    }
    
    
    public void setCurAcked(Integer curAck)
    {
        this.curAck =  curAck;
    }
    
    public void setSendTicket(Integer sendTicket)
    {
        this.sendTicket = sendTicket;
    }
    
    public Integer getCurAcked()
    {
        return this.curAck;
    }
    
    public Integer getSendTicket()
    {
        return this.sendTicket;
    }

    public boolean[] getNeedToAckedBeforeBecomingHisAlarm()
    {
        return m_needToAckedBeforeBecomingHisAlarm;
    }

}
