package com.alcatelsbell.nms.alarm.service;

import com.alcatelsbell.nms.alarm.common.AlarmProcessException;
import com.alcatelsbell.nms.common.ServiceException;
import com.alcatelsbell.nms.common.SpringContext;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.db.components.service.DBUtil;
import com.alcatelsbell.nms.db.components.service.JPAContext;
import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.db.dao.JpaGenericExtDao;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import com.alcatelsbell.nms.valueobject.alarm.FProductalarm;
import com.alcatelsbell.nms.valueobject.domain.RProduct;
import com.alcatelsbell.nms.valueobject.physical.Managedelement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;



/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author Ronnie
 * @version 1.0
 */
public class AlarmServerUtil
{
    static protected AlarmServerUtil alarmServerUtil = null;


    static synchronized public AlarmServerUtil getInstance()
    {
        if ( alarmServerUtil == null )
        {
            alarmServerUtil = new AlarmServerUtil();
        }
        return alarmServerUtil;
    }
    private Log logger = LogFactory.getLog(AlarmServerUtil.class);
    protected AlarmServerUtil()
    {

    }

    public void createEvent( JPAContext _context, Alarminformation _event ) throws AlarmProcessException
    {
        _event.setDn(SysUtil.nextDN());

        createObject(_context, -1, _event);

    }

    private Object productAlarmLock = new Object();
    public BObject createOrUpdateProductAlarm(JPASupport _context,long _sessionKey, FProductalarm fProductAlarm) throws Exception {
        BObject o = null;
        synchronized (productAlarmLock) {
              o = this.storeProductAlarmByDn(_context,fProductAlarm,SysConst.TOPIC_NAME_NMS_PRODUCTALARM);
         }

        return o;

    }

  public BObject storeProductAlarmByDn(JPASupport _context , FProductalarm _obj,String topic) throws Exception {
    BObject result = null;
    EntityManager em = _context.getEntityManager();

    FProductalarm bobjDB =// null;
      (FProductalarm) JpaGenericExtDao.getInstance().getObject(em, _obj, "dn");

//    List<BObject> objs =this.findObjects(_context,"select c from "+_obj.getClass().getSimpleName()+" as c where c.dn = '"+_obj.getDn()+"'");
//    if (objs != null && objs.size() > 0) bobjDB = objs.get(0);
    if (bobjDB == null)
    {

      result = createObject(_context, -1, _obj);

  //      SpringContext.getInstance().getJMSSupport().sendTopicMessage(topic,new GeneralMessage(result, GeneralMessage.ADD));

    } else {
            _obj.setId(bobjDB.getId());
           if (_obj.getMaxseverity() > bobjDB.getMaxseverity()) {
               _obj.setMaxseverity(bobjDB.getMaxseverity());
           }
          _context.begin();
          result = JPAUtil.getInstance().storeObject(_context, -1, _obj);
          _context.end();
    //    SpringContext.getInstance().getJMSSupport().sendTopicMessage(topic,new GeneralMessage(result, GeneralMessage.UPDATE));
    }
    return result;
  }


    public BObject createObject( JPASupport _context, long _sessionKey, BObject _obj) throws
            AlarmProcessException
    {
        try
        {
            _context.begin();
            BObject bo = JPAUtil.getInstance().createObject( _context, _sessionKey,  _obj);
            logger.debug("create1  "+bo.getClass().getName()+":"+bo.getId());
            _context.end();
            return bo;
        }
        catch ( Exception ex )
        {

            //ex.printStackTrace();
            ex.printStackTrace();
            _context.rollback();
            logger.error( ex.getMessage() );
            throw new AlarmProcessException(_obj.getDn(),  ex );
        }
    }

    public Object storeObject( JPAContext _context, long _sessionKey, BObject _obj,
                             boolean _writeLog ) throws ServiceException
    {
        try
        {
             _context.prepare();
            BObject bo = JPAUtil.getInstance().storeObject( _context, _sessionKey, _obj );
             _context.end();
            return bo;
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();
            _context.rollback();
            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }
    }

