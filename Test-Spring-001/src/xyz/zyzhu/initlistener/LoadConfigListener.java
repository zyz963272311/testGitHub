package xyz.zyzhu.initlistener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.liiwin.config.BasConfig;
/**
 * <p>标题： 加载config配置文件</p>
 * <p>功能： </p>
 * <p>所属模块： testSpring</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月11日 上午10:59:00</p>
 * <p>类全名：xyz.zyzhu.initlistener.LoadConfigListener</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LoadConfigListener implements ServletContextListener
{
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		System.out.println("加载config数据");
		BasConfig.LoadConfig();
		System.out.println("config数据加载完成" + BasConfig.getProperties());
	}
}
