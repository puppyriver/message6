package com.alcatelsbell.nms.alarm.service.timefilter;

import com.alcatelsbell.nms.alarm.service.core.AlarminformationProcessEngine;
import com.alcatelsbell.nms.util.SysProperty;

import java.util.*;


public class ClearUpAlarmEventCacheMgmt {
  //  private IAlarmModel m_alarmMgmt;

    public ClearUpAlarmEventCacheMgmt( ) {
     //   m_alarmMgmt = mgmt;
    }


    public void start() {
        Map<String, AlarmEvent> alarmEventMap = AlarmEventClearCache.alarmEventClearCacheInstance
                .getClearAlarmEventMap();
        synchronized (alarmEventMap) {
            long t1 = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            int delaySecond = SysProperty.getInt("alarm.clear.delay.seconds");
            if (delaySecond < 0)
                calendar.add(Calendar.MINUTE, -2);
            else
                calendar.add(Calendar.SECOND, -delaySecond);
            Date date = calendar.getTime();


            List<AlarmEvent> needClearAlarmEvent = new ArrayList<AlarmEvent>();
            List<Map.Entry> entrys = new ArrayList<Map.Entry>();
            for (Map.Entry<String, AlarmEvent> entry : alarmEventMap.entrySet()) {
                 entrys.add(entry);
            }
            for (Map.Entry<String, AlarmEvent> entry : entrys) {
                AlarmEvent alarmEvent = entry.getValue();
                // 如果clear告警发生在3分钟之前,则发出清除告警
                if (alarmEvent.getCreateTime().before(date)) {
                    try {

                     //   if (DebugUtil.useCore)
                            AlarminformationProcessEngine.getInstance().addAlarminformation(alarmEvent.getAlarmInformation());
//                        else
//                            m_alarmMgmt.addAlarmEvent(alarmEvent
//                                 .getAlarmInformation());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    needClearAlarmEvent.add(alarmEvent);
                }
            }
            for (AlarmEvent alarmEvent : needClearAlarmEvent) {
                AlarmEventClearCache.alarmEventClearCacheInstance
                        .removeAlarmEvent(alarmEvent);
            }
        }
    }
}
