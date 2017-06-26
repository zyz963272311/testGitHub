package com.user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.user.m.db.pojo.Userinfo;

public class LoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,	FilterChain arg2) throws IOException, ServletException {
		
		
		System.out.println("调用了防盗链的filter，来验证用户是否登录过");
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpSession session  = request.getSession();
		Userinfo u = (Userinfo)session.getAttribute("loginuser");
		if(u != null){
			//登录成功
			//放行
			arg2.doFilter(arg0, arg1);
		}else{
			//没有登录过是盗链
			request.getRequestDispatcher("/index.html").forward(arg0, arg1);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("创建了LoginFilter的对象");
		
	}

}
