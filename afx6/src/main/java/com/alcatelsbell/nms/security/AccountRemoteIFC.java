package com.alcatelsbell.nms.security;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.alcatelsbell.nms.valueobject.BObject;
import com.alcatelsbell.nms.valueobject.domain.Permission;
import com.alcatelsbell.nms.valueobject.domain.Role;

public abstract interface AccountRemoteIFC extends Remote {
	public List<Role>  getAllRoles() throws RemoteException,Exception;
    public List<Permission> getAllPermissions() throws RemoteException, Exception;
    public List getAllUsers() throws RemoteException, Exception;
    public List<Role> getRoles(int opid) throws RemoteException, Exception;
    public int insertOperator(Operator o) throws Exception;
    public int deleteOperator(long id) throws Exception;
    public int updateOperator(Operator o) throws Exception;
    public List queryPU(long id) throws RemoteException, Exception;
    public int insertRole(List list) throws Exception;
    public int deleteRole(long id) throws Exception;
    public int updateRole(List list) throws Exception;
    public List<Operator> findOperator(List list) throws Exception;
    public List<Role> findRole(String name) throws Exception;
    public List<Permission> findPermission(List list) throws Exception;
    public int insertPermission(BObject o, long id) throws Exception;
    public int deletePermission(BObject o, long id) throws Exception;
}
