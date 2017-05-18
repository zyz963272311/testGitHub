package future;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年3月6日 上午10:14:03</p>
 * <p>类全名：future.TestThread</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestThread
{
	public static void main(String[] args)
	{
		//创建runnable对象
		Callable<Map<String,Object>> callable = new MyCallable();
		//创建FutureTask对象
		FutureTask<Map<String,Object>> futureTask = new FutureTask<>(callable);
		for (int i = 0; i < 100; i++)
		{
			//创建线程
			Thread thread = new Thread(futureTask);
			//线程就绪状态
			thread.start();
		}
		System.out.println("主线程启动完毕");
	}
}
