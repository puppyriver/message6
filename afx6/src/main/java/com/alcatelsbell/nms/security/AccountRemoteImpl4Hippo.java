package com.alcatelsbell.nms.security;



import com.alcatelsbell.nms.common.SpringContext;
import com.alcatelsbell.nms.common.SysConst;
import com.alcatelsbell.nms.common.SysUtil;
import com.alcatelsbell.nms.common.message.GeneralMessage;
import com.alcatelsbell.nms.db.components.client.JpaClient;
import com.alcatelsbell.nms.db.components.service.JPASupportSpringImpl;
import com.alcatelsbell.nms.db.components.service.JPAUtil;
import com.alcatelsbell.nms.util.JVMRegistry;
import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import com.alcatelsbell.nms.valueobject.domain.PermissionAssign;
import com.alcatelsbell.nms.valueobject.domain.RRegion;
import com.alcatelsbell.nms.valueobject.domain.Role;
import com.alcatelsbell.nms.valueobject.domain.RoleAssign;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class AccountRemoteImpl4Hippo  implements AccountRemoteIFC {
    public AccountRemoteImpl4Hippo() throws RemoteException {
        JVMRegistry.getInstance().registerObject(getClass().getName(), this);
      //  System.out.println("AccountRemoteImpl4Hippo started!!!");
    }

    public String getJndiNamePrefix() {

        return SysConst.SERVICE_NAME_ACCOUNT;
    }

    public void start() {
        try {
//			Properties pros = this.getProperties();
//			String springxml = pros.getProperty("spring");
//			logger.debug("spring = " + springxml);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Role> getAllRoles() throws RemoteException, Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List<Role> roles = JPAUtil.getInstance().findObjects(context, "select r from Role r");
            return roles;
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Permission> getAllPermissions() throws RemoteException, Exception {
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List<Permission> permissions = JPAUtil.getInstance().findObjects(context, "select p from Permission p");
            return permissions;
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Operator> getAllUsers() throws RemoteException, Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List<com.alcatelsbell.nms.valueobject.domain.Operator> userlist = JPAUtil.getInstance().findObjects(context, "select o from com.alcatelsbell.nms.valueobject.domain.Operator o");
            List<Operator> users = new ArrayList<Operator>();
            List l = JPAUtil.getInstance().findObjects(context, "select ra.operatorid from RoleAssign ra where ra.roleid in " + "(select pr.roleid from PermissionAssign pr where pr.permissionid=" + SysConst.PARAM_ID + ")");
            return FlexOperatorUtil.setFlexOperator(userlist, users, l);
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public List<Role> getRoles(int opid) throws RemoteException, Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List<RoleAssign> assignList = JPAUtil.getInstance().findObjects(context, "select ra from RoleAssign ra where ra.operatorid=" + opid);
            List<Role> roleList = new ArrayList<Role>();
            Role r = new Role();
            for (int i = 0; i < assignList.size(); i++) {
                r = (Role) JPAUtil.getInstance().findObjectById(context, Role.class, assignList.get(i).getRoleid());
                roleList.add(r);
            }
            return roleList;
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public int insertOperator(Operator o) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            context.begin();
            com.alcatelsbell.nms.valueobject.domain.Operator opt = new com.alcatelsbell.nms.valueobject.domain.Operator();
            FlexOperatorUtil.setOperator(o, opt);
            if (opt.getDn() == null) opt.setDn(opt.getLoginName());
            com.alcatelsbell.nms.valueobject.domain.Operator newopt = (com.alcatelsbell.nms.valueobject.domain.Operator) JPAUtil.getInstance().saveObject(context, -1, opt);
            for (int i = 0; i < o.getRoleses().size(); i++) {
                RoleAssign roleassign = new RoleAssign();
                roleassign.setRoleid(Long.valueOf(o.getRoleses().get(i).toString()));
                roleassign.setOperatorid(newopt.getId());
                roleassign.setDn(o.getRoleses().get(i) +  SysUtil.DN_SEPERATOR + newopt.getId());
                JPAUtil.getInstance().saveObject(context, -1, roleassign);
          //      SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(roleassign,GeneralMessage.ADD));
            }
            context.end();
            return 1;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }

    @Override
    public int deleteOperator(long id) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        com.alcatelsbell.nms.valueobject.domain.Operator  operator = null;
        try {
            context.begin();
            operator = (com.alcatelsbell.nms.valueobject.domain.Operator) JPAUtil.getInstance().findObjectById(context,com.alcatelsbell.nms.valueobject.domain.Operator.class,id);
            if(operator!=null && operator.getId()!=null){
                JPAUtil.getInstance().executeQL(context, "delete from RoleAssign ra where ra.operatorid=" + id);
                JPAUtil.getInstance().executeQL(context, "delete from com.alcatelsbell.nms.valueobject.domain.Operator o where o.id=" + id);
         //       SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(operator,GeneralMessage.DELETE));
            }
            context.end();
            return 1;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }

    @Override
    public int updateOperator(Operator o) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        com.alcatelsbell.nms.valueobject.domain.Operator newopt = null;
        try {
            context.begin();
            com.alcatelsbell.nms.valueobject.domain.Operator opt = new com.alcatelsbell.nms.valueobject.domain.Operator();
            FlexOperatorUtil.setOperator(o, opt);
            newopt = (com.alcatelsbell.nms.valueobject.domain.Operator) JPAUtil.getInstance().storeObjectByDn(context, -1, opt);
            JPAUtil.getInstance().executeQL(context, "delete from RoleAssign ra where ra.operatorid=" + opt.getId());
            for (int i = 0; i < o.getRoleses().size(); i++) {
                RoleAssign roleassign = new RoleAssign();
                roleassign.setRoleid(Long.valueOf(o.getRoleses().get(i).toString()));
                roleassign.setOperatorid(opt.getId());
                roleassign.setDn(o.getRoleses().get(i)+SysUtil.DN_SEPERATOR+opt.getId());
                JPAUtil.getInstance().saveObject(context, -1, roleassign);
//                SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(roleassign,GeneralMessage.UPDATE));
            }
            context.end();
        //    SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(newopt,GeneralMessage.UPDATE));
            return 1;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }

    @Override
    public List queryPU(long id) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List list = new ArrayList();
            List<Permission> plist = new ArrayList<Permission>();
            List<Operator> olist = new ArrayList<Operator>();
            List<RoleAssign> rassign = JPAUtil.getInstance().findObjects(context, "select ra from RoleAssign ra where ra.roleid=" + id);
            List<PermissionAssign> passign = JPAUtil.getInstance().findObjects(context, "select pa from PermissionAssign pa where pa.roleid=" + id);
            List l = JPAUtil.getInstance().findObjects(context, "select ra.operatorid from RoleAssign ra where ra.roleid in " + "(select pr.roleid from PermissionAssign pr where pr.permissionid=" + SysConst.PARAM_ID + ")");
            for (int i = 0; i < rassign.size(); i++) {
                com.alcatelsbell.nms.valueobject.domain.Operator o1 = (com.alcatelsbell.nms.valueobject.domain.Operator) JPAUtil.getInstance().findObjectById(context, com.alcatelsbell.nms.valueobject.domain.Operator.class, rassign.get(i).getOperatorid());
                if(o1!=null){
                    Operator o = new Operator();
                    o.setId(o1.getId());
                    o.setLoginName(o1.getLoginName());
                    o.setDepartment(o1.getDepartment());
                    o.setTelephone(o1.getTelephone());
                    o.setName(o1.getName());
                    o.setEmail(o1.getEmail());
                    o.setIsweb(false);
                    for (int k = 0; k < l.size(); k++) {
                        if (String.valueOf(o.getId()).equals(l.get(k).toString())) {
                            o.setIsweb(true);
                        }
                    }
                    olist.add(o);
                }
            }
            for (int j = 0; j < passign.size(); j++) {
                Permission p = (Permission) JPAUtil.getInstance().findObjectById(context, Permission.class, passign.get(j).getPermissionid());
                plist.add(p);
            }
            list.add(olist);
            list.add(plist);
            return list;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public int deleteRole(long id) throws Exception {
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        List<com.alcatelsbell.nms.valueobject.domain.Operator> opts = null;
        try {
            context.begin();
            opts = (List<com.alcatelsbell.nms.valueobject.domain.Operator>) JPAUtil.getInstance().findObjects
                    (context,"select o from Operator o ,RoleAssign a where a.operatorid = o.id and a.roleid =" +  id);
            if(opts!=null && !(opts.isEmpty())){
                for (com.alcatelsbell.nms.valueobject.domain.Operator operator : opts) {
             //       SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(operator,GeneralMessage.DELETE));
                }
            }
            JPAUtil.getInstance().executeQL(context, "delete from RoleAssign ra where ra.roleid=" + id);
            JPAUtil.getInstance().executeQL(context, "delete from Role r where r.id=" + id);
            JPAUtil.getInstance().executeQL(context, "delete from PermissionAssign pa where pa.roleid=" + id);
            context.end();
            return 1;
        }
        catch (Exception e) {
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }

    @Override
    public List<Operator> findOperator(List list) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            List<Operator> users = new ArrayList<Operator>();
            String ql = "select o from com.alcatelsbell.nms.valueobject.domain.Operator o where id>0";
            String loginname = (String) list.get(0);
            String username = (String) list.get(1);
            if (loginname != null && !loginname.isEmpty()) {
                ql = ql + " and loginName like '%" + loginname + "%'";
            }
            if (username != null && !username.isEmpty()) {
                ql = ql + " and name like '%" + username + "%'";
            }
            List<com.alcatelsbell.nms.valueobject.domain.Operator> userlist = JPAUtil.getInstance().findObjects(context, ql);
            List l = JPAUtil.getInstance().findObjects(context, "select ra.operatorid from RoleAssign ra where ra.roleid in " + "(select pr.roleid from PermissionAssign pr where pr.permissionid=" + SysConst.PARAM_ID + ")");
            return FlexOperatorUtil.setFlexOperator(userlist, users, l);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public List<Permission> findPermission(List list) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            String  name  = (String)list.get(0);
            String  dec =  (String)list.get(1);
            String ql = "select p from Permission p where id>0";
            if (name != null && name != "") {
                ql = ql + " and name like '%" + name + "%'";
            }
            if (dec != null && dec != "") {
                ql = ql + " and description like '%" + dec + "%'";
            }
            System.out.println(ql);
            List<Permission> permissionList = JPAUtil.getInstance().findObjects(context, ql);
            return permissionList;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public List<Role> findRole(String name) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            String ql = "select r from Role r where id>0";
            if (name != null && name != "") {
                ql = ql + " and name like '%" + name + "%'";
            }
            List<Role> roleList = JPAUtil.getInstance().findObjects(context, ql);
            return roleList;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return null;
    }

    @Override
    public int insertRole(List list) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            context.begin();
            Role role = (Role) list.get(0);
            List plist = (List) list.get(1);
            Role r = new Role();
            r.setName(role.getName());
            r.setDescription(role.getDescription());
            r.setDn(role.getName());
            Role newrole = (Role) JPAUtil.getInstance().saveObject(context, -1, r);
            // System.out.println(newrole.getId());
            for (int i = 0; i < plist.size(); i++) {
                PermissionAssign passign = new PermissionAssign();
                passign.setRoleid(newrole.getId());
                passign.setPermissionid(Long.valueOf(plist.get(i).toString()));
                passign.setDn(plist.get(i)+SysUtil.DN_SEPERATOR+ newrole.getId());
                JPAUtil.getInstance().saveObject(context, -1, passign);
           //     SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(passign,GeneralMessage.ADD));
            }
            context.end();
            return 1;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }

    @Override
    public int updateRole(List list) throws Exception {
        // TODO Auto-generated method stub
        JPASupportSpringImpl context = new JPASupportSpringImpl();
        try {
            context.begin();
            Role r = (Role) list.get(0);
            List plist = (List) list.get(1);
            Role role = new Role();
            role.setName(r.getName());
            role.setDn(r.getDn());
            role.setDescription(r.getDescription());
            role.setId(r.getId());
            role.setCreateDate(r.getCreateDate());
            Role newrole = (Role) JPAUtil.getInstance().storeObjectByDn(context, -1, role);
            JPAUtil.getInstance().executeQL(context, "delete from PermissionAssign pa where pa.roleid=" + r.getId());
            for (int i = 0; i < plist.size(); i++) {
                PermissionAssign passign = new PermissionAssign();
                passign.setRoleid(role.getId());
                passign.setPermissionid(Long.valueOf(plist.get(i).toString()));
                passign.setDn(plist.get(i)+SysUtil.DN_SEPERATOR + role.getId());
                JPAUtil.getInstance().saveObject(context, -1, passign);
           //     SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(passign,GeneralMessage.UPDATE));
            }
            context.end();
            return 1;
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            context.rollback();
            e.printStackTrace();
        }
        finally {
            context.release();
        }
        return -1;
    }
    
    @Override
	public int insertPermission(BObject bObj,long id) throws Exception {
		// TODO Auto-generated method stub
		JPASupportSpringImpl context = new JPASupportSpringImpl();
		try {
			context.begin();
			String permissionDn = null;
		    permissionDn = SysConst.PERMISSION_TYPE_REGION + SysUtil.DN_SEPERATOR + bObj.getDn();
		    //如果该permission对象存在，则不再往Permission表中添加记录，只给PermissionAssign表中添加记录；否则两个表中都添加记录
		    if(JpaClient.getInstance().findObjectByDN(Permission.class, permissionDn)==null){
		    	//生成Permission的一个对象，插入到Permission表中
		    	RRegion rregn = (RRegion)bObj;
			    Permission permission = new Permission();
			    permission.setName("区域权限");
			    permission.setDescription(rregn.getRegionname());
			    permission.setType(SysConst.PERMISSION_TYPE_REGION);
			    permission.setTargetKey(bObj.getDn());
			    permission.setTargetId(bObj.getId());
			    permission.setDn(permission.getType() + SysUtil.DN_SEPERATOR + permission.getTargetKey());
			    JPAUtil.getInstance().saveObject(context, -1, permission);
		    }
		    //pn用来取得刚刚存入Permission表中的对象
			Permission pn = new Permission();
			pn = (Permission)JPAUtil.getInstance().findObjectByDn(context,-1,Permission.class, permissionDn);
		    //生成PermissionAssign对象，插入到PermissionAssign表中
		    PermissionAssign permissionAssign = new PermissionAssign();
		    permissionAssign.setPermissionid(pn.getId());
		    permissionAssign.setRoleid(id);
		    permissionAssign.setDn(pn.getId() + SysUtil.DN_SEPERATOR + id);
		    JPAUtil.getInstance().saveObject(context, -1, permissionAssign);
		//    SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(permissionAssign,GeneralMessage.ADD));
			context.end();
			return 1;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			context.rollback();
			e.printStackTrace();
		}
		finally {
			context.release();
		}
		return -1;
	}
	
	@Override
	public int deletePermission(BObject o,long id) throws Exception {
 		JPASupportSpringImpl context = new JPASupportSpringImpl();
		try {
			context.begin();
			StringBuffer delSql = new StringBuffer();
			Permission delP = new Permission();
			delP = (Permission)JPAUtil.getInstance().findObjectByDn(context,-1,Permission.class, SysConst.PERMISSION_TYPE_REGION + SysUtil.DN_SEPERATOR + o.getDn());
            if(delP!=null && delP.getId()!=null){
                PermissionAssign permissionAssign = (PermissionAssign) JPAUtil.getInstance().findObjectByDn(context,-1L,PermissionAssign.class,delP.getId() + SysUtil.DN_SEPERATOR + id);
                delSql.append("DELETE FROM ").append(PermissionAssign.class.getSimpleName()).append(" P WHERE P.dn = '");
                delSql.append(delP.getId() + SysUtil.DN_SEPERATOR + id + "'");
                JPAUtil.getInstance().executeQL(context,delSql.toString());
          //      SpringContext.getInstance().getJMSSupport().sendTopicMessage(SysConst.TOPIC_NAME_NMS_GENERAL,new GeneralMessage(permissionAssign,GeneralMessage.DELETE));
            }
			context.end();
			return 1;
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			context.rollback();
			e.printStackTrace();
		}
		finally {
			context.release();
		}
		return -1;
	}
}
