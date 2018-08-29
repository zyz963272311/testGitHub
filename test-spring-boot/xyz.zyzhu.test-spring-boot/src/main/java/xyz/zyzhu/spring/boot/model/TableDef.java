package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
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
