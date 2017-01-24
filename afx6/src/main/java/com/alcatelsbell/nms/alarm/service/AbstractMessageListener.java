package com.alcatelsbell.nms.alarm.service;


import com.alcatelsbell.nms.alarm.service.core.AlarminformationWorker;
import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEvent;
import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEventProducer;
import com.alcatelsbell.nms.alarm.service.timefilter.MsapAlarmEventSynProducer;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.util.SysProperty;
import com.alcatelsbell.nms.valueobject.alarm.Alarminformation;
import com.alcatelsbell.nms.valueobject.sys.Ems;
import com.alcatelsbell.nms.valueobject.sys.Vendor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jms.MessageListener;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AbstractMessageListener  implements MessageListener {
//	protected IAlarmModel m_alarmMgmt = null;
	protected AlarmEventProducer alarmEventProducer = null;
	protected MsapAlarmEventSynProducer msapAlarmEventSynProducer = null;
   
	protected Log logger  = LogFactory.getLog(getClass());
    
    protected com.alcatelsbell.nms.alarm.api.AlarmEventFilter filter = null;
    private ThreadPoolExecutor pool = null;
    private LinkedBlockingQueue<Runnable> queue = null;
    protected Map vendorMap = new HashMap();
    private long count = 0;
    private long startTime = System.currentTimeMillis();
    
    public MsapAlarmEventSynProducer getMsapAlarmEventSynProducer() {
		return msapAlarmEventSynProducer;
	}


	public void setMsapAlarmEventSynProducer(
			MsapAlarmEventSynProducer msapAlarmEventSynProducer) {
		this.msapAlarmEventSynProducer = msapAlarmEventSynProducer;
	}
    
	public AbstractMessageListener(
			    AlarmEventProducer alarmEventProducer) {
	//	m_alarmMgmt = mgmt;
		this.alarmEventProducer = alarmEventProducer;
		queue = new LinkedBlockingQueue<Runnable>();
		pool = new ThreadPoolExecutor( 1, 1, 5 * 60, TimeUnit.SECONDS, queue);
		try {
            String className = "gxlu.vpn.emos.server.AlarmFilterRuleProcessor";
            className = SysProperty.getString("alarm.eventFilterClass",className);
            filter = (com.alcatelsbell.nms.alarm.api.AlarmEventFilter) Class.forName(className).newInstance();
			List<Vendor> vendors = JpaClient.getInstance().findAllObjects(Vendor.class);
			List<Ems> emses = JpaClient.getInstance().findAllObjects(Ems.class);
			for(Ems ems : emses){
				for(Vendor vendor :vendors){
					if(ems.getVendordn().equals(vendor.getDn())){
						vendorMap.put(ems.getDn(), vendor.getId());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		}

        new Thread(new Runnable(){
            private int hour = 0;
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3600000l);
                    } catch (InterruptedException e) {
                        logger.error(e,e);
                    }

                    logger.info(" past "+(++hour)+" hours , "+count+" events processed ."+ AlarminformationWorker.total +" events worked, "+AlarminformationWorker.created+" events related");

                }
            }
        }).start();
	}

	
	public AbstractMessageListener() {
		this(null);

	}

        //123 @see  # public void consume(Object event)
        private String createKey(Alarminformation info) {
            return info.getCorrelationId()+":SEVERITY:"+info.getSeverity()+":Time:"+(info.getNetime() == null ? 0:info.getNetime().getTime());
        }


        //123 @see  # public void consume(Object event)
        public void fillBackup(Vector<Alarminformation> infos) {
            flag = false;
            synchronized (this) {
                for (Alarminformation info : infos) {
                    String key = this.createKey(info);
                    if (cache.containsKey(key)) {
                        consume(info);
                    }
                }
            }
        }
        //123 @see  # public void consume(Object event)
        private boolean flag = true;

        //123 @see  # public void consume(Object event)
        private Hashtable<String,Alarminformation>  cache = new Hashtable<String,Alarminformation>();


    // 是否使用告警过滤策略
    private boolean isfilter = true;

    private boolean tempBoolean = false;
        // 在刚重启server时，为了避免从远程告警备份服务中取到的告警信息可能与重启瞬间收到的消息发生重复，故在这段时间内需要做一个比对，相关代码在注释中带有 //123
	public void consume(Object event) {
		
		if (!(event instanceof Alarminformation)) {
			 return;
		}
        try {
        } catch (Throwable e) {
            logger.error( e, e);
            tempBoolean = true;
        }
        count ++;


		final Alarminformation alarminformation = (Alarminformation)event;
        logger.info("new Alarm:"+alarminformation.getCorrelationId()+"@"+alarminformation.getSeverity());
        if (alarminformation.getNetime() == null)
            alarminformation.setNetime(new Date());
        if (alarminformation.getEmstime() == null)
            alarminformation.setEmstime(new Date());
        
//        //----------------------------------------------------------------------------------------------
//        try{
//	        if(alarminformation!=null&&(alarminformation.getFieldType() == SysConst.EVENT_FIELDTYPE_MSAP))
//	        	msapAlarmEventSynProducer.addAlarmEvent(alarminformation);
//        }catch(Exception e){
//        	logger.error(e, e);
//        }
//        //----------------------------------------------------------------------------------------------
        if (isfilter && filter != null) {
            Runnable runnable = new Runnable(){
                public void run(){
                    filter.alarmFilterProcessor(alarminformation , alarmEventProducer,vendorMap);
                }
            };
            pool.execute(runnable);
        } else {
                if (alarminformation.getSeverity() == SysConst.ALARM_SEVERITY_CLEAR) {
                    alarmEventProducer.addClearAlarmEvent(new AlarmEvent(new Date(),
                            alarminformation));

                } else {
                    alarmEventProducer.addNewAlarmEvent(new AlarmEvent(new Date(),
                            alarminformation));
                }
        }
	}
}
