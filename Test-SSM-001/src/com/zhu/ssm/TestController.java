package com.zhu.ssm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月5日 下午5:33:43</p>
 * <p>类全名：com.zhu.TestController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Controller
public class TestController
{
	@RequestMapping("/showView")
	public ModelAndView showView()
	{
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("viewName");
		modelAndView.addObject(" 需要放到 model 中的属性名称 ", " 对应的属性值，它是一个对象 ");
		return modelAndView;
	}
}
