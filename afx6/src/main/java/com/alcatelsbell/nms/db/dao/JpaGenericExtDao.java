package com.alcatelsbell.nms.db.dao;

/**
 * User: Ronnie.Chen
 * Date: 11-5-11
 * Time: 下午3:33
 */


import com.alcatelsbell.nms.common.CommonUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.*;


public class JpaGenericExtDao extends JpaGenericDao<Object>
{
  protected static JpaGenericExtDao instance = null;

  protected JpaGenericExtDao()
  {
    super(Object.class);
  }

  public static synchronized JpaGenericExtDao getInstance()
  {
    if (instance == null)
      instance = new JpaGenericExtDao();

    return instance;
  }

  public Object getObject(EntityManager entityManager, Object obj)
    throws Exception
  {
    Object result = null;
    List objList = findObjects(entityManager, obj);
    if (objList.size() > 0)
      result = objList.get(0);

    return result;
  }

  public Object getObject(EntityManager entityManager, Object obj, String strParameters) throws Exception {
    Object result = null;
    List objList = findObjects(entityManager, obj, true, strParameters);
    if (objList.size() > 0)
      result = objList.get(0);

    return result;
  }

  public List findObjects(EntityManager entityManager, Object obj, boolean isEquals, String strParameters) throws Exception {
    List result = new ArrayList();
    if (obj == null)
      return result;
    try
    {
      String className = obj.getClass().getName();
      className = className.substring(className.lastIndexOf(46) + 1);

      StringBuilder queryString = new StringBuilder();
      queryString.append(new StringBuilder().append("select ").append(className).append("1  from ").append(className).append(" ").append(className).append("1 where  1=1").toString());
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
          try
          {
            Field objField = CommonUtil.getInstance().getField(obj.getClass(), parameter);
            boolean oldAccessible = objField.isAccessible();

            objField.setAccessible(true);
            Object value = objField.get(obj);
            objField.setAccessible(oldAccessible);
            if ((value != null) && (!(value.toString().equals("")))) {
              if (objField.getType().getName().equals("java.lang.String")) {
                if (isEquals) {
                  queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(objField.getName()).append("  = :").append(objField.getName()).toString());
                  parameters.put(objField.getName(), value);
                } else {
                  queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(objField.getName()).append("  LIKE :").append(objField.getName()).toString());
                  parameters.put(objField.getName(), new StringBuilder().append("%").append(value).append("%").toString());
                }
              } else {
                queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(objField.getName()).append(" = :").append(objField.getName()).toString());
                parameters.put(objField.getName(), value);
              }

            }
            else
            {
              return result;
            }
          } catch (Exception e) {
            this.logger.error(e);
            throw e;
          }
        }
      }
      Query query = entityManager.createQuery(queryString.toString());
      for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
        query.setParameter(key, parameters.get(key));
      }
      result = query.getResultList();
    }
    catch (Exception e) {
      this.logger.error(e);
      throw e;
    }
    return result;
  }

  public List findObjects(EntityManager entityManager, Object obj)
    throws Exception
  {
    List result = new ArrayList();
    if (obj == null)
      return result;
    try
    {
      String className = obj.getClass().getName();
      className = className.substring(className.lastIndexOf(46) + 1);

      StringBuilder queryString = new StringBuilder();
      queryString.append(new StringBuilder().append("select ").append(className).append(" from ").append(className).append(" ").append(className).append(" where  1=1").toString());
      Map parameters = new HashMap();

      Iterator iterator = CommonUtil.getInstance().getAllFields(obj.getClass()).iterator();
      while (iterator.hasNext()) {
        Field field;
        Object value;
        field = (Field)iterator.next();


            while ((field.getType().getName().contains("List")) ||
                       (field.getName().contains("Collection")) ||
                              (field.getName().contains("Set")) ||
                                     (field.getName().contains("Map")));
            if (!(field.getType().isArray())) {
              break;
            }



          boolean oldAccessible = field.isAccessible();
          field.setAccessible(true);
          value = field.get(obj);
          field.setAccessible(oldAccessible);

          if ((!(field.getType().isPrimitive())) ||
            (value.toString().compareToIgnoreCase("-1") != 0))
            break;



        if ((value != null) && (!(value.toString().equals(""))))
          if (field.getType().getName().equals("java.lang.String")) {
            queryString.append(new StringBuilder().append(" and ").append(field.getName()).append("  LIKE :").append(field.getName()).toString());
            parameters.put(field.getName(), new StringBuilder().append("%").append(value).append("%").toString());
          } else {
            queryString.append(new StringBuilder().append(" and ").append(field.getName()).append(" = :").append(field.getName()).toString());
            parameters.put(field.getName(), value);
          }
      }

      Query query = entityManager.createQuery(queryString.toString());
      for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
        query.setParameter(key, parameters.get(key));
      }
      result = query.getResultList();
    } catch (Exception e) {
      this.logger.error(e);
    }
    return result;
  }

  public List findObjects(EntityManager entityManager, Object obj, String strParameters)
    throws Exception
  {
    List result = new ArrayList();
    if (obj == null)
      return result;
    try
    {
      String className = obj.getClass().getName();
      className = className.substring(className.lastIndexOf(46) + 1);

      StringBuilder queryString = new StringBuilder();
      queryString.append(new StringBuilder().append("select ").append(className).append("1  from ").append(className).append(" ").append(className).append("1 where  1=1").toString());
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
          try
          {
            Field objField = CommonUtil.getInstance().getField(obj.getClass(), parameter);
            boolean oldAccessible = objField.isAccessible();

            objField.setAccessible(true);
            Object value = objField.get(obj);
            objField.setAccessible(oldAccessible);
            if ((value != null) && (!(value.toString().equals("")))) {
              if (objField.getType().getName().equals("java.lang.String")) {
                queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(objField.getName()).append("  LIKE :").append(objField.getName()).toString());
                parameters.put(objField.getName(), new StringBuilder().append("%").append(value).append("%").toString());
              } else {
                queryString.append(new StringBuilder().append(" and ").append(className).append("1.").append(objField.getName()).append(" = :").append(objField.getName()).toString());
                parameters.put(objField.getName(), value);
              }

            }
            else
            {
              return result;
            }
          } catch (Exception e) {
            this.logger.error(e);
            throw e;
          }
        }
      }
      Query query = entityManager.createQuery(queryString.toString());
      for (Iterator i$ = parameters.keySet().iterator(); i$.hasNext(); ) { String key = (String)i$.next();
        query.setParameter(key, parameters.get(key));
      }
      result = query.getResultList();
    }
    catch (Exception e) {
      this.logger.error(e);
      throw e;
    }
    return result;
  }

  public List findObjects(EntityManager entityManager, Object obj, String strParameters, Map mapValue) throws Exception {
    List result = new ArrayList();
    if (obj == null)
      return result;
    try
    {
      String className = obj.getClass().getName();
      className = className.substring(className.lastIndexOf(46) + 1);

      StringBuilder queryString = new StringBuilder();
      queryString.append(new StringBuilder().append("select ").append(className).append("1 from ").append(className).append(" ").append(className).append("1 where  1=1").toString());
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
              this.logger.error(e);
              throw e;
            }
        }

      }

      Query query = entityManager.createQuery(queryString.toString());
      Iterator iterator = parameters.keySet().iterator();
      while (iterator.hasNext()) {
          String key = (String)iterator.next();
            query.setParameter(key, parameters.get(key));
      }

      if (mapValue != null)
          iterator = mapValue.keySet().iterator();
         while (iterator.hasNext()) {

                 Object key = iterator.next();
                 if ((mapValue.get(key) != null) && (!(mapValue.get(key).equals(""))))
                     query.setParameter(key.toString(), mapValue.get(key));

         }


      result = query.getResultList();
    }
    catch (Exception e) {
      this.logger.error(e);
      throw e;
    }

    return result;
  }

  public List staticsObjects(EntityManager entityManager, String strSql, String strParameters, Map mapValue) throws Exception {
    List result = new ArrayList();
    try {
      StringBuilder whereString = new StringBuilder();

      if ((strParameters != null) && (strParameters.length() > 0) && (mapValue != null)) {
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

          if ((mapValue.get(parameter) != null) && (!(mapValue.get(parameter).toString().equals(""))))
            whereString.append(new StringBuilder().append(" and ").append(parameter).append(" = :").append(parameter).toString());
        }

      }

      strSql = strSql.replace("$Where", whereString.toString());

      Query query = entityManager.createQuery(strSql);

      if (mapValue != null)
        for (Iterator i$ = mapValue.keySet().iterator(); i$.hasNext(); ) { Object key = i$.next();
          if ((mapValue.get(key) != null) && (!(mapValue.get(key).equals(""))))
            query.setParameter(key.toString(), mapValue.get(key));

        }


      result = query.getResultList();
    }
    catch (Exception e) {
      this.logger.error(e);
      throw e;
    }

    return result;
  }

  public Object getObjectById(EntityManager entityManager, Object obj, Long id) throws Exception {
    return entityManager.find(obj.getClass(), id);
  }

  public Object getObjectById(EntityManager entityManager, Class clz, Long id) throws Exception {
    return entityManager.find(clz, id);
  }

  public boolean remove(EntityManager entityManager, Object obj, Long id) throws Exception
  {
    boolean result = false;
    Object object = getObjectById(entityManager, obj, id);
    if (object != null) {
      entityManager.remove(object);
      result = true;
    }
    return result;
  }

  public boolean remove(EntityManager entityManager, Class clz, Long id) throws Exception {
    boolean result = false;
    Object object = getObjectById(entityManager, clz, id);
    if (object != null) {
      entityManager.remove(object);
      result = true;
    }
    return result;
  }

  public List findOBjects(EntityManager entityManager, String queryString) throws Exception {
    Query query = entityManager.createQuery(queryString);
    return query.getResultList();
  }

  public Object findOBject(EntityManager entityManager, String queryString) throws Exception {
    Query query = entityManager.createQuery(queryString);
    return query.getSingleResult();
  }
}