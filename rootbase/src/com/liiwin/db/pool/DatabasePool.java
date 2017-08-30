package com.liiwin.db.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import com.liiwin.db.Database;
/**
 * <p>标题： 数据库连接池</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月29日 上午10:05:07</p>
 * <p>类全名：com.liiwin.db.ConnectionPool</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DatabasePool implements IDatabasePool
{
	//DB
	private Database						db;
	//数据库连接活动状态
	private boolean							isActive		= false;
	//记录创建的总的线程
	private int								activeCount		= 0;
	//空闲DB
	private final List<Database>			freeDatabase	= new Vector<Database>();
	//活动DB
	private final List<Database>			activeDatabase	= new Vector<Database>();
	//将线程和连接绑定，保证事务能统一执行
	private static ThreadLocal<Database>	threadLocal		= new ThreadLocal<Database>();

	public DatabasePool(Database db)
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
		try
		{
			Connection conn = db.getConn();
			if (conn != null)
			{
				for (int i = 0; i < db.getInitConnections(); i++)
				{
					Database database = null;
					if (i == 0)
					{
						database = db;
					} else
					{
						database = new Database(db.getDatabaseName());
					}
					if (database.getConn() != null)
					{
						freeDatabase.add(database);
						activeCount++;
					}
				}
				isActive = true;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Database getCurrentDatabase()
	{
		Database currDB = threadLocal.get();
		if (!isValid(currDB))
		{
			currDB = getDatabase();
		}
		return currDB;
	}

	@Override
	public synchronized Database getDatabase()
	{
		Database database = null;
		try
		{
			if (activeCount < db.getMaxConnects())
			{
				if (freeDatabase.size() > 0)
				{
					database = freeDatabase.get(0);
					//					if (database.getConn() != null)
					//					{
					//						threadLocal.set(database);
					//					}
					freeDatabase.remove(0);
				} else
				{
					database = newDatabase();
				}
			} else
			{
				wait(db.getConnTimeOut());
				database = getDatabase();
			}
			if (isValid(database))
			{
				threadLocal.set(database);
				activeDatabase.add(database);
				activeCount++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("获取DB失败：" + e.getMessage());
		}
		return database;
	}

	/**
	 * 创建一个新连接
	 * @return
	 * 赵玉柱
	 */
	private Database newDatabase()
	{
		if (getConnectionCount() < db.getMaxConnects())
		{
			Database database = null;
			try
			{
				if (db == null)
				{
					throw new RuntimeException("ConnectionPool.newDatabase的db不可为空");
				}
				if (!isValid(db))
				{
					database = new Database(db.getDatabaseName());
					db = database;
				} else
				{
					database = new Database(db.getDatabaseName());
				}
				if (!isValid(db))
				{
					throw new RuntimeException("数据库连接失败" + db.getDatabaseName());
				}
				database = new Database(db.getDatabaseName());
				activeDatabase.add(database);
				activeCount++;
			} catch (Exception e)
			{
				e.printStackTrace();
				throw new RuntimeException("创建新数据库失败" + e.getMessage());
			}
			return database;
		} else
		{
			try
			{
				wait(db.getConnTimeOut());
				return newDatabase();
			} catch (InterruptedException e)
			{
				throw new RuntimeException("报错内容", e);
			}
		}
	}

	@Override
	public synchronized void releaseDatabase(Database database) throws SQLException
	{
		if (isValid(database) && !(freeDatabase.size() > db.getMaxActiveConnections()))
		{
			freeDatabase.add(database);
			activeDatabase.remove(database);
			activeCount--;
			threadLocal.remove();
			notifyAll();
		}
	}

	/**
	 * 销毁所有的链接
	 */
	@Override
	public synchronized void destroy()
	{
		for (int i = freeDatabase.size() - 1; i >= 0; i--)
		{
			Database database = freeDatabase.remove(i);
			try
			{
				if (isValid(database))
				{
					database.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		for (int i = activeDatabase.size() - 1; i >= 0; i--)
		{
			Database database = activeDatabase.remove(i);
			try
			{
				if (isValid(database))
				{
					database.close();
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		isActive = false;
		activeCount = 0;
	}

	@Override
	public boolean isActive()
	{
		return isActive;
	}

	@Override
	public void checkPool()
	{
		if (db.isCheckPool())
		{
			new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					System.out.println("空闲DB链接数量：" + freeDatabase.size());
					System.out.println("活动DB线程数量：" + activeDatabase.size());
					System.out.println("总DB线程数量：" + activeCount);
				}
			}, db.getLazyCheck(), db.getPeriodCheck());
		}
	}

	/**
	 * 当前数据库对象是否可用
	 * @param database
	 * @return
	 * 赵玉柱
	 */
	private boolean isValid(Database database)
	{
		try
		{
			if (database == null)
			{
				return false;
			}
			Connection conn = database.getConn();
			if (conn == null || conn.isClosed())
			{
				return false;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("报错信息：" + e.getMessage());
		}
		return true;
	}

	private int getConnectionCount()
	{
		return activeDatabase.size() + freeDatabase.size();
	}
}
