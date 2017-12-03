package xyz.zyzhu.dao.impl;

import org.springframework.stereotype.Repository;
import xyz.zyzhu.dao.HelloDao;
import xyz.zyzhu.entry.Hello;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月1日 下午3:40:57</p>
 * <p>类全名：xyz.zyzhu.dao.impl.HelloDaoImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Repository("HelloDaoImpl")
public class HelloDaoImpl implements HelloDao
{
	public void say(Hello hello)
	{
		System.out.println("操作数据库");
	}
}
