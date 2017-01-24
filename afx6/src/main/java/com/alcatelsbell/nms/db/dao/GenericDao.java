package com.alcatelsbell.nms.db.dao;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 下午3:34
 */

import java.util.List;

public abstract interface GenericDao<T>
{
  public abstract T get(Long paramLong);

  public abstract T save(T paramT);

  public abstract T update(T paramT);

  public abstract boolean remove(Long paramLong);

  public abstract List<T> getAll();
}