package com.zhu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * <p>���⣺ HelloController</p>
 * <p>���ݣ� HelloController</p>
 * <p>����ʱ�䣺 2016��12��27��</p>
 * <p>copyRight @ zyzhu.xyz 2016</p>
 * <p>��ȫ���� com.zhu.controller.HelloController</p>
 * <p>���ߣ� Administrator</p>
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
