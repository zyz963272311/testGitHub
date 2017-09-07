package com.liiwin.timertask;

import java.util.Map;
/**
 * <p>标题： 定时任务类定义</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午5:30:57</p>
 * <p>类全名：com.liiwin.timertask.AppTimerDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AppTimerDef
{
	private String							appTimerName;
	private Class<AppTimer<AppTimerTask>>	appTimer;
	private long							startTime		= -1;
	private long							endTime			= -1;
	private long							interval		= -1;
	private Map<String,Object>				runParams;
	private int								minThreadCount	= -1;
	private int								maxThreadCount	= -1;

	public Class<AppTimer<AppTimerTask>> getAppTimer()
	{
		return appTimer;
	}

	public void setAppTimer(Class<AppTimer<AppTimerTask>> appTimer)
	{
		this.appTimer = appTimer;
	}

	public long getStartTime()
	{
		return startTime;
	}

	public void setStartTime(long startTime)
	{
		this.startTime = startTime;
	}

	public long getEndTime()
	{
		return endTime;
	}

	public void setEndTime(long endTime)
	{
		this.endTime = endTime;
	}

	public long getInterval()
	{
		return interval;
	}

	public void setInterval(long interval)
	{
		this.interval = interval;
	}

	public String getAppTimerName()
	{
		return appTimerName;
	}

	public void setAppTimerName(String appTimerName)
	{
		//setValue("appTimerName", appTimerName);
		this.appTimerName = appTimerName;
	}

	public Map<String,Object> getRunParams()
	{
		return runParams;
	}

	public void setRunParams(Map<String,Object> runParams)
	{
		//setValue("runParams", runParams);
		this.runParams = runParams;
	}

	public int getMinThreadCount()
	{
		return minThreadCount;
	}

	public void setMinThreadCount(int minThreadCount)
	{
		//setValue("minThreadCount", minThreadCount);
		this.minThreadCount = minThreadCount;
	}

	public int getMaxThreadCount()
	{
		return maxThreadCount;
	}

	public void setMaxThreadCount(int maxThreadCount)
	{
		//setValue("maxThreadCount", maxThreadCount);
		this.maxThreadCount = maxThreadCount;
	}
}
