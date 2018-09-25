package xyz.zyzhu.spring.boot.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.liiwin.date.DateUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.dao.UserDao;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.User;
import xyz.zyzhu.spring.boot.model.UserInfo;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月17日 下午10:31:28</p>
 * <p>类全名：xyz.zyzhu.spring.boot.dao.impl.UserDaoImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Repository
public class UserDaoImpl implements UserDao
{
	public UserInfo login(String username, String password)
	{
		User queryUser = new User();
		queryUser.setUsername(username);
		BootDatabase db = null;
		boolean rollback = true;
		UserInfo inf = null;
		int errCount = 0;
		try
		{
			db = BootDatabasePoolManager.getDatabaseByClass(User.class, true);
			List<User> querys = db.query(queryUser);
			if (querys == null)
			{
				throw new RuntimeException("登陆失败：无此用户");
			}
			List<UserInfo> infos = new ArrayList<>();
			boolean addCount = false;
			for (User user : querys)
			{
				errCount = StrUtil.obj2int(user.getErrcount());
				Date errtime = user.getErrtime();
				if (errtime != null && ((DateUtil.getCurDate().getTime() - errtime.getTime()) < (24 * 60 * 60 * 1000)))
				{
					addCount = true;
					if (errCount >= PropertiesUtils.getPropIntValue("LOGIN_ERR_MAX_COUNT", 3))
					{
						continue;
					}
				}
				if (StrUtil.equals(password, user.getPassword()))
				{
					UserInfo users = new UserInfo(username, password);
					infos.add(users);
				}
			}
			if (querys.size() == 1)
			{
				User user = querys.get(0);
				errCount = StrUtil.obj2int(user.getErrcount());
				db.beginTrans();
				if (addCount)
				{
					user.setErrtime(DateUtil.getCurDate());
					Date errtime = user.getErrtime();
					//仅此一条的情况下，设置失败次数
					if (errtime == null || ((DateUtil.getCurDate().getTime() - errtime.getTime()) >= (24 * 60 * 60 * 1000)))
					{
						user.setErrcount(1);
					} else
					{
						user.setErrcount(StrUtil.obj2int(user.getErrcount()) + 1);
					}
				} else
				{
					//登录成功清空登录失败信息，前提是没有超过当日最大次数限制的情况下
					user.setErrtime(null);
					user.setErrcount(0);
				}
				//更新数据并且不保留旧值，为了更新失败时间，防止置空
				db.update(user, false);
			}
			if (infos.isEmpty())
			{
				throw new RuntimeException("用户名或密码错误");
			}
			if (infos.size() > 1)
			{
				throw new RuntimeException("查询到多名相同用户,请更改用户信息");
			}
			inf = infos.get(0);
			//			for (String pwd : passwords)
			//			{
			//				if (StrUtil.equals(password, pwd))
			//				{
			//					UserInfo users = new UserInfo(username, password);
			//					return users;
			//				}
			//			}
			rollback = false;
		} finally
		{
			if (db != null)
			{
				try
				{
					db.rollback(rollback);
				} finally
				{
					BootDatabasePoolManager.close(db);
				}
			}
		}
		if (errCount >= PropertiesUtils.getPropIntValue("LOGIN_ERR_MAX_COUNT", 3))
		{
			throw new RuntimeException("用户名已达到当日最大登录失败次数限制");
		}
		return inf;
	}
}
