package _db.base;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 上午11:35:58</p>
 * <p>类全名：_db.ColumnType</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public enum ColumnType
{
	BOOLEAN("boolean", 1), BYTE("byte", 8), SHORT("short", 16), INT("int", 32), LONG("long", 64), FLOAT("float", 64), CHAR("char", 16), STRING("String", 256), DATETIME("dateTime", 64);
	String	type;
	int		max;

	private ColumnType(String type, int max)
	{
		this.type = type;
		this.max = max;
	}

	public String getType()
	{
		return type;
	}

	public int getMax()
	{
		return max;
	}
}
