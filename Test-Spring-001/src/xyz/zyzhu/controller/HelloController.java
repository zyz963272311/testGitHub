package xyz.zyzhu.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.zyzhu.entry.Hello;
import xyz.zyzhu.service.HelloService;
/**
 * <p>标题： 测试使用，HelloWorld，回顾Spring</p>
 * <p>功能： </p>
 * <p>所属模块： Test-Spring</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: zyzhu.xyz</p>
 * <p>创建日期：2017年8月1日 上午10:34:19</p>
 * <p>类全名：xyz.zyzhu.controller.HelloController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//表示这个类是controller类
@Controller
//表示这个类拦截所有网站根目录/hello的请求
@RequestMapping("hello")
public class HelloController
{
	@Resource
	HelloService	helloService;

	@RequestMapping("hello")
	public String testHello()
	{
		return "hello";
	}

	@RequestMapping("hello1")
	public String testHello1(Hello hello)
	{
		if (hello == null)
		{
			throw new RuntimeException("数据传输对象不能为空");
		}
		String name = hello.getName();
		String say = hello.getSay();
		if (name == null || name.trim().length() == 0)
		{
			throw new RuntimeException("数据hello对象name为空");
		}
		if (say == null || say.trim().length() == 0)
		{
			throw new RuntimeException("数据hello对象say为空");
		}
		helloService.say(hello);
		return "hello";
	}
}
