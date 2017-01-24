package com.alcatelsbell.nms.alarm.service;

import com.alcatelsbell.nms.alarm.common.AlarmProcessException;
import com.alcatelsbell.nms.alarm.service.core.AlarminformationHandler;
import com.alcatelsbell.nms.alarm.service.core.CurralarmOperations;
import com.alcatelsbell.nms.alarm.service.core.CurralarmServer;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import com.alcatelsbell.nms.valueobject.sys.Ems;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

/**
 * User: Ronnie.Chen
 * Date: 11-6-20
 */
public class CurralarmSync {
    private Log logger = LogFactory.getLog(getClass());
    public void setLogger(Log _logger) {
        logger = _logger;
    }

    public Object sync(String emsdn,int fieldtype,List<Alarminformation> events) {

        List<Alarminformation> _events = new ArrayList<Alarminformation>();
        if (events != null) {
            for (Alarminformation info : events) {

//                VendorAlarmLib val = AlarmLibCacheManager.getInstance().readVendorAlarmLib(info.getEmsDn(),info.getEventname());
//                if (val != null && val.getSeverity() != null) {
//                     if (val.getSeverity().intValue() == 0) {
//                         _events.add(info);
//                     }
//                }
//                else if (info.getSeverity() == 0)
                    _events.add(info);
            }
        }

        logger.info(" serverity = 0 ,size = "+_events.size());

        String info = "";
        int createAlarmNumber = 0;
        int customerAlarmNumber = 0;
        int clearAlarmNumber = 0;
        List<Curralarm> alarms = null;
        try {
         //   alarms = JpaClient.getInstance().findObjects("select a from Curralarm a where a.emsdn = '"+emsdn+"' and fieldtype="+fieldtype);
            String strSql = "select a from Curralarm a where a.emsdn = '" + emsdn + "' and a.severity = 0";
            alarms = JpaClient.getInstance().findObjects(strSql);
            logger.info("current db ql = "+strSql);
            logger.info("fieldtype = "+fieldtype);
            logger.info("current alarm size = "+(alarms == null ? -1:alarms.size()));
        } catch (Exception e) {
            logger.error(e,e);
        }
        HashMap<String,Curralarm> dbAlarms = new HashMap<String, Curralarm>();
        if (alarms != null) {
            for (Curralarm dbalarm : alarms) {
                dbAlarms.put(dbalarm.getIdentifier(),dbalarm);
            }
        }
        long t1 = System.currentTimeMillis();
        if (_events != null) {
            for (Alarminformation event : _events) {
                String correlationid = event.getCorrelationId();
                Curralarm dbalarm = dbAlarms.get(correlationid);

                if (dbalarm == null) {
                    logger.debug("New alarm:"+event.getCorrelationId()+" name="+event.getEventname()+" nativeemsname="+event.getMenativeemsname()+" and sourceobjectname = "+event.getSourceobjectname());

                    createAlarmNumber++;
                    try {

                        Curralarm alarm = createAlarm(event);
                        if (alarm != null && alarm.getCustomer() != null && alarm.getCustomer().length() > 0)
                            customerAlarmNumber++;
                    } catch (AlarmProcessException e) {
                        logger.error(e,e);
                    }
                } else {
                    dbAlarms.remove(correlationid);
                }
            }
        }
        long t2 = System.currentTimeMillis();
        logger.info(" create alarms ,spend time : "+(t2-t1)+" ms");

        Iterator<String> keys = dbAlarms.keySet().iterator();
        while (keys.hasNext()) {
            String identifer = keys.next();
            Curralarm dbalarm = dbAlarms.get(identifer);
            if (dbalarm != null)
             logger.debug(" Found alarm cleared:"+identifer+"; alarmName = "+dbalarm.getName()+"; sourceobjectname="+dbalarm.getSourceobjectname());
            Alarminformation information = new Alarminformation();
            information.setCorrelationId(identifer);
            information.setSeverity(SysConst.ALARM_SEVERITY_CLEAR);
            information.setEmsDn(emsdn);
            information.setClear_time(new Date());
            information.setFieldType(fieldtype);

            clearAlarmNumber++;
            clearAlarm(information);
        }
        long t3 = System.currentTimeMillis();
        logger.info(" clear alarms ,spend time : "+(t3-t2)+" ms");

        info = "新建告警数:"+createAlarmNumber+" 客户告警数:"+customerAlarmNumber+" 清除告警数:"+clearAlarmNumber;
        Ems ems = null;
        try {
            ems = (Ems) JpaClient.getInstance().findObjectByDN(Ems.class,emsdn);
            ems.setTag1("告警同步时间:"+new Date());
            ems.setTag2(info);
            JpaClient.getInstance().saveObject(-1,ems);
        } catch (Exception e) {
            logger.error(e,e);
        }

        return info;

    }


    private Curralarm createAlarm(Alarminformation alarminformation) throws AlarmProcessException {
        alarminformation.setTag1("SYNC");
        AlarminformationHandler handler = new AlarminformationHandler(alarminformation);
        handler.doProcess();
        return handler.getCurralarm();

    }

    private Curralarm clearAlarm(Alarminformation alarminformation) {
        logger.debug("ClearAlarm:"+alarminformation.getCorrelationId());
     //   JPAContext jpaContext = JPAContext.prepareReadOnlyContext();
        Curralarm alarm = null;
        try {
            alarm = CurralarmServer.getInstance().curralarmRead(alarminformation.getCorrelationId());

            if (alarm != null) {
                logger.debug("Try to Clear Alarm:"+alarm.getId());
                CurralarmOperations.getInstance().autoClearCurrAlarm(alarm.getDn(),alarminformation);
             //   AlarmServer.getInstance().clearCurrAlarm(jpaContext,-1,alarm,false,new Date(),new Date(),new Date());
            }
        } catch (Exception e) {
            logger.error(e, e);
        } finally {
        //    jpaContext.release();
        }

        return alarm;
    }
}
