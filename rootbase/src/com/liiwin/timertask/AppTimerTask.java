package com.liiwin.timertask;

import java.util.TimerTask;
/**
 * <p>标题： 定时任务的在执行类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午3:41:28</p>
 * <p>类全名：com.liiwin.timertask.AppTimerTask</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class AppTimerTask extends TimerTask
{
	private boolean	isFinish	= false;

	@Override
	public void run()
	{
		runTask();
		isFinish = true;
	}

	/**
	 * 子类需要重写此方法
	 * 
	 * 赵玉柱
	 */
	protected abstract void runTask();

	public boolean isFinish()
	{
		return isFinish;
	}
}
