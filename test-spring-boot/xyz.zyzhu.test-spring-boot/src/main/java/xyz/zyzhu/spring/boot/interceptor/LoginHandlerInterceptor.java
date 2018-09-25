package xyz.zyzhu.spring.boot.interceptor;

import java.net.InetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.zyzhu.spring.boot.constance.LoginConstance;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月26日 下午10:15:39</p>
 * <p>类全名：xyz.zyzhu.spring.boot.interceptor.LoginHandlerInterceptor</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class LoginHandlerInterceptor implements HandlerInterceptor, LoginConstance
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		HttpSession session = request.getSession();
		InetAddress.getLocalHost().getHostAddress();
		request.getHeader("x-forwarded-for");
		request.getHeaderNames();
		request.getHeader("Proxy-Client-IP");
		request.getRemoteAddr();
		request.getHeader("host");
		if (session.getAttribute("users") != null)
		{
			return true;
		} else
		{
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3) throws Exception
	{
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception
	{
	}
}
