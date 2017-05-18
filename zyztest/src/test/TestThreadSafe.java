package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
/**
 * <p>标题： 测试是否是线程安全</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午4:14:55</p>
 * <p>类全名：test.TestThreadSafe</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestThreadSafe
{
	public static void main(String[] args)
	{
		testHashMap();
		//testHashTable();
		//testTime(1000000);
	}

	/**
	 * 
	 * x250-2
	 */
	private static void testTime(int size)
	{
		testHashMapTime(size);
		testHashTableTime(size);
	}

	/**
	 * @param size
	 * x250-2
	 */
	private static void testHashMapTime(int size)
	{
		Map<Integer,Integer> map = Collections.synchronizedMap(new HashMap<Integer,Integer>());
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++)
		{
			map.put(i, i);
		}
		long finishWrite = System.currentTimeMillis();
		System.out.println("HashMap完成put时间为：" + (finishWrite - start));
		Set<Integer> set = map.keySet();
		for (Integer key : set)
		{
			map.get(key);
		}
		long finishRead = System.currentTimeMillis();
		System.out.println("HashMap完成get时间为：" + (finishRead - finishWrite));
	}

	/**
	 * @param size
	 * x250-2
	 */
	private static void testHashTableTime(int size)
	{
		Map<Integer,Integer> map = new Hashtable<Integer,Integer>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < size; i++)
		{
			map.put(i, i);
		}
		long finishWrite = System.currentTimeMillis();
		System.out.println("HashTable完成put时间为：" + (finishWrite - start));
		Set<Integer> set = map.keySet();
		for (Integer key : set)
		{
			map.get(key);
		}
		long finishRead = System.currentTimeMillis();
		System.out.println("HashTable完成get时间为：" + (finishRead - finishWrite));
	}

	/**
	 * 测试说明，HashMap不是线程安全的，需要在使用时添加上同步锁synchronized(HashMap对象进行保护)
	 * @param size
	 * x250-2
	 */
	private static void testHashMap()
	{
		final Map<String,String> map = new HashMap<String,String>();
		Thread thread1 = new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 1000; i++)
				{
					synchronized (map)
					{
						map.put("key" + i, "value" + i);
					}
				}
				super.run();
			}
		};
		Thread thread2 = new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 1000; i++)
				{
					synchronized (map)
					{
						map.put("key-" + i, "value-" + i);
					}
				}
				super.run();
			}
		};
		thread1.start();
		thread2.start();
		try
		{
			Thread.sleep(2048);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		Set<String> set = map.keySet();
		for (String key : set)
		{
			System.out.println("key=" + key + "---value=" + map.get(key));
		}
		System.out.println("map-size=" + map.size() + "---key-size=" + set.size());
	}

	private static void testHashTable()
	{
		final Map<String,String> map = new Hashtable<String,String>();
		Thread thread1 = new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 1000; i++)
				{
					synchronized (map)
					{
						map.put("key" + i, "value" + i);
					}
				}
				super.run();
			}
		};
		Thread thread2 = new Thread()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 1000; i++)
				{
					synchronized (map)
					{
						map.put("key-" + i, "value-" + i);
					}
				}
				super.run();
			}
		};
		thread1.start();
		thread2.start();
		try
		{
			Thread.sleep(2048);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		Set<String> set = map.keySet();
		for (String key : set)
		{
			System.out.println("key=" + key + "---value=" + map.get(key));
		}
		System.out.println("map-size=" + map.size() + "---key-size=" + set.size());
	}
}
