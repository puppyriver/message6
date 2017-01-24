package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEvent;
import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEventClearCache;
import com.alcatelsbell.nms.alarm.service.timefilter.DelayedAlarmEvent;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.concurrent.DelayQueue;

/**
 * User: Ronnie
 * Date: 11-11-27
 * Time:
 */
public class AlarminformationConsumer implements Runnable {


	private DelayQueue<DelayedAlarmEvent> delayAlarmEvent;

	private Log logger = LogFactory.getLog(getClass());

	public AlarminformationConsumer(
			DelayQueue<DelayedAlarmEvent> delayAlarmEvent) {
		this.delayAlarmEvent = delayAlarmEvent;

	}

	public void run() {
		while (true) {
			try {
                DelayedAlarmEvent delayedAlarmEvent = delayAlarmEvent.take();
                AlarmEvent alarmEvent = delayedAlarmEvent.getAlarmEvent();
        //        AlarmRecorder.getInstance().recordAlarminformation(alarmEvent.getAlarmInformation(),"Take from queue");

				if (AlarmEventClearCache.alarmEventClearCacheInstance
						.getClearAlarmEvent(alarmEvent) == null) {
					try {
						AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent
								.getAlarmInformation());
					} catch (Exception ex) {
						logger.error(ex,ex);
					}
					logger.info("receiver a alarmEvent:"
							+ alarmEvent.getAlarmInformation()
									.getCorrelationId());
              //      AlarmRecorder.getInstance().recordAlarminformation(alarmEvent.getAlarmInformation(), "Normal Process");

                } else {

                    AlarmEvent clearEvent = AlarmEventClearCache.alarmEventClearCacheInstance
                            .getClearAlarmEvent(alarmEvent);

                    logger.info("the event cleared in short time,so ignore it :"+alarmEvent.getAlarmInformation().getCorrelationId());



                    ///** tobe test

                    if (clearEvent == null){
                        AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
                        continue;
                    }
                    Date clearTime = clearEvent.getAlarmInformation().getEmstime();
                    Date eventTime =  alarmEvent.getAlarmInformation().getEmstime();
            //        AlarmRecorder.getInstance().recordAlarminformation(alarmEvent.getAlarmInformation(),"Find Clear Event ,cleartime="+ DateUtil.formatDate(clearTime,"yyyy-MM-dd HH:mm:ss")+",eventtime="+ DateUtil.formatDate(eventTime,"yyyy-MM-dd HH:mm:ss"));
                    if (clearTime.after(eventTime)) {

                        //先处理再清除
                        AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
                        AlarminformationProcessEngine.getInstance().addAlarminformation(clearEvent.getAlarmInformation());
                          //    AlarminformationProcessEngine.getInstance().addAlarminformation(clearEvent.getAlarmInformation());



                    } else if (clearTime.before(eventTime)){
                  //      AlarmRecorder.getInstance().recordAlarminformation(alarmEvent.getAlarmInformation(),"Still Normal Process ");
                        AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
                   //     AlarmRecorder.getInstance().recordAlarminformation(alarmEvent.getAlarmInformation(),"Remove Clear Event ");
                        AlarmEventClearCache.alarmEventClearCacheInstance
                                .removeAlarmEvent(alarmEvent);
                    } else {


                        //先处理再清除
               //         AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
                        AlarminformationProcessEngine.getInstance().addAlarminformation(clearEvent.getAlarmInformation());
                        AlarmEventClearCache.alarmEventClearCacheInstance
                                .removeAlarmEvent(alarmEvent);
                        logger.debug(" sametime two event : dn="+alarmEvent.getAlarmInformation().getDn());

                       // AlarminformationProcessEngine.getInstance().addAlarminformation(clearEvent.getAlarmInformation());


//                        Date cleartime = clearEvent.getCreateTime();
//                        Date alarmtime = alarmEvent.getCreateTime();
//                        if (clearTime != null && alarmtime != null) {
//                            if (alarmtime.before(cleartime)) {
//                                AlarminformationProcessEngine.getInstance().addAlarminformation(clearEvent.getAlarmInformation());
//                            } else {
//                                AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
//                            }
//                        }
                    }
                    //**/
				}

			} catch (Throwable ex) {
				logger.error(ex, ex);
			}
		}
	}

//    private void clearAndSave(Alarminformation alarminformation) {
//
//            JPASupport jpaSupport = JPASupportFactory.createJPASupport();
//            try {
//                jpaSupport.begin();
//                _event.setDn(SysUtil.nextDaySequenceDN("Alarminformation"));
//                _event.setId(null);
//                //       _event.setDn(SysUtil.nextDN());
//                JPAUtil.getInstance().createObject(jpaSupport,-1,_event);
//                jpaSupport.end();
//            } catch (Exception e) {
//                jpaSupport.rollback();
//                logger.error(e,e);
//            } finally {
//                jpaSupport.release();
//            }
//
//    }

//    private void checkQueue(String coreleationId) {
//        List list = new
//        for (DelayedAlarmEvent delayedAlarmEvent : delayAlarmEvent) {
//            AlarmEvent alarmEvent = delayedAlarmEvent.getAlarmEvent();
//            Alarminformation alarmInformation = alarmEvent.getAlarmInformation();
//            if (alarmInformation.getCorrelationId().equals(coreleationId)) {
//
//            }
//        }
//    }
}

