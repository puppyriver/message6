package com.alcatelsbell.nms.db.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 * @author:LiXiaoBing
 * @date:2012-1-6
 * @time:下午04:40:40
 */
public interface FaultDispatchRemoteIFC  extends Remote{
    public abstract List queryFaultDispatch(Map<String, Object> conditionmap, int from, int end)
    throws RemoteException, Exception;
    public abstract List queryFaultDispatchNew(Map<String, Object> conditionmap, int from, int end)
    throws RemoteException, Exception;
}
