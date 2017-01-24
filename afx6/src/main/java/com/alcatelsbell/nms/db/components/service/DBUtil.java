package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.valueobject.BObject;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;


public class DBUtil
{
  private static DBUtil instance = null;

  public static synchronized DBUtil getInstance()
  {
    if (instance == null)
      instance = new DBUtil();

    return instance;
  }

  public void createObject(JPASupport _context, long _sessionKey, Object _obj)
    throws Exception
  {
    createObject(_context, _sessionKey, _obj, true);
  }

  public void createObject(JPASupport _context, long _sessionKey, Object obj, boolean writeLog) throws Exception {
    EntityManager em = _context.getEntityManager();
    em.persist(obj);
  }

  public void storeObject(JPASupport _context, long _sessionKey, BObject _obj)
    throws Exception
  {
    storeObject(_context, _sessionKey, _obj, true);
  }

  public void storeObject(JPASupport _context, long _sessionKey, BObject obj, boolean writeLog) throws Exception {
    EntityManager em = _context.getEntityManager();
    Object newObject = em.merge(obj);
    em.persist(newObject);
  }

  public void deleteObject(JPASupport _context, long _sessionKey, String _str)
    throws Exception
  {
    executeNonSelectingSQL(_context, _str);
  }

  public void deleteObject(JPASupport _context, long _sessionKey, BObject _obj)
    throws Exception
  {
    deleteObject(_context, _sessionKey, _obj, true);
  }

  public void deleteObject(JPASupport _context, long _sessionKey, BObject obj, boolean writeLog) throws Exception {
    EntityManager em = _context.getEntityManager();
    obj = (BObject)em.merge(obj);
    em.remove(obj);
  }

  public void executeNonSelectingSQL(JPASupport _context, String sqlStatement) {
    EntityManager em = _context.getEntityManager();
    Query nq = em.createNativeQuery(sqlStatement);
    nq.executeUpdate();
  }

  public List executeSelectingSQL(JPASupport _context, String sqlStatement)
  {
    EntityManager em = _context.getEntityManager();
    Query nq = em.createNativeQuery(sqlStatement);
    return nq.getResultList();
  }

  public Object queryObjectById(JPASupport _context, Class objClass, long id) throws Exception
  {
    List result = null;
    EntityManager em = _context.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery c = cb.createQuery(objClass);
    Root emp = c.from(objClass);
    c.select(emp).where(cb.equal(emp.get("id"), Long.valueOf(id)));
    result = em.createQuery(c).getResultList();
    if (result.isEmpty())
      return null;

    return result.get(0);
  }

  public Object queryObjectByDn(JPASupport _context, long _sessionKey, Class _objClass, String _dn)
    throws Exception
  {
    List result = null;
    EntityManager em = _context.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery c = cb.createQuery(_objClass);
    Root emp = c.from(_objClass);
    c.select(emp).where(cb.equal(emp.get("dn"), _dn));
    result = em.createQuery(c).getResultList();
    if (result.isEmpty())
      return null;

    return result.get(0);
  }

