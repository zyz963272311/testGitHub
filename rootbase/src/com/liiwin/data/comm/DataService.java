package com.liiwin.data.comm;

import com.liiwin.db.Database;
/**
 * <p>标题： 导入导出定义Service</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午5:18:40</p>
 * <p>类全名：com.liiwin.data.comm.DataService</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DataService<DEF extends DataDef>
{
	//导入导出定义编号
	private String	datacode;

	public DataService(String datacode)
	{
		this.datacode = datacode;
	}

	/**
	 * 获取导入导出定义
	 * @return
	 * 赵玉柱
	 */
	public abstract DEF loadDataDef(Database db);

	public String getDatacode()
	{
		return datacode;
	}

	public void setDatacode(String datacode)
	{
		this.datacode = datacode;
	}
}
