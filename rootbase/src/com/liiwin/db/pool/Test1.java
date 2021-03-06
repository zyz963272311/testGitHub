package com.liiwin.db.pool;

import com.liiwin.db.Database;
/**
 * <p>标题： 数据库连接池的测试类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月29日 下午10:45:13</p>
 * <p>类全名：com.liiwin.db.pool.Test1</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test1 extends Thread
{
	@Override
	public void run()
	{
		DatabasePoolManager poolManager = DatabasePoolManager.getNewInstance();
		System.out.println("poolManager=" + poolManager);
		System.out.println("poolManager.pools" + DatabasePoolManager.pools);
		long start = System.currentTimeMillis();
		Database database = poolManager.getDatabase("ssm-test");
		System.out.println("初始化连接时长" + (System.currentTimeMillis() - start) + "ms");
		try
		{
			System.out.println("休眠10秒");
			Thread.sleep(10000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		int times = 3;
		Database[] dbs = new Database[times];
		for (int i = 0; i < times; i++)
		{
			start = System.currentTimeMillis();
			Database db = poolManager.getDatabase("ssm-test");
			dbs[i] = db;
			System.out.println("第" + i + "次连接时长为" + (System.currentTimeMillis() - start) + "ms");
		}
		try
		{
			System.out.println("休眠10秒");
			Thread.sleep(10000);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		start = System.currentTimeMillis();
		poolManager.close(database);
		System.out.println("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
		if (dbs != null)
		{
			for (Database db : dbs)
			{
				start = System.currentTimeMillis();
				poolManager.close(db);
				System.out.println("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
			}
		}
		for (int i = 0; i < times; i++)
		{
			start = System.currentTimeMillis();
			Database db = poolManager.getDatabase("ssm-test");
			dbs[i] = db;
			System.out.println("第" + i + "次连接时长为" + (System.currentTimeMillis() - start) + "ms");
		}
		System.out.println("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
		if (dbs != null)
		{
			for (Database db : dbs)
			{
				start = System.currentTimeMillis();
				poolManager.close(db);
				System.out.println("关闭DB时长" + (System.currentTimeMillis() - start) + "ms");
			}
		}
		System.out.println("test1执行完毕");
	}
}
