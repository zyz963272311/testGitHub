package com.liiwin.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.liiwin.db.Database;
import com.liiwin.service.RemoteLoginService;
import com.liiwin.util.StrUtil;

public class RemoteLoginServiceImpl extends UnicastRemoteObject implements RemoteLoginService {

	private static final long serialVersionUID = 20263226876980004L;

	public RemoteLoginServiceImpl() throws RemoteException {
		super();
	}

	@Override
	public Map<String, String> login(String user, String pwd) {
		Map<String, String> result = new HashMap<>();
		if(StrUtil.isStrTrimNull(user))
		{
			result.put("userErr", "用户名不能为空");
		}
		if(StrUtil.isStrTrimNull(pwd))
		{
			result.put("pwdErr", "密码不能为空");
		}
		if(!result.isEmpty())
		{
			return result;
		}
		Database db =null;
		Map<String, Object> params = new HashMap<>();
		params.put("userid", user);
		params.put("username", user);
		params.put("telephone", user);
		String sql = "select * from users where (userid=:userid or username=:username or telephone =:telephone) ";
		try {
			db=new Database("project01");
			ResultSet rs = db.sqlSelect(sql, params);
			rs.last();
			int rowid = rs.getRow();
			if(rowid<=0)
			{
				result.put("errmsg", "用户名不存在");
				return result;
			}
			if(rowid>1)
			{
				result.put("errmsg", "根据"+user+"找到多个用户，请练习管理员");
				return result;
			}
			rs.first();
			String password = rs.getString("password");
			if(!pwd.equals(password))
			{
				result.put("errmsg", "密码输入错误，请核对后输入");
				return result;
			}
			String telephone = rs.getString("telephone");
			String regcode = rs.getString("regcode");
			result.put("tetephone", telephone);
			result.put("regcode", regcode);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			result.put("sysErr", e.getMessage());
		}
		finally
		{
			if(db!=null)
			{
				db.close();
			}
		}
		return result;
		
	}

}
