package com.liiwin.timertask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
/**
 * <p>标题： 定时任务的启动类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午3:39:57</p>
 * <p>类全名：com.liiwin.timertask.AppTimer</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AppTimer<t extends AppTimerTask> extends Timer
{
	/**
	 *初始化参数 
	 */
	public AppTimer(Map<String,Object> params)
	{
		init(params);
	}

	/**
	 * 初始化参数
	 * 
	 * 赵玉柱
	 */
	private void init(Map<String,Object> params)
	{
		checpParams(params);
		execute();
	}

	/**
	 * 参数校验
	 * 启停状态
	 * 时间是否允许执行
	 * 
	 * 赵玉柱
	 */
	protected void checpParams(Map<String,Object> params)
	{
	}

	/**
	 * 执行定时任务
	 * 
	 * 赵玉柱
	 */
	public void execute()
	{
		runTask(loadTimerTask());
	}

	/**
	 * 这个方法需要重写，要求子类必须实现
	 * @return
	 * 赵玉柱
	 */
	public List<AppTimerTask> loadTimerTask()
	{
		List<AppTimerTask> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++)
		{
			AppTimerTask task = new AppTimerTask();
			tasks.add(task);
		}
		return tasks;
	}

	/**
	 * 运行定时任务
	 * @param tasks
	 * 赵玉柱
	 */
	public void runTask(List<AppTimerTask> tasks)
	{
		if (tasks == null || tasks.isEmpty())
		{
			return;
		}
		for (AppTimerTask task : tasks)
		{
			schedule(task, 1 * 1000);
		}
	}
}
