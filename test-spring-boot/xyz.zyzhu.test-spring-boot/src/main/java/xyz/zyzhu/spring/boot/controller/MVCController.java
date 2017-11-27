package xyz.zyzhu.spring.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月26日 下午3:10:42</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.MVCController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/mvc")
public class MVCController
{
	@RequestMapping(value = "get", method = { RequestMethod.GET })
	public ModelAndView mvcGet(String check)
	{
		String result = "登陆成功";
		ModelAndView view = new ModelAndView("get");
		if (!StrUtil.equals(check, "success"))
		{
			result = "登陆失败";
		}
		view.addObject("loginMessage", result);
		return view;
	}
}
