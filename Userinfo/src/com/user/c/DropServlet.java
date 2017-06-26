package com.user.c;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.UserDataHandler;

import com.user.m.db.pojo.Userinfo;
import com.user.m.service.UserService;
import com.user.m.service.myexception.QiangBiException;
import com.user.m.service.myexception.UserNameException;

public class DropServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DropServlet() {
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
		
		int id=Integer.parseInt(request.getParameter("temp"));
		UserService u=new UserService();
		HttpSession  session = request.getSession(); 
		Userinfo un=(Userinfo) session.getAttribute("loginuser");
		int uid=un.getId();
		boolean b=false;
			if(uid==id){
				request.getRequestDispatcher("/v/shibai.jsp").forward(request, response);
			}else{
				
			b=u.dropuser(id);
			if(b){

				request.getRequestDispatcher("/v/chenggong.jsp").forward(request, response);
			}else{
				request.setAttribute("errormessage", "服务器升级中，请稍后访问。");
				request.getRequestDispatcher("/v/shibai.jsp").forward(request, response);
			}
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
