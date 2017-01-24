package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.valueobject.BObject;

import java.rmi.RemoteException;
import java.util.List;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 上午9:24
 */
public class JPAService {
  public List findAllObjects(Class cls) throws Exception
  {
    List result = null;
    JPAContext context = JPAContext.prepareReadOnlyContext();
    try
    {
      result = DBUtil.getInstance().queryAllObjects(context, cls);
      context.end();
    } catch (Exception ex) {
      context.rollback();

      throw ex;
    } finally {
      context.release();
    }
    return result;
  }
    
  public BObject saveObject(long sessionKey, BObject obj) throws RemoteException, Exception {
    BObject result = null;
    JPAContext context = JPAContext.prepareContext(sessionKey);
    try
    {
      result = JPAUtil.getInstance().saveObject(context, sessionKey, obj);
      context.end();
    } catch (Exception ex) {
      context.rollback();

      throw ex;
    } finally {
      context.release();
    }
    return result;
  }    
}
