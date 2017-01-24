package com.alcatelsbell.nms.alarm.service;


import com.alcatelsbell.nms.db.components.service.*;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import com.alcatelsbell.nms.valueobject.physical.Managedelement;
import com.alcatelsbell.nms.valueobject.physical.Physicalterminationpoint;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;


public class AlarmQuery
{
  protected static final Log logger = LogFactory.getLog(AlarmQuery.class);
  protected static AlarmQuery alarmQuery = null;

  public static synchronized AlarmQuery getInstance()
  {
    if (alarmQuery == null)
      alarmQuery = new AlarmQuery();

    return alarmQuery;
  }

  public Curralarm readProducedAlarm(JPAContext _context, Alarminformation _event)
    throws Exception
  {
      long t1 = System.currentTimeMillis();

       List alarms = JPAUtil.getInstance().findObjects(_context,"select c from Curralarm c where c.identifier = '"+_event.getCorrelationId()+"'");

      long t2 = System.currentTimeMillis();
        logger.debug("readProducedAlarm: "+_event.getCorrelationId()+" spend "+(t2-t1)+"ms");


      if (alarms.size() <= 0)
      return null;

    Curralarm ret = (Curralarm)alarms.get(0);

    return ret;
  }

    public Managedelement getMEByNatvieName(JPAContext sessionContext,String natviename,String emsdn) {
        long t1 = System.currentTimeMillis();
        String hql = "select m from Managedelement as m where m.nativeemsname = '"+natviename+"' and emsdn = '"+emsdn+"'";
        List<Managedelement> mes = null;
        try {
            mes = JPAUtil.getInstance().findObjects(sessionContext,hql);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        long t2 = System.currentTimeMillis();
        logger.debug("getMEByNatvieName: "+natviename+" spend "+(t2-t1)+"ms");
        if (mes != null && mes.size() > 0) return  mes.get(0);
        return null;
    }
    public Physicalterminationpoint getPortByAlarmEvent(JPAContext sessionContext, Alarminformation event)  {
        long t1 = System.currentTimeMillis();
        List<Physicalterminationpoint> ptps = null;
        try {
            ptps = JPAUtil.getInstance().findObjects(sessionContext,"select c from Physicalterminationpoint c where c.dn = '"+event.getSourceobjectname()+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long t2 = System.currentTimeMillis();
        logger.debug("getPortByAlarmEvent: "+event.getSourceobjectname()+" spend "+(t2-t1)+"ms");
        if (ptps != null && ptps.size() > 0) return  ptps.get(0);

        return null;  //To change body of created methods use File | Settings | File Templates.
    }
    public Managedelement getMEByNatvieName(String natviename,String emsdn) {
        long t1 = System.currentTimeMillis();
        String hql = "select m from Managedelement as m where m.nativeemsname = '"+natviename+"' and emsdn = '"+emsdn+"'";
        List<Managedelement> mes = null;
        JPASupport sessionContext = JPASupportFactory.createJPASupport();
        try {
            mes = JPAUtil.getInstance().findObjects(sessionContext,hql);
        } catch (Exception e) {
           logger.error(e,e);
        } finally {
            sessionContext.release();
        }
        long t2 = System.currentTimeMillis();
   //     logger.debug("getMEByNatvieName: "+natviename+" spend "+(t2-t1)+"ms");
        if (mes != null && mes.size() > 0) return  mes.get(0);
        return null;
    }
    public Physicalterminationpoint getPortByAlarmEvent( Alarminformation event)  {
        long t1 = System.currentTimeMillis();
        List<Physicalterminationpoint> ptps = null;
        JPASupport sessionContext = JPASupportFactory.createJPASupport();
        try {
            ptps = JPAUtil.getInstance().findObjects(sessionContext,"select c from Physicalterminationpoint c where c.nativeemsname = '"+event.getSourceobjectname()+"' and c.emsdn = '"+event.getEmsDn()+"'");
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            sessionContext.release();
        }
        long t2 = System.currentTimeMillis();
    //    logger.debug("getPortByAlarmEvent: "+event.getSourceobjectname()+" spend "+(t2-t1)+"ms");
        if (ptps != null && ptps.size() > 0) return  ptps.get(0);

        return null;  //To change body of created methods use File | Settings | File Templates.
    }
    public Managedelement getMEByPort(JPAContext sessionContext, Physicalterminationpoint m_ptp) {

        try {
            Managedelement me = (Managedelement)JPAUtil.getInstance().findObjectByDn(sessionContext,-1,Managedelement.class,m_ptp.getMedn());
            return me;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}