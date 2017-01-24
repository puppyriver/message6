package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.alarm.common.AlarmWrapper;
import com.alcatelsbell.nms.alarm.common.CurralarmInterceptor;
import com.alcatelsbell.nms.alarm.common.CurralarmInterceptorFactory;
import com.alcatelsbell.nms.alarm.common.DefaultCurralarmInterceptorFactory;
import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPASupportFactory;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.util.TimeLogger;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午11:15
 *
 * 对Curralarm的操作均通过CurralarmServer
 */
public class CurralarmServer {
    private static Log logger = LogFactory.getLog(CurralarmServer.class);
    public CurralarmMemoryCache2 alarmMemoryCache = null;
    protected CurralarmLockManager lockManager = new CurralarmLockManager();
    protected Vector<CurralarmChangeEventListener> curralarmChangeEventListeners
            = new Vector<CurralarmChangeEventListener>();
    protected CurralarmInterceptorFactory curralarmInterceptorFactory = null;
    protected AtomicLong serialId = null;

    private static CurralarmServer inst = null;
    public static CurralarmServer getInstance() {
        if (inst == null) {
            synchronized (CurralarmServer.class) {
                if (inst == null) {
                    String curralarmServerCls = SysProperty.getString("alarm.server.cls");
                    if (curralarmServerCls == null)
                        inst = new CurralarmServer();
                    else {
                        try {
                            inst = (CurralarmServer)Class.forName(curralarmServerCls).newInstance();
                        } catch ( Exception e) {
                            logger.error(e, e);
                        }
                    }
                }
            }
        }
        return inst;


    }
    protected CurralarmServer() {
        init();

    }

    protected void init() {
        try {
            initCleanJob();
        } catch (Exception e) {
            logger.error(e,e);
        }

        try {
            String factoryName = SysProperty.getString(SysConst.SYSTEM_PROPERTY_CURRALARM_INTERCEPTOR_FACTORY);
            if (factoryName == null)
                factoryName = "com.alcatelsbell.nms.service.alarm.vendors.VendorCurralarmInterceptorFactory";
            logger.info("Init CurralarmServer with :"+factoryName);
            curralarmInterceptorFactory = (CurralarmInterceptorFactory)Class.forName(factoryName).newInstance();
        } catch (Exception e) {
            System.err.println("[ERROR] FAILED TO INITIAL CurralarmInterceptorFactory,Using Default Instead");
            curralarmInterceptorFactory = new DefaultCurralarmInterceptorFactory();
        }

        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        List<Long> maxSerialId = null;
        try {
            maxSerialId = JPAUtil.getInstance().findObjects(jpaSupport,"select max(c.serial) from Curralarm c");
            if (maxSerialId != null && maxSerialId.size() > 0) {
                Long currentMaxSerialId = maxSerialId.get(0);
                if (currentMaxSerialId != null && currentMaxSerialId > 0)
                    serialId = new AtomicLong(currentMaxSerialId);
            }

        } catch (Exception e) {
            logger.error(e,e);

        } finally {
            jpaSupport.release();
        }

        if (serialId == null)
            serialId = new AtomicLong(0);
    }

