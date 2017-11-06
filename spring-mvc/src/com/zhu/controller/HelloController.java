package com.zhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <p>标题： HelloController</p>
 * <p>内容： HelloController</p>
 * <p>创建时间： 2016年12月27日</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>类全名： com.zhu.controller.HelloController</p>
 * <p>作者： Administrator</p>
 */
@Controller
@RequestMapping("/hello")
public class HelloController
{
	@RequestMapping(method=RequestMethod.GET)
	public String printHello(ModelMap model)
	{
		model.addAttribute("message", "Hello Spring MVC Framework!");
		return "hello";
	}
}
