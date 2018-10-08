package xyz.zyzhu.spring.boot.model;

import xyz.zyzhu.spring.boot.constance.ResponseConstance;
/**
 * <p>标题： 底层的回执信息</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年10月3日 上午9:23:05</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BasResponse</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BasResponse extends BasObject implements ResponseConstance
{
	private static final long	serialVersionUID	= -2374097034556508726L;
	//值
	private String				code;
	//异常原因
	private String				message;

	public BasResponse()
	{
		code = "0";
	}

	/**
	 * 返回状态是否为成功
	 * @return
	 * 赵玉柱
	 */
	public boolean isSuccess()
	{
		String tempCode = getCode();
		if (tempCode == null || CODE_SUCCESS.equals(tempCode))
		{
			return true;
		}
		return false;
	}

	public BasResponse(String code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		//setValue("code", code);
		this.code = code;
	}

	public String getMessage()
	{
		return message;
	}

	public void setErrMessage(String message)
	{
		setCode(CODE_ERROR);
		setMessage(message);
	}

	public void setMessage(String message)
	{
		//setValue("message", message);
		this.message = message;
	}
}