  public List findObjectsByLike(JPASupport _context, Class objClass, String strParameters, Object[] values)
    throws Exception
  {
    List result = new ArrayList();

    String className = objClass.getSimpleName();
    className = className.substring(className.lastIndexOf(46) + 1);

    StringBuilder queryString = new StringBuilder();
    queryString.append(new StringBuilder().append("select ").append(className).append("1  from ").append(className).append(" ").append(className).append("1 where  1=1").toString());
    Map parameters = new HashMap();

    if ((strParameters != null) && (strParameters.length() > 0)) {
      boolean isExit = false;
      int n = 0;
      while (!(isExit))
      {
        String parameter;
        int chIndex = strParameters.indexOf(44);
        if (chIndex > 0) {
          parameter = strParameters.substring(0, chIndex).trim();
          strParameters = strParameters.substring(chIndex + 1);
        } else {
          parameter = strParameters.substring(0).trim();
          isExit = true;
        }
        Object value = values[(n++)];
        if ((value != null) && (!(value.toString().equals(""))))
          if (value instanceof String) {
            queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(parameter).append("  LIKE :").append(parameter).toString());
            parameters.put(parameter, new StringBuilder().append("%").append(value).append("%").toString());
          } else {
            queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(parameter).append(" = :").append(parameter).toString());
            parameters.put(parameter, value);
          }
        else
          return result;
      }
    }

    Query query = _context.getEntityManager().createQuery(queryString.toString());
    for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
      query.setParameter(key, parameters.get(key));
    }
    result = query.getResultList();

    return result;
  }

  public List findObjectsByEqual(JPASupport _context, Class objClass, String strParameters, Object[] values) throws Exception {
    List result = new ArrayList();

    String className = objClass.getSimpleName();
    className = className.substring(className.lastIndexOf(46) + 1);

    StringBuilder queryString = new StringBuilder();
    queryString.append(new StringBuilder().append("select ").append(className).append("1  from ").append(className).append(" ").append(className).append("1 where  1=1").toString());
    Map parameters = new HashMap();

    if ((strParameters != null) && (strParameters.length() > 0)) {
      boolean isExit = false;
      int n = 0;
      while (!(isExit))
      {
        String parameter;
        int chIndex = strParameters.indexOf(44);
        if (chIndex > 0) {
          parameter = strParameters.substring(0, chIndex).trim();
          strParameters = strParameters.substring(chIndex + 1);
        } else {
          parameter = strParameters.substring(0).trim();
          isExit = true;
        }
        Object value = values[(n++)];
        if ((value != null) && (!(value.toString().equals("")))) {
          queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(parameter).append(" = :").append(parameter).toString());
          parameters.put(parameter, value);
        } else {
          return result;
        }
      }
    }
    Query query = _context.getEntityManager().createQuery(queryString.toString());
    for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
      query.setParameter(key, parameters.get(key));
    }
    result = query.getResultList();

    return result;
  }

  public List queryChildObjectsById(JPASupport _context, Class objClass, String parentField, long parentId) throws Exception {
    EntityManager em = _context.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery c = cb.createQuery(objClass);
    Root emp = c.from(objClass);
    c.select(emp).where(cb.equal(emp.get(parentField), Long.valueOf(parentId)));
    return em.createQuery(c).getResultList();
  }

  public List queryAllObjects(JPASupport _context, Class objClass)
    throws Exception
  {
    return queryAllObjects(_context, objClass, null, null);
  }

  public List queryAllObjects(JPASupport _context, Class objClass, Integer start, Integer limit) throws Exception {
    EntityManager em = _context.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery c = cb.createQuery(objClass);
    Root emp = c.from(objClass);
    c.select(emp);
    Query query = em.createQuery(c);
    if (start != null)
      query.setFirstResult(start.intValue());

    if (limit != null)
      query.setMaxResults(limit.intValue());

    return query.getResultList();
  }

  public List queryObjects(JPASupport _context, Class objClass, String queryString) throws Exception {
    return queryObjects(_context, objClass, queryString, null, null);
  }

  public List queryObjects(JPASupport _context, Class objClass, String queryString, Integer start, Integer limit) throws Exception {
    EntityManager em = _context.getEntityManager();
    Query nq = em.createNativeQuery(queryString, objClass);
    if (start != null)
      nq.setFirstResult(start.intValue());

    if (limit != null)
      nq.setMaxResults(limit.intValue());

    return nq.getResultList();
  }

  public List queryObjects(JPASupport _context, Class objClass, String tableName, Map cond) throws Exception {
    return queryObjects(_context, objClass, tableName, cond, true);
  }

  public List queryObjects(JPASupport _context, Class objClass, String tableName, Map cond, Integer start, Integer limit) throws Exception {
    return queryObjects(_context, objClass, tableName, cond, true, start, limit);
  }

  public List queryObjects(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike) throws Exception {
    return queryObjects(_context, objClass, tableName, cond, _isLike, null, null);
  }

  public List queryObjects(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike, Integer start, Integer limit) throws Exception {
    if ((cond == null) || (cond.isEmpty()))
    {
      return queryAllObjects(_context, objClass, start, limit);
    }

    String whereClause = makeWhereClause(cond, true, _isLike);
    if (whereClause.trim().compareTo("") == 0)
      return queryAllObjects(_context, objClass);

    StringBuilder sql = new StringBuilder();
    sql.append("select * from ").append(tableName).append(" where ");
    sql.append(whereClause);

    return queryObjects(_context, objClass, sql.toString(), start, limit);
  }

  public List queryObjectsInTheseData(JPASupport _context, Class objClass, String tableName, Map cond, String inTheseData) throws Exception {
    return queryObjectsInTheseData(_context, objClass, tableName, cond, inTheseData);
  }

  public List queryObjectsInTheseData(JPASupport _context, Class objClass, String tableName, Map cond, String inTheseData, Integer start, Integer limit) throws Exception {
    if (cond == null)
    {
      return queryAllObjects(_context, objClass, start, limit);
    }

    String whereClause = makeWhereClause(cond, true);
    if (whereClause.trim().compareTo("") == 0)
      return queryAllObjects(_context, objClass);

    StringBuilder sql = new StringBuilder();
    sql.append("select * from ").append(tableName).append(" where ");
    sql.append(whereClause);
    sql.append(" and ").append(inTheseData);

    return queryObjects(_context, objClass, sql.toString(), start, limit);
  }

  public long queryCountOfObjectWhereCondition(JPASupport _context, Class cls, String tableName, String sql) throws Exception
  {
    if ((sql == null) || (sql.length() == 0) || (tableName == null) || (tableName.length() == 0)) {
      return queryCountOfObjects(_context, cls);
    }

    EntityManager em = _context.getEntityManager();

    sql = new StringBuilder().append("select count(*) from ").append(tableName).append(" ").append(tableName).append(sql).toString();
    Query query = em.createQuery(sql);
    Number countResult = (Number)query.getSingleResult();
    return countResult.longValue();
  }

  public long queryCountsOfObjectWhereCondition(JPASupport _context, Class cls, String sql)
    throws Exception
  {
    String className = cls.getName();
    className = className.substring(className.lastIndexOf(46) + 1);

    return queryCountOfObjectWhereCondition(_context, cls, className, sql);
  }

  public long queryCountOfObjects(JPASupport _context, Class cls)
    throws Exception
  {
    EntityManager em = _context.getEntityManager();
    String className = cls.getName();
    className = className.substring(className.lastIndexOf(46) + 1);
    String sql = new StringBuilder().append("select count(*) from ").append(className).append(" ").append(className).toString();
    Query query = em.createQuery(sql);
    Number countResult = (Number)query.getSingleResult();
    return countResult.longValue();
  }

  public long queryObjectsCount(JPASupport _context, String _queryStr)
    throws Exception
  {
    EntityManager em = _context.getEntityManager();
    Query query = em.createNativeQuery(_queryStr);
    Number countResult = (Number)query.getSingleResult();
    return countResult.longValue();
  }

  public List queryObjectsByOr(JPASupport _context, Class objClass, String tableName, Map cond)
    throws Exception
  {
    return queryObjectsByOr(_context, objClass, tableName, cond, true);
  }

  public List queryObjectsByOr(JPASupport _context, Class objClass, String tableName, Map cond, Integer start, Integer limit) throws Exception {
    return queryObjectsByOr(_context, objClass, tableName, cond, true);
  }

  public List queryObjectsByOr(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike) throws Exception {
    return queryObjectsByOr(_context, objClass, tableName, cond, _isLike, null, null);
  }

  public List queryObjectsByOr(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike, Integer start, Integer limit) throws Exception {
    if ((cond == null) || (cond.isEmpty()))
    {
      return queryAllObjects(_context, objClass);
    }
    String whereClause = makeWhereClause(cond, false, _isLike);
    if (whereClause.trim().compareTo("") == 0)
      return queryAllObjects(_context, objClass);

    StringBuilder sql = new StringBuilder();
    sql.append("select * from ").append(tableName).append(" where ");
    sql.append(whereClause);

    return queryObjects(_context, objClass, sql.toString(), start, limit);
  }

  public String makeWhereClause(Map cond, boolean isAND) {
    return makeWhereClause(cond, isAND, true);
  }

  public String makeWhereClause(Map cond, boolean isAND, boolean _isLike) {
    StringBuilder whereSql = new StringBuilder();
    String andString = " ";
    Iterator it = cond.keySet().iterator();
    while (it.hasNext()) {
        String key = (String)it.next();

        if (!(cond.get(key) instanceof String))
            if (cond.get(key).toString().trim().compareTo("") != 0)
          break;

      String operator = (_isLike) ? " like " : " = ";
      String value = (_isLike) ? new StringBuilder().append("'%").append(cond.get(key)).append("%' ").toString() : new StringBuilder().append("'").append(cond.get(key)).append("' ").toString();

        operator = " = ";
      value = new StringBuilder().append("").append(cond.get(key)).append(" ").toString();

      StringBuilder append = whereSql.append(andString).append(key).append(operator).append(value);
      if (isAND)
        andString = " and ";
      else
        andString = " or ";
    }

     return whereSql.toString();
  }

  public Object queryAttrBySql(JPASupport _context, String sqlString, String attrName) throws Exception {
    EntityManager em = _context.getEntityManager();
    Query nq = em.createNativeQuery(sqlString);
    List rl = nq.getResultList();
    if ((rl == null) || (rl.size() == 0)) {
      return null;
    }

    return rl.get(0);
  }

