package xyz.zyzhu.spring.boot.model;

import java.io.Serializable;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月3日 下午8:58:13</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DataimpgDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataimpgDef implements Serializable
{
	private static final long	serialVersionUID	= 6135398017810960366L;
	private String				version;
	private String				tablename;
	private String				beforeExec;
	private String				columns;
	private String				remark;
	private Integer				savemode;

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		//setValue("version", version);
		this.version = version;
	}

	/**
	 * 获取执行前删除的语句
	 * @return
	 * 赵玉柱
	 */
	public String getBeforeExec()
	{
		return beforeExec;
	}

	public String getTablename()
	{
		return tablename;
	}

	public void setTablename(String tablename)
	{
		//setValue("tablename", tablename);
		this.tablename = tablename;
	}

	public String getColumns()
	{
		return columns;
	}

	public void setColumns(String columns)
	{
		//setValue("columns", columns);
		this.columns = columns;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		//setValue("remark", remark);
		this.remark = remark;
	}

	public Integer getSavemode()
	{
		return savemode;
	}

	public void setSavemode(Integer savemode)
	{
		//setValue("savemode", savemode);
		this.savemode = savemode;
	}

	public void setBeforeExec(String beforeExec)
	{
		//setValue("beforeExec", beforeExec);
		this.beforeExec = beforeExec;
	}
}
