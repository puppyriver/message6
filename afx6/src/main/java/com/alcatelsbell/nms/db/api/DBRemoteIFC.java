package com.alcatelsbell.nms.db.api;

import com.alcatelsbell.nms.common.ServiceException;
import com.alcatelsbell.nms.valueobject.BObject;

import javax.sql.DataSource;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 下午1:47
 */


public interface DBRemoteIFC
    extends Remote {

  public BObject createObject(long sessionKey, BObject obj,
                              boolean logmessage, boolean _sendMessage) throws
          RemoteException, ServiceException;

  public BObject storeObject(long sessionKey, BObject obj,
                             boolean logmessage, boolean _sendMessage) throws
      RemoteException, ServiceException;

  public void deleteObject(long sessionKey, BObject obj,
                           boolean logmessage, boolean _sendMessage) throws
      RemoteException,
      ServiceException;

  public BObject createObject(long sessionKey, BObject obj,
                              boolean _sendMessage) throws
      RemoteException, ServiceException;

  public BObject storeObject(long sessionKey, BObject obj,
                             boolean _sendMessage) throws
      RemoteException, ServiceException;

  public void deleteObject(long sessionKey, BObject obj,
                           boolean _sendMessage) throws RemoteException,
      ServiceException;

  public void executeNonSelectingSQL(String sqlStatement) throws
      RemoteException, ServiceException;

  public Object queryObjectById(Class objClass, long id) throws RemoteException,
      ServiceException;

  public long queryObjectsCount(String _queryString) throws RemoteException,
      ServiceException;

  public double querySum(String _queryString) throws RemoteException,
      ServiceException;

  public Vector querySQL(String _queryString) throws RemoteException,
      ServiceException;

  public Vector queryChildObjectsById(Class objClass, String parentField,
                                      long parentId) throws RemoteException,
      ServiceException;

  public Vector queryAllObjects(Class objClass) throws RemoteException,
      ServiceException;

  public Vector queryObjects(Class objClass, String queryString) throws
      RemoteException, ServiceException;

  public Vector queryObjects(Class objClass, String tableName, Hashtable cond) throws
      RemoteException, ServiceException;

  public Vector queryObjectsInTheseData(Class objClass, String tableName,
                                        Hashtable cond, String inTheseData) throws
      RemoteException, ServiceException;

  public Vector queryObjectsByOr(Class objClass, String tableName,
                                 Hashtable cond) throws RemoteException,
      ServiceException;

  public Date getServerCurrentDate() throws RemoteException, ServiceException;

  public long queryCountOfObjects(Class cls, String tableName,
                                  String sql) throws RemoteException,
      ServiceException;

  ;
  public long queryCountsOfObjects(Class cls, String sql) throws
      RemoteException, ServiceException;

  ;
  public long queryMaxOfObject(Class objClass, String attrName) throws
      RemoteException,
      ServiceException;

  public long queryMaxOfObjectWhereCondition(Class cls, String attribute,
                                             String sql) throws Exception;

  public Date getDBCurrentDate() throws RemoteException, ServiceException;

  public String getDateClause(Date date) throws RemoteException, ServiceException;

  public DataSource getDBConnection() throws Exception, RemoteException;

  public Vector querySQLs(Vector<String> _queryStrings) throws RemoteException, ServiceException  ;
  public byte[] queryZipedObjects(Class objClass, String queryString) throws RemoteException, ServiceException  ;
}


