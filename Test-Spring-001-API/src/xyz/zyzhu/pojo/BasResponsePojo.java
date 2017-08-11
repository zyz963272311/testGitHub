package xyz.zyzhu.pojo;

import java.io.Serializable;
/**
 * <p>标题： 基础数据回执POJO</p>
 * <p>功能： </p>
 * <p>所属模块： Test-Spring-API</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年8月11日 下午3:18:11</p>
 * <p>类全名：xyz.zyzhu.pojo.BasResponsePojo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasResponsePojo implements Cloneable, Serializable
{
	private static final long	serialVersionUID	= -1644934943129628077L;
	private String				status;
	private String				code;
	private String				message;

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
