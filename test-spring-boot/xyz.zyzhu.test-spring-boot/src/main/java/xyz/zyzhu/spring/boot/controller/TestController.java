package xyz.zyzhu.spring.boot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.zyzhu.spring.boot.datafile.export.DataExport;
import xyz.zyzhu.spring.boot.datafile.export.ZipDataExport;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.Menu1;
import xyz.zyzhu.spring.boot.service.TestService;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
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
		boolean rollback = false;
		try
		{
			db.beginTrans();
			Menu1 menu = new Menu1();
			menu.setMname("0001");
			List<Menu1> query = db.query(menu);
			System.out.println("查询完成");
			Menu1 menu1 = new Menu1();
			menu1.setMname("测试名称");
			menu1.setUrl("www.baidu.com");
			menu1.setLimits("*");
			db.save(menu1);
			if (query != null && !query.isEmpty())
			{
				Menu1 menu12 = query.get(0);
				menu12.setLimits(menu12.getLimits() + menu12.getLimits());
				menu12.setSaveMode(2);
				db.save(menu12);
				if (query.size() > 0)
				{
					Menu1 menu13 = query.get(1);
					menu13.setLimits(null);
					menu13.setSaveMode(4);
					db.save(menu13);
				}
			}
			db.commit();
			rollback = false;
			return query != null ? query.toString() : null;
		} finally
		{
			try
			{
				db.rollback(rollback, false);
			} finally
			{
				BootDatabasePoolManager.close(db);
			}
		}
	}

	@RequestMapping(path = "/testDataExp", method = { RequestMethod.GET, RequestMethod.POST })
	public String testDataExp(@RequestParam(name = "expcode") String expcode, @RequestParam(name = "type", defaultValue = "1") int type)
	{
		DataExport ecport = null;
		switch (type)
		{
		case 1:
			ecport = new ZipDataExport();
			break;
		default:
			throw new RuntimeException("不存在对应的类型");
		}
		String export = ecport.export(expcode);
		return export;
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
