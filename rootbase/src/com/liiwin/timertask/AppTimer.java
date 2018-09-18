package com.liiwin.timertask;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public abstract class AppTimer<t extends AppTimerTask> extends Timer
{
	private static Logger logger = LoggerFactory.getLogger(AppTimer.class);

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
		checkParams(params);
		execute();
	}

	/**
	 * 参数校验
	 * 启停状态
	 * 时间是否允许执行
	 * 
	 * 赵玉柱
	 */
	protected void checkParams(Map<String,Object> params)
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
	public abstract List<AppTimerTask> loadTimerTask();

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
		stopTask(tasks);
	}

	public void stopTask(List<AppTimerTask> tasks)
	{
		while (true)
		{
			if (isFinish(tasks))
			{
				logger.error("任务执行完成了");
				cancel();
				break;
			}
			try
			{
				logger.error("任务没有执行完成，需要等待3秒");
				Thread.sleep(3000);
			} catch (InterruptedException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	public boolean isFinish(List<AppTimerTask> tasks)
	{
		boolean isFinish = true;
		for (AppTimerTask task : tasks)
		{
			isFinish = isFinish & task.isFinish();
			if (!isFinish)
			{
				break;
			}
		}
		return isFinish;
	}
}
