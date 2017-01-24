package com.alcatelsbell.nms.common;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;


@Repository("springContext")
public class SpringContext
  implements ApplicationContextAware
{
  private ApplicationContext ac;
  private static SpringContext instance = null;


  private JMSSupport JMSSupport =  null;

  private Log logger = LogFactory.getLog(getClass());
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setJMSSupport(JMSSupport jmsSupport) {
        this.JMSSupport = jmsSupport;
    }


    public JMSSupport getSpringJMSSupport() {
        return (JMSSupport)getApplicationContext().getBean("jmsSupport");
    }




    private Configuration configuration = null;
  public static SpringContext getInstance()
  {
    if (instance == null)
      instance = new SpringContext();

    return instance;
  }

  public void loadClasspathXmlAppContext(String[] locations) {
   ApplicationContext ctx = new ClassPathXmlApplicationContext(locations);

        String[] names = ctx.getBeanDefinitionNames();
        for (String name : names) {
            Object bean =  ctx.getBean(name);
        }

       this.setApplicationContext(ctx);
  }

  public void setApplicationContext(ApplicationContext ac) {
    this.ac = ac;
  }

  public ApplicationContext getApplicationContext()
  {
    return this.ac;
  }

  public static Object getEntityManagerFactory() {
      Object emf =  getInstance().getApplicationContext().getBean("entityManagerFactory");
    return emf;
  }

  public static Object getResEntityManagerFactory() {
      Object emf =  getInstance().getApplicationContext().getBean("resEntityManagerFactory");
    return emf;
  }
}