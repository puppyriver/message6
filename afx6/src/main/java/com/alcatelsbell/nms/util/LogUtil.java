package com.alcatelsbell.nms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Ronnie.Chen
 * Date: 12-11-8
 * Time: 上午10:49
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class LogUtil {
    private static ConcurrentHashMap<String,Log> logCache = new ConcurrentHashMap();
    public static void error(Object source,Object o,Throwable e) {
         findLog(source).error(o,e);
    }
    public static void error(Object source,Object o) {
        findLog(source).error(o);
    }


    public static void info(Object source,Object o) {
        findLog(source).info(o);
    }
    public static void debug(Object source,Object o) {
        Log log = findLog(source);
        if (log.isDebugEnabled())
            log.debug(o);
    }
    public static void trace(Object source,Object o) {
        findLog(source).trace(o);
    }
    public static void warn(Object source,Object o) {
        findLog(source).warn(o);
    }

    public static Log findLog(Object source) {
        String logName = getLogName(source);
        Log log = logCache.get(logName);
        if (log == null) {
            synchronized (logCache) {
                log = LogFactory.getLog(logName);
                logCache.put(logName, log);
            }
        }
        return log;
    }

    private static String getLogName(Object source) {
        if (source instanceof Class)
            return source.toString();
        if (source instanceof String)
            return ((String) source);
        return source.getClass().toString();
    }
}
