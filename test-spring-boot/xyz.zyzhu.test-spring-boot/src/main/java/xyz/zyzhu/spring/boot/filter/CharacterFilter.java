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
import org.springframework.core.annotation.Order;
/**
 * <p>标题： 字符集编码过滤器</p>
 * <p>功能： </p>
 * <p>所属模块： Web</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月27日 下午9:28:58</p>
 * <p>类全名：xyz.zyzhu.spring.boot.filter.CharacterFilter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Order(2)
@WebFilter(filterName = "charsetFilter", urlPatterns = "/*")
public class CharacterFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
		//初始化不做任何事情
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy()
	{
	}
}
