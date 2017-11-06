package com.zhu.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloMvcController {
	/**
	 * url/hello/hello
	 * @return
	 */
	@RequestMapping("/hello")
	public String hello()
	{
		return "hello";
	}

}
