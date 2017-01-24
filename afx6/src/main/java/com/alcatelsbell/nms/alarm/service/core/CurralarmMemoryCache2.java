package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPASupportFactory;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.util.cache.Cache;
import com.alcatelsbell.nms.util.cache.ConcurrentHashMapCache;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//import net.sf.ehcache.Element;

//import net.sf.ehcache.Cache;
//import net.sf.ehcache.CacheManager;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午11:17
 */
public class CurralarmMemoryCache2 {
    private Log logger = LogFactory.getLog(getClass());
     private CurralarmLockManager lockManager = null;
     private Cache cache = null;
    private CurralarmServerStatistics statistics = CurralarmServerStatistics.getInstance();

//    public static class InstHolder {
//        public static CurralarmMemoryCache inst = new CurralarmMemoryCache();
//    }
//
//    public static CurralarmMemoryCache getInstance() {
//        return CurralarmMemoryCache.InstHolder.inst;
//    }

    private Lock lock = new ReentrantLock();

    public CurralarmMemoryCache2(CurralarmLockManager lockManager) {
        this.lockManager = lockManager;
    }



    private Cache findCache(Curralarm alarm) {
        logger.debug("find cache");
        if (cache == null) {
            lock.lock();
            try {

                if (cache == null) {
                    cache = new ConcurrentHashMapCache();
                }
            } catch (Exception e) {
                logger.error(e,e);
            } finally {
                lock.unlock();
            }

        }

        return cache;
    }
    public synchronized void reloadCache() throws Exception {
        lockManager.removeAllLock();
        findCache(null).removeAll();
        initCache();
    }
    public void initCache() throws Exception {
        // long count = JpaClient.getInstance().findObjectsCount("select count(c) from Curralarm c");
        int FETCH_SIZE = 100000;
        int begin = 0;
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            while (true) {
                List<Curralarm> alarms = JPAUtil.getInstance().findObjects(jpaSupport, "select c from Curralarm c", null, null, begin, FETCH_SIZE);
                logger.info("initCache:size="+(begin+(alarms == null ? 0 : alarms.size())));
                addCurralarms(alarms);
                if (alarms.size() < FETCH_SIZE)
                    break;
                begin = begin + FETCH_SIZE;
            }
        } catch (Exception e) {
            logger.error(e,e);
        } finally {
            jpaSupport.release();
        }



    }

    public void addCurralarms(List<Curralarm> alarms) {
        if (alarms != null) {
            for (Curralarm alarm : alarms) {
                addCurralarm(alarm);
            }
        }
    }

    public Curralarm readCurralarm(String dn) {
        long t1 = System.currentTimeMillis();
        Cache cache = findCache(null);
        long t2 = System.currentTimeMillis();
        Object object = cache.findObject(dn);

        long t3 = System.currentTimeMillis();

        if (t3 - t1 > 50) {
            logger.error(" readCurralarm: "+dn+"+spend too much time ,"+(t2-t1)+" and "+(t3-t2));
        }
        if (object != null)
            return (Curralarm) object;
        return null;
    }

    public void addCurralarm(Curralarm alarm) {
        if (alarm != null) {
            Cache cache = findCache(alarm);
            if (cache != null) {
                cache.addObject(alarm.getDn(),alarm);
                try {
                    statistics.addCurralarm(alarm);
                } catch (Exception e) {
                    logger.error(e,e);
                }
                try {
                    lockManager.createRWLock(alarm.getDn());
                } catch (IllegalCurralarmStateException e) {
                    logger.error(e,e);
                }
                //   logger.debug("addCurralarm:"+alarm.getId()+" cache size = "+cache.getSize());

            }
        }
    }



    public void removeCurralarm(Curralarm alarm) {
        Cache cache = findCache(alarm);
        if (cache != null) {
            cache.removeObject(alarm.getDn());
            try {
                statistics.removeCurralarm(alarm);
            } catch (Exception e) {
                logger.error(e,e);
            }
            if (logger.isDebugEnabled())
                logger.debug("removeCurralarm:"+alarm.getId()+" cache size = "+cache.getSize());

        }
    }

    public void updateCurralarm(Curralarm alarm) {
        Cache cache = findCache(alarm);
        Curralarm oldAlarm = readCurralarm(alarm.getDn());
        if (cache != null) {
            cache.updateObject(alarm.getDn(),(alarm));
            try {
                statistics.updateCurralarm(oldAlarm,alarm);
            } catch (Exception e) {
                logger.error(e,e);
            }
            logger.debug("updateCurralarm:"+alarm.getId()+" cache size = "+cache.getSize());
        }
    }

//    public long getCurralarmSize() {
//        String[] cacheNames = manager.getCacheNames();
//        long total = 0;
//        if (cacheNames != null) {
//            for (String cacheName : cacheNames) {
//                Cache cache = manager.getCache(cacheName);
//
//                int storeSize = cache.getDiskStoreSize();
//                long memSize = cache.getMemoryStoreSize();
//
//                total+= storeSize;
//                total+= memSize;
//            }
//        }
//        return total;
//    }

    public HashMap<String,List> getAllCurralarmKeys() {
        HashMap<String,List> map = new HashMap<String, List>();
        map.put("temp",cache.getKeys());
        return map;

    }

}


