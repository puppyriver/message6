package com.alcatelsbell.nms.alarm.service.core;

import com.alcatelsbell.nms.common.JMSSupport;
import com.alcatelsbell.nms.common.SpringContext;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * User: Ronnie
 * Date: 11-11-26
 * Time: 下午10:50
 *
 * 定义需要被发出JMS消息的事件,在什么样的告警变化下，需要发出什么样的告警事件，可以灵活修改
 */
public class CurralarmJMSNotifier implements CurralarmChangeEventListener{
//    private JMSSupport jmsUtil = SpringContext.getInstance().getJMSSupport();
    private Log logger = LogFactory.getLog(getClass());
//
//    private long genSessionKey() {
//        return SysUtil.nextLongUUId("CURRALARM_JMS_NOTIFIER");
//    }

    @Override
    public void fireOnCurralarmChange(CurralarmChangeEvent event) {
        try {
            int eventType = event.getEventType();
            //logger.info("*****JMS******             eventType:"+eventType);
            switch (eventType) {
                case CurralarmChangeEvent.ALARM_EVENT_TYPE_ADD : {
                    Curralarm alarm = event.getCurralarm();
                    if (alarm.getAlarmstatus() == SysConst.CURRALARM_ALARMSTATUS_VALID) {
                        sendTopic(new GeneralMessage(event.getCurralarm(), GeneralMessage.ADD));
                    }
                    break;
                }
                case CurralarmChangeEvent.ALARM_EVENT_TYPE_UPDATE : {
//                    Object enriched = event.getProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_ENRICHED);
//                    if (enriched != null) {
//                        logger.info("sendGeneralMessage:ADD:curralarmid:"+event.getCurralarm().getId()+"  curralarmdn:"+event.getCurralarm().getDn());
//                        jmsUtil.sendTopicMessage(SysConst.TOPIC_NAME_NMS_ALARM,new GeneralMessage(event.getCurralarm(), GeneralMessage.ADD));
//                    } else

                    if (event.getProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_SEVERITY_CHANGE) != null
                            || event.getProperty(SysConst.CURRALARM_CHANGE_EVENT_PROPERTY_KEY_STATUS_CHANGE) != null){
                        logger.info("sendGeneralMessage:UPDATE:curralarmid:"+event.getCurralarm().getId()+" curralarmdn:"+event.getCurralarm().getDn());
                        sendTopic(new GeneralMessage(event.getCurralarm(), GeneralMessage.UPDATE));
                    }
                    break;
                }
                case CurralarmChangeEvent.ALARM_EVENT_TYPE_DELETE : {
                    logger.info("sendGeneralMessage:DELETE:curralarmid:"+event.getCurralarm().getId()+":  curralarmdn"+event.getCurralarm().getDn());
                    sendTopic(new GeneralMessage(event.getCurralarm(), GeneralMessage.DELETE));
                    break;
                }


            }
        } catch (Exception e) {
            logger.error(e,e);
        }
    }

    private void sendTopic (GeneralMessage message) throws Exception {
      //  message.setSessionKey(genSessionKey());
        GeneralMessageArray.getInstance().addGeneralMessage(message);

      //  jmsUtil.sendTopicMessage(SysConst.TOPIC_NAME_NMS_ALARM,message);
    }





    public static void main(String[] args) {
        System.out.println(new Curralarm().getSerial());
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                long l = (((Curralarm)o1).getSerial() - ((Curralarm)o2).getSerial()) ;
                if (l > 0) return 1;
                if (l == 0) return 0;
                if (l < 0) return -1;
                return 0;
            }
        });

        for (int i = 0; i < 100; i++) {
            long serial =System.currentTimeMillis() % 100;
            Curralarm alarm = new Curralarm();
            alarm.setId((long)i);
            alarm.setSerial(i);
            set.add(alarm);
//            GeneralMessage gm = new GeneralMessage(alarm,GeneralMessage.ADD);
//            set.add(gm);
        }

        Object[] array = set.toArray();
        for(Object o : array) {

        }
    }
}
