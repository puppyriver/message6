package com.alcatelsbell.nms.db.components.service;

import com.alcatelsbell.nms.common.CommonUtil;
import com.alcatelsbell.nms.common.DatePeriod;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.db.dao.JpaGenericExtDao;
import com.alcatelsbell.nms.valueobject.BObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;


public class JPAUtil
{
	protected final Log logger = LogFactory.getLog(super.getClass());
	private static JPAUtil instance = null;

	public static synchronized JPAUtil getInstance()
	{
		if (instance == null)
			instance = new JPAUtil();

		return instance;
	}

	public BObject createObject(JPASupport _context, long _sessionKey, BObject obj) throws Exception {
        if (obj.getDn() == null) {
            obj.setDn(SysUtil.nextDN());
        }
		EntityManager em = _context.getEntityManager();
		obj.setCreateDate(new Date());
		em.persist(obj);
      //   logger.debug("create "+obj.getClass().getName()+":"+obj.getId());
		_context.addReturnObjects(obj);
		return obj;
	}


	/**
	 *  Please use removeObject
	 * @param obj
	 * @throws Exception
	 */
	@Deprecated
	public void deleteObject(JPASupport _context, long _sessionKey, BObject obj) throws Exception {
		EntityManager em = _context.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery c = cb.createQuery(obj.getClass());
		Root emp = c.from(obj.getClass());
		c.select(emp).where(cb.equal(emp.get("id"), Long.valueOf(obj.getId())));
		List result = em.createQuery(c).getResultList();
		if (!result.isEmpty())
			em.remove(result.get(0));
	}
	public void removeObject(JPASupport _context, long _sessionKey, BObject obj) throws Exception {
		EntityManager em = _context.getEntityManager();
		em.remove(em.merge(obj));
	}

	public BObject storeObject(JPASupport _context, long _sessionKey, BObject _obj) throws Exception {
		EntityManager em = _context.getEntityManager();

		_obj.setUpdateDate(new Date());

		BObject newObject = (BObject)em.merge(_obj);
		em.persist(newObject);
		_context.addReturnObjects(newObject);
		return newObject;
	}

	public BObject saveObject(JPASupport _context, long sessionKey, BObject obj) throws Exception {
		if (obj.getId() == null)
			createObject(_context, sessionKey, obj);
		else
			storeObject(_context, sessionKey, obj);
		return obj;
	}

	public BObject loadObject(JPASupport _context, BObject obj) throws Exception {
		if (obj.getId() == null)
			return null;

		return ((BObject)DBUtil.getInstance().queryObjectById(_context, obj.getClass(), obj.getId().longValue()));
	}

	//  public BObject createObjectByDn(JPASupport _context, long sessionKey, BObject _obj) throws Exception
	//  {
	//    BObject result = null;
	//    EntityManager em = _context.getEntityManager();
	//
	//    BObject bobjDB = (BObject)JpaGenericExtDao.getInstance().getObject(em, _obj, "dn");
	//
	//    if (bobjDB == null)
	//    {
	//      result = createObject(_context, sessionKey, _obj);
	//    }
	//
	//    return result;
	//  }
	//

	public BObject findObjectByDn(JPASupport _context, long sessionKey,Class  cls,String dn) throws Exception {
		List<BObject> objs = this.findObjects(_context,"select c from "+cls.getSimpleName()+" as c where c.dn = '"+dn+"'");
		if (objs != null && objs.size() > 0)
			return objs.get(0);
		return null;
	}
	public BObject findObjectByDn(JPASupport _context, long sessionKey, BObject _obj) throws Exception {
		BObject result = null;
		EntityManager em = _context.getEntityManager();

		BObject bobjDB = //null;
			(BObject) JpaGenericExtDao.getInstance().getObject(em, _obj, "dn");
		return bobjDB;
	}
	public BObject storeObjectByDn(JPASupport _context, long sessionKey, BObject _obj) throws Exception {
		BObject result = null;
		EntityManager em = _context.getEntityManager();

		BObject bobjDB =// null;
			(BObject) JpaGenericExtDao.getInstance().getObject(em, _obj, "dn");

		//    List<BObject> objs =this.findObjects(_context,"select c from "+_obj.getClass().getSimpleName()+" as c where c.dn = '"+_obj.getDn()+"'");
		//    if (objs != null && objs.size() > 0) bobjDB = objs.get(0);
		if (bobjDB == null)
		{
			result = createObject(_context, sessionKey, _obj);
		} else {
			_obj.setId(bobjDB.getId());
            _obj.setCreateDate(bobjDB.getCreateDate());
			result = storeObject(_context, sessionKey, _obj);
		}
		return result;
	}


