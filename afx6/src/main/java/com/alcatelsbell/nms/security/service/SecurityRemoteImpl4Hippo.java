package com.alcatelsbell.nms.security.service;
 
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.db.components.service.JPASupport;
import com.alcatelsbell.nms.db.components.service.JPASupportFactory;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.security.BASE64;
import com.alcatelsbell.nms.security.LoginInfo;
import com.alcatelsbell.nms.security.Permissions;
import com.alcatelsbell.nms.security.api.SecurityIFC;
import com.alcatelsbell.nms.security.client.SecurityClient;
import com.alcatelsbell.nms.valueobject.domain.Operator;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-3-14
 * Time: 上午11:23
 */
public class SecurityRemoteImpl4Hippo  implements SecurityIFC {
    public static final String SU_USERNAME = "sa";
    public static final String SU_INIT_PASSWORD = "sa";      
    private Logger logger = LoggerFactory.getLogger(getClass());


    public SecurityRemoteImpl4Hippo() throws RemoteException {
        logger.info("SecurityRemoteImpl started");
    }

    public String getJndiNamePrefix() {
        return SysConst.SERVICE_NAME_SECURITY;
    }


    private Operator findOrCreateSU() {
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        Operator su = null;
        try {
            List<Operator> list = JPAUtil.getInstance().findObjects(jpaSupport,"select c from Operator c where c.loginName = '"+SU_USERNAME+"'");
            if (!list.isEmpty()) return list.get(0);
            Operator op = new Operator();
            //  op.setId(SU_ID);
            op.setName(SU_USERNAME);
            op.setLoginName(SU_USERNAME);
            op.setDn(SU_USERNAME);
            op.setPassWD(BASE64.encryptBASE64(SU_INIT_PASSWORD.getBytes()));
            jpaSupport.begin();
            su = (Operator)JPAUtil.getInstance().saveObject(jpaSupport,-1,op);
            jpaSupport.end();
            return su;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);

        } finally {
            jpaSupport.release();
        }
        return null;
    }

    @Override
    public LoginInfo login(String loginName, String password) throws RemoteException {

       return login(null,loginName,password);
    }

    @Override
    public LoginInfo login(String securityKey, String loginName, String password) throws RemoteException {
        LoginInfo info = new LoginInfo();
        info.setResult(LoginInfo.NamePwdErr);
        if (password == null || password.trim().isEmpty()) {
            info.setResult(LoginInfo.NamePwdErr);
            return info;
        }
        try {
            password =  BASE64.encryptBASE64(password.getBytes());
        } catch (Exception e) {
            throw new RemoteException(e.getMessage(),e);
        }
        Operator operator = getOperator(loginName);

        if (operator != null) {
            if (password.trim().equals(operator.getPassWD().trim())
                    && operator.getAccountStatus()==LoginInfo.ACCOUNTSTATE_ABLE) {
                info.setResult(LoginInfo.Success);
                info.setOperator(operator);
                if (securityKey != null)
                    info.setSecuritykey(securityKey);
                else
                    info.setSecuritykey(SysUtil.nextDN());
                loginUsers.put(info.getSecuritykey(),info);
            } else if(operator.getAccountStatus()==LoginInfo.ACCOUNTSTATE_DISABLE) {  //账户状态不能用为 1
                info.setResult(LoginInfo.AccountDisable);//
            } else {
                info.setResult(LoginInfo.NamePwdErr);//密码错误
            }

        }else {
            info.setResult(LoginInfo.AccountExpire);//不存在或者过期
        }
        return info;
    }

    private Hashtable<String,LoginInfo> loginUsers = new Hashtable();

    @Override
    public Operator getOperator(String loginName) throws RemoteException{
        if (SU_USERNAME.equals(loginName)) return findOrCreateSU();

        List<Operator> list = null;
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            list = JPAUtil.getInstance().findObjects(jpaSupport,"select c from Operator c where c.loginName = '"+loginName+"'");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            jpaSupport.release();
        }
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


    @Override
    public Operator getOperator(long operatorId) throws RemoteException{
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            return (Operator)JPAUtil.getInstance().findObjectById(jpaSupport,Operator.class,operatorId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            jpaSupport.release();
        }

        return null;
    }

    @Override
    public boolean isSuperUser(long operatorId) throws RemoteException {
        Operator operator = getOperator(operatorId);
        return operator != null ? SU_USERNAME.equals(operator.getLoginName()) : false;
    }

    @Override
    public List<Permission> getAllPermissions() throws RemoteException {
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            return JPAUtil.getInstance().findAllObjects(jpaSupport,Permission.class);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        } finally {
            jpaSupport.release();
        }
    }
    @Override
    public List<Permission> getAllPermissions(String type)  throws RemoteException{
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            return JPAUtil.getInstance().findObjects(jpaSupport,"select p from Permission p where p.type = '"+type +"'");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        } finally {
            jpaSupport.release();
        }
    }

    @Override
    public List<Permission> getUserPermissions(Operator operator)  throws RemoteException{
        if (operator.getLoginName().equals(SU_USERNAME))
            return getAllPermissions();
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            return JPAUtil.getInstance().findObjects(jpaSupport,"select p from Permission p,PermissionAssign a, " +
                    "RoleAssign r where a.permissionid = p.id and a.roleid = r.roleid and r.operatorid = "+operator.getId());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        } finally {
            jpaSupport.release();
        }
    }



    @Override
    public List<Permission> getUserPermissions(Long operatorId, String type) throws RemoteException {

        if (isSuperUser(operatorId)) return getAllPermissions(type);

        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            return JPAUtil.getInstance().findObjects(jpaSupport,"select p from Permission p,PermissionAssign a, " +
                    "RoleAssign r where p.type = '"+type +"' and  a.permissionid = p.id and a.roleid = r.roleid and r.operatorid = "+operatorId);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return null;
        } finally {
            jpaSupport.release();
        }
    }

    @Override
    public boolean userHasAllCustomerPermission(Long operatorId)  throws RemoteException{
        return userHasPermission(operatorId, Permissions.PERMISSION_TYPE_SYSTEM,Permissions.PERMISSION_TARGET_KEY_ALL_CUSTOMER);
    }

    @Override
    public boolean userHasAllRegionPermission(Long operatorId)  throws RemoteException{
        return userHasPermission(operatorId, Permissions.PERMISSION_TYPE_SYSTEM,Permissions.PERMISSION_TARGET_KEY_ALL_REGION);
    }

    @Override
    public boolean userHasPermission(Long operatorId, String permssionType, String targetKey)  throws RemoteException{
        if (isSuperUser(operatorId)) return true;
        JPASupport jpaSupport = JPASupportFactory.createJPASupport();
        try {
            List list =  JPAUtil.getInstance().findObjects(jpaSupport,"select p from Permission p,PermissionAssign a, " +
                    "RoleAssign r where p.type = '"+permssionType+"' and p.targetKey = '"+targetKey+"' and a.permissionid = p.id and a.roleid = r.roleid and r.operatorid = "+operatorId);
            return list != null && list.size() > 0;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        } finally {
            jpaSupport.release();
        }
    }

    public static void main(String[] args) throws Exception {
        LoginInfo info = SecurityClient.getInstance().login("jxadmin","abcdd1234");
        System.out.println(info.getResult());
    }

    @Override
    public void stopAllServer(Object obj) throws RemoteException {
        try {
       //     ServerManager.getAdminProxy().stopAllServer(null);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public Object getServerInfo(Object obj) throws RemoteException {
        return "";
//        try {
//            return ServerManager.getAdminProxy().getServerInfo("datadomain","s1");
//        } catch (Exception e) {
//            logger.error(e.getMessage(),e);
//            return null;
//        }
    }



}
