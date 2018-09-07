package xyz.zyzhu.spring.boot.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
/**
 * <p>标题： 全局 controller 默认的异常处理</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月7日 上午9:40:10</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.DefaultErrorController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@ControllerAdvice
public class DefaultErrorHandler
{
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("errortitle", "统一异常处理页面");
		mav.addObject("errorname", "统一异常处理页面");
		mav.addObject("errmessage", e.getMessage());
		mav.addObject("exception", e);
		mav.addObject("url", request.getRequestURL());
		mav.setViewName(DEFAULT_ERROR_VIEW);
		return mav;
	}
}
