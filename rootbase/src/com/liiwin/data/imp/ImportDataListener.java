package com.liiwin.data.imp;

import java.util.HashMap;
import java.util.Map;
import com.liiwin.data.comm.Datalistener;
import com.liiwin.db.Database;
/**
 * <p>标题： 导入监听</p>
 * <p>功能： </p>
 * <p>所属模块： rootbase</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月8日 上午9:10:22</p>
 * <p>类全名：com.liiwin.data.imp.ImportDataListener</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ImportDataListener<D extends ImportData,DEF extends ImportDataDef> extends Datalistener
{
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
	 * 提交事务之前
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
	public void afterTransCommited(D data, Database db)
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
