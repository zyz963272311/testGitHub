package xyz.zyzhu.spring.boot.datafile.fileimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.liiwin.db.DatabaseCacheUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.domain.DataImportDetail;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
/**
 * <p>标题： 默认的数据导入</p>
 * <p>功能： </p>
 * <p>所属模块： 默认的数据导入</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月3日 下午8:47:14</p>
 * <p>类全名：xyz.zyzhu.spring.boot.datafile.fileimport.DefaultDataImport</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DefaultDataImport implements DataImport
{
	Map<String,Object> params;

	public DefaultDataImport(Map<String,Object> params)
	{
		if (params == null)
		{
			this.params = new HashMap<>();
		} else
		{
			this.params = params;
		}
	}

	@Override
	public String dataImport()
	{
		List<BootDatabase> dbs = new ArrayList<>();
		List<DataImportDetail> details = null;
		try
		{
			//1、获取导入信息对象
			details = getImportDetails();
			//2、数据入库操作
			//2.1、所有数据入库前操作
			beforeDetailsImport(details, dbs);
			if (details == null || details.isEmpty())
			{
				return null;
			}
			for (DataImportDetail detail : details)
			{
				BootDatabase db = getDb(detail, dbs);
				//2.2、单条数据入库前操作
				beforeDetailImport(detail, db);
				//2.3、执行数据入库
				importData(detail, db);
				//3、数据入库操作之后
				//3、1单条数据入库后
				afterDetailImport(detail, db);
			}
			//3.2、所有数据入库后的操作
			afterDetailsImport(details, dbs);
		} finally
		{
			//4、事务提交
			//4.1、所有db的事务提交之前
			beforeDetailsTrans(details, dbs);
			transAndCloseDb(details, dbs);
		}
		return null;
	}

	/**
	 * 根据数据导入文件或其他获取导入信息
	 * @return
	 * 赵玉柱
	 */
	public abstract List<DataImportDetail> getImportDetails();

	/**
	 * 执行数据导入
	 * @param detail
	 * @param db
	 * 赵玉柱
	 */
	public void importData(DataImportDetail detail, BootDatabase db)
	{
	}

	/**
	 * 
	 * @param detail
	 * @param dbs
	 * @return
	 * 赵玉柱
	 */
	private BootDatabase getDb(DataImportDetail detail, List<BootDatabase> dbs)
	{
		String tablename = detail.getTablename();
		String dbName = DatabaseCacheUtils.getDbNameByTableName(tablename);
		if (dbName == null)
		{
			throw new RuntimeException("根据表名未获取到对应的数据库名称:" + tablename);
		}
		for (BootDatabase db : dbs)
		{
			StrUtil.equals(dbName, db.getDatabaseName());
			return db;
		}
		BootDatabase db = BootDatabasePoolManager.getDatabase(dbName, true);
		if (db == null)
		{
			throw new RuntimeException("根据db名称未获取到对应的DB:" + dbName);
		}
		db.beginTrans();
		dbs.add(db);
		return db;
	}

	/**
	 * 数据插入之前
	 * @param details
	 * 赵玉柱
	 */
	protected void beforeDetailsImport(List<DataImportDetail> details, List<BootDatabase> dbs)
	{
	}

	/**
	 * 数据插入之前，主要用于生成内码，外码，数据关联等操作，主要用于数据插入之后的后续处理
	 * @param detail
	 * 赵玉柱
	 */
	protected void beforeDetailImport(DataImportDetail detail, BootDatabase db)
	{
	}

	/**
	 * 数据插入到表之后，此时事务已经开启，但是还未提交事务，主要用于数据插入之后的后续处理
	 * @param detail
	 * @param dbs
	 * 赵玉柱
	 */
	protected void afterDetailsImport(List<DataImportDetail> details, List<BootDatabase> dbs)
	{
	}

	/**
	 * 数据插入到表之后，此时还未提交事务
	 * @param detail
	 * @param db
	 * 赵玉柱
	 */
	protected void afterDetailImport(DataImportDetail detail, BootDatabase db)
	{
	}

	/**
	 * 事务提交之前的操作
	 * @param details
	 * @param dbs
	 * 赵玉柱
	 */
	protected void beforeDetailsTrans(List<DataImportDetail> details, List<BootDatabase> dbs)
	{
	}

	/**
	 * 事务提交之前的操作
	 * @param detail
	 * @param db
	 * 赵玉柱
	 */
	protected void beforeDetailTrans(DataImportDetail detail, BootDatabase db)
	{
	}

	/**
	 * 挡某一个DB在提交事务抛出异常之后
	 * @param detail
	 * @param db
	 * @param e
	 * 赵玉柱
	 */
	protected void afterDbDetailTrensException(List<DataImportDetail> detail, BootDatabase db, Exception e)
	{
	}

	/**
	 * 提交事务之后，此处不可抛出异常
	 * @param detail
	 * @param db
	 * 赵玉柱
	 */
	protected void afterDetailTrans(DataImportDetail detail, BootDatabase db)
	{
	}

	/**
	 * 
	 * @param dbs
	 * 赵玉柱
	 */
	private void transAndCloseDb(List<DataImportDetail> details, List<BootDatabase> dbs)
	{
		Map<String,BootDatabase> dbMap = new HashMap<>();
		Map<String,List<DataImportDetail>> detailsMap = new HashMap<>();
		if (details != null && !details.isEmpty() && dbs != null && !dbs.isEmpty())
		{
			for (DataImportDetail detail : details)
			{
				String tablename = detail.getTablename();
				String dbname = DatabaseCacheUtils.getDbNameByTableName(tablename);
				List<DataImportDetail> list = detailsMap.get(dbname);
				if (list == null)
				{
					list = new ArrayList<>();
					detailsMap.put(dbname, list);
				}
				list.add(detail);
			}
			for (Entry<String,List<DataImportDetail>> entry : detailsMap.entrySet())
			{
				String dbname = entry.getKey();
				List<DataImportDetail> tempDetails = entry.getValue();
				BootDatabase db = dbMap.get(dbname);
				if (db == null)
				{
					for (BootDatabase d : dbs)
					{
						if (dbname.equals(d.getDatabaseName()))
						{
							db = d;
							dbMap.put(dbname, db);
						}
					}
				}
				try
				{
					for (DataImportDetail detail : tempDetails)
					{
						//4.2、单条记录事务提交前操作
						beforeDetailTrans(detail, db);
					}
					db.commit();
				} catch (Exception e)
				{
					//4.3、某一个db对应的一批数据提交事务出现异常
					afterDbDetailTrensException(tempDetails, db, e);
					db.rollback(true);
				} finally
				{
					try
					{
						BootDatabasePoolManager.close(db);
					} catch (Exception e)
					{
					}
				}
			}
		}
	}

	public Map<String,Object> getParams()
	{
		return params;
	}
}
