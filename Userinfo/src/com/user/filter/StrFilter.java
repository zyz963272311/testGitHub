package com.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class StrFilter implements Filter{

	public void destroy() {
		System.out.println("当filter被销毁的时候，会调用该方法");
	}

	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain arg2) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("调用了strfilter的dofilter方法");
		//放行
		arg2.doFilter(request, response);
		//跳转到其他页面
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("tomcat启动就会创建filter的对象，filter是单例模式的");
		
	}

}
