package xyz.zyzhu.spring.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月3日 上午12:01:59</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.MenuController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/menu")
public class MenuController {
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public ModelAndView getListMenu()
	{
		return null;
	}
}