    public void deleteObject( JPAContext _context, long _sessionKey, BObject _obj,
                              boolean _writeLog ) throws ServiceException
    {
        try
        {
              _context.prepare();
            JPAUtil.getInstance().deleteObject( _context, _sessionKey, _obj );
              _context.end();
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();
             _context.rollback();
            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }
    }

    public Object queryObjectById( JPAContext _context, Class objClass, long id ) throws ServiceException
    {
        Object result = null;
        try
        {
            result = DBUtil.getInstance().queryObjectById( _context, objClass, id );
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }

        return result;
    }





    public void storeBusinessBrokenStatus( JPAContext _context, int _status, RProduct _business )throws ServiceException
    {
        storeBusinessBrokenStatus( _context, _status, _business.getId() );
    }

    public void storeBusinessBrokenStatus( JPAContext _context, int _status, long _businessId )throws ServiceException
    {
        String sql = "update business set SERVICESTATUS = " + _status + " where id = " + _businessId;
        logger.debug( sql );

        try
        {
            DBUtil.getInstance().executeNonSelectingSQL( _context, sql );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }
    }


    public void deleteAlarminformationByCurralarm( JPAContext _context, Curralarm _alarm )throws ServiceException
    {
        String sql = "delete alarminformation where alarmid = " + _alarm.getId(); // + " or alarmid is null "
        //remove " or alarmid is null" for performance improvement.
        logger.debug( sql );

        try
        {
            DBUtil.getInstance().deleteObject( _context, -1, sql );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }
    }


    public Vector queryAllObjects( JPAContext _context, Class objClass ) throws ServiceException
    {
        List result = null;
        try
        {
            result = JPAUtil.getInstance().findAllObjects(_context, objClass);
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }

        return result == null ? new Vector():new Vector(result);

    }

    public Object queryObjectByDN( JPAContext _context, Class _objClass, String _DN ) throws ServiceException
    {
        Object ret = null;
        try
        {
            logger.debug( "query object of " + _objClass.getName() +" by dn: " + _DN );
            ret = DBUtil.getInstance().queryObjectByDn( _context, -1, _objClass, _DN );
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }
        return ret;
    }

    public Vector queryObjects( JPAContext _context, Class objClass, String queryString ) throws ServiceException
    {
        List result = null;
        try
        {
            result = JPAUtil.getInstance().findObjects( _context, queryString );
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }

        return result == null ? new Vector():new Vector(result);
    }

    public Vector queryObjects( JPAContext _context, Class objClass, String tableName, Map cond, boolean _isLike ) throws
            ServiceException
    {
        Vector result = null;
        try
        {
            throw new Exception("unimplemented!!");
          //  result = DBUtil.getInstance().queryObjects( _context, objClass, tableName, cond, _isLike );
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }

      //  return result;
    }

    public Vector queryObjects( JPAContext _context, Class objClass, String tableName, Map cond ) throws
            ServiceException
    {
        return queryObjects( _context, objClass, tableName, cond, true );
    }


    public Object queryAttrBySql( JPAContext _context, String sqlString, String attrName ) throws
            ServiceException
    {
        Object result = null;
        try
        {
            result = DBUtil.getInstance().queryAttrBySql( _context, sqlString, attrName );
        }
        catch ( Exception ex )
        {
            //ex.printStackTrace();
            ex.printStackTrace();

            logger.error( ex.getMessage() );
            throw AlarmExceptionUtil.getInstance().convertException( ex );
        }

        return result;
    }


    public Alarminformation cretateMeAlarminformation(String eventName,Managedelement me,String desc,String additionalInfo,int severity,String correlationID,int fieldtype) {
        if (me == null) return null;

        Alarminformation result = new Alarminformation();

//        result.setVendor(SysConst.EVENT_CATEGORY_ALARM);
//        result.setAlarmtype(SysConst.ALARMEVENT_ALARMTYPE_EQUIPMENTALARM);
        result.setFieldType(fieldtype);
        Date time = new Date();
        result.setNetime(time);
        result.setEmstime(time);


        result.setEventname(eventName);  //

   //     result.setMeName(me.getDn());
        result.setSourceobjectname(me.getDn());
        result.setObjecttype(SysConst.ALARM_OBJECTTYPE_NE);
  //      result.setObjectname(me.getName());

        result.setDescription(desc);
     //   result.setRepeattimes(1);

     //   result.setNotificationid(0);
        result.setAddintionalinfo(additionalInfo);
        result.setSeverity(severity);
        result.setCorrelationId(correlationID);
        return result;
    }



