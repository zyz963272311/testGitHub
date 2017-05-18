package com.liiwin.db;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午3:17:58</p>
 * <p>类全名：db.ReadDataset</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface ReadDataset
{
	public void setTablename(String tablename);

	public String getTableName();

	public Object[][] loadDatasetValues();

	public DataColumn[] getDatacolumns();

	public void setDatacolumns(DataColumn[] tcs);
}
