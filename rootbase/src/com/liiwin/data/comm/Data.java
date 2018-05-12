package com.liiwin.data.comm;

import java.io.Serializable;
import java.util.List;
/**
 * <p>标题： 导入导出数据</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午4:40:39</p>
 * <p>类全名：com.liiwin.data.Data</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Data implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private DataDef				datadef;					//导入导出定义
	private String				path;						//路径
	private Data				parent;					//父节点
	private String				tablename;					//表名
	private List<Object>		tableDatas;				//表数据
	private List<Data>			subDatas;					//子表数据

	public DataDef getDatadef()
	{
		return datadef;
	}

	public void setDatadef(DataDef datadef)
	{
		this.datadef = datadef;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public Data getParent()
	{
		return parent;
	}

	public void setParent(Data parent)
	{
		this.parent = parent;
	}

	public String getTablename()
	{
		return tablename;
	}

	public void setTablename(String tablename)
	{
		this.tablename = tablename;
	}

	public List<Object> getTableDatas()
	{
		return tableDatas;
	}

	public void setTableDatas(List<Object> tableDatas)
	{
		this.tableDatas = tableDatas;
	}

	public List<Data> getSubDatas()
	{
		return subDatas;
	}

	public void setSubDatas(List<Data> subDatas)
	{
		this.subDatas = subDatas;
	}
}