    public void sendGeneralMessage( int operationType, Object messageObj )
    {
        GeneralMessage msg = new GeneralMessage();
        msg.setMsgObj( messageObj );
        msg.setOperationType( operationType );
        try {
        //    SpringContext.getInstance().getJMSSupport().sendTopicMessage( SysConst.TOPIC_NAME_NMS_ALARM, msg );
        } catch (Exception e) {
            logger.error(e,e);
        }

//        if (operationType == GeneralMessage.ADD) {
//            if (emosListener == null) {
//               emosListener =  (AlarmListener)JVMRegistry.getInstance().getRegistedObject(AlarmListener.class);
//            }
//
//            if (emosListener != null) {
//                emosListener.processGeneralMessage(msg);
//            }
//        }

    }

  public BObject storeObjectByDn(JPAContext _context , BObject _obj,String topic) throws Exception {
    BObject result = null;
    EntityManager em = _context.getEntityManager();

    BObject bobjDB =// null;
      (BObject) JpaGenericExtDao.getInstance().getObject(em, _obj, "dn");

//    List<BObject> objs =this.findObjects(_context,"select c from "+_obj.getClass().getSimpleName()+" as c where c.dn = '"+_obj.getDn()+"'");
//    if (objs != null && objs.size() > 0) bobjDB = objs.get(0);
    if (bobjDB == null)
    {
        _context.prepare();
      result = createObject(_context, -1, _obj);
        _context.end();
      //  if (topic != null)
    //    SpringContext.getInstance().getJMSSupport().sendTopicMessage(topic,new GeneralMessage(result, GeneralMessage.ADD));

    } else {
      _obj.setId(bobjDB.getId());
          _context.prepare();
      result = JPAUtil.getInstance().storeObject(_context, -1, _obj);
          _context.end();
       // if (topic != null)
      //  SpringContext.getInstance().getJMSSupport().sendTopicMessage(topic,new GeneralMessage(result, GeneralMessage.UPDATE));
    }
    return result;
  }

 //   private AlarmListener emosListener = null;