	//
	public BObject storeObjectByKeys(JPASupport _context, long sessionKey, BObject _obj, String keys) throws Exception {
		BObject result = null;
		EntityManager em = _context.getEntityManager();

		BObject bobjDB = (BObject)JpaGenericExtDao.getInstance().getObject(em, _obj, keys);

		if (bobjDB == null)
		{
			result = createObject(_context, sessionKey, _obj);
		} else {
			_obj.setId(bobjDB.getId());
			result = storeObject(_context, sessionKey, _obj);
		}
		return result;
	}
	//
	//  public Object getObject(JPASupport _context, Object obj, String strParameters) throws Exception {
	//    EntityManager em = _context.getEntityManager();
	//    return JpaGenericExtDao.getInstance().getObject(em, obj, strParameters);
	//  }
	//
	public List findObjects(JPASupport _context, Object obj, String strParameters, Map mapValue) throws Exception {
		EntityManager em = _context.getEntityManager();
		return JpaGenericExtDao.getInstance().findObjects(em, obj, strParameters, mapValue);
	}

	public List findObjects(JPASupport _context, Object obj, boolean isEquals,String strParameters) throws Exception {
		EntityManager em = _context.getEntityManager();
		return JpaGenericExtDao.getInstance().findObjects(em, obj, isEquals,strParameters);
	}
	//
	public List<Object> findObjectsByMapTable(JPASupport _context, Class cls, BObject bobj, Class mapClass, String[] mapkeys) throws Exception {
		List result = null;
		EntityManager em = _context.getEntityManager();

		String className = cls.getSimpleName();
		String cls1 = new StringBuilder().append(className).append("1").toString();
		String className2 = bobj.getClass().getSimpleName();
		String cls2 = new StringBuilder().append(className2).append("1").toString();
		String className3 = mapClass.getSimpleName();
		String cls3 = new StringBuilder().append(className3).append("1").toString();

		StringBuilder queryString = new StringBuilder();

		queryString.append(new StringBuilder().append("select DISTINCT ").append(cls1).append(" from ").append(className).append(" ").append(cls1).append(", ").append(className3).append(" ").append(cls3).toString());
		queryString.append(new StringBuilder().append(" where ").append(cls3).append(".").append(mapkeys[0]).append(" = ").append(cls1).append(".id and ").append(cls3).append(".").append(mapkeys[1]).append(" = ").append(bobj.getId()).toString());

		Query query = em.createQuery(queryString.toString());
		result = query.getResultList();
		return result;
	}

