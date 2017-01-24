package com.alcatelsbell.nms.alarm.service;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.components.service.JPAContext;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
//import gxlu.afx.system.common.CommonClientEnvironment;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.*;

public class AlarmAckAndClearMgmt {
	private Log logger = null;

	private String m_header = "AlarmAckAndClearMgmt: ";

	private FaultRemoteIFC faultRemoteIFC = null;

	private int days = 30;

	public AlarmAckAndClearMgmt() {
		try {
			logger = LogFactory.getLog(this.getClass());
		//	faultRemoteIFC = (FaultRemoteIFC)CommonClientEnvironment.getServiceIFC(SysConst.SERVICE_NAME_ALARM);
		} catch (Exception ex) {
			logger.debug("construct AlarmAckAndClearMgmt error:" + ex);
			ex.printStackTrace();
		}

	}

	public void start() {

		ackAndClearAlarms();
	}

	private void ackAndClearAlarms() {
		Vector<Curralarm> curralarms = getAckAndClearAlarms();
		if (curralarms != null && curralarms.size() > 0) {
			for (Curralarm currAlarm : curralarms) {
				try {
					currAlarm.setLastmodified(new Date());
					currAlarm.setSummary(currAlarm.getSummary()+"\n 清除信息: 系统自动确认清除");
					faultRemoteIFC.ackAndClearCurrAlarm(-1, currAlarm);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}

	private Vector<Curralarm> getAckAndClearAlarms() {
		JPAContext context = JPAContext.prepareContext(-1);
		String sql = "select c from Curralarm c where c.lastmodified <= :date";
		List<Curralarm> alarms = null;
		try {
            Map map = new HashMap();
            map.put("date", getDeleteDate());
			alarms = JPAUtil.getInstance().findObjects(context, sql,null,map,null,null);
			context.end();
		} catch (Exception ex) {
			ex.printStackTrace();
            logger.error(ex.getMessage(),ex);
		} finally {
			context.release();
		}

		return alarms == null ? new Vector():new Vector(alarms);
	}

	private Date getDeleteDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -days);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
        return date;
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String toDate = "to_date('" + format.format(date)
//				+ "' , 'yyyy-mm-dd  hh24:mi:ss')";
//		return toDate;
	}
}
