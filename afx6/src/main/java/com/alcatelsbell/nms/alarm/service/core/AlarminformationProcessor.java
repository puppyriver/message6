package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.util.workline.WorkBus;
import com.alcatelsbell.nms.util.workline.WorkUnit;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午1:32
 * 告警处理器，一般用于处理某一类告警，一条告警事件属于一个确定了的告警处理器,一个告警处理器中的所有告警事件按顺序处理
 */
public class AlarminformationProcessor {
    private Log logger = null;
    private String processKey = null;
    private WorkBus<Alarminformation> workBus = null;

    public AlarminformationProcessor(String processKey) {
        this.processKey = processKey;
        logger =  LogFactory.getLog(getClass().getName()+"."+processKey);
        workBus = new WorkBus<Alarminformation>(1,AlarminformationWorker.class);

//        WorkBus slaWorkbus = null;
//        try {
//            Class slaCls = Class.forName("com.alcatelsbell.nms.service.SlaWorker");
//            slaWorkbus = new WorkBus(1,slaCls);
//            workBus.addNextBus(slaWorkbus);
//        } catch (ClassNotFoundException e) {
//            logger.error(e, e);
//        }
    }

    public void start() {

        workBus.start();
    }

    public void addAlarminformation(Alarminformation alarminformation) {
        int queueSize = workBus.getWorkContext().getQueueSize();
        System.out.println(new Date()+" "+ processKey+" queue size = "+queueSize+" severity = "+alarminformation.getSeverity());
    //    if (queueSize > 1000)
            logger.debug(processKey+" queue size = "+queueSize);

        if (queueSize > 500) {
            if (alarminformation.getSeverity() == SysConst.ALARM_SEVERITY_MAJOR
                    ||alarminformation.getSeverity() == SysConst.ALARM_SEVERITY_MINOR
                    ||alarminformation.getSeverity() == SysConst.ALARM_SEVERITY_WARNING
                    ||alarminformation.getSeverity() == SysConst.ALARM_SEVERITY_INTERMEDIATE )
                return;
        }
        if (queueSize > 5000)
            return;
        WorkUnit wu = new WorkUnit(alarminformation);
        workBus.addWorkUnit(wu);



    }




    public String getProcessKey() {
        return processKey;
    }

    public void setProcessKey(String processKey) {
        this.processKey = processKey;
    }

}
