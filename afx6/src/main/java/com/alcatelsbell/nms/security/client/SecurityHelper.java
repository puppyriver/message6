package com.alcatelsbell.nms.security.client;

import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.security.Permissions;
import com.alcatelsbell.nms.valueobject.domain.Operator;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import com.alcatelsbell.nms.valueobject.domain.UserCustomerAssign;

import java.rmi.RemoteException;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-3-16
 * Time: 上午9:18
 */
public class SecurityHelper {
    /**
     *  String ql = "select c from RCustomer c where " + getCustomerDnWhereString(111, "c.dn");
     * @param operatorId
     * @param customerDnPrefix
     * @return
     */
    public static  String getCustomerDnWhereString(Long operatorId,String customerDnPrefix) {
        if (SecurityClient.getInstance().userHasAllCustomerPermission(operatorId))
            return " and  1=1 ";
        List<Permission> permissions = SecurityClient.getInstance().getUserPermissions(operatorId, Permissions.PERMISSION_TYPE_CUSTOMER) ;
        StringBuffer sb = new StringBuffer();
        sb.append(" and " + customerDnPrefix).append(" in ").append("( '-1'");
        if (permissions != null && permissions.size() > 0) {
            for (Permission permisson : permissions) {
                sb.append(",'").append(permisson.getTargetKey()).append("'");
            }
        }
        sb.append(" )");
        return sb.toString();

    }
    
    
    public static  String getCustomerDnWhereString(String operatorDn,String customerDnPrefix) {
        
        String hql = "select uca from UserCustomerAssign uca where uca.operatorDn = '"+operatorDn+"'";
        List<UserCustomerAssign> userCustomerAssigns;
        try {
            Operator operator = (Operator)JpaClient.getInstance().findObjectByDN(Operator.class, operatorDn);
            if(operator.getName().equals("sa")) return "";
            userCustomerAssigns = JpaClient.getInstance().findObjects(hql);
            StringBuffer sb = new StringBuffer();
            sb.append(" and (" + customerDnPrefix).append(" in ").append("( '-1'");
            int k=0;
            if (userCustomerAssigns != null && userCustomerAssigns.size() > 0) {
                for (UserCustomerAssign userCustomerAssign : userCustomerAssigns) {
                    k++;
                    if(k%971==0){
                        sb.append(" ) or " + customerDnPrefix).append(" in ").append("( '-1'");
                    }else
                        sb.append(",'").append(userCustomerAssign.getCustomerDn()).append("'");
                }
            }
            sb.append(" ))");
            return sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
