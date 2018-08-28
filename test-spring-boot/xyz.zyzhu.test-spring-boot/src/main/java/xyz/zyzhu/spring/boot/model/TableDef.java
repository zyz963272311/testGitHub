package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午4:47:23</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.Table</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
import xyz.zyzhu.spring.boot.annotation.FieldDef;
@Table(name = "tb")
public class TableDef extends BasModel
{
	private static final long	serialVersionUID	= -7915131937401192504L;
	@FieldDef()
	private String				tbname;
	@FieldDef()
	private String				dbname;
	@FieldDef()
	private String				tblang1name;

	public String getTbname()
	{
		return tbname;
	}

	public void setTbname(String tbname)
	{
		setValue("tbname", tbname);
	}

	public String getDbname()
	{
		return dbname;
	}

	public void setDbname(String dbname)
	{
		setValue("dbname", dbname);
	}

	public String getTblang1name()
	{
		return tblang1name;
	}

	public void setTblang1name(String tblang1name)
	{
		setValue("tblang1name", tblang1name);
	}
}
