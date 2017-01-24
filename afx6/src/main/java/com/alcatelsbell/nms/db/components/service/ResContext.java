package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.common.SpringContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * User: Ronnie.Chen
 * Date: 11-8-26
 */
public class ResContext extends JPAContext{
  private static EntityManagerFactory resemf;
  protected static EntityManagerFactory getEntityManagerFactory() {
    if (resemf == null) {
          resemf = (EntityManagerFactory)SpringContext.getInstance().getApplicationContext().getBean("resEntityManagerFactory");

    }
    return resemf;
  }


    public static void main(String[] args) {
        String[] locations = {"appserver-spring.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(locations);
        EntityManagerFactory factory = getEntityManagerFactory();
        factory.getMetamodel().getEntities();
        EntityManager em = factory.createEntityManager();
        JPAContext context = JPAContext.prepareContext();
    }
}
