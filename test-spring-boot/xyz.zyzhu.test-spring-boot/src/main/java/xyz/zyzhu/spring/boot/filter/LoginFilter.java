package xyz.zyzhu.spring.boot.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import xyz.zyzhu.spring.boot.model.UserInfo;
import com.alibaba.fastjson.parser.ParserConfig;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月26日 下午3:56:49</p>
 * <p>类全名：xyz.zyzhu.spring.boot.filter.LoginFilter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Order(1)
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
public class LoginFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		System.out.println("filter启动了");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		req.getRequestURI();
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String url = req.getRequestURI().substring(req.getContextPath().length());
		if (url.startsWith("login") || url.startsWith("/login") || url.startsWith("/user/login"))
		{
			chain.doFilter(req, resp);
			return;
		}
		if (session == null)
		{
			//异常
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		Object users = session.getAttribute("users");
		UserInfo info = com.alibaba.fastjson.util.TypeUtils.cast(users, UserInfo.class, ParserConfig.getGlobalInstance());
		if (info == null)
		{
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy()
	{
	}
}