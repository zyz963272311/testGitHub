package xyz.zyzhu.spring.boot.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/**
 * <p>标题： 限制IP登陆</p>
 * <p>功能： </p>
 * <p>所属模块： Spring boot</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月27日 下午8:04:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.interceptor.IPHandlerInterceptor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class IPHandlerInterceptor implements HandlerInterceptor
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		System.out.println("");
		ModelAndView view = new ModelAndView("error");
		request.setAttribute("returnObj", view);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
		System.out.println("");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception
	{
		System.out.println("");
	}

	private List<String> getLimitIP()
	{
		List<String> limitIP = new ArrayList<String>();
		return limitIP;
	}
}
