package com.alcatelsbell.nms.db.api;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 下午2:40
 */


import com.alcatelsbell.nms.valueobject.BObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;


public abstract interface JpaRemoteIFC extends Remote
{
	public abstract BObject saveObject(long paramLong, BObject paramBObject)
	throws RemoteException, Exception;

	public abstract BObject storeObjectByDn(long paramLong, BObject paramBObject)
	throws RemoteException, Exception;

	public abstract BObject storeObjectByKeys(long paramLong, BObject paramBObject, String paramString)
	throws RemoteException, Exception;

	public abstract List findAllObjects(Class paramClass)
	throws RemoteException, Exception;

	//  public abstract List findObjectsByFilter(Class paramClass, Object paramObject, Integer paramInteger1, Integer paramInteger2)
	//    throws RemoteException, Exception;
	//
	//  public abstract long findObjectsNumByFilter(Class paramClass, Object paramObject)
	//    throws RemoteException, Exception;
	//
	//  public abstract List findObjectsByFilter(String paramString, Class paramClass, Object paramObject, Integer paramInteger1, Integer paramInteger2)
	//    throws RemoteException, Exception;

	public abstract List<Object> findObjectsByMapTable(Class paramClass1, BObject paramBObject, Class paramClass2, String[] paramArrayOfString)
	throws RemoteException, Exception;

	public abstract List findObjects(String paramString1, String paramString2, Map paramMap, Integer paramInteger1, Integer paramInteger2)
	throws RemoteException, Exception;

	public abstract long findObjectsCount(Class paramClass, Map paramMap)
	throws RemoteException, Exception;

	public  long findObjectsCount(String hql) throws Exception;

	public Object findObjectById(Class cls, long id)  throws Exception;

	public Object findObjectByDN(Class cls, String dn)  throws Exception;

	public  void  executeNativeSql(String sql) throws Exception;

	public List  executeSql(String sql) throws Exception;

	public List  executeSql(String sql, Class clazz) throws Exception;

	public int  executeUpdateSQL(String sql) throws Exception ;

	public void deleteObject(BObject obj) throws Exception ;
	public void removeObject(BObject obj) throws Exception ;


	//测试删除
	public void executeDeleteSQL(String sql, Map paramMap) throws Exception;
    
    public void saveBinaryObject(byte[] bs, String key) throws Exception;
    public byte[] readBinaryObject(String key) throws Exception;
    //加载所有图片
    public List readBinaryObjectAll() throws Exception;

}