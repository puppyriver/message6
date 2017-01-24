package com.alcatelsbell.nms.db.components.service;

import javax.persistence.EntityManager;

/**
 * User: Ronnie
 * Date: 11-9-28
 * Time: 上午11:21
 */
public interface JPASupport {
     public void addReturnObjects(Object obj) ;
     public EntityManager getEntityManager();
     public void begin() throws Exception;
     public void end() throws Exception;
     public void rollback();
     public void release();
}
