package com.alcatelsbell.nms.alarm.service.timefilter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;


public enum AlarmEventClearCache {
	alarmEventClearCacheInstance;

    private Map<String, AlarmEvent> clearAlarmEventMap = new HashMap<String, AlarmEvent>();
    private Log  logger = LogFactory.getLog(getClass());
	public void addClearAlarmEvent(AlarmEvent alarmEvent) {
        if (!clearAlarmEventMap.containsKey(alarmEvent.getAlarmInformation().getCorrelationId()))
		    clearAlarmEventMap.put(alarmEvent.getAlarmInformation()
				.getCorrelationId(), alarmEvent);
        else  {
            clearAlarmEventMap.put(alarmEvent.getAlarmInformation()
                    .getCorrelationId(), alarmEvent);
//            logger.debug("clear event existed,so ignore it:"+alarmEvent.getAlarmInformation().getCorrelationId());
        }


	}

	public AlarmEvent getClearAlarmEvent(AlarmEvent alarmEvent) {
		return clearAlarmEventMap.get(alarmEvent.getAlarmInformation() 
				.getCorrelationId());
	}

	public Map<String, AlarmEvent> getClearAlarmEventMap() {
		return this.clearAlarmEventMap;
	}

	public void removeAlarmEvent(AlarmEvent alarmEvent) {
		clearAlarmEventMap.remove(alarmEvent.getAlarmInformation()
				.getCorrelationId());
	}
}
