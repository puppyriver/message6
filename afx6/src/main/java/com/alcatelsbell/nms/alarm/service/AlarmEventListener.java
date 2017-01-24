

package com.alcatelsbell.nms.alarm.service;

import com.alcatelsbell.nms.alarm.service.timefilter.AlarmEventProducer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class AlarmEventListener extends AbstractMessageListener {
	
	private ThreadPoolExecutor pool = null;
    private LinkedBlockingQueue<Runnable> queue = null;

    public String getFilterString() {
        return filterString;
    }

    public void setFilterString(String filterString) {
        this.filterString = filterString;
    }

    //    Collection<Rule> rules = null;
	private String filterString = null;
	public AlarmEventListener(
			AlarmEventProducer alarmEventProducer) {
		super(alarmEventProducer);
		queue = new LinkedBlockingQueue<Runnable>();
        pool = new ThreadPoolExecutor( 1, 1, 5 * 60, TimeUnit.SECONDS, queue);
	}
	public AlarmEventListener(){
		super();
	}





	/**
	 * Casts the message to a ObjectMessage and process objectMessage.
	 * 
	 * @param message
	 *            the incoming message
	 */
	public void onMessage(Message message) {
		ObjectMessage msg = null;
		try {
			if (message instanceof ObjectMessage) {
			//	logger.debug("Message coming");
				final ObjectMessage msg0 = (ObjectMessage) message;
				Runnable runnable = new Runnable(){
		        	public void run(){
		        		try {
							consume(msg0.getObject());
						} catch (JMSException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        };
		        pool.execute(runnable);
				
			} else {
				logger.error(
						"Message of wrong type: "
								+ message.getClass().getName());
			}
		}  catch (Throwable t) {
			t.printStackTrace();
			logger.error(
					"Exception in onMessage():" + t.getMessage());
		}
	}
}
