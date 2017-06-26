package com.user.m.service.myexception;

public class QiangBiException extends Exception{

	public QiangBiException(){
		super("信息中包含非法字符或敏感词汇");
	}
}
