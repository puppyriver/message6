package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.common.SpringContext;
import com.alcatelsbell.nms.common.SysUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * User: Ronnie
 * Date: 11-9-28
 * Time: 上午11:38
 */
public class JPASupportSpringImpl implements JPASupport{

	private EntityManagerFactory emf = null;
	private EntityManager entityManager = null;
    private boolean singleThreadTx = false;
    private static  ReentrantLock lock = new ReentrantLock();
  //  private static Condition singleThreadCondition = lock.newCondition();
	protected EntityTransaction tx = null;
    private int clearCacheSize = 1000;
    private boolean clearCacheOnEnd = false;
    private HashMap properties = new HashMap();
    private Log logger  = LogFactory.getLog(getClass());
    public HashMap getProperties() {
        return properties;
    }

    public void setProperties(HashMap properties) {
        this.properties = properties;
    }

    public boolean isClearCacheOnEnd() {
        return clearCacheOnEnd;
    }

    public void setClearCacheOnEnd(boolean clearCacheOnEnd) {
        this.clearCacheOnEnd = clearCacheOnEnd;
    }

    public int getClearCacheSize() {
        return clearCacheSize;
    }

    public void setClearCacheSize(int clearCacheSize) {
        this.clearCacheSize = clearCacheSize;
    }

    public JPASupportSpringImpl(String factoryBeanId) {

        if (factoryBeanId != null)
        {
            id = SysUtil.nextLongId();


            emf = (EntityManagerFactory)SpringContext.getInstance().getApplicationContext().getBean(factoryBeanId);
            logger.debug("createEntityManager :"+factoryBeanId+",id="+id);
            entityManager = emf.createEntityManager();
            logger.debug("createEntityManager success :"+id);
        } else {
            emf = (EntityManagerFactory)SpringContext.getEntityManagerFactory();
            entityManager = emf.createEntityManager();
        }

	}

    private Long id = null;
	public JPASupportSpringImpl() {
		emf = (EntityManagerFactory)SpringContext.getEntityManagerFactory();

		entityManager = emf.createEntityManager();

	}

    public JPASupportSpringImpl(EntityManagerFactory factory) {
        emf = factory;
        entityManager = emf.createEntityManager();
    }
    private transient long batchCount = 0;
	@Override
	public void addReturnObjects(Object obj) {
        if (clearCacheSize > 0) {
             if (++batchCount % clearCacheSize == 0) {
                entityManager.flush();
                 entityManager.clear();
             }
        }

	}

	@Override
	public EntityManager getEntityManager() {
		if (entityManager == null) entityManager = emf.createEntityManager();
		return entityManager;
	}

	@Override
	public void begin() throws Exception {
        batchCount = 0;
        if (singleThreadTx) {
            lock.lock();
        }

		if (entityManager == null) entityManager = emf.createEntityManager();
		this.tx = this.entityManager.getTransaction();
		this.tx.begin();
	}

	@Override
	public void end() throws Exception {
        try {
            if (this.tx != null)
                this.tx.commit();
        } finally {
            if (singleThreadTx) {

                lock.unlock();
            }
            batchCount = 0;
        }
        if (clearCacheOnEnd)
            entityManager.clear();


	}

	@Override
	public void rollback() {
		if (this.tx == null)
			return;

		if (this.tx.isActive())
			this.entityManager.getTransaction().rollback();
	}

	@Override
	public void release()
	{
        if (id != null)
            logger.debug("release EntityManager :"+id);

		if (entityManager != null) {
			this.entityManager.clear();
			this.entityManager.close();
			this.entityManager = null;
		}

//        try {
//            if (this.getProperties() != null) {
//                Object datasource = this.getProperties().get("Datasource");
//                if (datasource != null && datasource instanceof DataSource)
//                    ((DataSource) datasource).close();
//            }
//        } catch (Exception e) {
//            logger.error(e, e);
//        }
    }

    public boolean isSingleThreadTx() {
        return singleThreadTx;
    }

    public void setSingleThreadTx(boolean singleThreadTx) {
        this.singleThreadTx = singleThreadTx;
        clearCacheSize = -1;
    }
}
