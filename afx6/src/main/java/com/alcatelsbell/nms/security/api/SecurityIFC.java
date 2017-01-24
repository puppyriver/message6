package com.alcatelsbell.nms.security.api;

import com.alcatelsbell.nms.security.LoginInfo;
import com.alcatelsbell.nms.valueobject.domain.Operator;
import com.alcatelsbell.nms.valueobject.domain.Permission;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * User: Ronnie
 * Date: 12-5-25
 * Time: 下午3:37
 */
public interface SecurityIFC  extends Remote{
    public LoginInfo login(String loginName, String password) throws RemoteException;
    public LoginInfo login(String securityKey, String loginName, String password) throws RemoteException;
    public Operator getOperator(long operatorId) throws RemoteException;

    public boolean isSuperUser(long operatorId) throws RemoteException;
    public Operator getOperator(String loginName)throws RemoteException;

    public List<Permission> getAllPermissions()throws RemoteException;

    public List<Permission> getAllPermissions(String type)throws RemoteException;

    public List<Permission> getUserPermissions(Operator operator)throws RemoteException;

    public List<Permission> getUserPermissions(Long operatorId, String type)throws RemoteException;

    public boolean userHasAllCustomerPermission(Long operatorId)throws RemoteException;

    public boolean userHasAllRegionPermission(Long operatorId)throws RemoteException;

    public boolean userHasPermission(Long operatorId, String permssionType, String targetKey)throws RemoteException;
    public void stopAllServer(Object obj) throws RemoteException;
    public Object getServerInfo(Object obj) throws RemoteException;

}
