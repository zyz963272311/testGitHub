package com.liiwin.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.liiwin.service.IService;

public class ServiceImpl extends UnicastRemoteObject implements IService {

	String name;

	public  ServiceImpl(String name) throws RemoteException {
		this.name =name;
	}

	@Override
	public String service(String content) throws RemoteException {
		return "server>>"+(content==null?"":content);
	}

}
