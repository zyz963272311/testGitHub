package com.user.m.service.myexception;

public class UserNameException  extends Exception{

	public UserNameException(){
		super("用户名重复");
	}
}
