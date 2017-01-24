package com.alcatelsbell.nms.common;

import com.alcatelsbell.nms.util.SysProperty;

import javax.jms.ConnectionFactory;
import java.util.Iterator;
import java.util.Properties;


/**
 * User: Ronnie
 * Date: 11-12-16
 * Time: 下午2:11
 */
public class Configuration {



    private ConnectionFactory jmsConnectionFactory;
    private Object entityManagerFactory = null;


    public Object getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public void setEntityManagerFactory(Object entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public ConnectionFactory getJmsConnectionFactory() {
        return jmsConnectionFactory;
    }

    public void setJmsConnectionFactory(ConnectionFactory jmsConnectionFactory) {
        this.jmsConnectionFactory = jmsConnectionFactory;
    }

    public void setSystemProperties(Properties systemProperties) {
        SysProperty.load();
        if (systemProperties != null) {
            Iterator keys = systemProperties.keySet().iterator();
            while (keys.hasNext()) {
                Object next = keys.next();
                System.getProperties().setProperty(String.valueOf(next),String.valueOf(systemProperties.get(next)));
            }
        }
        System.getProperties().list(System.out);
    }

}
