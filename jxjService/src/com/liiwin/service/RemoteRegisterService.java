package com.liiwin.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface RemoteRegisterService extends Remote{
	public Map<String, String> register(Map<String, Object> registerParams) throws RemoteException;
}
