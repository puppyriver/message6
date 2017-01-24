package com.alcatelsbell.nms.alarm.service.timefilter;

import com.alcatelsbell.nms.alarm.common.DebugUtil;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.util.SysProperty;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.DelayQueue;


public class AlarmEventProducer {
    private DelayQueue<DelayedAlarmEvent> delayAlarmEvent;

	private static final int SECONDS = SysProperty.getInt("alarm.delay.seconds",10);

	private Log logger = LogFactory.getLog(getClass());

	public AlarmEventProducer(DelayQueue<DelayedAlarmEvent> delayAlarmEvent,
			Object _logger) {
		this.delayAlarmEvent = delayAlarmEvent;
	//	this.logger = logger;
	}
    public AlarmEventProducer(DelayQueue<DelayedAlarmEvent> delayAlarmEvent ) {
        this.delayAlarmEvent = delayAlarmEvent;

    }
	public void addNewAlarmEvent(AlarmEvent alarmEvent) {
            if (DebugUtil.isServiceDebug())
                delayAlarmEvent.put(new DelayedAlarmEvent(alarmEvent, SECONDS));
            else
		        delayAlarmEvent.put(new DelayedAlarmEvent(alarmEvent, SECONDS));
		logger.info("add a new alarm_event:"
				+ alarmEvent.getAlarmInformation().getCorrelationId()+" size="+delayAlarmEvent.size());
           }

	public void addClearAlarmEvent(AlarmEvent alarmEvent) {
		AlarmEventClearCache.alarmEventClearCacheInstance
				.addClearAlarmEvent(alarmEvent);
		logger.info("add a clear alarm_event:"
				+ alarmEvent.getAlarmInformation().getCorrelationId());
	}
}
