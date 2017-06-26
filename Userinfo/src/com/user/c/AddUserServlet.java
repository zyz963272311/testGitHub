package com.user.c;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.m.db.pojo.Userinfo;
import com.user.m.service.UserService;
import com.user.m.service.myexception.QiangBiException;
import com.user.m.service.myexception.UserNameException;

public class AddUserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddUserServlet() {
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

		
		Userinfo u = new Userinfo();
		u.setName(request.getParameter("tu"));
		u.setPass(request.getParameter("tp"));
		String a = request.getParameter("ta");
		int age = Integer.parseInt(a);
		u.setAge(age);
		u.setMail(request.getParameter("tm"));
		u.setXingbie(request.getParameter("r"));
		String c[] = request.getParameterValues("c");
		String aihao ="";
		if(c != null){
			for(int i=0;i<c.length;i++){
				aihao=aihao+c[i]+";";
			}
		}
		aihao = aihao.substring(0,aihao.length()-1);
		u.setAihao(aihao);
		u.setChengshi(request.getParameter("city"));
		u.setGexingqianming(request.getParameter("tt"));
		UserService us = new UserService();
		boolean b =false;
		try {
			b = us.addUser(u);
			if(b){
				request.getRequestDispatcher("/v/index.jsp").forward(request, response);
			}else{
				request.setAttribute("errormessage", "服务器升级中，请稍后访问。");
				request.getRequestDispatcher("/heihei/servlet/ErrorServlet").forward(request, response);
			}
		} catch (UserNameException e) {
			request.setAttribute("errormessage",e.getMessage());
			request.getRequestDispatcher("/heihei/servlet/ErrorServlet").forward(request, response);
		
		} catch (QiangBiException e) {
			request.setAttribute("errormessage", e.getMessage());
			request.getRequestDispatcher("/heihei/servlet/ErrorServlet").forward(request, response);
			
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
