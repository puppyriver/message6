package com.alcatelsbell.nms.security;


import java.util.List;
import java.util.Vector;

import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import com.alcatelsbell.nms.valueobject.domain.PermissionAssign;
import com.alcatelsbell.nms.valueobject.domain.RCustomer;
import com.alcatelsbell.nms.valueobject.domain.RRegion;
import com.alcatelsbell.nms.valueobject.domain.Role;

/**
 * User: Ronnie
 * Date: 12-3-13
 * Time: 下午8:58
 */
public class PermissionCreator {

    public static void createCustomerPermissions() throws Exception {
        List<RCustomer> customers = JpaClient.getInstance
                ().findObjects("select c from RCustomer c where c.customertype != " + SysConst.CUSTOMER_TYPE_THREE);
        int idx =0;
        for (RCustomer customer : customers) {
            Permission permission = new Permission();
            permission.setName("客户权限");
            permission.setDescription(customer.getName());
            permission.setFromWhere(idx);
            permission.setType("CUSTOMER");
            permission.setTargetKey(customer.getDn());
            permission.setDn(permission.getType() + SysUtil.DN_SEPERATOR+permission.getTargetKey());
            JpaClient.getInstance().saveObject(-1, permission);
        }
    }
    public static void createRegionPermissions() throws Exception {
        List<RRegion> regions = JpaClient.getInstance
                ().findAllObjects(RRegion.class);
        int idx = 0;
        for (RRegion rRegion : regions) {

            Permission permission = new Permission();
            permission.setFromWhere(idx);
            permission.setName("区域权限");
            permission.setDescription(rRegion.getName());
            permission.setType("REGION");
            permission.setTargetKey(rRegion.getDn());
            permission.setTargetId(rRegion.getId());
            permission.setDn(permission.getType() + SysUtil.DN_SEPERATOR+permission.getTargetKey());
            JpaClient.getInstance().storeObjectByDn(-1, permission);
        }
    }
    public static void createCustomerPropertyPermissions() throws Exception {
        String[] cps = {"省外客户", "省级客户", "本地客户","小区"};
        int idx = 0;
        for (String cp : cps) {
            Permission permission = new Permission();
            permission.setFromWhere(idx);
            permission.setName("客户类别权限");
            permission.setDescription(cp);
            permission.setType("CUSTOMERPROPERTY");
            permission.setTargetKey(cp);
            //permission.setTargetId();
            permission.setDn(permission.getType() + SysUtil.DN_SEPERATOR+permission.getTargetKey());
            JpaClient.getInstance().saveObject(-1, permission);
        }
    }
    public static void createSystemPermissions() throws Exception {
        String[] cps = {"ALL_CUSTOMER"};
        String[] desc = {"所有客户"};
        int idx = 0;
        for (String cp : cps) {

            Permission permission = new Permission();
            permission.setName("系统权限");
            permission.setDescription(desc[idx++]);
            permission.setFromWhere(idx);
            permission.setType("SYSTEM");
            permission.setTargetKey(cp);
            //permission.setTargetId();
            permission.setDn(permission.getType() + SysUtil.DN_SEPERATOR+permission.getTargetKey());
            JpaClient.getInstance().saveObject(-1, permission);
        }
    }
    public static void main(String[] args) throws Exception {
//        PermissionCreator.createCustomerPermissions();
//        PermissionCreator.createRegionPermissions();
//        PermissionCreator.createCustomerPropertyPermissions();
//        PermissionCreator.createSystemPermissions();
//        PermissionCreator.createSystemRole();
//        PermissionCreator.createButtomRole();
    }

    public static void createSystemRole() {
        Role role = new Role();
        role.setName("系统默认权限");
        role.setDn(role.getName());
        role.setDescription("系统默认创建的角色");
        try {
            Role storeObject = (Role) JpaClient.getInstance().storeObjectByDn(-1L,role);
            if(storeObject!=null && storeObject.getId()!=null){
                List<Permission> permissions = JpaClient.getInstance().findAllObjects(Permission.class);
                if(permissions!=null && (!permissions.isEmpty())){
                    for (Permission permission : permissions) {
                        PermissionAssign passign=new PermissionAssign();
                        passign.setRoleid(storeObject.getId());
                        passign.setPermissionid(permission.getId());
                        passign.setDn(permission.getId() +  SysUtil.DN_SEPERATOR+storeObject.getId());
                        JpaClient.getInstance().storeObjectByDn(-1L,passign);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
//    public static void createButtomRole() {
//        
//        try {
//            Vector<gxlu.afx.system.user.service.dataobject.InternalPermission> vc = DBClient.getInstance().queryAllObjects(gxlu.afx.system.user.service.dataobject.InternalPermission.class);
//            for(gxlu.afx.system.user.service.dataobject.InternalPermission permission : vc){
//                Permission persion = new Permission();
//                persion.setName(permission.getDescription());
//                persion.setDescription(permission.getDescription());
//                persion.setFromWhere(0);
//                persion.setType("BUTTON");
//                persion.setTargetKey(permission.getName());
//                //permission.setTargetId();
////                persion.setDn(persion.getType() + SysUtil.DN_SEPERATOR+persion.getTargetKey());
//                JpaClient.getInstance().saveObject(-1, persion); 
//            }
//            System.out.println(vc.size());
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//    }
}
