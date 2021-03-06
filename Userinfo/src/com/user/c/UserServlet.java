package com.user.c;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.m.db.pojo.Userinfo;
import com.user.m.service.UserService;
import com.user.m.service.myexception.UserOnlineException;

public class UserServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
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
		
		
		
		//��ȡ�ͻ����ύ���û���������
		String name = request.getParameter("haha");//��ȡ���пؼ���ֵ
		String pass = request.getParameter("hehe");
		if(name== null || name.length() == 0 || pass == null || pass.length()==0)
		{
			throw new RuntimeException("用户名和密码不能为空");
		}
		System.out.println(name+","+pass);
 		System.out.println(request.getRemoteAddr());
		Userinfo u = new Userinfo();
		u.setName(name);
		u.setPass(pass);
		UserService us = new UserService();
		boolean b= false;
		try {
			b = us.denglu(u);
		} catch (UserOnlineException e) {
			request.getRequestDispatcher("/v/cuowu2.jsp").forward(request, response);
			return;
		}
		if(b){
			//�û�����������ȷ��������ת��zhuye.html
//			response.sendRedirect("/Userinfo/zhuye.html");
			//u�д洢�˵�¼�ߵ���Ϣ
			//��u�洢��session��
			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(30);
			session.setAttribute("loginuser", u);
			//-----------
			String s=u.getName();
			String news ="";
			for(int i=0;i<s.length();i++){
				int v = s.charAt(i);
				news =news+v+"a";
			}
			news = news.substring(0,news.length()-1);
		
			
			
			Cookie cname = new Cookie("username",news);
			Cookie cpass = new Cookie("password",u.getPass());
			cname.setMaxAge(60*60*24*7);
			cpass.setMaxAge(60*60*24*7);
			response.addCookie(cname);
			response.addCookie(cpass);
			//--------------
			request.getRequestDispatcher("/v/main.jsp").forward(request, response);
		}else{
			//�û��������������ת������ҳ��
//			response.sendRedirect("/Userinfo/cuowu.html");
			request.getRequestDispatcher("/v/cuowu.jsp").forward(request, response);
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
