package com.alcatelsbell.nms.alarm.service;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AlarmAckAndClearJob implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		AlarmAckAndClearMgmt mgmt = new AlarmAckAndClearMgmt();
		mgmt.start();

	}

}
