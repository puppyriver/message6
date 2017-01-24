package com.alcatelsbell.nms.common;

import javax.jms.Queue;
import javax.jms.Topic;


/**
 * User: Ronnie
 * Date: 11-12-22
 * Time: 下午3:02
 */
public interface JMSDestinationBuilder {
    public Topic buildTopic(String topicName);
    public Queue buildQueue(String queueName);
}
