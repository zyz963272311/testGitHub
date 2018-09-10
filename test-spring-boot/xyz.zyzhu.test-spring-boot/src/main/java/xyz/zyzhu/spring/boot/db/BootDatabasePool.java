package xyz.zyzhu.spring.boot.db;

import java.sql.SQLException;
import com.alibaba.druid.pool.DruidDataSource;
import com.liiwin.db.pool.IDatabasePool;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月10日 上午10:18:32</p>
 * <p>类全名：xyz.zyzhu.spring.boot.db.BootDatabasePool</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BootDatabasePool implements IDatabasePool<BootDatabase>
{
	//DB
	private BootDatabase db;
	//	//数据库连接活动状态
	//	private boolean								isActive		= false;
	//	//记录创建的总的线程
	//	private int									activeCount		= 0;
	//	//空闲DB
	//	private final List<BootDatabase>			freeDatabase	= new Vector<BootDatabase>();
	//	//活动DB
	//	private final List<BootDatabase>			activeDatabase	= new Vector<BootDatabase>();
	//将线程和连接绑定，保证事务能统一执行
	//	private static ThreadLocal<BootDatabase>	threadLocal		= new ThreadLocal<BootDatabase>();

	public BootDatabasePool(BootDatabase db)
	{
		super();
		this.db = db;
		init();
		checkPool();
	}

	/**
	 * 初始化
	 * 
	 * 赵玉柱
	 */
	public void init()
	{
		//		try
		//		{
		//			Connection conn = db.getConn();
		//			if (conn != null)
		//			{
		//				for (int i = 0; i < db.getInitConnections(); i++)
		//				{
		//					BootDatabase database = null;
		//					if (i == 0)
		//					{
		//						database = db;
		//					} else
		//					{
		//						database = new BootDatabase(db.getDatabaseName(), db.getIsWrite());
		//					}
		//					if (database.getConn() != null)
		//					{
		//						freeDatabase.add(database);
		//						activeCount++;
		//					}
		//				}
		//				isActive = true;
		//			}
		//		} catch (Exception e)
		//		{
		//			e.printStackTrace();
		//		}
	}

	@Override
	public BootDatabase getCurrentDatabase()
	{
		return db;
	}

	@Override
	public synchronized BootDatabase getDatabase()
	{
		String databaseName = db.getDatabaseName();
		boolean isWrite = db.getIsWrite();
		DruidConfig druidConfig = SpringBeanUtils.getBean(DruidConfig.class);
		DruidDataSource datasource = druidConfig.dataSourceByDbName(databaseName, isWrite);
		BootDatabase resultDb = new BootDatabase(datasource, databaseName);
		if (isWrite)
		{
			resultDb.setIsWrite();
		} else
		{
			resultDb.setIsRead();
		}
		return resultDb;
		//		BootDatabase database = null;
		//		try
		//		{
		//			if (activeCount < db.getMaxConnects())
		//			{
		//				if (freeDatabase.size() > 0)
		//				{
		//					database = freeDatabase.get(0);
		//					freeDatabase.remove(0);
		//				} else
		//				{
		//					database = newDatabase();
		//				}
		//			} else
		//			{
		//				wait(db.getConnTimeOut());
		//				database = (BootDatabase) getDatabase();
		//			}
		//			if (isValid(database))
		//			{
		//				threadLocal.set(database);
		//				activeDatabase.add(database);
		//				activeCount++;
		//			}
		//		} catch (Exception e)
		//		{
		//			e.printStackTrace();
		//			throw new RuntimeException("获取DB失败：" + e.getMessage());
		//		}
		//		return database;
	}

	@Override
	public synchronized void releaseDatabase(BootDatabase database) throws SQLException
	{
		database.close();
		//		if (isValid(database) && !(freeDatabase.size() > db.getMaxActiveConnections()))
		//		{
		//			freeDatabase.add((BootDatabase) database);
		//			activeDatabase.remove(database);
		//			activeCount--;
		//			threadLocal.remove();
		//			notifyAll();
		//		}
	}

	/**
	 * 销毁所有的链接
	 */
	@Deprecated
	@Override
	public synchronized void destroy()
	{
		//		for (int i = freeDatabase.size() - 1; i >= 0; i--)
		//		{
		//			BootDatabase database = freeDatabase.remove(i);
		//			try
		//			{
		//				if (isValid(database))
		//				{
		//					database.close();
		//				}
		//			} catch (Exception e)
		//			{
		//				e.printStackTrace();
		//			}
		//		}
		//		for (int i = activeDatabase.size() - 1; i >= 0; i--)
		//		{
		//			BootDatabase database = activeDatabase.remove(i);
		//			try
		//			{
		//				if (isValid(database))
		//				{
		//					database.close();
		//				}
		//			} catch (Exception e)
		//			{
		//				e.printStackTrace();
		//			}
		//		}
		//		isActive = false;
		//		activeCount = 0;
	}

	@Override
	public boolean isActive()
	{
		return true;
	}

	@Override
	public void checkPool()
	{
		//		if (db.isCheckPool())
		//		{
		//			new Timer().schedule(new TimerTask()
		//			{
		//				@Override
		//				public void run()
		//				{
		//					System.out.println("空闲DB链接数量：" + freeDatabase.size());
		//					System.out.println("活动DB线程数量：" + activeDatabase.size());
		//					System.out.println("总DB线程数量：" + activeCount);
		//				}
		//			}, db.getLazyCheck(), db.getPeriodCheck());
		//		}
	}
}
