package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.common.SpringContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class JPAContext implements JPASupport
{
  protected static final Log logger = LogFactory.getLog(JPAContext.class);
  protected EntityManager entityManager = null;
  protected EntityTransaction tx = null;
  static EntityManagerFactory emf = null;
  protected Object savedObject = null;
  protected List deleteObjects = new ArrayList();
  protected List returnObjects = new ArrayList();
  private long sessionKey;

    public String getInvokeInfo() {
        return invokeInfo;
    }

    public void setInvokeInfo(String invokeInfo) {
        this.invokeInfo = invokeInfo;
    }

    private String invokeInfo = null;

  private static Vector<JPAContext> openContexts = new Vector<JPAContext>();

  @Override
  public EntityManager getEntityManager()
  {
    return this.entityManager;
  }

  public Object getSavedObject()
  {
    return this.savedObject;
  }

  public void setSavedObject(Object savedObject) {
    this.savedObject = savedObject;
  }

  public List getDeleteObjects() {
    return this.deleteObjects;
  }

  public void addDeleteObjects(Object obj) {
    this.deleteObjects.add(obj);
  }

  public void addDeleteObjects(List objs) {
    if (objs == null)
      return;

    this.deleteObjects.addAll(objs);
  }

  public List getReturnObjects()
  {
    return this.returnObjects;
  }

  @Override
  public void addReturnObjects(Object obj) {
  //  this.returnObjects.add(obj);
  }

  public long getSessionKey()
  {
    return this.sessionKey;
  }

  public void setSessionKey(long sessionKey) {
    this.sessionKey = sessionKey;
  }

  protected static EntityManagerFactory getEntityManagerFactory() {
    if (emf == null)
      emf = (EntityManagerFactory)SpringContext.getEntityManagerFactory();

    return emf;

  }

  public static JPAContext createNgmContext() {
    JPAContext result = new  JPAContext();
    return result;
  }

  public static  JPAContext prepareReadOnlyContext() {
     JPAContext result = new  JPAContext();
    result.entityManager = getEntityManagerFactory().createEntityManager();
    return result;
  }

  public static JPAContext prepareContext() {
    return prepareContext(-1L);
  }

  public static JPAContext prepareContext(long sessionKey) {
     JPAContext result = new JPAContext();
    result.entityManager = getEntityManagerFactory().createEntityManager();

    result.setSessionKey(sessionKey);

    result.begin();
    return result;
  }

  public void prepare() {
      if (this.entityManager != null) {
          try {
            entityManager.close();
          }  catch(Exception e){
             logger.error(e,e);
          }
      }
    this.entityManager = getEntityManagerFactory().createEntityManager();
    begin();
  }

  public void prepareReadOnly() {
      if (this.entityManager != null) {
          try {
            entityManager.close();
          }  catch(Exception e){
             logger.error(e,e);
          }
      }
    this.entityManager = getEntityManagerFactory().createEntityManager();
  }

  public void begin()
  {
    this.tx = this.entityManager.getTransaction();
    this.tx.begin();


    StackTraceElement[] sts = Thread.currentThread().getStackTrace();
    for (int i = 1; i < sts.length; i++) {
        StackTraceElement st = sts[i];
        if (!st.getClassName().contains("JPAContext")) {
            if (st.getClassName().contains("alcatelsbell") || st.getClassName().contains("gxlu")) {
              invokeInfo = st.toString();
                break;
            }
        }
    }
    logger.debug("************************ unrelased context size = "+openContexts.size()+" ***********");
//    for (JPAContext ctx : openContexts) {
//       // logger.debug("************************ invokeInfo:"+ctx.getInvokeInfo());
//    }
    logger.debug("************************ end *********************************");
    openContexts.add(this);
  }

  public void end() throws Exception {
    if (this.tx != null)
      this.tx.commit();

      openContexts.remove(this);
  }

  public void rollback()
  {
    if (this.tx == null)
      return;

    if (this.tx.isActive())
      this.entityManager.getTransaction().rollback();

       openContexts.remove(this);
  }

  public void release()
  {
      if (entityManager != null) {
          this.entityManager.clear();
        this.entityManager.close();
        this.entityManager = null;
      }


  }

  protected void finalize()
    throws Throwable
  {
    finalize();
    if ((this.entityManager != null) && (this.entityManager.isOpen()))
      this.entityManager.close();
  }
}