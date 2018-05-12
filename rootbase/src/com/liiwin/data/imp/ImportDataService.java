package com.liiwin.data.imp;

import java.util.HashMap;
import java.util.Map;
import com.liiwin.data.comm.DataService;
import com.liiwin.db.Database;
import com.liiwin.db.pool.DatabasePoolManager;
import com.liiwin.doc.Document;
import com.liiwin.doc.utils.DocumentUtils;
/**
 * <p>标题： 数据导入Service</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午5:26:15</p>
 * <p>类全名：com.liiwin.data.imp.ImportDataService</p>
 * 作者：赵玉柱
 * 初审：`
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class ImportDataService<D extends ImportData,DEF extends ImportDataDef> extends DataService
{
	protected ImportDataListener<ImportData,ImportDataDef>	impListener;	//导入监听
	protected DEF											impDef;		//导入定义

	/**
	 * @param datacode
	 */
	public ImportDataService(String datacode)
	{
		super(datacode);
	}

	public void importData(String context)
	{
		Database db = null;
		D data = null;
		try
		{
			//获取db
			db = DatabasePoolManager.getNewInstance().getDatabase("zyztext");
			//1.加载需要导入的数据
			data = loadImpData(context, db);
		} catch (Exception e)
		{
			//异常了
			throw new RuntimeException(onException(e, data, db));
		} finally
		{
			if (db != null)
			{
				//如果db在事务中
				if (db.inTrans())
				{
					//提交事务之前
					beforeTransCommit(data, db);
					try
					{
						//事务提交
						db.commit();
						//提交事务成功
						afterTransCommitedSuccess(data, db);
					} catch (Exception e)
					{
						//提交事务失败
						afterTransCommitedFailed(data, db);
					}
				}
				try
				{
					//db关闭之前
					beforeDbClose(data, db);
				} finally
				{
					try
					{
						//关闭db
						DatabasePoolManager.getNewInstance().close(db);
					} finally
					{
						//关闭db之后
						afterDbClosed(data);
					}
				}
			}
		}
	}

	@Override
	public DEF loadDataDef(Database db)
	{
		return null;
	}

	/**
	 * 加载导入定义数据
	 * @return
	 * 赵玉柱
	 */
	public D loadImpData(String context, Database db)
	{
		impDef = loadDataDef(db);
		if (impDef == null)
		{
			throw new RuntimeException("根据编号[" + getDatacode() + "]未查询到导入定义");
		}
		Integer type = impDef.getType();
		if (type == null)
		{
			type = new Integer(1);
		}
		Document doc = null;
		switch (type)
		{
		case 1:
			//xml
			doc = DocumentUtils.getXmlUtils().buildDocumentByContent(context);
			break;
		default:
			throw new RuntimeException("暂不支持的类型");
		}
		return null;
	}

	/**
	 * 组装导入监听
	 * @param impDef
	 * @return
	 * 赵玉柱
	 */
	protected ImportDataListener<D,DEF> loadImportDataListener(DEF impDef)
	{
		return new ImportDataListener<>();
	}

	/**********************************下面是Listener的方法************************/
	/**
	 * 准备导入表数据之前
	 * @param data
	 * @param table
	 * @param db
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> beforeTablePrepared(D data, String table, Database db)
	{
		return new HashMap<>();
	}

	/**
	 * 表数据准备方法
	 * @param data 导入的数据
	 * @param tableData 表数据
	 * @param table 表名
	 * @param db 传入的开启事务的db
	 * @return
	 * 赵玉柱
	 */
	public Map<String,Object> onTableDataPrepared(D data, Map<String,Object> tableData, String table, Database db)
	{
		return tableData;
	}

	/**
	 * 表更新之后
	 * @param Data 导入的数据
	 * @param tableData 表数据
	 * @param table 表名
	 * @param db 传入的开启事务的db
	 * 赵玉柱
	 */
	public void onTableUpdated(D Data, Map<String,Object> tableData, String table, Database db)
	{
	}

	/**
	 * 导入数据准备完成
	 * @param data data
	 * @param db 传入的开启事务的db
	 * 赵玉柱
	 */
	public void onDataPrepared(D data, Database db)
	{
	}

	/**
	 * 数据更新之后，未提交事务
	 * @param data
	 * @param db
	 * 赵玉柱
	 */
	public void onDataUpdated(D data, Database db)
	{
	}

	/**
	 * 提交事务之前 此处不允许抛出异常
	 * @param data
	 * @param db
	 * 赵玉柱
	 */
	public void beforeTransCommit(D data, Database db)
	{
	}

	/**
	 * 发生异常时，可以自定义组装异常
	 * @param thr
	 * @param data
	 * @param db
	 * @return
	 * 赵玉柱
	 */
	public Throwable onException(Throwable thr, D data, Database db)
	{
		return thr;
	}

	/**
	 * 提交事务之后 ，此时db未关闭
	 * @param data
	 * @param db
	 * 赵玉柱
	 */
	public void afterTransCommitedSuccess(D data, Database db)
	{
	}

	public void afterTransCommitedFailed(D data, Database db)
	{
	}

	/**
	 * 关闭db之前
	 * @param data
	 * @param db
	 * 赵玉柱
	 */
	public void beforeDbClose(D data, Database db)
	{
	}

	/**
	 * db关闭之后
	 * @param data
	 * 赵玉柱
	 */
	public void afterDbClosed(D data)
	{
	}
}
