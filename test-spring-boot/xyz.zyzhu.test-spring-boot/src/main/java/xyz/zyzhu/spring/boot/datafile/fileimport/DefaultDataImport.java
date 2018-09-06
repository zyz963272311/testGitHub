package xyz.zyzhu.spring.boot.datafile.fileimport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.liiwin.db.DatabaseCacheUtils;
import com.liiwin.utils.MapUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.domain.DataImportDetail;
import xyz.zyzhu.spring.boot.datafile.utils.DataFileUtils;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.DataimpgDef;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
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
	Map<String,Object>	params;
	String				errMessage;

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
		} catch (Exception e)
		{
			String message = e.getMessage();
			message = StrUtil.isStrTrimNull(message) ? "数据导入异常" : message;
			setErrMessage(message);
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
		if (detail == null)
		{
			return;
		}
		DataimpgDef impgDef = detail.getImpgDef();
		String beforeExec = impgDef.getBeforeExec();
		//先删除数据
		beforeExec(beforeExec, db);
		//数据入库
		execDataSave(detail, db);
	}

	/**
	 * 执行数据入库
	 * @param detail
	 * @param db
	 * 赵玉柱
	 */
	protected void execDataSave(DataImportDetail detail, BootDatabase db)
	{
		List<Map<String,Object>> impDatas = detail.getImpDatas();
		if (impDatas == null || impDatas.isEmpty())
		{
			return;
		}
		DataimpgDef def = detail.getImpgDef();
		String tablename = detail.getTablename();
		boolean isInsert = DataFileUtils.isImpdefInsert(def);
		boolean isUpdate = DataFileUtils.isImpdefUpdate(def);
		if (isInsert)
		{
			//执行数据插入
			db.insertTableList(tablename, impDatas);
		} else if (isUpdate)
		{
			//执行数据更新
			List<String> tablePrimaryCols = ModelUtils.getTablePrimaryCols(tablename);
			String columns = StrUtil.trim(detail.getColumns());
			List<String> tableColumns = new ArrayList<>();
			if (StrUtil.equals(columns, "*"))
			{
				tableColumns = ModelUtils.getTableColumnByTableName(tablename);
			} else
			{
				String[] columnArray = StrUtil.split(columns, ',');
				for (String column : columnArray)
				{
					tableColumns.add(column);
				}
			}
			if (tablePrimaryCols != null && !tablePrimaryCols.isEmpty())
			{
				StringBuffer sqlSb = new StringBuffer("update ").append(tablename).append(" set ");
				int length = sqlSb.length();
				for (String column : tableColumns)
				{
					if (tablePrimaryCols.contains(column))
					{
						sqlSb.append(column).append("=:").append(column).append(" ").append(", ");
					}
				}
				if (sqlSb.length() > length)
				{
					//去掉最后的", "
					sqlSb.setLength(sqlSb.length() - 2);
				}
				sqlSb.append(" where ");
				length = sqlSb.length();
				for (String column : tablePrimaryCols)
				{
					if (tablePrimaryCols.contains(column))
					{
						sqlSb.append(column).append("=:").append(column).append(" ").append(" and ");
					}
				}
				if (sqlSb.length() > length)
				{
					//去掉最后的", "
					sqlSb.setLength(sqlSb.length() - 4);
				}
				for (Map<String,Object> data : impDatas)
				{
					db.execSqlForWrite(sqlSb.toString(), data);
				}
			}
		} else
		{
			//执行数据存盘  先查询再插入
			//1.进行数据查询
			List<String> primaryCols = ModelUtils.getTablePrimaryCols(tablename);
			List<String> tableColumns = ModelUtils.getTableColumnByTableName(tablename);
			if (primaryCols != null && !primaryCols.isEmpty() && tableColumns != null && !tableColumns.isEmpty())
			{
				String limit = "@";
				Map<String,List<Map<String,Object>>> mapList = MapUtil.buildMapByList1(impDatas, primaryCols, limit);
				Set<String> keySet = mapList.keySet();
				List<Map<String,Object>> queryList = new ArrayList<>();
				StringBuffer sqlSb = new StringBuffer("select * from " + tablename + " where ");
				for (String primaryCol : primaryCols)
				{
					sqlSb.append(primaryCol).append("=:").append(primaryCol).append(" and ");
				}
				sqlSb.setLength(sqlSb.length() - 4);
				for (String keyValue : keySet)
				{
					String[] split = StrUtil.split(keyValue, limit.charAt(0));
					List<Object> keyList = new ArrayList<>();
					for (String key : split)
					{
						keyList.add(key);
					}
					Map<String,Object> map = db.getMapFromSqlByListParam(sqlSb.toString(), keyList);
					queryList.add(map);
				}
				Map<String,List<Map<String,Object>>> queryListMap = MapUtil.buildMapByList1(queryList, primaryCols, limit);
				//2.进行数据分类与数据组装
				List<Map<String,Object>> updateList = new ArrayList<>();
				List<Map<String,Object>> insertList = new ArrayList<>();
				StringBuffer updateSb = new StringBuffer("update " + tablename + " set ");
				for (Entry<String,List<Map<String,Object>>> mepEntry : mapList.entrySet())
				{
					String key = mepEntry.getKey();
					Map<String,Object> value = mepEntry.getValue().get(0);
					List<Map<String,Object>> list = queryListMap.get(key);
					if (list != null && !list.isEmpty())
					{
						Map<String,Object> map = list.get(0);
						MapUtil.copyMap(value, map, false);
						updateList.add(map);
					} else
					{
						insertList.add(value);
					}
				}
				//3.进行数据更新
				//3.1 进行数据更新
				if (!updateList.isEmpty())
				{
					for (String tableCol : tableColumns)
					{
						updateSb.append(tableCol).append("=:").append(tableCol).append(" , ");
					}
					updateSb.setLength(updateSb.length() - 2);
					updateSb.append(" where ");
					for (String primaryCol : primaryCols)
					{
						updateSb.append(primaryCol).append("=:").append(primaryCol).append(" and ");
					}
					updateSb.setLength(updateSb.length() - 4);
					for (Map<String,Object> updateMap : updateList)
					{
						db.execSqlForWrite(updateSb.toString(), updateMap);
					}
				}
				//进行数据插入
				if (insertList != null && !insertList.isEmpty())
				{
					db.insertTableList(tablename, insertList);
				}
			}
		}
	}

	/**
	 * 
	 * 执行数据导入前的数据删除
	 * @param beforeExec
	 * @param db
	 * 赵玉柱
	 */
	protected void beforeExec(String beforeExec, BootDatabase db)
	{
		if (StrUtil.isNoStrTrimNull(beforeExec))
		{
			db.execSqlForWrite(beforeExec);
		}
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
		//单个数据库中，若出现异常，不会影响其他的db进行事务提交
		boolean isSuccess = isSuccess();
		boolean hasException = false;
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
					if (isSuccess)
					{
						db.commit();
					}
				} catch (Exception e)
				{
					//4.3、某一个db对应的一批数据提交事务出现异常
					afterDbDetailTrensException(tempDetails, db, e);
					hasException = true;
				} finally
				{
					db.rollback(hasException || isSuccess);
					hasException = false;
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

	protected void setErrMessage(String errMessage)
	{
		this.errMessage = errMessage;
	}

	protected String getErrMessage()
	{
		return this.errMessage;
	}

	public boolean isSuccess()
	{
		String errMessage2 = getErrMessage();
		return StrUtil.isStrTrimNull(errMessage2);
	}

	public boolean isError()
	{
		return !isSuccess();
	}
}
