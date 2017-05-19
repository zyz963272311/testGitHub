package com.liiwin.service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class Test
{
	public static void main(String[] args)
	{
		String url = "rmi://127.0.0.1:6666/";
		String serviceName = "service";
		try
		{
			Naming.lookup(url + serviceName);
			System.out.println("成功了");
		} catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		} catch (NotBoundException e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}
}
