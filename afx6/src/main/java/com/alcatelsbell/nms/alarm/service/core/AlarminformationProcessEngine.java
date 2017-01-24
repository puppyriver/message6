package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午1:27
 */
public class AlarminformationProcessEngine {
    private Log logger = LogFactory.getLog(getClass());
    static class InstHolder {
        public static AlarminformationProcessEngine inst = new AlarminformationProcessEngine();
    }
    public static AlarminformationProcessEngine getInstance() {
        return InstHolder.inst;
    }

    private ReentrantLock lock = new ReentrantLock();
    private HashMap<String,AlarminformationProcessor> processors = new HashMap<String, AlarminformationProcessor>();
    public void addAlarminformation(Alarminformation alarminformation) {
        String processKey = Tools.getProcessKey(alarminformation);
        AlarminformationProcessor processor = processors.get(processKey);
        if (processor == null) {
            lock.lock();
            try {
                processor = processors.get(processKey);
                if (processor == null) {
                    processor = new AlarminformationProcessor(processKey);
                    processors.put(processKey,processor);
                }
            } catch (Exception e) {
                logger.error(e,e);
            } finally {
                lock.unlock();
            }

        }

        processor.addAlarminformation(alarminformation);
    }


    static class Tools {
        public static String getProcessKey(Alarminformation alarminformation) {
            String correlationId = alarminformation.getCorrelationId();
            if (correlationId == null || correlationId.isEmpty())
                return alarminformation.getEmsDn()+"_"+alarminformation.getFieldType();
            int c = correlationId.charAt(correlationId.length()-1);
            c = c % 5;
            return alarminformation.getEmsDn()+"_"+alarminformation.getFieldType()+"_"+c;
        }
    }
}
