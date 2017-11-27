package xyz.zyzhu.spring.boot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.zyzhu.spring.boot.dao.UserDao;
import xyz.zyzhu.spring.boot.model.UserInfo;
import xyz.zyzhu.spring.boot.service.UserService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月17日 下午10:53:29</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.impl.UserServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDao	dao;

	@Override
	public UserInfo login(String username, String password)
	{
		checkUser(username, password);
		return dao.login(username, password);
	}

	private void checkUser(String username, String password)
	{
		if (username == null)
		{
			throw new RuntimeException("用户名不可为空");
		}
		if (password == null)
		{
			throw new RuntimeException("密码不可为空");
		}
	}
}
