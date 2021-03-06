package com.liiwin.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.timertask.AppTimer;
import com.liiwin.timertask.AppTimerTask;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午4:18:51</p>
 * <p>类全名：com.liiwin.test.TimerTaskTest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TimerTaskTest
{
	private static Logger logger = LoggerFactory.getLogger(TimerTaskTest.class);

	public static void main(String[] args)
	{
		Map<String,Object> params = new HashMap<>();
		new AppTimer<AppTimerTask>(params)
		{
			@Override
			public List<AppTimerTask> loadTimerTask()
			{
				List<AppTimerTask> tasks = new ArrayList<>();
				for (int i = 0; i < 5; i++)
				{
					AppTimerTask task = new AppTimerTask()
					{
						@Override
						protected void runTask()
						{
							logger.error("执行了定时任务");
						}
					};
					tasks.add(task);
				}
				return tasks;
			}
		};
	}
}
