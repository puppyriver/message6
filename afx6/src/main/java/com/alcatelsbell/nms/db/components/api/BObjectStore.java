package com.alcatelsbell.nms.db.components.api;

import com.alcatelsbell.nms.valueobject.BObject;

import java.util.List;
import java.util.Map;

/**
 * User: Ronnie
 * Date: 12-1-7
 * Time: 上午10:19
 */
public interface BObjectStore {
    public BObject saveObject(long sessionKey, BObject obj) throws   Exception;

    public List findAllObjects(Class cls) throws   Exception;

    public List<Object> findObjectsByMapTable(Class cls, BObject bobj, Class mapClass, String[] mapkeys) throws   Exception;

    public List findObjects(String strSql, String strPrefix, Map mapValue, Integer start, Integer limit) throws   Exception;

    public List findObjects(String strSql) throws  Exception;

    public long findObjectsCount(Class cls, Map mapValue) throws  Exception;

    public long findObjectsCount(String ql) throws Exception;

    public Object findObjectById(Class cls, long id)  throws Exception;

    public Object findObjectByDN(Class cls, String dn)  throws Exception;

    public void removeObject(BObject obj) throws Exception;
}
