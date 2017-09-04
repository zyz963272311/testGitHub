package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/**
 * <p>标题：测试多线程的future类 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月19日 下午4:00:30</p>
 * <p>类全名：test.TestFuture</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestFuture
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, ExecutionException
	{
		System.out.println("=========任务开始========");
		Date date = new Date();
		int taskSize = 100;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		List<Future> list = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++)
		{
			Callable c = new MyCallable(i + "");
			Future f = pool.submit(c);
			list.add(f);
		}
		pool.shutdown();
		for (Future f : list)
		{
			System.out.println(">>>" + f.get().toString());
		}
		Date date2 = new Date();
		System.out.println("=====time程序结束=====\n=====运行时间：" + (date2.getTime() - date.getTime()) + "毫秒" + date2.getTime());
		System.out.println("=====date程序结束=====\n=====运行时间：" + (date2.getDate() - date.getDate()) + "毫秒" + date2.getDate());
	}
}
class MyCallable implements Callable<Object>
{
	String	taskNum;

	public MyCallable(String taskNum)
	{
		this.taskNum = taskNum;
	}

	@Override
	public Object call() throws Exception
	{
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Thread.sleep(1000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
	}
}