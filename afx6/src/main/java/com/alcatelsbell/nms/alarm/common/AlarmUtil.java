package com.alcatelsbell.nms.alarm.common;

/**
 * User: Ronnie.Chen
 * Date: 11-6-24
 */
import java.awt.*;
import java.beans.PropertyDescriptor;
import java.util.*;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;



public class AlarmUtil {
	public static final Color criticalColor = new Color(255,0,0);

	public static final Color majorColor = new Color(255, 165, 0);

	public static final Color minorColor = new Color(255, 240, 0);

	public static final Color warningColor = new Color(0,0,128);




	public final static Color ColorAck = Color.green;

	public final static Color ColorActive = Color.red;

	public final static Color ColorClear = Color.blue;

	public final static Color ColorAckAndClear = Color.gray;

	public final static String CURR_ALARM_TABLE = "curralarm";

	public final static String HISTROY_ALARM_TABLE = "historicalalarm";

	public static void initSeverityColor() {

//		String obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_CRITICAL);
//		criticalColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_MAJOR);
//		majorColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_MINOR);
//		minorColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_INFO);
//		infoColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_CLEAR);
//		clearColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_WARNING);
//		warningColor = new Color(Integer.valueOf(obj).intValue());
//
//		obj = AlarmGlobal.getInstance().getProperty(
//				AlarmConstants.PHS_ALARMCOLOR_INTERMEDIATE);
	}



	public static Color getStatusColor(int alarmStatus) {
		if (alarmStatus == SysConst.CURRALARM_STATUS_ACKED) {
			return AlarmUtil.ColorActive;
		} else if (alarmStatus == SysConst.CURRALARM_STATUS_ACTIVE) {
			return AlarmUtil.ColorActive;
		} else if (alarmStatus == SysConst.CURRALARM_STATUS_CLEARED) {
//			return AlarmUtil.ColorClear;
		        return AlarmUtil.ColorAck;
		} else if (alarmStatus == SysConst.CURRALARM_STATUS_ACKEDCLEARED) {
			return AlarmUtil.ColorAckAndClear;
		}
		return AlarmUtil.ColorActive;

	}

	public static Color getAckStatusColor(int alarmStatus, String ackMemo) {
		if (alarmStatus == SysConst.CURRALARM_STATUS_ACKED) {
			return AlarmUtil.ColorAck;
		}
		if (alarmStatus == SysConst.CURRALARM_STATUS_CLEARED) {
			if (ackMemo != null && !ackMemo.equals("")) {
				return AlarmUtil.ColorAck;
			}
		}
		return null;
	}

	// 返回不同告警等级的颜色，包括紧急告警（Critical）、主要告警（Major）、次要告警（Minor）
	public static Color getSeverityColor(int severity) {
		if (severity == SysConst.ALARM_SEVERITY_CRITICAL) {
			return criticalColor;
		} else if (severity == SysConst.ALARM_SEVERITY_MAJOR) {
			return majorColor;
		} else if (severity == SysConst.ALARM_SEVERITY_MINOR) {
			return minorColor;
		} else if(severity == SysConst.ALARM_SEVERITY_WARNING)
		    return new Color(0, 0, 128);
		else
			return Color.gray;
	}



	public static int getMaxSeverityByAlarms(Vector<Curralarm> alarms) {
		if (alarms == null)
			return -1;
		int severity = -1;
		for (Curralarm alarm : alarms) {
			if (severity < 0 || severity > alarm.getSeverity())
				severity = alarm.getSeverity();
		}
		return severity;
	}






	// 获得未确认告警
	public static Vector getUnAckalarms(Vector objVects) {
		if (objVects == null) {
			return null;
		}
		Vector returnVects = new Vector();
		for (int i = 0; i < objVects.size(); i++) {
			Curralarm objalarm = (Curralarm) objVects.elementAt(i);
			if (objalarm == null) {
				continue;
			}
			if (objalarm.getStatus() == SysConst.CURRALARM_STATUS_ACTIVE
					|| objalarm.getStatus() == SysConst.CURRALARM_STATUS_CLEARED) {
				returnVects.add(objalarm);
			}
		}
		return returnVects.size() == 0 ? null : returnVects;
	}

	// 获得清楚告警
	public static Vector getUnClearAlarms(Vector objVects) {
		if (objVects == null) {
			return null;
		}
		Vector returnVects = new Vector();
		for (int i = 0; i < objVects.size(); i++) {
			Curralarm objalarm = (Curralarm) objVects.elementAt(i);
			if (objalarm == null) {
				continue;
			}
			if (objalarm.getStatus() == SysConst.CURRALARM_STATUS_ACKED
					|| objalarm.getStatus() == SysConst.CURRALARM_STATUS_ACTIVE) {
				returnVects.add(objalarm);
			}
		}
		return returnVects.size() == 0 ? null : returnVects;
	}

	public static String getCurrAlarmName(Object _source) {
		String winTit = null;
		if (_source == null) {
			winTit = "";
		}

		return winTit;
	}


	public static String getHistoryAlarmName(Object _source) {
		String winTit = null;
		if (_source == null) {
			winTit = "";
		}
		return winTit;
	}



 }

