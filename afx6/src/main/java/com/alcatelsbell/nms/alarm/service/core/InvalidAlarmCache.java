package com.alcatelsbell.nms.alarm.service.core;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.RegisteredEventListeners;
//import net.sf.ehcache.management.ManagementService;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import javax.management.MBeanServer;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-6-14
 * Time: 下午8:07
 */

//
public class InvalidAlarmCache {
    private static InvalidAlarmCache ourInstance = new InvalidAlarmCache();

    public static InvalidAlarmCache getInstance() {
        return ourInstance;
    }


    private Cache cache = null;
    private InvalidAlarmCache() {
        //cache = new Cache("name",50000,,false,null,false,);
        String name = "InvalidAlarmCache";
        int maxElementsInMemory = 50000;
        long maxElementsOnDisk = 0;
        String diskStorePath = null;
        boolean overflowToDisk = false;
        boolean diskPersistent = false;
        boolean  eternal = true ; // true 数据永不过期 ， false 数据非永不过期
        long timeToIdleSeconds = 3600;    // 空闲失效时间 second  失效后，key还存在，但是get不出来，调用get之后，key也没了
        long timeToLiveSeconds = 24 * 3600 ; // 对象存活时间
        long diskExpiryThreadIntervalSeconds = 0;     // 轮询时间,默认是0
        RegisteredEventListeners listeners = null;
        cache = new Cache(name, maxElementsInMemory,
                MemoryStoreEvictionPolicy.LFU, overflowToDisk, diskStorePath, eternal,
                timeToLiveSeconds, timeToIdleSeconds, diskPersistent, diskExpiryThreadIntervalSeconds,listeners );

        CacheManager.getInstance().addCache(cache);

        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
    //    ManagementService.registerMBeans(CacheManager.getInstance(), mBeanServer, false, false, false, true);

    }

    public void addInvalidAlarmDn(String alarmDn) {
        cache.put(new Element(alarmDn,alarmDn));
    }
    public List getAllKeys() {
        return cache.getKeys();
    }

    public void clearAll() {
        cache.removeAll();
    }
    public boolean isInvalidAlarm(String alarmDn) {
        return cache.get(alarmDn) != null;       // 如果调用  isElementInMemory 的话，不会计入hit数
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            InvalidAlarmCache.getInstance().addInvalidAlarmDn(i+"");
            if (i < 10)
                InvalidAlarmCache.getInstance().isInvalidAlarm(i+"");
        }
        System.out.println(InvalidAlarmCache.getInstance().getAllKeys());
        Thread.sleep(90000l);
        System.out.println(InvalidAlarmCache.getInstance().getAllKeys().size());
        Thread.sleep(90000l);
        System.out.println(InvalidAlarmCache.getInstance().getAllKeys().size());


    }
}
