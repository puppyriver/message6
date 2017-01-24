package com.alcatelsbell.nms.db.dao;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 下午3:35
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaGenericDao<T>
{
  protected final Log logger = LogFactory.getLog(super.getClass());
  private Class<T> type;

  public JpaGenericDao(Class<T> type)
  {
    this.type = type;
  }

  public T get(EntityManager entityManager, Long id)
  {
    return entityManager.find(this.type, id);
  }

  public boolean remove(EntityManager entityManager, Long id)
  {
    boolean result = false;
    Object object = get(entityManager, id);
    if (object != null) {
      entityManager.remove(object);
      result = true;
    }
    return result;
  }

  public List<T> getAll(EntityManager entityManager)
  {
    return (List<T>)entityManager.createQuery("select obj from " + this.type.getName() + " obj").getResultList();
  }

  public T create(EntityManager entityManager, T object)
  {
    entityManager.persist(object);
    return object;
  }

  public T update(EntityManager entityManager, T object)
  {
    Object newObject = entityManager.merge(object);
    entityManager.persist(newObject);
    return (T)newObject;
  }

  public T remove(EntityManager entityManager, T object) {
    entityManager.remove(object);
    return object;
  }
}