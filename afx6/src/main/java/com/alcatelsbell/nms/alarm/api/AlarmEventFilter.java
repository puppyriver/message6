package com.alcatelsbell.nms.alarm.api;

import java.util.Hashtable;
import java.util.Map;

import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEventProducer;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;

/**
 * @author:LiXiaoBing
 * @date:2011-7-20
 * @time:上午09:56:09
 */
public interface AlarmEventFilter {
	
	public void alarmFilterProcessor(Alarminformation alarmInfo, AlarmEventProducer alarmEventProducer, Map vendorMap);
	
}
