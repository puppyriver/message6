package com.alcatelsbell.nms.db.components.client;

/**
 * User: Ronnie.Chen
 * Date: 11-5-17
 * Time:
 */

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.db.api.JpaRemoteIFC;
import com.alcatelsbell.nms.db.components.api.BObjectStore;
import com.alcatelsbell.nms.util.NamingUtil;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.config.BinaryObject;
import com.alcatelsbell.nms.valueobject.domain.RRegion;
import com.alcatelsbell.nms.valueobject.physical.Managedelement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
public class JpaClient implements BObjectStore {
	protected static final Log logger = LogFactory.getLog(JpaClient.class);
	private static ConcurrentHashMap<String,JpaClient> instances = new ConcurrentHashMap();
	private JpaRemoteIFC remoteIfc = null;
    private JpaRemoteIFC localIfc = null;
    private String remoteServiceName = SysConst.SERVICE_NAME_JPA;



	public static JpaClient getInstance()

	{

		return getInstance(SysConst.SERVICE_NAME_JPA);
	}

    public static JpaClient getInstance(String remoteServiceName)

    {
        if (remoteServiceName == null)remoteServiceName = SysConst.SERVICE_NAME_JPA;
       
        JpaClient inst = instances.get(remoteServiceName);
        if (inst == null) {
            synchronized (instances) {
                inst = instances.get(remoteServiceName);
                if (inst == null) {
                    inst = new JpaClient(remoteServiceName);
                    instances.put(remoteServiceName,inst);
                }
            }
        }


        return inst;
    }

    public JpaClient(String remoteServiceName) {
        if (remoteServiceName != null)
            this.remoteServiceName = remoteServiceName;
    }
    public JpaClient() {

    }


    private JpaRemoteIFC getDBIFC() throws Exception {

        if (this.remoteIfc == null) {
			this.remoteIfc = (JpaRemoteIFC) NamingUtil.getAnyOneService(remoteServiceName);
		//	JpaClientProxy hanlder = new JpaClientProxy();
		//	remoteIfc = (JpaRemoteIFC)hanlder.bind(remoteIfc);
		}

        try {
            remoteIfc.findObjectById(RRegion.class,-1);
        } catch (Exception e) {
            logger.error(e,e);
            this.remoteIfc = (JpaRemoteIFC) NamingUtil.getAnyOneService(remoteServiceName);
        } finally {
        }

        return this.remoteIfc;
	}