	//  public long findObjectsNumByFilter(JPASupport _context, Class cls, Object filter) throws Exception {
	//    EntityManager em = _context.getEntityManager();
	//
	//    StringBuilder queryString = new StringBuilder();
	//    String className = cls.getSimpleName();
	//    String cls1 = new StringBuilder().append(className).append("1").toString();
	//    queryString.append(new StringBuilder().append("select COUNT(").append(cls1).append(") from ").append(className).append(" ").append(cls1).append(" where ").toString());
	//    Map dateMap = new HashMap();
	//    queryString.append(getCondtionSql(cls, filter, dateMap));
	//
	//    Query query = em.createQuery(queryString.toString());
	//    for (Iterator i$ = dateMap.keySet().iterator(); i$.hasNext(); ) { Object key = i$.next();
	//      if ((dateMap.get(key) != null) && (!(dateMap.get(key).equals(""))))
	//        query.setParameter(key.toString(), dateMap.get(key));
	//    }
	//
	//    Number cResults = (Number)query.getSingleResult();
	//    return cResults.longValue();
	//  }
	//
	//  public List findObjectsByFilter(JPASupport _context, Class cls, Object filter, Integer start, Integer limit) throws Exception {
	//    List result = null;
	//    EntityManager em = _context.getEntityManager();
	//
	//    StringBuilder queryString = new StringBuilder();
	//    String className = cls.getSimpleName();
	//    String cls1 = new StringBuilder().append(className).append("1").toString();
	//    queryString.append(new StringBuilder().append("select ").append(cls1).append(" from ").append(className).append(" ").append(cls1).append(" where ").toString());
	//    Map dateMap = new HashMap();
	//    queryString.append(getCondtionSql(cls, filter, dateMap));
	//
	//    Query query = em.createQuery(queryString.toString());
	//    if (start != null)
	//      query.setFirstResult(start.intValue());
	//
	//    if (limit != null)
	//      query.setMaxResults(limit.intValue());
	//
	//    for (Iterator i$ = dateMap.keySet().iterator(); i$.hasNext(); ) { Object key = i$.next();
	//      if ((dateMap.get(key) != null) && (!(dateMap.get(key).equals(""))))
	//        query.setParameter(key.toString(), dateMap.get(key));
	//    }
	//
	//    result = query.getResultList();
	//    return result;
	//  }
	//
	//  public List findObjectsByFilter(JPASupport _context, String strSql, Class cls, Object filter, Integer start, Integer limit) throws Exception {
	//    List result = null;
	//    EntityManager em = _context.getEntityManager();
	//
	//    Map dateMap = new HashMap();
	//    String whereString = getCondtionSql(cls, filter, dateMap);
	//    strSql = strSql.replace("$Where", whereString.toString());
	//
	//    Query query = em.createQuery(strSql);
	//    if (start != null)
	//      query.setFirstResult(start.intValue());
	//
	//    if (limit != null)
	//      query.setMaxResults(limit.intValue());
	//
	//    for (Iterator i$ = dateMap.keySet().iterator(); i$.hasNext(); ) { Object key = i$.next();
	//      if ((dateMap.get(key) != null) && (!(dateMap.get(key).equals(""))))
	//        query.setParameter(key.toString(), dateMap.get(key));
	//    }
	//
	//    result = query.getResultList();
	//    return result;
	//  }

