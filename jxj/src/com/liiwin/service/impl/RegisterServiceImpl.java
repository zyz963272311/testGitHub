package com.liiwin.service.impl;

import java.util.Map;

import com.liiwin.service.RegisterService;
import com.liiwin.service.RemoteRegisterService;
import com.liiwin.util.ServiceUtil;

public class RegisterServiceImpl implements RegisterService {

	@Override
	public Map<String, String> register(Map<String, Object> registerParams) {
		RemoteRegisterService register = ServiceUtil.getRemoteService(
				RemoteRegisterService.class, "register", false);
		try {
			Map<String, String> result = register.register(registerParams);
			if (result != null && !result.isEmpty()) {
				System.out.println(result);
			}
			else
			{
				System.out.println("返回参数为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
