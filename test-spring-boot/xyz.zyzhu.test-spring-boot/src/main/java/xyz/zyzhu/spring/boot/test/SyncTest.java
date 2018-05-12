package xyz.zyzhu.spring.boot.test;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年1月28日 下午12:43:57</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.SyncTest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SyncTest
{
	private final static List<String>	list	= new ArrayList<>();
	private final static int			len		= 10;

	public static void main(String[] args) throws InterruptedException
	{
		init();
		ThreadClear threadClear = new SyncTest().new ThreadClear();
		threadClear.start();
		iter();
	}

	private static void iter() throws InterruptedException
	{
		synchronized (list)
		{
			int listLen = list.size();
			for (int i = 0; i < listLen; i++)
			{
				Thread.sleep(1000);
				System.out.println("当前时间" + System.currentTimeMillis() + "第" + i + "个---\t" + list.get(i));
			}
		}
	}

	private static void init()
	{
		synchronized (list)
		{
			for (int i = 0; i < len; i++)
			{
				System.out.println("当前时间" + System.currentTimeMillis());
				list.add(i + "+++++++++++");
			}
		}
	}

	private class ThreadClear extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				sleep(4000);
			} catch (InterruptedException e)
			{
				throw new RuntimeException("报错内容", e);
			}
			clear();
		}

		private void clear()
		{
			System.out.println("当前时间" + System.currentTimeMillis() + "执行clear语句，未获取到锁");
			synchronized (list)
			{
				System.out.println("当前时间" + System.currentTimeMillis() + "执行clear语句，已经获取到锁");
				System.out.println("list大小===clear之前" + list.size());
				list.clear();
				System.out.println("list大小clear之后" + list.size());
			}
		}
	}
}