	@Override
    public BObject saveObject(long sessionKey, BObject obj) throws RemoteException, Exception {
		try {
			return getDBIFC().saveObject(sessionKey, obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}



	public BObject storeObjectByDn(long sessionKey, BObject obj) throws RemoteException, Exception {
		try {
			return getDBIFC().storeObjectByDn(sessionKey, obj);
		} catch (RemoteException e) {
			throw new Exception("remote failed");
		}
	}

	public BObject storeObjectByKeys(long sessionKey, BObject _obj, String keys) throws RemoteException, Exception {
		try {
			return getDBIFC().storeObjectByKeys(sessionKey, _obj, keys);
		} catch (RemoteException e) {
			throw new Exception("remote failed");
		}
	}

	@Override
    public List findAllObjects(Class cls) throws RemoteException, Exception {
		try {
			return getDBIFC().findAllObjects(cls);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	//  public long findObjectsNumByFilter(Class cls, Object filter) throws RemoteException, Exception {
	//    try {
	//      return getDBIFC().findObjectsNumByFilter(cls, filter);
	//    } catch (RemoteException e) {
	//      throw new Exception("Զ�����Ӵ���");
	//    }
	//  }
	//
	//  public List findObjectsByFilter(Class cls, Object filter, Integer start, Integer limit) throws RemoteException, Exception
	//  {
	//    try {
	//      return getDBIFC().findObjectsByFilter(cls, filter, start, limit);
	//    } catch (RemoteException e) {
	//      throw new Exception("Զ�����Ӵ���");
	//    }
	//  }
	//
	//  public List findObjectsByFilter(String strSql, Class cls, Object filter, Integer start, Integer limit) throws RemoteException, Exception {
	//    try {
	//      return getDBIFC().findObjectsByFilter(strSql, cls, filter, start, limit);
	//    } catch (RemoteException e) {
	//      throw new Exception("Զ�����Ӵ���");
	//    }
	//  }

	@Override
    public List<Object> findObjectsByMapTable(Class cls, BObject bobj, Class mapClass, String[] mapkeys) throws RemoteException, Exception {
		try {
			return getDBIFC().findObjectsByMapTable(cls, bobj, mapClass, mapkeys);
		} catch (RemoteException e) {
			throw new Exception("remote failed");
		}
	}

	@Override
    public List findObjects(String strSql, String strPrefix, Map mapValue, Integer start, Integer limit) throws RemoteException, Exception {
		try {
			return getDBIFC().findObjects(strSql, strPrefix, mapValue, start, limit);
		} catch (RemoteException e) {
            logger.error("Failed to execute jpql -- "+strSql,e);
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	@Override
    public List findObjects(String strSql) throws RemoteException, Exception {
		try {
			return getDBIFC().findObjects(strSql, null, null, null, null);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	//    public List findObjectById(long id) throws RemoteException, Exception {
	//      try {
	//        return getDBIFC().findObjects(strSql, null, null, null, null);
	//      } catch (RemoteException e) {
	//          e.printStackTrace();
	//        throw new Exception("remote failed");
	//      }
	//    }
	@Override
    public long findObjectsCount(Class cls, Map mapValue) throws RemoteException, Exception {
		try {
			return getDBIFC().findObjectsCount(cls, mapValue);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	@Override
    public  long findObjectsCount(String ql) throws Exception {
		try {
			return getDBIFC().findObjectsCount(ql);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	@Override
    public Object findObjectById(Class cls, long id)  throws Exception {
		try {
			return getDBIFC().findObjectById(cls, id);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}
	@Override
    public Object findObjectByDN(Class cls, String dn)  throws Exception {
		try {
			return getDBIFC().findObjectByDN(cls, dn);
		} catch (RemoteException e) {
			e.printStackTrace();
			String url = Managedelement.class.getClass().getResource("").getPath();
			System.out.println(url);
			throw new Exception("remote failed");
		}
	}

    public Object findOneObject(String strSql) throws Exception {
        List l =  findObjects(strSql,null,null,null,null);
        if (l != null && l.size() > 0)
            return l.get(0);
        return null;
    }



	public  void  executeNativeSql(String sql) throws Exception {
		try {
			getDBIFC().executeNativeSql(sql);
		} catch (RemoteException e) {

			throw new Exception("remote failed");
		}
	}

	public List querySql(String sql) throws RemoteException, Exception {
		try {
			return getDBIFC().executeSql(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}
	public List querySql(String sql,Class clazz) throws RemoteException, Exception {
		try {
			return getDBIFC().executeSql(sql,clazz);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	public int executeUpdateSQL(String sql) throws RemoteException, Exception {
		try {
			return getDBIFC().executeUpdateSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	@Deprecated
	public void deleteObject(BObject obj) throws Exception{
		try {
			getDBIFC().deleteObject(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}

	@Override
    public void removeObject(BObject obj) throws Exception{
		try {
			getDBIFC().removeObject(obj);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}


	/**
	 * 测试删除
	 * @param sql
	 * @param map
	 * @throws Exception
	 */
	public void executeDeleteSQL(String sql, Map map) throws Exception{
		try {
			getDBIFC().executeDeleteSQL(sql, map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("remote failed");
		}
	}
    public void saveBinaryObject(BinaryObject binaryObject) throws Exception {
        getDBIFC().saveObject(-1,binaryObject);
        getDBIFC().saveBinaryObject(binaryObject.getContent(),binaryObject.getKey());
    }
    public void saveBinaryObject(byte[] bs,String key) throws Exception {
        getDBIFC().saveBinaryObject(bs,key);
    }
    public byte[] readBinaryObject(String key) throws Exception{
        return getDBIFC().readBinaryObject(key);
    }

    public List readBinaryObjectAll() throws Exception{
        return getDBIFC().readBinaryObjectAll();
    }

	public static void main(String[] args) throws Exception {
        String sql = "SELECT endpointName,endpointDn,SUM(requestCount),SUM(responseCount),SUM(totalTrafficInKB),AVG(averageTrafficInKB),MIN(updateDate),MAX(updateDate) FROM `i_servicetraffic` GROUP BY endpointDn";

        List allObjects = JpaClient.getInstance().querySql(sql);
        System.out.println("allobject="+allObjects.size());

    }
}