    public int compareSeverity( int severitySrc, int severityDest )
    {
        if ( severitySrc < 0 )
        {
            severitySrc = 1000;
        }
        if ( severityDest < 0 )
        {
            severityDest = 1000;
        }
        if ( severitySrc == severityDest )
        {
            return 0;
        }
        if ( severitySrc > severityDest )
        {
            return -1;
        }
        return 1;
    }




//    public Vector<FilteredAlarm> m_filteredAlarms = null;
//    public  Vector<FilteredAlarm>  getFilteredAlarmsCache() {
//        return m_filteredAlarms;
//    }
   public boolean isAlarmFiltered(JPAContext context, Alarminformation _alarmEvent )
   {
//       if (m_filteredAlarms == null) {
//        try {
//            m_filteredAlarms = DBUtil.getInstance().queryAllObjects(context,
//                    FilteredAlarm.class);
//            if (m_filteredAlarms == null) m_filteredAlarms = new Vector<FilteredAlarm>();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//       }
//       String srcCorrelationid = _alarmEvent.getCorrelationId();
//       if( srcCorrelationid == null )
//       {
//           srcCorrelationid = "";
//       }
//
//       for( FilteredAlarm fa : m_filteredAlarms )
//       {
//           String tarCorrelationid = fa.getCorrelationId();
//           if( tarCorrelationid == null )
//           {
//               tarCorrelationid = "";
//           }
//
//           if( tarCorrelationid.equalsIgnoreCase( srcCorrelationid ) )
//           {
//               Date nowDate = new Date();
//               if(fa.getEndTime() != null) {
//                   return nowDate.after(fa.getStartTime()) && nowDate.before(fa.getEndTime());
//               } else {
//                  return nowDate.after(fa.getStartTime());
//               }
//           }
//       }

       return false;
   }


//    protected Curralarm produceCurrAlarm(JPAContext context,Alarminformation m_alarmEvent) throws AlarmProcessException {
//        logger.debug("produceCurrAlarm ");
//        if (m_alarmEvent.getSeverity() == SysConst.ALARM_SEVERITY_CLEAR) {
//           logger.debug(
//                            "the severity of the alarm is clear and we could not find the correlated curralarm, "
//                                    + "so we ignore the alarm event "
//                                    + m_alarmEvent.getCorrelationId());
//            return null;
//        }
//
//        Curralarm alarm = new Curralarm();
//
//        alarm.setIdentifier(m_alarmEvent.getCorrelationId());
//        if (m_alarmEvent.getCorrelationId() == null)
//            System.out.println();
//        alarm.setDn(m_alarmEvent.getCorrelationId());
//        alarm.setAlarmstatus(SysConst.CURRALARM_ALARMSTATUS_ACTIVE);
//        alarm.setStatus(SysConst.CURRALARM_STATUS_ACTIVE);
//
//        alarm.setFieldtype(m_alarmEvent.getFieldType());
//        alarm.setName(m_alarmEvent.getEventname());
//        alarm.setSeverity(m_alarmEvent.getSeverity());
//        Date time = new Date();
//        alarm.setFirstoccurrence(time);
//        alarm.setSummary(m_alarmEvent.getDescription());
//     //   alarm.setVendor(m_alarmEvent.getVendor());
//
//        alarm.setSourceobjectname(m_alarmEvent.getSourceobjectname());
//        if (alarm.getSourceobjectname() == null)  //处理定位信息存在ObjectName里面的情况
//            alarm.setSourceobjectname(m_alarmEvent.getObjectname());
//        alarm.setTally(1);
//        alarm.setAdditionalinfo(m_alarmEvent.getAddintionalinfo());
//    //    alarm.setAlarmtype(m_alarmEvent.getAlarmtype());
//    //    alarm.setVendor(m_alarmEvent.getVendor());
//
//        alarm.setObjecttype(m_alarmEvent.getObjecttype());
//
//        alarm.setEmsdn(m_alarmEvent.getEmsDn());
//
//
//        Managedelement m_me = null;
//        Physicalterminationpoint m_ptp = null;
//        long t1 = System.currentTimeMillis();
//         if (m_alarmEvent.getMenativeemsname() != null) {
//            m_me =  AlarmQuery.getInstance().getMEByNatvieName(context,m_alarmEvent.getMenativeemsname(),m_alarmEvent.getEmsDn());
//             long t2 = System.currentTimeMillis();
//             logger.debug("getMEByNatvieName:spend "+(t2-t1)+"ms");
//         }
//         if (m_alarmEvent.getObjecttype() == SysConst.ALARM_OBJECTTYPE_PTP) {
//            m_ptp = null;
//             t1 = System.currentTimeMillis();
//            m_ptp = AlarmQuery.getInstance().getPortByAlarmEvent(
//                    context, m_alarmEvent);
//             long t2 = System.currentTimeMillis();
//             logger.debug("getPortByAlarmEvent:spend "+(t2-t1)+"ms");
//
//        }
//
//        if (m_me != null){
//            alarm.setMedn(m_me.getDn());
//             alarm.setMename(m_me.getName());
//        }
//        else if (m_ptp != null)
//            alarm.setPtpdn(m_ptp.getDn());
//
//
//
//
//
//        alarm.setLastoccurrence(time);
//
//        alarm.setStatus(SysConst.CURRALARM_STATUS_ACTIVE);
//
//        alarm.setEmseventtime(m_alarmEvent.getEmstime());
//
//
//        return alarm;
//    }






} //end class
