package com.user.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class StrErrFilter implements Filter{

	private static Properties p = new Properties();
	static{
		try {
			p.load(StrErrFilter.class.getResourceAsStream("strerr.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException {
		String str2 = p.getProperty("key");
		String str ="";
		String s2[] = str2.split("a");
		for(int i=0;i<s2.length;i++){
			str = str + (char)Integer.parseInt(s2[i]);
		}
		System.out.println(str);
		String s[] = str.split(";");
		HttpServletRequest request = (HttpServletRequest)arg0;
		Enumeration e = request.getParameterNames();
		boolean b = false;
		while(e.hasMoreElements()){
			
			String name = (String)e.nextElement();
			String value = request.getParameter(name);
			for(int i=0;i<s.length;i++){
				if(value != null){
					int j = value.indexOf(s[i]);
					if(j != -1){
						b = true;
						break;
					}
				}
			}
		}
		if(b){
			request.getRequestDispatcher("/error2.html").forward(arg0, arg1);
		}else{
			arg2.doFilter(arg0, arg1);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

		System.out.println("创建了筛选非法关键字的过滤器对象");
		
	}

}
