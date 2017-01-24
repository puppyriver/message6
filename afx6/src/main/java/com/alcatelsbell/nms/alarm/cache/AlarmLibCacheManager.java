package com.alcatelsbell.nms.alarm.cache;

/**
 * User: Ronnie
 * Date: 11-11-15
 * Time: 上午10:53
 */

import com.alcatelsbell.nms.common.SpringContext;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.db.components.client.JpaClient;

import com.alcatelsbell.nms.domain.EmsDataCache;
import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.util.cache.Cache;
import com.alcatelsbell.nms.util.cache.ConcurrentHashMapCache;

import com.alcatelsbell.nms.valueobject.alarm.VendorAlarmLib;
import com.alcatelsbell.nms.valueobject.sys.Ems;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/**
 * User: Ronnie.Chen
 * Date: 11-9-21
 */
public class AlarmLibCacheManager implements MessageListener {
    private Log logger = LogFactory.getLog(getClass());
  //  private static CacheManager manager = CacheManager.getInstance();
    private ConcurrentHashMap<String,Cache> manager = new ConcurrentHashMap<String, Cache>();

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
                try {
                     com.alcatelsbell.nms.common.message.GeneralMessage generalMessage = ( com.alcatelsbell.nms.common.message.GeneralMessage) ((
                            ObjectMessage) message).getObject();

                    Object obj = generalMessage.getMsgObj();
                    logger.debug("onMessage: obj="+ obj +" opType="+generalMessage.getOperationType());

                    if (obj instanceof com.alcatelsbell.nms.valueobject.alarm.VendorAlarmLib) {
                        if (generalMessage.getOperationType() == GeneralMessage.ADD) {
                            addVendorAlarmLib((VendorAlarmLib)obj);
                        } else if (generalMessage.getOperationType() == GeneralMessage.DELETE) {
                            removeVendorAlarmLib((VendorAlarmLib)obj);
                        }  else if (generalMessage.getOperationType() == GeneralMessage.UPDATE) {
                            updateVendorAlarmLib((VendorAlarmLib)obj);
                        }
                    }
                } catch (Exception e) {
                    logger.error(e,e);
                }
        }

    }

    public static class InstHolder {
        public static AlarmLibCacheManager inst = new AlarmLibCacheManager();
    }

    public static AlarmLibCacheManager getInstance() {
        return InstHolder.inst;
    }

    private Lock lock = new ReentrantLock();

    public AlarmLibCacheManager() {
//        try {
//            SpringContext.getInstance().getJMSSupport().addTopicSubscriber(SysConst.TOPIC_NAME_NMS_GENERAL,this);
//        } catch (Exception e) {
//            logger.error( e, e);
//        }
    }



    private Cache findCache(String vendorDn) {
        String name = "NMS.ALARM.ALARMLIB."+vendorDn;
        Cache cache = manager.get(name);
        if (cache == null) {
            lock.lock();
            try {
                int maxElementsInMemory = SysProperty.getInt("VendorAlarmLib_MaxElementsInMemory", 500);
                boolean overflowToDisk = true;
                boolean eternal = true;
                cache = new ConcurrentHashMapCache();
                manager.put(name, cache);
            } catch ( Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }  finally {
                 lock.unlock();
            }

        }

        return cache;
    }

    public void initCache() throws Exception {
        // long count = JpaClient.getInstance().findObjectsCount("select count(c) from VendorAlarmLib c");
        int FETCH_SIZE = 10000;
        int begin = 0;
        while (true) {
            List<VendorAlarmLib> alarms = JpaClient.getInstance().findObjects("select c from VendorAlarmLib c", null, null, begin, FETCH_SIZE);
            logger.info("initCache:size="+(begin+(alarms == null ? 0 : alarms.size())));
            addVendorAlarmLibs(alarms);
            if (alarms.size() < FETCH_SIZE)
                break;
            begin = begin + FETCH_SIZE;
        }

    }

    public void addVendorAlarmLibs(List<VendorAlarmLib> alarms) {
        if (alarms != null) {
            for (VendorAlarmLib alarm : alarms) {
                addVendorAlarmLib(alarm);
            }
        }
    }

//    public VendorAlarmLib readVendorAlarmLib(String vendordn,String alarmName) {
//        Element element = findCache(vendordn).get(alarmName);
//        if (element != null)
//            return (VendorAlarmLib) element.getValue();
//        return null;
//    }


    public VendorAlarmLib readVendorAlarmLib(String emsdn,String alarmName) {
        Ems ems = EmsDataCache.getInstance().getEMS(emsdn);
        if (ems == null || ems.getVendordn() == null) return null;
        Object element = findCache(ems.getVendordn()).findObject(alarmName);
        if (element != null)
            return (VendorAlarmLib) element ;
        return null;
    }
    public void addVendorAlarmLib(VendorAlarmLib alarmLib) {
        if (alarmLib != null) {
            Cache cache = findCache(alarmLib.getVendorDn());
            if (cache != null&&alarmLib.getAlarmName()!=null) {
                cache.addObject(alarmLib.getAlarmName(),alarmLib);
                logger.debug("addVendorAlarmLib:"+alarmLib.getVendorDn()+":"+alarmLib.getAlarmName()+" cache size = "+cache.getSize());

            }
        }
    }


    public void removeVendorAlarmLib(VendorAlarmLib alarm) {
        Cache cache = findCache(alarm.getVendorDn());
        if (cache != null) {
            cache.removeObject(alarm.getAlarmName());
            logger.debug("removeVendorAlarmLib:"+alarm.getVendorDn()+":"+alarm.getAlarmName()+" cache size = "+cache.getSize());
        }
    }

    public void updateVendorAlarmLib(VendorAlarmLib alarm) {
        Cache cache = findCache(alarm.getVendorDn());
        if (cache != null) {
            cache.addObject(alarm.getAlarmName(),alarm);
            logger.debug("updateVendorAlarmLib:"+alarm.getVendorDn()+":"+alarm.getAlarmName()+" cache size = "+cache.getSize());
        }
    }

    public static void main(String[] args) throws Exception {
         AlarmLibCacheManager.getInstance().initCache();
        System.out.println(AlarmLibCacheManager.getInstance().findCache(null).getSize());
        System.out.println("");
        for (int i = 0; i < 10000000; i++) {
            VendorAlarmLib alarm = new VendorAlarmLib();
            alarm.setAlarmName("id"+i);
         //   CurralarmCacheManager.getInstance().initCache();
            AlarmLibCacheManager.getInstance().addVendorAlarmLib(alarm);
            if (i % 1000 == 0) {
                System.out.println(i+"  "+Runtime.getRuntime().freeMemory());
            }
        }

    }
}
