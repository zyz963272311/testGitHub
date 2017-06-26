package com.user.c;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.m.db.pojo.Userinfo;
import com.user.m.service.UserService;
import com.user.m.service.myexception.UserOnlineException;

public class DefaultServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DefaultServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cookie c[]  = request.getCookies();
		if(c !=null){
			String name ="";
			String pass ="";
			for(int i=0;i<c.length;i++){
				if(c[i].getName().equals("username")){
					name = c[i].getValue();
				}
				if(c[i].getName().equals("password")){
					pass = c[i].getValue();
				}
			}
			
			if(name.equals("")|| pass.equals("")){
				
				//访问过网站但是没有登录过
				request.getRequestDispatcher("/v/index.jsp").forward(request, response);
			}else{
				String s=name;
				String news[] = s.split("a");
				String name2 ="";
				for(int i=0;i<news.length;i++){
					int v = Integer.parseInt(news[i]);
					char c2 = (char)v;
					name2= name2+c2;
				}
				//
				Userinfo u = new Userinfo();
				u.setName(name2);
				u.setPass(pass);
				UserService us = new UserService();
				boolean b =false;
				try {
					b = us.denglu(u);
				} catch (UserOnlineException e) {
					request.getRequestDispatcher("/v/cuowu2.jsp").forward(request, response);
					return;
				}
				if(b){
					//登录成功
					HttpSession session = request.getSession();
//					session.setMaxInactiveInterval(30);
					session.setAttribute("loginuser", u);
					request.getRequestDispatcher("/v/main.jsp").forward(request, response);
				}else{
					//拿到了name和pass，但是在其他的计算机上修改过密码
					request.getRequestDispatcher("/v/index.jsp").forward(request, response);
				}
				
			}
			
			
			
			
		}else{
			//从来没有访问过本网站
			request.getRequestDispatcher("/v/index.jsp").forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
