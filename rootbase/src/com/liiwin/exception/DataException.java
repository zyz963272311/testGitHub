package com.liiwin.exception;

/**
 * 
 * <p>标题： 数据异常</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月31日 下午4:05:51</p>
 * <p>类全名：com.liiwin.exception.DataException</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataException extends RuntimeException
{
	private static final long	serialVersionUID	= 1L;

	public DataException(String message)
	{
		super(message);
	}
}
