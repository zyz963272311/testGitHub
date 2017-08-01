package xyz.zyzhu.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import xyz.zyzhu.dao.HelloDao;
import xyz.zyzhu.entry.Hello;
import xyz.zyzhu.service.HelloService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月1日 下午3:36:53</p>
 * <p>类全名：xyz.zyzhu.service.impl.HelloServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service("HelloServiceImpl")
public class HelloServiceImpl implements HelloService
{
	@Resource
	HelloDao	helloDao;

	@Override
	public void say(Hello hello)
	{
		helloDao.say(hello);
	}
}
