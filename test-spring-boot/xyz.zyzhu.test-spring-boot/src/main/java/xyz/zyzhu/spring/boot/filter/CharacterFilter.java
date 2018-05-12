package xyz.zyzhu.spring.boot.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import com.liiwin.config.BasConfig;
import com.liiwin.utils.StrUtil;
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
@WebFilter(filterName = "loginFilter", urlPatterns = "/*")
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
		response.setContentType("");
		String method = request.getMethod();
		if (StrUtil.isNoStrTrimNull(method) && method.equalsIgnoreCase("post"))
		{
			//post方式
		} else
		{
			//get方式
		}
	}

	private class CharacterFilterInnerWapper extends HttpServletRequestWrapper
	{
		/**
		 * @param request
		 */
		public CharacterFilterInnerWapper(HttpServletRequest request)
		{
			super(request);
		}

		@Override
		public String getParameter(String name)
		{
			String value = super.getParameter(name);
			try
			{
				value = new String(value.getBytes(getRequest().getCharacterEncoding()), StrUtil.obj2str(BasConfig.getPropertie("PROJECT_WEB_CHARACTER"), "UTF-8"));
			} catch (UnsupportedEncodingException e)
			{
				throw new RuntimeException("报错内容", e);
			}
			return value;
		}
	}

	@Override
	public void destroy()
	{
	}
}
