package com.alcatelsbell.nms.alarm.service;

import java.rmi.Remote;

import com.alcatelsbell.nms.alarm.common.AlarmProcessException;
import com.alcatelsbell.nms.valueobject.alarm.Curralarm;

import java.rmi.RemoteException;


public interface RootAnalysisRemoteIFC extends Remote{
  public void  sentToRootAnalysisQueue(Curralarm _curralarm)throws RemoteException, AlarmProcessException;
}