//  public Date getDBCurrentDate(JPASupport _context)
//  {
//    EntityManager em = _context.getEntityManager();
//    String sql = "SELECT TO_CHAR(SYSDATE, 'YYYYMMDDHH24MISS') as RES FROM DUAL";
//    Query query = em.createQuery(sql);
//    String timeStr = query.getSingleResult().toString();
//    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
//    try {
//      Date d = format.parse(timeStr);
//      return d; } catch (ParseException ex) {
//    }
//    return null;
//  }

  public double querySum(JPASupport _context, String _queryStr)
    throws Exception
  {
    EntityManager em = _context.getEntityManager();
    Query query = em.createNativeQuery(_queryStr);
    Number countResult = (Number)query.getSingleResult();
    return countResult.doubleValue();
  }

  public List querySQL(JPASupport _context, String _queryStr)
  {
    EntityManager em = _context.getEntityManager();
    Query query = em.createNativeQuery(_queryStr);
    return query.getResultList();
  }
  public List querySQL(JPASupport _context, String _queryStr,Class clazz)
  {
    EntityManager em = _context.getEntityManager();
    Query query = em.createNativeQuery(_queryStr,clazz);
    return query.getResultList();
  }
  
  public int executeUpdateSQL(JPASupport _context, String sql) {
	    EntityManager em = _context.getEntityManager();
	    int re = em.createQuery(sql).executeUpdate();
	    return re;
	  }
}