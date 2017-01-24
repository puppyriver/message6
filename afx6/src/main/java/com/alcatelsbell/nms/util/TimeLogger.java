package com.alcatelsbell.nms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Hashtable;

/**
 * User: Ronnie
 * Date: 12-5-18
 * Time: 上午9:40
 */
public class TimeLogger {
    public static final int LEVEL_ERROR = 0;
    public static final int LEVEL_WARNING = 1;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_DEBUG = 3;
    public static final int LEVEL_TRACE = 4;

    private static Hashtable<String,Long> table = new Hashtable<String, Long>();
    public static void start(String key) {
        key = Thread.currentThread().getName()+"_"+key;
        table.put(key,System.currentTimeMillis());
    }

    public static void end(String key,Log logger,int level) {
        end(key, logger,level,-1l);
    }

    public static void end(String key,Log logger,int level,long thresholdTimeInMilis) {
        key = Thread.currentThread().getName()+"_"+key;
        Long t1 = table.get(key);
        if (t1 == null) {
            log(logger,level,key+" not start");
            return;
        }
        long now = System.currentTimeMillis();
        long cost = now - t1;
        if (cost > thresholdTimeInMilis)
            log(logger,level,key+" spend "+ cost+" ms ");

        table.remove(key);
    }

    private static void log(Log logger,int level,String msg) {
        switch (level) {
            case LEVEL_ERROR: {
                logger.error(msg) ;
                return ;
            }
            case LEVEL_WARNING: {
                logger.warn(msg);
                return ;
            }
            case LEVEL_INFO: {
                logger.info(msg);
                return ;
            }
            case LEVEL_DEBUG: {
                logger.debug(msg);
                return ;
            }
            case LEVEL_TRACE: {
                logger.trace(msg);
                return ;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Log logger = LogFactory.getLog(TimeLogger.class);
        start("abc");
        Thread.sleep(1000);
        end("abc",logger,LEVEL_ERROR,900);
    }
}
