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
		System.out.println("��filter�����ٵ�ʱ�򣬻���ø÷���");
	}

	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain arg2) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("������strfilter��dofilter����");
		//����
		arg2.doFilter(request, response);
		//��ת������ҳ��
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("tomcat�����ͻᴴ��filter�Ķ���filter�ǵ���ģʽ��");
		
	}

}