	//  public String getCondtionSql(Class cls, Object filter, Map dateMap) throws Exception {
	//    Field field;
	//    StringBuilder queryString = new StringBuilder();
	//    Map nameToField = new HashMap();
	//    String className1 = new StringBuilder().append(cls.getSimpleName()).append("1").toString();
	//
	//    Field[] arr$ = cls.getDeclaredFields(); int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { field = arr$[i$];
	//      if (field.getEquipmenttype().getName().contains(".Date")) {
	//        nameToField.put(new StringBuilder().append(field.getName()).append("Begin").toString(), field);
	//        nameToField.put(new StringBuilder().append(field.getName()).append("End").toString(), field);
	//      }
	//      nameToField.put(field.getName(), field);
	//    }
	//
	//    queryString.append("(1 = 1 ");
	//    arr$ = filter.getClass().getDeclaredFields(); len$ = arr$.length; for (i$ = 0; i$ < len$; ++i$) { field = arr$[i$];
	//      Field srcField = (Field)nameToField.get(field.getName());
	//      if (srcField == null)
	//        break label849:
	//
	//      Object value = getFiledValue(filter, field);
	//      if (value != null) { if (value.toString().equals(""))
	//          break label849:
	//
	//        label849: if (srcField.getEquipmenttype().getName().equals("java.lang.String")) {
	//          queryString.append(new StringBuilder().append(" and ").append(className1).append(".").append(field.getName()).append("  LIKE '%").append(value).append("%' ").toString());
	//        } else if (srcField.getEquipmenttype().getName().equals("java.util.Date")) {
	//          dateMap.put(field.getName(), value);
	//          if (field.getName().endsWith("Begin"))
	//            queryString.append(new StringBuilder().append(" and ").append(className1).append(".").append(srcField.getName()).append("  >= :").append(field.getName()).toString());
	//          else if (field.getName().endsWith("End"))
	//            queryString.append(new StringBuilder().append(" and ").append(className1).append(".").append(srcField.getName()).append("  <= :").append(field.getName()).toString());
	//          else
	//            queryString.append(new StringBuilder().append(" and ").append(className1).append(".").append(srcField.getName()).append("  = :").append(field.getName()).toString());
	//        }
	//        else if ((srcField.getEquipmenttype().isPrimitive()) && (field.getEquipmenttype().getName().equals("java.lang.String"))) {
	//          String[] values = value.toString().split(";|,");
	//          queryString.append(" and ( 1 = 0 ");
	//          String[] arr$ = values; int len$ = arr$.length; for (int i$ = 0; i$ < len$; ++i$) { String strValue = arr$[i$];
	//            queryString.append(new StringBuilder().append(" or ").append(className1).append(".").append(field.getName()).append(" = ").append(strValue).toString());
	//          }
	//          queryString.append(" ) ");
	//        }
	//        else if ((!(field.getEquipmenttype().getName().contains("List"))) && (!(field.getEquipmenttype().getName().contains("Collection"))) && (!(field.getEquipmenttype().getName().contains("Set"))) && (!(field.getEquipmenttype().getName().contains("Map")))) { if (field.getEquipmenttype().isArray())
	//          {
	//            break label849: }
	//          if (field.getEquipmenttype().getName().compareTo(srcField.getEquipmenttype().getName()) == 0)
	//            queryString.append(new StringBuilder().append(" and ").append(className1).append(".").append(field.getName()).append("  = ").append(value).toString());
	//        }
	//      }
	//    }
	//    queryString.append(" ) ");
	//
	//    return queryString.toString();
	//  }

	private Object getFiledValue(Object obj, Field field) throws Exception {
		boolean oldAccessible = field.isAccessible();
		field.setAccessible(true);
		Object value = field.get(obj);
		field.setAccessible(oldAccessible);
		return value;
	}

	public List findObjects(JPASupport _context, Object obj, String sqlStr, String strParameters, Map mapValue, String orderStr, Integer start, Integer limit) throws Exception {
		List result = new ArrayList();
		EntityManager entityManager = _context.getEntityManager();
		if (obj == null)
			return result;
		try
		{
			String className = obj.getClass().getSimpleName();
			String aliasName = new StringBuilder().append(className).append("1").toString();

			StringBuilder queryString = new StringBuilder();
			if (CommonUtil.getInstance().isEmptyString(sqlStr))
				queryString.append(new StringBuilder().append("select ").append(aliasName).append(" from ").append(className).append(" ").append(aliasName).append(" where  1=1").toString());
			else
				queryString.append(new StringBuilder().append(sqlStr).append(" where  1=1").toString());

			Map parameters = new HashMap();

			if ((strParameters != null) && (strParameters.length() > 0)) {
				boolean isExit = false;
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

					if (parameter.contains(":"))
						queryString.append(new StringBuilder().append(" and ").append(parameter).toString());
					else
						try
					{
							Field objField = obj.getClass().getDeclaredField(parameter);
							boolean oldAccessible = objField.isAccessible();

							objField.setAccessible(true);
							Object value = objField.get(obj);
							objField.setAccessible(oldAccessible);
							if ((value != null) && (!(value.toString().equals(""))))
								if (objField.getType().getName().equals("java.lang.String")) {
									queryString.append(new StringBuilder().append(" and ").append(objField.getName()).append("  LIKE :").append(objField.getName()).toString());
									parameters.put(objField.getName(), new StringBuilder().append("%").append(value).append("%").toString());
								} else {
									queryString.append(new StringBuilder().append(" and ").append(objField.getName()).append(" = :").append(objField.getName()).toString());
									parameters.put(objField.getName(), value);
								}
					}
					catch (Exception e)
					{
						this.logger.error(e,e);
						throw e;
					}
				}
			}

			if (!(CommonUtil.getInstance().isEmptyString(orderStr)))
				queryString.append(new StringBuilder().append(" ").append(orderStr).toString());

			Query query = entityManager.createQuery(queryString.toString());
			for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
			query.setParameter(key, parameters.get(key));
			}

