package com.user.m.service.myexception;

public class UserOnlineException  extends Exception{
	public UserOnlineException(){
		super("用户已经在线，请更换其他账户登录系统");
	}

}
