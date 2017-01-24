package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPASupportFactory;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午11:17
 */
public class CurralarmMemoryCache {
    private Log logger = LogFactory.getLog(getClass());
    private static CacheManager manager = CacheManager.create();
    private CurralarmLockManager lockManager = null;


//    public static class InstHolder {
//        public static CurralarmMemoryCache inst = new CurralarmMemoryCache();
//    }
//
//    public static CurralarmMemoryCache getInstance() {
//        return CurralarmMemoryCache.InstHolder.inst;
//    }

    private Lock lock = new ReentrantLock();

    CurralarmMemoryCache(CurralarmLockManager lockManager) {
         this.lockManager = lockManager;
    }


    private Cache findCache(Curralarm alarm) {
        logger.debug("find cache");
        String name = "NMS.ALARM.CACHE.V2";
        Cache cache = manager.getCache(name);
        logger.debug("get cache:"+cache);
        if (cache == null) {
            lock.lock();
            try {
                cache = manager.getCache(name);
                if (cache == null) {
                    int maxElementsInMemory = SysProperty.getInt("Curralarm_MaxElementsInMemory", 1000000);
                    boolean overflowToDisk = true;
                    boolean eternal = true;
                    cache = new Cache(name, maxElementsInMemory, overflowToDisk, eternal, -1, -1);
                    manager.addCache(cache);
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
        Element element =cache.get(dn);
        long t3 = System.currentTimeMillis();

        if (t3 - t1 > 50) {
            logger.error(" readCurralarm: "+dn+"+spend too much time ,"+(t2-t1)+" and "+(t3-t2));
        }
        if (element != null)
            return (Curralarm) element.getValue();
        return null;
    }

    public void addCurralarm(Curralarm alarm) {
        if (alarm != null) {
            Cache cache = findCache(alarm);
            if (cache != null) {
                cache.put(pack(alarm));
                try {
                    lockManager.createRWLock(alarm.getDn());
                } catch (IllegalCurralarmStateException e) {
                    logger.error(e,e);
                }
            //   logger.debug("addCurralarm:"+alarm.getId()+" cache size = "+cache.getSize());

            }
        }
    }

    private Element pack(Curralarm alarm) {
        return new Element(alarm.getDn(), alarm);
    }

    public void removeCurralarm(Curralarm alarm) {
        Cache cache = findCache(alarm);
        if (cache != null) {
            cache.remove(alarm.getDn());
            if (logger.isDebugEnabled())
             logger.debug("removeCurralarm:"+alarm.getId()+" cache size = "+cache.getSize());

        }
    }

    public void updateCurralarm(Curralarm alarm) {
        Cache cache = findCache(alarm);
        if (cache != null) {
            cache.put(pack(alarm));
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
        String[] cacheNames = manager.getCacheNames();
        if (cacheNames != null) {
            for (String cacheName : cacheNames) {
                Cache cache = manager.getCache(cacheName);

                map.put(cacheName,cache.getKeys());
            }
        }

        return map;

    }

    public static void main(String[] args) throws InterruptedException {
        CurralarmLockManager lockManager = new CurralarmLockManager();
        final CurralarmMemoryCache cache = new CurralarmMemoryCache(lockManager);
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            Curralarm alarm = new Curralarm();
            alarm.setDn("dn:"+i);
         //   cache.findCache(alarm).put(cache.pack(alarm));
            long t = System.currentTimeMillis();
            cache.addCurralarm(alarm);
            long tt = System.currentTimeMillis();
            if (tt - t > 10)
            System.out.println(i+" _ "+(tt-t));
        }
        long t2 = System.currentTimeMillis();
        System.out.println("spend :"+(t2-t1)+"ms");
        System.out.println(Runtime.getRuntime().totalMemory()/1000000);

        new Thread(new Runnable(){
            @Override
            public void run() {
                long t3 = System.currentTimeMillis();
                for (int i = 0; i < 50000; i++) {
                    Curralarm al = cache.readCurralarm("dn:"+i);
                }

                long t4 = System.currentTimeMillis();
                System.out.println("t1:"+(t4-t3));

            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                long t3 = System.currentTimeMillis();
                for (int i = 0; i < 50000; i++) {
                    Curralarm al = cache.readCurralarm("dn:" + i);
                    if (i % 10 == 0)
                        cache.removeCurralarm(al);
                }

                long t4 = System.currentTimeMillis();
                System.out.println("t2:"+(t4-t3));

            }
        }).start();
        Thread.sleep(100000l);

    }
}


