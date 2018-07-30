package xyz.zyzhu.spring.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.zyzhu.spring.boot.service.TestDubboService;
/**
 * <p>标题： TestDubboController</p>
 * <p>功能： </p>
 * <p>所属模块： TestDubboController</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月16日 上午11:28:31</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.TestDubboController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/dubbo")
public class TestDubboController
{
	Logger				logger	= LoggerFactory.getLogger(TestDubboController.class);
	//此方式在发布的时候发布到了dubbo，但是在实际使用的时候，使用的是本地的实现，非dubbo上面的实现
	@Autowired
	TestDubboService	service;

	@RequestMapping(value = "dubbo", method = { RequestMethod.GET, RequestMethod.POST })
	public String testDubbo(String name)
	{
		logger.info("执行前" + name);
		String insert = service.getInsert(name);
		logger.info("执行后" + insert);
		return insert;
	}
}
