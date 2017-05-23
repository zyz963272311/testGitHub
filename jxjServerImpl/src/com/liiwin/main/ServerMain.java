package com.liiwin.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import com.liiwin.service.RemoteLoginService;
import com.liiwin.service.impl.RemoteLoginServiceImpl;

public class ServerMain {
	public static void main(String[] args) {
		try {
			RemoteLoginService service = new RemoteLoginServiceImpl();
			LocateRegistry.createRegistry(6666);
			Naming.rebind("rmi://localhost:6666/service", service);
			System.out.println("服务启动");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
