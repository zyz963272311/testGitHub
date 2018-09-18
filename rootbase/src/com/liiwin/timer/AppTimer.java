package com.liiwin.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liiwin.timer.constant.TimerConstant;
import com.liiwin.timer.task.AppTimerTask;
/**
 * <p>标题： 定时任务</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2018年2月7日 下午2:04:40</p>
 * <p>类全名：com.timer.Timer</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class AppTimer<T extends AppTimerTask> extends Timer implements Runnable
{
	private static Logger			logger	= LoggerFactory.getLogger(AppTimer.class);
	//初始化参数
	protected Map<String,Object>	params;
	//最大执行数据量
	protected int					maxcount;
	//执行策略---执行之间从---到
	protected Map<String,Date>		strategy;
	//执行时间间隔
	protected long					interval;
	CopyOnWriteArrayList<T>			tasks	= new CopyOnWriteArrayList<>();

	public AppTimer(Map<String,Object> params)
	{
		super();
		this.params = params;
		init();
	}

	/**
	 * 初始化
	 * 
	 * 赵玉柱
	 */
	public void init()
	{
		if (params == null)
		{
			throw new NullPointerException("定时任务初始化参数不可为空");
		}
		//策略
		//hh:mm:ss-hh:mm:ss   从几点到几点执行
		strategy = getStrategy();
		logger.error("{}", strategy);
		//执行时间间隔
		//单位为秒 5
		interval = getInterval();
		//最大执行数量
		maxcount = getMaxcount();
	}

	/**
	 * 获取执行策略，即执行时间
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Date> getStrategy()
	{
		Map<String,Date> _strategy = new HashMap<>();
		Object strategyObj = params.get(TimerConstant.STRATEGY);
		if (strategyObj != null)
		{
			String strategyStr = strategyObj.toString().trim();
			String[] strategyStrArr = strategyStr.split("-");
			if (strategyStrArr != null && strategyStrArr.length == 2)
			{
				SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
				format.setLenient(false);
				String strategyStrFrom = strategyStrArr[0];
				if (strategyStrFrom != null)
				{
					String[] strategyStrFromArray = strategyStrFrom.split(":");
					if (strategyStrFromArray == null || strategyStrFromArray.length != 3)
					{
						if (strategyStrFromArray == null)
						{
							strategyStrFrom = "00:00:00";
						} else
						{
							if (strategyStrFromArray.length > 3)
							{
								strategyStrFrom = strategyStrFromArray[0] + ":" + strategyStrFromArray[1] + ":" + strategyStrFromArray[2];
							} else
							{
								boolean endWithMH = strategyStrFrom.endsWith(":");
								for (int i = strategyStrFromArray.length; i < 3; i++)
								{
									if (!endWithMH && i == strategyStrFromArray.length)
									{
										strategyStrFrom += ":";
									}
									strategyStrFrom += "00";
									if (i != 2)
									{
										strategyStrFrom += ":";
									}
								}
							}
						}
					}
				}
				String strategyStrTo = strategyStrArr[1];
				if (strategyStrTo != null)
				{
					String[] strategyStrToArray = strategyStrTo.split(":");
					if (strategyStrToArray == null || strategyStrToArray.length != 3)
					{
						if (strategyStrToArray == null)
						{
							strategyStrTo = "00:00:00";
						} else
						{
							if (strategyStrToArray.length > 3)
							{
								strategyStrTo = strategyStrToArray[0] + ":" + strategyStrToArray[1] + ":" + strategyStrToArray[2];
							} else
							{
								boolean endWithMH = strategyStrTo.endsWith(":");
								for (int i = strategyStrToArray.length; i < 3; i++)
								{
									if (!endWithMH && i == strategyStrToArray.length)
									{
										strategyStrTo += ":";
									}
									strategyStrTo += "00";
									if (i != 2)
									{
										strategyStrTo += ":";
									}
								}
							}
						}
					}
				}
				try
				{
					Date strategyFrom = format.parse(strategyStrFrom);
					Date strategyTo = format.parse(strategyStrTo);
					_strategy.put(TimerConstant.STRATEGY_FROM, strategyFrom);
					_strategy.put(TimerConstant.STRATEGY_TO, strategyTo);
				} catch (ParseException e)
				{
					throw new RuntimeException("时间格式错误，应该为hh:mm:ss格式", e);
				}
			}
		}
		return _strategy;
	}

	/**
	 * 获取最大数量
	 * @return
	 * 赵玉柱
	 */
	public int getMaxcount()
	{
		//默认值500
		int result = 500;
		Object maxcountStr = params.get(TimerConstant.MAXCOUNT);
		if (maxcountStr == null)
		{
			return result;
		}
		return Integer.valueOf(maxcountStr.toString().trim());
	}

	/**
	 * 获取时间间隔
	 * @return
	 * 赵玉柱
	 */
	public long getInterval()
	{
		long result = 5000;
		Object intervalStr = params.get(TimerConstant.INTERVAL);
		if (intervalStr == null)
		{
			return result;
		}
		return Long.valueOf(intervalStr.toString().trim());
	}

	@Override
	public void run()
	{
		try
		{
			//step1 判断当前时间是否允许执行此定时任务
			boolean allotRun = isAllotRun();
			if (!allotRun)
			{
				logger.error("不满足执行条件");
				return;
			}
			//step2 装载timerTask数据
			List<T> loadTask = loadTask();
			if (loadTask != null && !loadTask.isEmpty())
			{
				//step3将装载到的task提交到任务队列中
				addTask(loadTask);
				submitTask();
			} else
			{
				//若获取到的任务数为空，则直接完结此定时任务
				cancel();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally
		{
			//所有的定时任务都执行了之后则完结此定时任务
			doCancel();
		}
	}

	/**
	 * 装载task
	 * @return
	 * 赵玉柱
	 */
	public abstract List<T> loadTask();

	/**
	 * 判断当前时间是否允许执行此定时任务
	 * @return
	 * 赵玉柱
	 */
	public boolean isAllotRun()
	{
		Date strategyFrom = strategy.get(TimerConstant.STRATEGY_FROM);
		Date strategyTo = strategy.get(TimerConstant.STRATEGY_TO);
		if (strategyFrom == null && strategyTo == null)
		{
			//没有时间限制，可以执行
			return true;
		}
		long dateTo = getDateTime(strategyTo);
		long dateFrom = getDateTime(strategyFrom);
		long currTime = System.currentTimeMillis();
		if (strategyFrom == null || strategyTo == null)
		{
			//时间从或时间到至少有一个为空,限制开始时间或结束时间
			//获取当前时间对应今天的毫秒数
			currTime = currTime % (24 * 60 * 60 * 1000);
			if (strategyFrom != null)
			{
				return currTime <= dateTo;
			}
			if (strategyTo != null)
			{
				return currTime >= dateFrom;
			}
		}
		//判断时间起点与时间截止是否在同一天
		boolean isSameDay = dateFrom <= dateTo;
		if (isSameDay)
		{
			//若时间起点与时间截止在同一天，则要求当前时间在之间
			return currTime >= dateFrom && currTime <= dateTo;
		} else
		{
			//否则则需要大于起点或小于截止
			return currTime >= dateFrom || currTime <= dateTo;
		}
	}

	/**
	 * 根据date获取时间
	 * @param date
	 * @return
	 * 赵玉柱
	 */
	private long getDateTime(Date date)
	{
		if (date == null)
		{
			return 0;
		}
		return date.getTime();
	}

	/**
	 * 将任务添加到定时任务队列中
	 * @param tasks
	 * 赵玉柱
	 */
	public void addTask(List<T> tasks)
	{
		if (tasks == null)
		{
			return;
		}
		//过滤掉无效的task
		int size = tasks.size();
		//有效的task次数
		int effectTimes = 0;
		for (int i = 0; i < size; i++)
		{
			T task = tasks.get(i);
			if (effectTimes >= maxcount)
			{
				break;
			}
			if (task != null)
			{
				effectTimes++;
				this.tasks.add(task);
			}
		}
	}

	/**
	 * 提交任务
	 * 
	 * 赵玉柱
	 */
	public void submitTask()
	{
		if (tasks == null || tasks.isEmpty())
		{
			return;
		}
		for (T task : tasks)
		{
			logger.error("将任务" + task + "添加到任务中");
			//间隔一端时间后执行任务
			schedule(task, interval);
		}
	}

	/**
	 * 为了结束此定时任务，此方法采用定时任务的方式，不会阻塞主线程，用来判断是否所有的定时任务都已经执行过
	 * 
	 * 赵玉柱
	 */
	private void doCancel()
	{
		logger.error("执行取消消息");
		Thread thread = new Thread()
		{
			@Override
			public void run()
			{
				boolean allExec = false;
				while (!allExec)
				{
					if (tasks == null || tasks.isEmpty())
					{
						allExec = true;
						break;
					}
					int execSize = 0;
					int size = tasks.size();
					for (T task : tasks)
					{
						boolean exec = task.getStep() > 0;
						logger.error("任务" + task + (exec ? "已经执行" : "未执行"));
						if (exec)
						{
							execSize++;
							continue;
						} else
						{
							execSize = 0;
							logger.error("有任务不符合条件，进行下一次判断");
							break;
						}
					}
					if (size > execSize)
					{
						try
						{
							sleep(1000);
						} catch (InterruptedException e)
						{
							throw new RuntimeException("报错内容", e);
						}
					} else
					{
						allExec = true;
					}
				}
				logger.error("所有的定时任务都已经执行，进行取消操作");
				cancel();
			}
		};
		thread.start();
	}
}
