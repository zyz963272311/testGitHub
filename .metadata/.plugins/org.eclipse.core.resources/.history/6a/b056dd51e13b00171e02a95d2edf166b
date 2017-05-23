package com.liiwin.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.liiwin.service.IService;
import com.liiwin.service.impl.ServiceImpl;
public class ServerMain
{
	public static IService	service;

	public static void main(String[] args)
	{
		try
		{
			service = new ServiceImpl("service");
			LocateRegistry.createRegistry(6666);
			Context context = new InitialContext();
			context.rebind("rmi://localhost:6666/service", service);
			System.out.println("�������������ע����Զ�̷������");
		} catch (RemoteException e)
		{
			e.printStackTrace();
		} catch (NamingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
