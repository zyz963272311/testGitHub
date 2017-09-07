package com.liiwin.timertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 定时任务管理类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年9月7日 下午3:53:33</p>
 * <p>类全名：com.liiwin.timertask.AppTaskManager</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AppTaskManager
{
	private static Map<String,AppTimerDef>	appTimers	= new HashMap<String,AppTimerDef>();

	/**
	 * 启动多个定时任务
	 * @param paramsList
	 * 赵玉柱
	 */
	public static void startTask(List<Map<String,Object>[]> paramsList)
	{
		if (paramsList == null || paramsList.isEmpty())
		{
			return;
		}
		for (Map<String,Object>[] paramsArray : paramsList)
		{
			if (paramsArray == null || paramsArray.length < 2)
			{
				continue;
			}
			startTask(paramsArray[0], paramsArray[1]);
		}
	}

	/**
	 * 启动
	 * @param method
	 * 赵玉柱
	 */
	public static void startTask(Map<String,Object> params, Map<String,Object> runParams)
	{
		String appTimerId = StrUtil.obj2str(params.get(AppTimerConstant.APP_TIMER_ID));
		if (StrUtil.isStrTrimNull(appTimerId))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_ID + "不可为空");
		}
		String appTimerName = StrUtil.obj2str(params.get(AppTimerConstant.APP_TIMER_NAME));
		if (StrUtil.isStrTrimNull(appTimerName))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_NAME + "不可为空");
		}
		String appTimerClassNew = StrUtil.obj2str(params.get(AppTimerConstant.APP_TIMER_CLASS));
		if (StrUtil.isStrTrimNull(appTimerClassNew))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_CLASS + "不可为空");
		}
		long executeTimeFrom = StrUtil.obj2long(params.get(AppTimerConstant.EXECUTE_TIME_FROM), -1);
		long executeTimeTo = StrUtil.obj2long(params.get(AppTimerConstant.EXECUTE_TIME_TO), -1);
		long executeInterval = StrUtil.obj2long(params.get(AppTimerConstant.EXECUTE_INTERVAL), 10);
		int maxThreadCount = StrUtil.obj2int(params.get(AppTimerConstant.MAX_THREAD_COUNT));
		int minThreadCount = StrUtil.obj2int(params.get(AppTimerConstant.MIN_THREAD_COUNT));
		if (maxThreadCount < minThreadCount)
		{
			throw new RuntimeException("最大线程数不可小于最小线程数");
		}
		int p = appTimerClassNew.lastIndexOf('.');
		if (p <= 0)
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_CLASS + "定义错误[" + appTimerClassNew + "]");
		}
		String NEW = appTimerClassNew.substring(p);
		if (!"new".equalsIgnoreCase(NEW))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_CLASS + "定义错误[" + appTimerClassNew + "],正确格式为[class.new]");
		}
		String appTimerClass = appTimerClassNew.substring(0, p);
		if (!"new".equalsIgnoreCase(appTimerClass))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_CLASS + "定义错误[" + appTimerClassNew + "],正确格式为[package.class.new]");
		}
		if (StrUtil.isStrTrimNull(appTimerClass))
		{
			throw new RuntimeException("任务启动时参数" + AppTimerConstant.APP_TIMER_CLASS + "定义错误[" + appTimerClassNew + "],类名不可为空");
		}
		Class<AppTimer<AppTimerTask>> cls = null;
		try
		{
			cls = (Class<AppTimer<AppTimerTask>>) Class.forName(appTimerClass);
			cls.getConstructor(Map.class);
			AppTimerDef appTimerDef = new AppTimerDef();
			appTimerDef.setInterval(executeInterval);
			appTimerDef.setAppTimer(cls);
			appTimerDef.setRunParams(runParams);
			appTimerDef.setAppTimerName(appTimerName);
			appTimerDef.setStartTime(executeTimeFrom);
			appTimerDef.setEndTime(executeTimeTo);
			appTimerDef.setMaxThreadCount(maxThreadCount);
			appTimerDef.setMinThreadCount(minThreadCount);
			appTimers.put(appTimerId, appTimerDef);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 停止多个定时任务
	 * @param paramsList
	 * 赵玉柱
	 */
	public static void stopTask(List<Map<String,Object>> paramsList)
	{
		if (paramsList == null || paramsList.isEmpty())
		{
			return;
		}
		for (Map<String,Object> params : paramsList)
		{
			stopTask(params);
		}
	}

	/**
	 * 加载定时任务
	 * 
	 * 赵玉柱
	 */
	public static void loadTimer()
	{
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
	}

	/**
	 * 停止 
	 * @param method
	 * 赵玉柱
	 */
	public static void stopTask(Map<String,Object> params)
	{
		String appTimerId = StrUtil.obj2str(params.get(AppTimerConstant.APP_TIMER_ID));
		if (StrUtil.isStrTrimNull(appTimerId))
		{
			throw new RuntimeException("停止启动时参数" + AppTimerConstant.APP_TIMER_ID + "不可为空");
		}
		appTimers.remove(appTimerId);
	}

	/**
	 * 检查执行时间看当前任务是否在运行时间中
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	private boolean checkRunTime(Map<String,Object> params)
	{
		return true;
	}
}
