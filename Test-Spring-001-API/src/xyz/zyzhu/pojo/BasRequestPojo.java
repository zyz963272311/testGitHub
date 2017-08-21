package xyz.zyzhu.pojo;

import java.io.Serializable;
/**
 * <p>标题： 基础数据接收POJO</p>
 * <p>功能： </p>
 * <p>所属模块： test-Spring-API</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年8月11日 下午3:16:12</p>
 * <p>类全名：xyz.zyzhu.pojo.BasRequestPojo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class BasRequestPojo implements Cloneable, Serializable
{
	private static final long	serialVersionUID	= 4147907350070675551L;
	private String				token;										//token
	private String				fromCode;									// 调用方

	public Class<? extends BasResponsePojo> getRespClass()
	{
		return BasResponsePojo.class;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getFromCode()
	{
		return fromCode;
	}

	public void setFromCode(String fromCode)
	{
		this.fromCode = fromCode;
	}

	/**
	 * 为了获取APIname，构造
	 * @return
	 * 赵玉柱
	 */
	public abstract String getApiName();
}