			if (mapValue != null)
				for (Iterator i$ = mapValue.keySet().iterator(); i$.hasNext(); ) {
					Object key = i$.next();
					if ((mapValue.get(key) != null) && (!(mapValue.get(key).equals(""))))
						query.setParameter(key.toString(), mapValue.get(key));
				}


			if (start != null)
				query.setFirstResult(start.intValue());

			if (limit != null)
				query.setMaxResults(limit.intValue());

			result = query.getResultList();
		}
		catch (Exception e) {
			this.logger.error(e,e);
			throw e;
		}

		return result;
	}
	public List findAllObjects(JPASupport _context, Class cls) throws Exception{
		return findObjects(_context,"select c from "+cls.getSimpleName()+" as c");
	}
	public List findObjects(JPASupport _context, String strSql) throws Exception{
		return findObjects(_context,strSql,null,null,null,null);
	}

	/**
	 * Add by Aaron for delete records which have been backuped
	 * @throws Exception 
	 * */
	public void executeQL(JPASupport _context, String strSql, String strPrefix, Map mapValue) throws Exception{
		
		EntityManager entityManager=_context.getEntityManager();
		
		try {
			strSql = getSqlString(strSql, strPrefix, mapValue);
			Query query = entityManager.createQuery(strSql);
			if (mapValue != null) {
				Iterator iterator = mapValue.keySet().iterator();
				while (iterator.hasNext()) {
					Object key = iterator.next();
					if ((mapValue.get(key) == null) || (mapValue.get(key).toString().isEmpty())) {
						continue;
					}

					if ((mapValue.get(key) instanceof DatePeriod)) {
						continue;
					}
					query.setParameter(key.toString(), mapValue.get(key));
				}

			}
			//_context.begin();
			int result = query.executeUpdate();
			this.logger.info(result+" records deleted");
			//_context.rollback();
			
		} catch (Exception e) {
			this.logger.error(e,e);
			throw e;
		}
		
		
	}
	
	
	public void executeQL(JPASupport _context,String ql) {
		EntityManager entityManager = _context.getEntityManager();
		Query query = entityManager.createQuery(ql);
		query.executeUpdate();
	}
    public List queryQL(JPASupport _context,String ql) {
        EntityManager entityManager = _context.getEntityManager();
        Query query = entityManager.createQuery(ql);
        return query.getResultList();
    }
	/**
	 * 
	 * @param _context
	 * @param ql
	 * @param mapValue
	 */
	public void executeQL(JPASupport _context,String ql,Map mapValue) {
		EntityManager entityManager = _context.getEntityManager();
		Query query = entityManager.createQuery(ql);

		if (mapValue != null) {
			Iterator iterator = mapValue.keySet().iterator();
			while (iterator.hasNext()) {
				Object key = iterator.next();
				if ((mapValue.get(key) == null) || (mapValue.get(key).toString().isEmpty())) {
					continue;
				}
				if ((mapValue.get(key) instanceof DatePeriod)) {
					continue;
				}
				query.setParameter(key.toString(), mapValue.get(key));
			}
		}
		query.executeUpdate();
	}

	public List findObjects(JPASupport _context, String strSql, String strPrefix, Map mapValue, Integer start, Integer limit) throws Exception
	{
        long t1 = System.currentTimeMillis();
		List result = new ArrayList();
		EntityManager entityManager = _context.getEntityManager();
		try
		{
			strSql = getSqlString(strSql, strPrefix, mapValue);
			Query query = entityManager.createQuery(strSql);

			if (mapValue != null) {
				Iterator iterator = mapValue.keySet().iterator();
				while (iterator.hasNext()) {
					Object key = iterator.next();
					if ((mapValue.get(key) == null) || (mapValue.get(key).toString().isEmpty())) {
						continue;
					}

					if ((mapValue.get(key) instanceof DatePeriod)) {
						continue;
					}
					query.setParameter(key.toString(), mapValue.get(key));
				}

			}

			if (start != null)
				query.setFirstResult(start.intValue());

			if (limit != null)
				query.setMaxResults(limit.intValue());

			result = query.getResultList();
		}
		catch (Throwable e) {
            logger.error("JPAUtil: queryObjects:"+strSql+"  start="+start+", limit="+limit);
			this.logger.error(e,e);
            if (e instanceof Exception)
			    throw (Exception)e;
		}
        long t2 = System.currentTimeMillis();
        if (t2 -t1  > 1000) {
            logger.error("JPAUtil: queryObjects:"+strSql+" spend "+(t2-t1)+"ms size = "+(result == null ? -1 : result.size()));
        }
		return result;
	}

	public Object findObjectById(JPASupport _context, Class objClass, long id) throws Exception
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

	protected String getSqlString(String strSql, String strPrefix, Map mapValue) throws Exception {
		String result = "";
		try {
			StringBuilder whereString = new StringBuilder();

			if ((mapValue != null) && (!(mapValue.isEmpty()))) {
				Set mapSet = new HashSet(mapValue.keySet());

				Iterator it = mapSet.iterator();
				while (it.hasNext()) {
					String parameter;
					Object value;
					Object key = it.next();
					parameter = key.toString();
					value = mapValue.get(key);
					if (value == null)
						continue;

					if ( value instanceof String && ((String) value).length() > 0){
						whereString.append(new StringBuilder().append(" and (").append(strPrefix).append(".").append(parameter).append("  LIKE :").append(parameter).append(")").toString());
						mapValue.put(parameter, new StringBuilder().append(value).toString()); continue;
					}
					if (value instanceof DatePeriod) {
						DatePeriod ngmdate = (DatePeriod)value;
						String strTemp = "";
						if (ngmdate.getStartDate() != null) {
							strTemp = new StringBuilder().append(strPrefix).append(".").append(parameter).append(">= :").append(parameter).append("_Begin ").toString();
							mapValue.put(new StringBuilder().append(parameter).append("_Begin").toString(), ngmdate.getStartDate());
						} else {
							strTemp = "1=1 ";
						}
						if (ngmdate.getEndDate() != null) {
							strTemp = new StringBuilder().append(strTemp).append(" and ").append(strPrefix).append(".").append(parameter).append("<= :").append(parameter).append("_End ").toString();
							mapValue.put(new StringBuilder().append(parameter).append("_End").toString(), ngmdate.getEndDate());
						}
						whereString.append(new StringBuilder().append(" and (").append(strTemp).append(")").toString());
					} else {
						whereString.append(new StringBuilder().append(" and (").append(strPrefix).append(".").append(parameter).append(" = :").append(parameter).append(")").toString());
					}
				}
			}

			result = strSql.replace("$Where", whereString.toString());
		}
		catch (Exception e)
		{
			this.logger.error(e,e);
			throw e;
		}
		return result;
	}


	public long findObjectsCount(JPASupport _context, Class cls, Map mapValue) throws Exception {
		EntityManager entityManager = _context.getEntityManager();
		try {
			StringBuilder queryString = new StringBuilder();
			String className = cls.getSimpleName();
			String cls1 = new StringBuilder().append(className).append("1").toString();
			queryString.append(new StringBuilder().append("select COUNT(").append(cls1).append(") from ").append(className).append(" ").append(cls1).append(" where (1=1) $Where").toString());
			String strSql = queryString.toString();
			strSql = getSqlString(strSql, cls1, mapValue);

			Query query = entityManager.createQuery(strSql);

			if (mapValue != null) {
				Iterator iterator = mapValue.keySet().iterator();

				while (iterator.hasNext()) {
					Object key = iterator.next();
					if ((mapValue.get(key) != null) &&  (mapValue.get(key) instanceof String)) {
						try{
							query.setParameter(key.toString(), mapValue.get(key));
						}catch(IllegalArgumentException e){
							continue;
						}
					}
					else if ((mapValue.get(key) instanceof DatePeriod)) {
						try{
							query.setParameter(key.toString(), mapValue.get(key));
						}catch(IllegalArgumentException e){
							continue;
						}
					}
					/*add by Aaron*/
					else{
						try{
							query.setParameter(key.toString(), mapValue.get(key));
						}catch(IllegalArgumentException e){
							continue;
						}
					}
					/*end*/
				}
			}

			Number cResults = (Number)query.getSingleResult();
			return cResults.longValue();
		} catch (Exception e) {
			this.logger.error(e,e);
			throw e;
		}
	}

	public List findObjects(JPASupport _context, Class objClass, String tableName, Map cond) throws Exception {
		return findObjects(_context, objClass, tableName, cond, true);
	}

	public List findObjects(JPASupport _context, Class objClass, String tableName, Map cond, Integer start, Integer limit) throws Exception {
		return findObjects(_context, objClass, tableName, cond, true, start, limit);
	}

	public List findObjects(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike) throws Exception {
		return findObjects(_context, objClass, tableName, cond, _isLike, null, null);
	}

	public List findAllObjects(JPASupport _context, Class objClass, Integer start, Integer limit) throws Exception {
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

	public List findObjects(JPASupport _context, Class objClass, String tableName, Map cond, boolean _isLike, Integer start, Integer limit) throws Exception {
		if ((cond == null) || (cond.isEmpty()))
		{
			return findAllObjects(_context, objClass, start, limit);
		}

		String whereClause = makeWhereClause(cond, true, _isLike);
		if (whereClause.trim().compareTo("") == 0)
			return findAllObjects(_context, objClass);

		StringBuilder sql = new StringBuilder();
		sql.append("select * from ").append(tableName).append(" where ");
		sql.append(whereClause);

		return findObjects(_context, objClass, sql.toString(), start, limit);
	}

	public List findObjects(JPASupport _context, Class objClass, String queryString, Integer start, Integer limit) throws Exception {
		EntityManager em = _context.getEntityManager();
		Query nq = em.createNativeQuery(queryString, objClass);
		if (start != null)
			nq.setFirstResult(start.intValue());

		if (limit != null)
			nq.setMaxResults(limit.intValue());

		return nq.getResultList();
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

	public List  querySQL(JPASupport _context, String _queryStr,Class clazz)
	{
		EntityManager em = _context.getEntityManager();
		Query query = em.createNativeQuery(_queryStr,clazz);
		return query.getResultList();
	}

    public List  querySQL(JPASupport _context, String _queryStr)
    {
        EntityManager em = _context.getEntityManager();
        Query query = em.createNativeQuery(_queryStr);
        return query.getResultList();
    }

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("appserver-spring.xml");

        JPASupport support = JPASupportFactory.createJPASupport();
        List list = JPAUtil.getInstance().querySQL(support, "select * from META_CONFIG");
        System.out.println("list = " + list);
    }

}