package xyz.zyzhu.spring.boot.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import xyz.zyzhu.spring.boot.dao.UserDao;
import xyz.zyzhu.spring.boot.model.UserInfo;
import com.liiwin.utils.StrUtil;
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
	@Autowired
	JdbcTemplate	jdbcTemplate;

	public UserInfo login(String username, String password)
	{
		String sql = "select password from users where name =?";
		List<String> passwords = jdbcTemplate.queryForList(sql, new Object[] { username }, String.class);
		if (passwords == null || passwords.isEmpty())
		{
			throw new RuntimeException("登陆失败：根据用户名未查询到数据");
		}
		for (String pwd : passwords)
		{
			if (StrUtil.equals(password, pwd))
			{
				UserInfo users = new UserInfo(username, password);
				return users;
			}
		}
		throw new RuntimeException("用户名或密码错误");
	}
}
