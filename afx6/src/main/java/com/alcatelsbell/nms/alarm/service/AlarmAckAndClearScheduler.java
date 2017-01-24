package com.alcatelsbell.nms.alarm.service;




import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;

public class AlarmAckAndClearScheduler {
	private static AlarmAckAndClearScheduler instance = null;

	private Logger logger = null;

	private String m_header = "AlarmAckAndClearScheduler: ";

	private Scheduler m_scheduler;

	private CronTrigger m_trigger;

	private AlarmAckAndClearScheduler() {
	}

	public static synchronized AlarmAckAndClearScheduler getInstance() {
		if (instance == null) {
			instance = new AlarmAckAndClearScheduler();
		}
		return instance;
	}

	public void init(Logger logger, String hour, String minute) {
		this.logger = logger;
		startScheduler(hour, minute);
	}

	public Logger getLogger() {
		return logger;
	}

	protected void startScheduler(String hour, String minute) {
		try {
			m_scheduler = StdSchedulerFactory.getDefaultScheduler();
			JobDetail jobDetail = new JobDetail("AlarmAckAndClearScheduler",
					Scheduler.DEFAULT_GROUP, AlarmAckAndClearJob.class);

			try {
				m_trigger = new CronTrigger("AlarmAckAndClearCronTrigger",
						null, "0 " + minute + " " + hour + " * * ?");

				m_scheduler.scheduleJob(jobDetail, m_trigger);
			} catch (ParseException ex) {
				logger.error("Error parsing cron expr", ex);

			}
			m_scheduler.start();

		} catch (SchedulerException ex) {

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
