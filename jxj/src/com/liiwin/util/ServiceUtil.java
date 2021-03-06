package com.liiwin.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public class ServiceUtil {
	@SuppressWarnings("unchecked")
	public static < C extends Remote> C getRemoteService(Class<C> service,String servicename,boolean isThrow)
	{
		System.out.println();
		try {
			return (C)Naming.lookup("rmi://localhost:6666/"+servicename);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
			if(isThrow)
			{
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}
}
