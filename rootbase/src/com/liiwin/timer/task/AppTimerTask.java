package com.liiwin.timer.task;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年2月7日 下午2:07:44</p>
 * <p>类全名：com.timer.task.AppTimerTask</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AppTimerTask extends TimerTask
{
	private static Logger	logger	= LoggerFactory.getLogger(AppTimerTask.class);
	//当前执行的步骤
	volatile protected int	step;

	@Override
	public void run()
	{
		//设置已经执行了
		setStep(1);
		try
		{
			Thread.sleep(1000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		logger.error(this + "执行了定时任务" + System.currentTimeMillis());
	}

	public int getStep()
	{
		return step;
	}

	public void setStep(int step)
	{
		this.step = step;
	}

	@Override
	public boolean cancel()
	{
		logger.error("task类执行取消执行方法");
		return super.cancel();
	}
}
