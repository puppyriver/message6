package com.alcatelsbell.nms.alarm.service.timefilter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedAlarmEvent implements Delayed {

	private static final long NANO_ORIGIN = System.nanoTime();

	final static long now() {
		return System.nanoTime() - NANO_ORIGIN;
	}

	private final long time;

	private final AlarmEvent alarmEvent;

	public DelayedAlarmEvent(AlarmEvent alarmEvent, long timeoutInSeconds) {
		this.time = now()
				+ TimeUnit.NANOSECONDS.convert(timeoutInSeconds,
						TimeUnit.SECONDS);
		this.alarmEvent = alarmEvent;
	}

	public AlarmEvent getAlarmEvent() {
		return this.alarmEvent;
	}

	public long getDelay(TimeUnit unit) {
		long d = unit.convert(time - now(), TimeUnit.NANOSECONDS);
		return d;
	}

	public int compareTo(Delayed other) {
		DelayedAlarmEvent that = (DelayedAlarmEvent) other;
		long diff = time - that.time;
		if (diff < 0)
			return -1;
		else if (diff > 0)
			return 1;
		else
			return 0;
	}

}
