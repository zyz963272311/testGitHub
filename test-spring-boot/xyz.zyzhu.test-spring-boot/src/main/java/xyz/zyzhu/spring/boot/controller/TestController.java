package xyz.zyzhu.spring.boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.Menu1;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月1日 下午4:00:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.TestController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
import xyz.zyzhu.spring.boot.service.TestService;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
@EnableAutoConfiguration
@RestController
@RequestMapping("/test")
public class TestController
{
	@Autowired
	TestService testService;

	@RequestMapping(path = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test()
	{
		BootDatabase db = BootDatabasePoolManager.getDatabase("zyztest");
		try
		{
			Menu1 menu = new Menu1();
			menu.setMname("0001");
			List<Menu1> query = db.query(menu);
			if (query != null && !query.isEmpty())
			{
				return query.toString();
			}
		} finally
		{
			BootDatabasePoolManager.close(db);
		}
		return null;
	}

	/**
	 * @return
	 * 赵玉柱
	 */
	private String test1()
	{
		DruidConfig cfg = SpringBeanUtils.getBean(DruidConfig.class);
		System.out.println(cfg);
		System.out.println(cfg.defaultDataSource());
		Environment bean = SpringBeanUtils.getBean(Environment.class);
		System.out.println(bean.getProperty("spring.datasource.type"));
		AutoCode test = testService.test();
		return test.toString();
	}
}
