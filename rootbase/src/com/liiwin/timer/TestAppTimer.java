package com.liiwin.timer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.liiwin.timer.constant.TimerConstant;
import com.liiwin.timer.task.AppTimerTask;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年2月7日 下午5:26:33</p>
 * <p>类全名：com.timer.TestAppTimer</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestAppTimer extends AppTimer<AppTimerTask>
{
	/**
	 * @param params
	 */
	public TestAppTimer(Map<String,Object> params)
	{
		super(params);
	}

	/**
	 * 装载task
	 * @return
	 * 赵玉柱
	 */
	@Override
	public List<AppTimerTask> loadTask()
	{
		List<AppTimerTask> tasks = new ArrayList<>();
		for (int i = 0; i < 1000; i++)
		{
			AppTimerTask task = new AppTimerTask();
			tasks.add(task);
		}
		return tasks;
	}

	public static void main(String[] args)
	{
		Map<String,Object> params = new HashMap<>();
		params.put(TimerConstant.MAXCOUNT, 5);
		//		params.put("strategy", "11:22:00:11-11:55:55");
		TestAppTimer appTimer = new TestAppTimer(params);
		new Thread(appTimer).start();
	}
}
