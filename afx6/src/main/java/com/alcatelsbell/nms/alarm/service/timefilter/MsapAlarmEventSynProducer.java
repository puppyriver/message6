package com.alcatelsbell.nms.alarm.service.timefilter;

import java.util.concurrent.LinkedBlockingQueue;

import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;

public class MsapAlarmEventSynProducer {
	
	private LinkedBlockingQueue<Alarminformation> maspSynQueue ;
	
	public MsapAlarmEventSynProducer(LinkedBlockingQueue<Alarminformation> maspSynQueue){
		this.maspSynQueue = maspSynQueue;
	}
	
	public void addAlarmEvent(Alarminformation event){
//		maspSynQueue.add(event);
		try {
			maspSynQueue.put(event);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
