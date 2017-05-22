package com.liiwin.service.impl;

import java.rmi.RemoteException;
import java.util.Map;

import com.liiwin.service.LoginService;
import com.liiwin.service.RemoteLoginService;
import com.liiwin.util.ServiceUtil;

public class LoginServiceImpl implements LoginService{

	@Override
	public Map<String, String> login(String user, String pwd) {
		RemoteLoginService service = ServiceUtil.getRemoteService(RemoteLoginService.class, "service", false);
		try {
			Map<String, String> result = service.login(user, pwd);
			System.out.println(result);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
