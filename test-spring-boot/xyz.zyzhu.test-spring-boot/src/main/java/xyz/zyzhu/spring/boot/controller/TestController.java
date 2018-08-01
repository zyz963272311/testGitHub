package xyz.zyzhu.spring.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.zyzhu.spring.boot.model.AutoCode;
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
		AutoCode test = testService.test();
		return test.toString();
	}
}
