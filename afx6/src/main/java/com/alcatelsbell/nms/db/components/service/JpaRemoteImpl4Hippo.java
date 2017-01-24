package com.alcatelsbell.nms.db.components.service;

/**
 * User: Ronnie
 * Date: 12-7-24
 * Time: 下午1:57
 */


import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.api.JpaRemoteIFC;
import com.alcatelsbell.nms.util.JVMRegistry;
import com.alcatelsbell.nms.valueobject.BObject;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class JpaRemoteImpl4Hippo implements JpaRemoteIFC {
    public JpaRemoteImpl4Hippo() throws RemoteException
    {
        JVMRegistry.getInstance().registerObject(getClass().getName(),this);
    }
    public String getJndiNamePrefix(){
        return SysConst.SERVICE_NAME_JPA;
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

    public void start() {

    }

    public BObject storeObjectByDn(long sessionKey, BObject obj) throws RemoteException, Exception {
        BObject result = null;
        JPAContext context = JPAContext.prepareContext(sessionKey);
        try
        {
            result = JPAUtil.getInstance().storeObjectByDn(context, sessionKey, obj);
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return result;
    }

    public BObject storeObjectByKeys(long sessionKey, BObject _obj, String keys) throws RemoteException, Exception
    {
        BObject result = null;
        JPAContext context = JPAContext.prepareContext(sessionKey);
        try
        {
            result = JPAUtil.getInstance().storeObjectByKeys(context, sessionKey, _obj, keys);
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return result;
    }

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

    //    public long findObjectsNumByFilter(Class cls, Object filter) throws Exception {
    //      long result = -5677600560883171328L;
    //      JPAContext context = JPAContext.prepareReadOnlyContext();
    //      try
    //      {
    //        result = JPAUtil.getInstance().findObjectsNumByFilter(context, cls, filter);
    //        context.end();
    //      } catch (Exception ex) {
    //        context.rollback();
    //
    //        throw ex;
    //      } finally {
    //        context.release();
    //      }
    //      return result;
    //    }

    //    public List findObjectsByFilter(Class cls, Object filter, Integer start, Integer limit) throws Exception
    //    {
    //      List result = null;
    //      JPAContext context = JPAContext.prepareReadOnlyContext();
    //      try
    //      {
    //        result = JPAUtil.getInstance().findObjectsByFilter(context, cls, filter, start, limit);
    //        context.end();
    //      } catch (Exception ex) {
    //        context.rollback();
    //
    //        throw ex;
    //      } finally {
    //        context.release();
    //      }
    //      return result;
    //    }

    //    public List findObjectsByFilter(String strSql, Class cls, Object filter, Integer start, Integer limit) throws Exception
    //    {
    //      List result = null;
    //      JPAContext context = JPAContext.prepareReadOnlyContext();
    //      try
    //      {
    //        result = JPAUtil.getInstance().findObjectsByFilter(context, strSql, cls, filter, start, limit);
    //        context.end();
    //      } catch (Exception ex) {
    //        context.rollback();
    //
    //        throw ex;
    //      } finally {
    //        context.release();
    //      }
    //      return result;
    //    }

    public List<Object> findObjectsByMapTable(Class cls, BObject bobj, Class mapClass, String[] mapkeys) throws Exception
    {
        List result = null;
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            result = JPAUtil.getInstance().findObjectsByMapTable(context, cls, bobj, mapClass, mapkeys);
        }
        catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return result;
    }

    public List findObjects(String strSql, String strPrefix, Map mapValue, Integer start, Integer limit) throws Exception
    {
        long t1 = System.currentTimeMillis();
        List result = null;
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            result = JPAUtil.getInstance().findObjects(context, strSql, strPrefix, mapValue, start, limit);
        }
        catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }

        return result;
    }

    public long findObjectsCount(Class cls, Map mapValue) throws Exception {
        long result = -5677600560883171328L;
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            result = JPAUtil.getInstance().findObjectsCount(context, cls, mapValue);
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return result;
    }

    @Override
    public long findObjectsCount(String hql) throws Exception {
        long result = -5677600560883171328L;
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            List l = JPAUtil.getInstance().findObjects(context,hql);
            if (l != null && l.size() > 0) {
                Object n = l.get(0);
                if (n instanceof Number) {
                    return ((Number)n).longValue();
                }
            }
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return result;
    }


    @Override
    public Object findObjectById(Class cls, long id)  throws Exception {
        if (cls == null || id < 0) return null;
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            List l = JPAUtil.getInstance().findObjects(context,"select c from "+cls.getName()+" as c where c.id = "+id);
            if (l != null && l.size() > 0) {
                Object n = l.get(0);
                return n;
            }
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return null;
    }

    @Override
    public Object findObjectByDN(Class cls, String dn)  throws Exception {

        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            List l = JPAUtil.getInstance().findObjects(context,"select c from "+cls.getName()+" as c where c.dn = '"+dn+"'");
            if (l != null && l.size() > 0) {
                Object n = l.get(0);
                return n;
            }
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
        return null;
    }

    @Override
    public void  executeNativeSql(String sql) throws Exception{
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            DBUtil.getInstance().executeNonSelectingSQL(context,sql);
            context.end();
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
    }
    public List  executeSql(String sql) throws Exception{
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            return DBUtil.getInstance().querySQL(context,sql);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }
    public List  executeSql(String sql,Class clazz) throws Exception{
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            return DBUtil.getInstance().querySQL(context,sql,clazz);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }
    public int  executeUpdateSQL(String sql) throws Exception{
        JPAContext context = JPAContext.prepareContext();
        try
        {
            int i=  DBUtil.getInstance().executeUpdateSQL(context, sql);
            context.end();
            return i;
        } catch (Exception ex) {
       //     ex.printStackTrace();

            throw ex;
        } finally {
            context.release();
        }
    }

    @Override
    public void deleteObject(BObject obj) throws Exception {
        JPAContext context = JPAContext.prepareContext();
        try
        {
            JPAUtil.getInstance().deleteObject(context,-1,obj);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }

    @Override
    public void removeObject(BObject obj) throws Exception {
        JPAContext context = JPAContext.prepareContext();
        try
        {
            JPAUtil.getInstance().removeObject(context,-1,obj);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }


    /**
     * 测试删除
     */
    @Override
    public void executeDeleteSQL(String sql, Map mapValue) throws Exception {
        JPAContext context = JPAContext.prepareContext();
        try
        {
            JPAUtil.getInstance().executeQL(context, sql ,mapValue);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }

    public void saveBinaryObject(byte[] bs,String key) throws Exception {
        BinaryObjectUtil.saveObject(key,bs);
    }
    public byte[] readBinaryObject(String key) throws Exception{
        return BinaryObjectUtil.readObject(key);
    }

    public List readBinaryObjectAll() throws Exception{
        return BinaryObjectUtil.readObjectAll();
    }
}
