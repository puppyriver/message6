package message6.web.util;

import com.alcatelsbell.nms.db.components.service.BinaryObjectUtil;
import com.alcatelsbell.nms.db.components.service.DBUtil;
import com.alcatelsbell.nms.db.components.service.JPAContext;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.valueobject.BObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Author: Ronnie.Chen
 * Date: 12-8-1
 * Time: 上午10:53
 * rongrong.chen@alcatel-sbell.com.cn
 */
public class JpaServerUtils {
    private static JpaServerUtils ourInstance = new JpaServerUtils();
    private Log logger = LogFactory.getLog(getClass());
    public static JpaServerUtils getInstance() {
        return ourInstance;
    }

    private JpaServerUtils() {

    }



    public BObject saveObject(long sessionKey, BObject obj) throws  Exception {
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



    public BObject storeObjectByDn(long sessionKey, BObject obj) throws  Exception {
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

    public BObject storeObjectByKeys(long sessionKey, BObject _obj, String keys) throws  Exception
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


    public List findObjects(String strSql) throws Exception {
        return findObjects(strSql,null,null,null,null);
    }
    public Object findOneObject(String strSql) throws Exception {
        List l =  findObjects(strSql,null,null,null,null);
        if (l != null && l.size() > 0)
            return l.get(0);
        return null;
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
        long t2 = System.currentTimeMillis();
        if (t2 - t1 > 100)
        logger.info("query:"+strSql+" spend "+(t2-t1)+"ms");
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


   
    public Object findObjectById(Class cls, long id)  throws Exception {

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

   
    public void  executeNativeSql(String sql) throws Exception{
        JPAContext context = JPAContext.prepareContext();
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
        JPAContext context = JPAContext.prepareContext();
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
        JPAContext context = JPAContext.prepareContext();
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
            return DBUtil.getInstance().executeUpdateSQL(context, sql);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.end();
            context.release();
        }
    }

   
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

    public List queryQL(String ql) throws Exception {
        JPAContext context = JPAContext.prepareReadOnlyContext();
        try
        {
            return JPAUtil.getInstance().queryQL(context, ql);
        } catch (Exception ex) {
            context.rollback();
            throw ex;
        } finally {
            context.release();
        }
    }

    public void saveBinaryObject(byte[] bs,String key) throws Exception {
        BinaryObjectUtil.saveObject(key,bs);
    }
    public byte[] readBinaryObject(String key) throws Exception{
        return BinaryObjectUtil.readObject(key);
    }

}
