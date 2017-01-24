package com.alcatelsbell.nms.valueobject.domain;

import java.io.Serializable;
import java.util.Date;

public class Performance implements Serializable {
	
	private String businessdn;			//业务dn
	
	private String devicedn;			//设备dn
	
	private String performanceparameter;	//性能参数	Ping	Flow
	
	private String portdn;			//端口dn
	
	private boolean timeFlag ;   //true 临时时间段     false 固定时间
	
	private Date starttime;				//临时开始时间
	
	private Date endtime;				//临时结束时间
	
	private int rbTime;					//固定时间

	public String getBusinessdn() {
		return businessdn;
	}

	public void setBusinessdn(String businessdn) {
		this.businessdn = businessdn;
	}

	public String getDevicedn() {
		return devicedn;
	}

	public void setDevicedn(String devicedn) {
		this.devicedn = devicedn;
	}

	public String getPerformanceparameter() {
		return performanceparameter;
	}

	public void setPerformanceparameter(String performanceparameter) {
		this.performanceparameter = performanceparameter;
	}

	public String getPortdn() {
		return portdn;
	}

	public void setPortdn(String portdn) {
		this.portdn = portdn;
	}

	public boolean isTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(boolean timeFlag) {
		this.timeFlag = timeFlag;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public int getRbTime() {
		return rbTime;
	}

	public void setRbTime(int rbTime) {
		this.rbTime = rbTime;
	}
	
	
}
