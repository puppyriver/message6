package com.alcatelsbell.nms.alarm.service.timefilter;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ClearUpAlarmEventCacheJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		ClearUpAlarmEventCacheMgmt mgmt = new ClearUpAlarmEventCacheMgmt();
		mgmt.start();
	}

}
