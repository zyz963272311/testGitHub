package com.user.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.user.m.db.pojo.Userinfo;
import com.user.m.service.UserService;

public class SessionListener implements HttpSessionListener{

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession  session = arg0.getSession();
		Userinfo u = (Userinfo)session.getAttribute("loginuser");
		UserService us = new UserService();
		us.deleteUserOnLine(u.getId());
		System.out.println("在线列表已经删除，可以登录了");
		
	}
	
	

}
