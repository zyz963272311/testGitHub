package com.liiwin.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class Test {
	public static void main(String[] args) {
		String url = "rmi://localhost:6666/service";
			try {
				IService service = (IService)Naming.lookup(url);
				System.out.println(service.service("321321321"));
			} catch (MalformedURLException | RemoteException
					| NotBoundException e) {
				e.printStackTrace();
			}
	}

}
