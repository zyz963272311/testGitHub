package com.liiwin.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import com.liiwin.code.Code;
import com.liiwin.code.CodePart;
import com.liiwin.code.CodeType;
import com.liiwin.db.Database;
import com.liiwin.service.RemoteRegisterService;

public class RemoteRegisterServiceImpl extends UnicastRemoteObject implements
		RemoteRegisterService {

	private static final long serialVersionUID = 1L;

	public RemoteRegisterServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Map<String, String> register(Map<String, Object> registerParams)
			throws RemoteException {
		Map<String, String> result = new HashMap<String, String>();
		if (registerParams == null || registerParams.isEmpty()) {
			result.put("ERROR", "请求参数为空");
			return result;
		}
		Database db = null;
		String userID = null;
		boolean rollBack = true;
		try {
			CodePart part1 = new CodePart("usr",3,CodeType.FIX_LENGTH);
			CodePart part2 = new CodePart("yyyyMMdd",8,CodeType.TIME);
			CodePart part3 = new CodePart("",8,CodeType.RANDOM_NUMBER);
			Code code = new Code(part1,part2,part3);
			userID = code.makeCode();
			registerParams.put("userid", userID);
			db = new Database("project01");
			db.insertTable("users", registerParams);
			db.commit();
			rollBack = false;
			result.put("userId", userID);
		} catch (Exception e) {
			result.put("ERROR", "注册失败，请联系管理员");
			throw e;
		} finally {
			db.rollback(rollBack);
		}
		return result;
	}

}