    protected void initCleanJob() throws SchedulerException {
        Scheduler m_scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = new JobDetail("CurralarmServerCleanJob",
                Scheduler.DEFAULT_GROUP, CurralarmServerCleanJob.class);

        try {
            CronTrigger m_trigger = new CronTrigger("CurralarmServerCleanJobCronTrigger",
                    null, "0 " + 30 + " " + 2 + " * * ?");       //每天凌晨2:30
//            CronTrigger m_trigger = new CronTrigger("CurralarmServerCleanJobCronTrigger",
//                    null, "0 " + 10 + " " + 13 + " * * ?");       //每天20:10

            m_scheduler.scheduleJob(jobDetail, m_trigger);
        } catch (ParseException ex) {
            logger.error("Error parsing cron expr", ex);

        }
        m_scheduler.start();
    }
    public void runClearAlamrinformation() throws Exception {
        logger.info("begin runClearAlamrinformation");
        Map map = new HashMap();
        Date date = new Date(System.currentTimeMillis() - (3600l * 1000l * 24l * 14l));
        map.put("date", date);
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();

        try {
            jpaSupport.begin();
            JPAUtil.getInstance().executeQL(jpaSupport, "delete from Alarminformation cur where cur.createDate <:date",map);
            jpaSupport.end();
            logger.info("runClearAlamrinformation success");
        } catch (Exception e) {
            logger.error(e,e);
            jpaSupport.rollback();
        } finally {
            jpaSupport.release();
        }
    }
    public void runCleanCurralarm() throws Exception {
        logger.info(" **************************** runCleanCurralarm ******************************");
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            Map map = new HashMap();
            Date date = new Date(System.currentTimeMillis() - (3600l * 1000l * 24l * 30l));
            map.put("date", date);
            List<Curralarm> alarms = JPAUtil.getInstance().findObjects(jpaSupport,"select c from Curralarm c where (c.lastmodified is not null and c.lastmodified <= :date ) or (c.lastmodified is null and c.createDate <= :date )",null,map,null,null) ;
            logger.info(alarms == null ? 0: alarms.size()+" alarms to be cleaned");
            for (Curralarm alarm : alarms) {
                logger.info("delete alarm :"+alarm.getId()+":"+alarm.getDn()+":"+alarm.getCreateDate());
                CurralarmOperations.getInstance().manuClearCurrAlarm(alarm.getDn(),"EXPIRED");
            }

            date = new Date(System.currentTimeMillis() - (3600l * 1000l));
            map.put("date", date);





//            while (true) {
//                alarms = JPAUtil.getInstance().findObjects(jpaSupport,"select c from Curralarm c where (c.createDate <= :date and c.customer is null and c.fieldtype != 100)",null,map,0,1000) ;
//                if (alarms == null || alarms.size() == 0) break;
//                logger.info(alarms == null ? 0: alarms.size()+" alarms to be cleaned");
//                for (Curralarm alarm : alarms) {
//                    logger.info("delete alarm :"+alarm.getId()+":"+alarm.getDn()+":"+alarm.getCreateDate());
//                    CurralarmOperations.getInstance().manuClearCurrAlarm(alarm.getDn(),"EXPIRED");
//                }
//            }

            lockManager.lockOp();
            logger.info("begin to delete invalid alarms");
            try {
                jpaSupport.begin();
                JPAUtil.getInstance().executeQL(jpaSupport,"delete from Curralarm c where c.alarmstatus = "+SysConst.CURRALARM_ALARMSTATUS_INVALID);
                jpaSupport.end();
                logger.info("end delete invalid alarms");


                Map map2 = new HashMap();
                Date date2 = new Date(System.currentTimeMillis() - (3600l * 1000l * 24l * 7l));
                map2.put("date", date2);
                jpaSupport.begin();
                JPAUtil.getInstance().executeQL(jpaSupport,"delete from Curralarm c where c.createDate <:date and (c.severity = 2 or c.severity = 3 or c.severity = 4 )",map2);
                jpaSupport.end();
                logger.info("end delete expired alarms");


                alarmMemoryCache.reloadCache();
                logger.info("finish reloadCache");
            }catch (Throwable e) {
                logger.error(e,e);
            } finally {
                lockManager.unlockOp();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            jpaSupport.release();
        }

    }

    public void start() throws Exception {
        logger.info("Start CurralarmServer...");
        String cacheCls = SysProperty.getString("alarm.memoryCache.cls");
        if (cacheCls == null)
            alarmMemoryCache = new CurralarmMemoryCache2(lockManager);
        else {
            alarmMemoryCache = (CurralarmMemoryCache2)Class.forName(cacheCls).getConstructor(CurralarmLockManager.class).newInstance(lockManager);
        }
        alarmMemoryCache.initCache();
        logger.info("CurralarmMemoryCache init success!");
        logger.info("Start CurralarmServer Success!");



    }

    public void addCurralarmChangeEventListener(CurralarmChangeEventListener listener) {
        curralarmChangeEventListeners.add(listener);
    }

    public void removeCurralarmChangeEventListener(CurralarmChangeEventListener listener) {
        curralarmChangeEventListeners.remove(listener);
    }

    protected CurralarmInterceptor findCurralarmInterceptor(Curralarm alarm) {
         return curralarmInterceptorFactory.createCurralarmInterceptor(alarm);
    }


    public Curralarm createCurralarm(Curralarm alarm) throws IllegalCurralarmStateException {
        return createCurralarm(alarm,null);
    }


