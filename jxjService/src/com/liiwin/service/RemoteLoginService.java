package com.liiwin.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface RemoteLoginService extends Remote {
	public Map<String, String> login(String user,String pwd)  throws RemoteException;
}
