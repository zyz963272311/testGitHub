package com.liiwin.utils.tac;

import java.util.List;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年11月20日 下午8:47:36</p>
 * <p>类全名：com.liiwin.utils.tac.Grammer</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Grammer
{
	private List<String>	grammerList;
	private int				line;

	/**
	 * 
	 */
	public Grammer()
	{
		super();
	}

	/**
	 * @param grammerList
	 * @param line
	 */
	public Grammer(List<String> grammerList, int line)
	{
		super();
		this.grammerList = grammerList;
		this.line = line;
	}

	public List<String> getGrammerList()
	{
		return grammerList;
	}

	public void setGrammerList(List<String> grammerList)
	{
		this.grammerList = grammerList;
	}

	public int getLine()
	{
		return line;
	}

	public void setLine(int line)
	{
		this.line = line;
	}
}
