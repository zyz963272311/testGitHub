package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.baidu.ueditor.Encoder;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:10:49</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.define.BaseState</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BaseState implements State
{
	private boolean				state	= false;
	private String				info	= null;
	private Map<String,String>	infoMap	= new HashMap<String,String>()
										{
										};

	public BaseState()
	{
		this.state = true;
	}

	public BaseState(boolean state)
	{
		setState(state);
	}

	public BaseState(boolean state, String info)
	{
		setState(state);
		this.info = info;
	}

	public BaseState(boolean state, int infoCode)
	{
		setState(state);
		this.info = AppInfo.getStateInfo(infoCode);
	}

	public boolean isSuccess()
	{
		return this.state;
	}

	public void setState(boolean state)
	{
		this.state = state;
	}

	public void setInfo(String info)
	{
		this.info = info;
	}

	public void setInfo(int infoCode)
	{
		this.info = AppInfo.getStateInfo(infoCode);
	}

	public String toJSONString()
	{
		return toString();
	}

	public String toString()
	{
		String key = null;
		String stateVal = (isSuccess()) ? AppInfo.getStateInfo(0) : this.info;
		StringBuilder builder = new StringBuilder();
		builder.append("{\"state\": \"" + stateVal + "\"");
		Iterator iterator = this.infoMap.keySet().iterator();
		while (iterator.hasNext())
		{
			key = (String) iterator.next();
			builder.append(",\"" + key + "\": \"" + ((String) this.infoMap.get(key)) + "\"");
		}
		builder.append("}");
		return Encoder.toUnicode(builder.toString());
	}

	public void putInfo(String name, String val)
	{
		this.infoMap.put(name, val);
	}

	public void putInfo(String name, long val)
	{
		putInfo(name, val + "");
	}
}
