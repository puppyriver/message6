package com.alcatelsbell.nms.common;

import javax.jms.MessageListener;
import java.io.Serializable;

/**
 * User: Ronnie
 * Date: 11-12-16
 * Time: 下午2:17
 */
public interface JMSSupport {
    /**
     *  Send topic message
     * @param topicName
     * @param message
     */
    public void sendTopicMessage(String topicName, Serializable message) throws Exception;

//    /**
//     *
//     * @param topicName
//     * @param obj
//     * @param operationType
//     */
//    public void sendGeneralMessage(String topicName,Serializable obj,int operationType) throws Exception;

    /**
     *
     * @param _topicName
     * @param _topicListener
     */
    public void addTopicSubscriber(String _topicName, MessageListener _topicListener) ;

    /**
     *
     * @param _topicName
     * @param _topicListener
     */
    public void removeTopicSubscriber(String _topicName, MessageListener _topicListener);


    /**
     * Send queue message
     * @param _queueName
     * @param message
     */
    public void produceQueueMessage(String _queueName, Serializable message) throws Exception;

    /**
     *  add QUEUE consumer
     * @param _queueName
     * @param _topicListener
     */
    public void addQueueConsumer(String _queueName, MessageListener _topicListener) ;

    /**
     *  remove Queue Consumer
     * @param _queueName
     * @param _topicListener
     */
    public void removeQueueConsumer(String _queueName, MessageListener _topicListener);

}
