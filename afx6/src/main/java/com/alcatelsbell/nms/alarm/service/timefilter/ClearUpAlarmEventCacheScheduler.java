package com.alcatelsbell.nms.alarm.service.timefilter;

import com.alcatelsbell.nms.util.SysProperty;


import java.util.Calendar;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class ClearUpAlarmEventCacheScheduler {
	private static ClearUpAlarmEventCacheScheduler instance = null;

	private Scheduler m_scheduler;

	private SimpleTrigger m_trigger;

	private static final int seconds = SysProperty.getInt("alarm.clear.interval.seconds",60);
	


	public static synchronized ClearUpAlarmEventCacheScheduler getInstance() {
		if (instance == null)
			instance = new ClearUpAlarmEventCacheScheduler();
		return instance;
	}



	public void startScheduler() {
		try {
			m_scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail jobDetail = new JobDetail("ClearUpAlarmEventCacheJob",
					Scheduler.DEFAULT_GROUP, ClearUpAlarmEventCacheJob.class);
			m_trigger = new SimpleTrigger("ClearUpAlarmEventTrigger",
					"ClearUpAlarmEventCacheGroup", SimpleTrigger.REPEAT_INDEFINITELY,
					seconds * 1000L);
			Calendar c = Calendar.getInstance();
			c.add(Calendar.SECOND, 5);
			m_trigger.setStartTime(c.getTime());
			m_scheduler.scheduleJob(jobDetail, m_trigger);
			m_scheduler.start();
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
	}

	public void stopScheduler() {
		try {
			if (m_scheduler != null) {
				m_scheduler.shutdown();
				m_scheduler = null;
			}
		} catch (SchedulerException ex) {
			ex.printStackTrace();
		}
	}
}
