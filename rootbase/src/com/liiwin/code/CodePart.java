package com.liiwin.code;

/**
 * 
 * <p>标题： 码的组成部分</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月26日 下午2:34:39</p>
 * <p>类全名：com.liiwin.code.CodePart</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CodePart
{
	private String	code;
	/** 一个字符串，指定这部分码的样式 */
	private String	codePartFormate;
	/** 指定码的长度，取length和codePartFormate.length()的最小值*/
	private int		length;
	/**
	 * 1:随机数
	 * 2:时间格式
	 * 4:固定字符串
	 */
	private int		type;

	public CodePart()
	{
	}

	public CodePart(String codePartFormate, int length, int type)
	{
		this.codePartFormate = codePartFormate;
		this.length = length;
		this.type = type;
	}

	public CodePart(String code, String codePartFormate, int length, int type)
	{
		this.code = code;
		this.codePartFormate = codePartFormate;
		this.length = length;
		this.type = type;
	}

	public String getCodePartFormate()
	{
		return codePartFormate;
	}

	public void setCodePartFormate(String codePartFormate)
	{
		this.codePartFormate = codePartFormate;
	}

	public int getLength()
	{
		return length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@Override
	public String toString()
	{
		return "code=" + code + "codePartFormate=" + codePartFormate + ",length=" + length + ",type=" + type;
	}
}