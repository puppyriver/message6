package com.alcatelsbell.nms.common;

import java.util.Date;

public class DatePeriod extends Date
{
	private Date startDate = null;
	private Date endDate = null;

	public DatePeriod(Date date)
	{
		super(date.getTime());
	}

	public DatePeriod()
	{
	}

	public Date getStartDate()
	{
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}