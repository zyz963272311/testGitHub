package map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * <p>标题：多线程Map不安全 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月15日 下午3:29:57</p>
 * <p>类全名：map.ThreadMap</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ThreadMap
{
	//最大线程数
	public static final int		THREAD_COUNT	= 1;
	//Map记录数
	public static final int		MAP_SIZE		= 1000;
	//等待时间
	public static final int		EXECOTE_MILLS	= 1000;
	public static final int[]	KEYS			= new int[100];

	public static void main(String[] args) throws Exception
	{
		//初始化
		Random random = new Random();
		for (int i = 0; i < KEYS.length; i++)
		{
			KEYS[i] = random.nextInt();
			long start = System.currentTimeMillis();
			Thread[] thread = new Thread[THREAD_COUNT];
			for (int j = 0; j < THREAD_COUNT; ++j)
			{
				thread[j] = new SynThread();
				thread[j].start();
			}
			Thread.sleep(EXECOTE_MILLS);
			long sum = 0;
			for (int j = 0; j < THREAD_COUNT; ++j)
			{
				sum += thread[j].getClass().getDeclaredField("count").getLong(thread[j]);
			}
			long millsCost = System.currentTimeMillis() - start;
			System.out.println(sum + "(" + (millsCost) + ")ms");
			System.exit(0);
		}
	}

	public static void fillMap(Map<Integer,Integer> map)
	{
		Random random = new Random();
		for (int i = 0; i < MAP_SIZE; i++)
		{
			map.put(random.nextInt(), random.nextInt());
		}
	}
}
class SynThread extends Thread
{
	private static Map<Integer,Integer>	map		= new HashMap<Integer,Integer>();
	public long							count	= 0;
	static
	{
		ThreadMap.fillMap(map);
	}

	@Override
	public void run()
	{
		for (;;)
		{
			int index = (int) (count % ThreadMap.KEYS.length);
			synchronized (SynThread.class)
			{
				map.get(ThreadMap.KEYS[index]);
			}
			++count;
		}
	}
}
class LockThread extends Thread
{
	private static Map<Integer,Integer>	map		= new HashMap<Integer,Integer>();
	private static Lock					lock	= new ReentrantLock();
	public long							count	= 0;
	static
	{
		ThreadMap.fillMap(map);
	}

	@Override
	public void run()
	{
		for (;;)
		{
			int index = (int) (count % ThreadMap.KEYS.length);
			lock.lock();
			map.get(ThreadMap.KEYS[index]);
			lock.unlock();
			++count;
		}
	}
}
