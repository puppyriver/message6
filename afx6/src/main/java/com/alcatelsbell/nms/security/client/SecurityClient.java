package com.alcatelsbell.nms.security.client;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.security.LoginInfo;
import com.alcatelsbell.nms.security.api.SecurityIFC;
import com.alcatelsbell.nms.util.LogUtil;
import com.alcatelsbell.nms.util.NamingUtil;
import com.alcatelsbell.nms.valueobject.domain.Operator;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-3-14
 * Time: 上午11:23
 */
public class SecurityClient {
    public static final String SU_USERNAME = "sa";
    public static final String SU_PASSWORD = "sa";
     public static final long SU_ID = 99999l;
    private static SecurityClient ourInstance = new SecurityClient();

    private SecurityIFC securityIFC = null;
    private Log logger = LogFactory.getLog(getClass());
    public static SecurityClient getInstance() {
        return ourInstance;
    }

    private SecurityIFC getSecurityIFC() {
        if (securityIFC == null)
            securityIFC = (SecurityIFC) NamingUtil.getAnyOneService(SysConst.SERVICE_NAME_SECURITY);

        try {
            securityIFC.getOperator(-1);
        } catch (Exception e) {
            logger.error(e,e);
            securityIFC = (SecurityIFC)NamingUtil.getAnyOneService(SysConst.SERVICE_NAME_SECURITY);
        }

        return securityIFC;
    }
    private SecurityClient() {
        try{
            System.out.println();
//        securityIFC =  (SecurityIFC)CommonClientEnvironment.getServiceIFC(SysConst.SERVICE_NAME_SECURITY);
            securityIFC =  (SecurityIFC)NamingUtil.getAnyOneService(SysConst.SERVICE_NAME_SECURITY);
            System.out.println();
            if (securityIFC == null) logger.error("Failed to find security ifc");
        }catch(Exception e){
            e.printStackTrace();
        }
    }






    public LoginInfo login(String loginName, String password) throws Exception {
        LoginInfo loginInfo = getSecurityIFC().login(loginName, password);
        if (loginInfo.getSecuritykey() != null)
            loginInfoHashtable.put(loginInfo.getSecuritykey(),loginInfo);
        return loginInfo;
    }

    public LoginInfo login(String sessionId,String loginName, String password) throws Exception {
        LoginInfo loginInfo = getSecurityIFC().login(sessionId,loginName, password);
        if (loginInfo.getSecuritykey() != null)
            loginInfoHashtable.put(loginInfo.getSecuritykey(),loginInfo);
        return loginInfo;
    }

    public LoginInfo getLoginInfo(long sessionKey) {
        return loginInfoHashtable.get(sessionKey);
    }

    public LoginInfo getLoginInfo(String loginName) {
        Collection<LoginInfo> values = loginInfoHashtable.values();
        for (LoginInfo info : values) {
            if (info.getOperator() != null && info.getOperator().getLoginName().equals(loginName))
                return info;
        }
        return null;
    }

    private Hashtable<String,LoginInfo> loginInfoHashtable = new Hashtable();
    

    public Operator getOperator(String loginName) {
        try {
            return getSecurityIFC().getOperator(loginName);
        } catch (RemoteException e) {
            logger.error(e,e);
        }
        return null;
    }


    public List<Permission> getAllPermissions() {
        try {
            return getSecurityIFC().getAllPermissions();
        } catch (Exception e) {
            logger.error(e,e);
            return null;
        }
    }

    public List<Permission> getAllPermissions(String type) {
        try {
            return getSecurityIFC().getAllPermissions(type);
        } catch (Exception e) {
            logger.error(e,e);
            return null;
        }
    }


    public List<Permission> getUserPermissions(Operator operator) {
        try {
            return getSecurityIFC().getUserPermissions(operator);
        } catch (Exception e) {
            logger.error(e,e);
            return null;
        }
    }


    public List<Permission> getUserPermissions(Long operatorId, String type) {
        if (operatorId == SU_ID) return getAllPermissions(type);
        try {
            return getSecurityIFC().getUserPermissions(operatorId, type);
        } catch (Exception e) {
            logger.error(e,e);
            return null;
        }
    }
    

    public boolean userHasAllCustomerPermission(Long operatorId) {
        try {
            return getSecurityIFC().userHasAllCustomerPermission(operatorId);
        } catch (RemoteException e) {
            logger.error(e,e);
            return false;
        }
    }


    public boolean userHasAllRegionPermission(Long operatorId) {
        try {
            return getSecurityIFC().userHasAllRegionPermission(operatorId);
        } catch (RemoteException e) {
            logger.error(e,e);
            return false;
        }
    }


    public boolean userHasPermission(Long operatorId, String permssionType, String targetKey) {
         try {
           return getSecurityIFC().userHasPermission(operatorId, permssionType, targetKey);
        } catch (Exception e) {
            logger.error(e,e);
            return false;
        }
    }
    public void stopAllServer(Object obj) {
        try {
             getSecurityIFC().stopAllServer(obj);
        } catch (RemoteException e) {
          //  LogUtil.error(this, e, e);
        }
    }
    public Object getServerInfo(Object obj) {
        try {
            return getSecurityIFC().getServerInfo(obj);
        } catch (RemoteException e) {
            LogUtil.error(this, e, e);
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        LoginInfo info = SecurityClient.getInstance().login("sa","sa");
        System.out.println(info.getResult());
    }
    
 
    	

}
