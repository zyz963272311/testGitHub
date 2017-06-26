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

public class ExitServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ExitServlet() {
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
		System.out.println("调用了ExitServlet，注销用户");
		Cookie cname = new Cookie("username","");
		Cookie cpass = new Cookie("password","");
		cname.setMaxAge(1);
		cpass.setMaxAge(1);
		response.addCookie(cname);
		response.addCookie(cpass);
		HttpSession session = request.getSession();
		Userinfo u = (Userinfo)session.getAttribute("loginuser");
		UserService us = new UserService();
		us.deleteUserOnLine(u.getId());
		session.invalidate();//销毁session对象
		request.getRequestDispatcher("/v/index.jsp").forward(request, response);
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
