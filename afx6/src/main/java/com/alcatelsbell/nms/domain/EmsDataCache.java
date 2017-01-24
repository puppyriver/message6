package com.alcatelsbell.nms.domain;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.valueobject.sys.Ems;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 11-11-30
 * Time: 下午3:41
 */
public class EmsDataCache {
    private static class InstHolder {
        public static EmsDataCache inst = new EmsDataCache();
    }

    public static EmsDataCache getInstance() {
        return InstHolder.inst;
    }

    private Log logger = LogFactory.getLog(getClass());
    private HashMap<String,Ems> emsCache = new HashMap<String, Ems>();
    private ReentrantLock lock = new ReentrantLock();
    public Ems getEMS(String emsdn) {
        if (emsdn == null) return null;
        if (emsCache.get(emsdn) == null) {
            Ems ems  = null;
            try {
                ems = (Ems) JpaClient.getInstance().findObjectByDN(Ems.class,emsdn);
            } catch (Exception e) {
                logger.error(e,e);
            }
            if (ems != null) {
                lock.lock();
                try {
                    emsCache.put(emsdn,ems);
                } catch (Exception e) {
                    logger.error(e,e);
                } finally {
                    lock.unlock();
                }

            } else {
                if (!"NMS".equals(emsdn))
                    logger.error("Failed to find emsdn:"+emsdn);
            }
             return ems;
        }else{
        	return emsCache.get(emsdn);
        }
    }
}
