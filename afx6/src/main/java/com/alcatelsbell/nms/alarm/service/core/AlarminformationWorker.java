package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.util.workline.WorkUnit;
import com.alcatelsbell.nms.util.workline.Worker;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time: 上午8:05
 */
public class AlarminformationWorker extends Worker{

    public static volatile long total = 0;
    public static volatile long created = 0;

    private Log logger = LogFactory.getLog(getClass());
    private long totalTime = 0;
    private long startTime = -1;
    @Override
    public void work(WorkUnit workUnit) throws Exception {
        total ++;
        if (startTime < 0) startTime = System.currentTimeMillis();

        long t1 = System.currentTimeMillis();
        Alarminformation alarminformation = (Alarminformation)workUnit.getWorkObject();

        String cls = SysProperty.getString("alarm.handler.cls");

        AlarminformationHandler handler = null;
        if (cls == null)
            handler = new AlarminformationHandler(alarminformation);
        else {
            handler = (AlarminformationHandler)Class.forName(cls).getConstructor(Alarminformation.class).newInstance(alarminformation);
        }
        handler.doProcess();
        if (handler.getCurralarm() != null)
            created++;
        long t2 = System.currentTimeMillis() - t1;
        if (logger.isDebugEnabled())
            logger.debug("process:"+alarminformation.getCorrelationId()+" spend:"+t2+" ms ");

        totalTime += (t2);
//        if (totalTime < 0) {
//            resetNumber();
//            totalTime =(t2-t1);
//        }
        if (getNumber() > 0 && getNumber() % 1000 == 0) {
            logger.info("eventworker"+this+" total time = "+totalTime+" ms, number = "+getNumber()+" average time = "+(totalTime/getNumber())+" ms");
            long percent = (totalTime * 100 / (System.currentTimeMillis() - startTime));
            logger.info("eventworker"+this+" estimate workload = "+percent+"%");
            if (percent > 80) {
                logger.error("AlarminformaionWoker Suffering High Workload !!!!!!!!!!!!!! Please Check System !!!!");
            }
        }

    }

    @Override
    public void doNoMoreWorkUnit() throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("No more work...");
    }

    @Override
    public void doBeforeDestory() throws Exception {
        if (logger.isDebugEnabled())
            logger.debug("doBeforeDestory");

    }
    public void finalize() {
        logger.debug(this.getName()+" finalized !");
    }
}