    public Curralarm createCurralarm(Curralarm alarm,CurralarmCreateWork work) throws IllegalCurralarmStateException {

        lockManager.checkOpLock();
        alarm.setSerial(serialId.incrementAndGet());
        AlarmWrapper wrapper = new AlarmWrapper(alarm);
        TimeLogger.start("createCurralarm0");
        if (!findCurralarmInterceptor(alarm).beforeAlarmCreate(wrapper)) {
            TimeLogger.end("createCurralarm0",logger,TimeLogger.LEVEL_INFO,20);
            return null;
        }
        TimeLogger.end("createCurralarm0",logger,TimeLogger.LEVEL_INFO,20);

        TimeLogger.start("createCurralarm1");
        JPASupport support = JPASupportFactory.createJPASupport();
        TimeLogger.end("createCurralarm1",logger,TimeLogger.LEVEL_INFO,10);
        //    lockManager.createRWLock(alarm.getDn());
        try {
            TimeLogger.start("createCurralarm2");
            support.begin();
            TimeLogger.end("createCurralarm2",logger,TimeLogger.LEVEL_INFO,10);

            TimeLogger.start("createCurralarm3");
            String ext = alarm.getExtendedattr();
            alarm.setExtendedattr(null);
            alarm = (Curralarm)JPAUtil.getInstance().createObject(support,-1,alarm);
            alarm.setExtendedattr(ext);
            TimeLogger.end("createCurralarm3",logger,TimeLogger.LEVEL_INFO,10);

            TimeLogger.start("createCurralarm4");
            support.end();
            TimeLogger.end("createCurralarm4",logger,TimeLogger.LEVEL_INFO,10);

            TimeLogger.start("createCurralarm5");
            alarmMemoryCache.addCurralarm(alarm);

            wrapper.setCurralarm(alarm);
            findCurralarmInterceptor(alarm).afterAlarmCreate(wrapper);


            TimeLogger.end("createCurralarm5",logger,TimeLogger.LEVEL_INFO,10);
            CurralarmChangeEvent event = new CurralarmChangeEvent(null,alarm, CurralarmChangeEvent.ALARM_EVENT_TYPE_ADD);
            if (work != null)
                work.prepareCurralarmChangeEvent(event);

            event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ENRICHED,"true");
            event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_CUSTOMERS,
                    wrapper.getAlarmCustomerAssigns());
            event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_AFFECTED_PRODUCTS,
                    wrapper.getAlarmProductAssigns());
            event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_EVENT,wrapper.getEvent());
            event.setProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ALARM_WRAPPER,wrapper);

            fireCurralarmChangeEvent(event);
        } catch (Exception e) {
            logger.error(e,e);
            support.rollback();
            logger.error("Failed to create alarm:"+ CommonUtil.toString(alarm));
        } finally {
            support.release();

        }

        return  alarm;
    }

    /**
     *
     * @param alarmDn
     * @param work
     * @return
     */
    public Curralarm curralarmWrite(String alarmDn,CurralarmWriteWork work) throws Exception {

        lockManager.checkOpLock();
        //   logger.info("****JMS ***** alarmDn:"+alarmDn+"  curralarmWrite  begin");
        TimeLogger.start("getlock");
        ReentrantReadWriteLock lock = lockManager.getRWLock(alarmDn);
        TimeLogger.end("getlock",logger,TimeLogger.LEVEL_INFO,10);

        Curralarm alarm = null;
        Curralarm oldAlarmCopy = null;
        int workType = CurralarmWriteWork.WORK_TYPE_NONE;

        TimeLogger.start("lock");
        lock.writeLock().lock();
        TimeLogger.end("lock",logger,TimeLogger.LEVEL_INFO,10);
        try {
            //logger.info("****JMS ***** alarmDn:"+alarmDn+"  alarmMemoryCache.readCurralarm(alarmDn)   ");

            TimeLogger.start("readCurralarm");
            alarm = alarmMemoryCache.readCurralarm(alarmDn);
            TimeLogger.end("readCurralarm",logger,TimeLogger.LEVEL_INFO,10);

            if (alarm == null)
                throw new IllegalCurralarmStateException(alarmDn,"alarm not existed!");
            // logger.info("****JMS ***** alarmDn:"+alarmDn+"  alarm copy   ");
            oldAlarmCopy = new Curralarm();
            oldAlarmCopy.copyShallow(alarm);
            oldAlarmCopy.setSeverity(alarm.getSeverity());
            oldAlarmCopy.setStatus(alarm.getStatus());
            oldAlarmCopy.setAdditionalinfo(alarm.getAdditionalinfo());
            oldAlarmCopy.setCustomer(alarm.getCustomer());
            oldAlarmCopy.setAcknowledged(alarm.getAcknowledged());
            oldAlarmCopy.setAlarmstatus(alarm.getAlarmstatus());
            oldAlarmCopy.setGrade(alarm.getGrade());
            oldAlarmCopy.setTally(alarm.getTally());
            oldAlarmCopy.setSerial(alarm.getSerial());
            // 以上只是确保几个主要的字段肯定被拷贝过去了，防止copyShallow方法不完�?
            //    logger.info("****JMS ***** alarmDn:"+alarmDn+"  以上只是确保几个主要的字段肯定被拷贝过去了，防止copyShallow方法不完�?  ");
            JPASupport support = null;
            try {
                workType = work.prepareBeforeOperation(alarm);

                alarm.setLastmodified(new Date());
                alarm.setSerial(serialId.incrementAndGet());
                if (workType != CurralarmWriteWork.WORK_TYPE_NONE) {
                    //       logger.info("****JMS ***** alarmDn:"+alarmDn+"  persistence curralarm begin workType:"+workType);
                    support = JPASupportFactory.createJPASupport();
                    TimeLogger.start("writeAlarm_"+workType+alarmDn);
                    support.begin();
                    if (workType == CurralarmWriteWork.WORK_TYPE_DELETE) {
                        findCurralarmInterceptor(alarm).beforeAlarmDelete(new AlarmWrapper(alarm));
                        JPAUtil.getInstance().removeObject(support,-1,alarm);

                    }
                    else if (workType == CurralarmWriteWork.WORK_TYPE_UPDATE) {
                        findCurralarmInterceptor(alarm).beforeAlarmUpdate(new AlarmWrapper(alarm));
                        alarm = (Curralarm)JPAUtil.getInstance().storeObject(support,-1,alarm);
                    }

//大量的无效告警导致频繁的数据库更新，效率低下
                    else if (workType == CurralarmWriteWork.WORK_TYPE_INVALID)
                        alarm = (Curralarm)JPAUtil.getInstance().storeObject(support,-1,alarm);
                    support.end();

                    TimeLogger.end("writeAlarm_"+workType+alarmDn,logger,TimeLogger.LEVEL_INFO,10);
                    //      logger.info("****JMS ***** alarmDn:"+alarmDn+"  persistence curralarm end workType:"+workType);
                }

            } catch (Exception e) {
                logger.error(e,e);
                if (support != null)
                    support.rollback();
                logger.error("Failed to update alarm:"+ CommonUtil.toString(alarm));
                throw e;
            } finally {
                if (support != null)
                    support.release();
            }


            // 更新内存
            if (workType == CurralarmWriteWork.WORK_TYPE_DELETE) {
                TimeLogger.start("WORK_TYPE_DELETE");
                alarmMemoryCache.removeCurralarm(alarm);
                lockManager.removeLock(alarmDn);
                TimeLogger.end("WORK_TYPE_DELETE",logger,TimeLogger.LEVEL_INFO,10);
            } else if (workType == CurralarmWriteWork.WORK_TYPE_UPDATE) {
                TimeLogger.start("WORK_TYPE_UPDATE");
                alarmMemoryCache.updateCurralarm(alarm);
                TimeLogger.end("WORK_TYPE_UPDATE",logger,TimeLogger.LEVEL_INFO,10);
            } else if (workType == CurralarmWriteWork.WORK_TYPE_INVALID) {
                TimeLogger.start("WORK_TYPE_INVALID");
                alarmMemoryCache.updateCurralarm(alarm);
                TimeLogger.end("WORK_TYPE_INVALID",logger,TimeLogger.LEVEL_INFO,10);
            }


        } catch (Exception e) {
            throw e;
        } finally {
            TimeLogger.start("unlock");
            lock.writeLock().unlock();
            TimeLogger.end("unlock",logger,TimeLogger.LEVEL_INFO,10);
        }


        int eventType = -1;
        if (workType == CurralarmWriteWork.WORK_TYPE_DELETE ||
                workType == CurralarmWriteWork.WORK_TYPE_UPDATE) {
            if (workType == CurralarmWriteWork.WORK_TYPE_DELETE )
                eventType = CurralarmChangeEvent.ALARM_EVENT_TYPE_DELETE;
            if (workType == CurralarmWriteWork.WORK_TYPE_UPDATE )
                eventType = CurralarmChangeEvent.ALARM_EVENT_TYPE_UPDATE;
            //   logger.info("****JMS ***** alarmDn:"+alarmDn+"   eventType is "+eventType);
            CurralarmChangeEvent event = new CurralarmChangeEvent(oldAlarmCopy,alarm,eventType);
            work.prepareCurralarmChangeEvent(event);

          //  TimeLogger.start("fireCurralarmChangeEvent");
            fireCurralarmChangeEvent(event);
          //  TimeLogger.end("fireCurralarmChangeEvent",logger,TimeLogger.LEVEL_INFO,10);
            //   logger.info("****JMS ***** alarmDn:"+alarmDn+"   curralarmWrite  end");
        }

        return  alarm;
    }

    public Curralarm curralarmRead(String alarmDn) {
        lockManager.checkOpLock();
        return alarmMemoryCache.readCurralarm(alarmDn);
    }

    public void updateCurralarmCache(Curralarm alarm) {
        lockManager.checkOpLock();
        alarmMemoryCache.updateCurralarm(alarm);
    }

    public void updateCurralarmCache(List<Curralarm> alarms) {
        lockManager.checkOpLock();
        if (alarms != null) {
            for (Curralarm alarm : alarms)
                alarmMemoryCache.updateCurralarm(alarm);
        }
    }
    /**
     *
     * @param alarmDn
     * @param work
     */
    public void curralarmRead(String alarmDn,CurralarmReadWork work) throws IllegalCurralarmStateException {
        lockManager.checkOpLock();
        ReentrantReadWriteLock lock = null;
        try {
            lock = lockManager.getRWLock(alarmDn);
        } catch (IllegalCurralarmStateException e) {
            logger.debug("Failed to find lock : "+alarmDn);
        }
        Curralarm alarm = null;
        if (lock != null) {
            lock.readLock().lock();
            alarm = alarmMemoryCache.readCurralarm(alarmDn);
        }
        try {
            work.readAlarmSafty(alarm);
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            if (lock != null)
                lock.readLock().unlock();
        }

    }

    public List getAllCurralarmDns() {
        List dns = new ArrayList();
        HashMap<String,List> map = alarmMemoryCache.getAllCurralarmKeys();
        Collection<List> vs = map.values();
        for (List l : vs) {
            if (l != null)
                dns.addAll(l);
        }
        return dns;
    }

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(100,100,10, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(),new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            logger.error(" too many fireCurralarmChangeEvent threads ,size = "+executor.getQueue().size());
        }
    });

    protected void fireCurralarmChangeEvent(CurralarmChangeEvent event) {

        //删除无效告警，不触发
        if (event.getEventType() == CurralarmChangeEvent.ALARM_EVENT_TYPE_DELETE
                && event.getCurralarm() != null &&
                event.getCurralarm().getAlarmstatus() == SysConst.CURRALARM_ALARMSTATUS_INVALID) {
            return;
        }

        //更新无效告警，不触发
        if (event.getEventType() == CurralarmChangeEvent.ALARM_EVENT_TYPE_UPDATE
                && event.getOldalarm() != null &&
                event.getOldalarm().getAlarmstatus() == SysConst.CURRALARM_ALARMSTATUS_INVALID) {
            return;
        }
        TimeLogger.start("fireCurralarmChangeEvent");
        executor.execute(new FireCurralarmChangeEventThread(event));
        TimeLogger.end("fireCurralarmChangeEvent",logger,TimeLogger.LEVEL_INFO,10);
    }
    /**
     * 暂时为单线程
     * @param event
     */
    private void doFireCurralarmChangeEvent(CurralarmChangeEvent event) {
        //logger.info("fireCurralarmChangeEvent:"+event);
        //logger.info("fireCurralarmChangeEventType:"+event.getEventType());
        for (CurralarmChangeEventListener listener : curralarmChangeEventListeners) {
            try {
                // logger.info("fire:"+listener);
                listener.fireOnCurralarmChange(event);
            } catch (Exception e) {
                logger.error(e,e);
            }
        }
    }

    class FireCurralarmChangeEventThread implements Runnable {
        private  CurralarmChangeEvent event;

        FireCurralarmChangeEventThread(CurralarmChangeEvent event) {
            this.event = event;
        }

        @Override
        public void run() {
            doFireCurralarmChangeEvent(event);
        }
    }
}
