package _db.base;

/**
 * <p>标题：模拟数据表的列类型 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 上午11:34:57</p>
 * <p>类全名：_db.Column</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Column
{
	String		columnName;
	ColumnType	type;
	int			size;
	Object		autoValue;
	boolean		IsNull;

	/**
	 * @param columnName
	 * @param type
	 * @param size
	 * @param autoValue
	 * @param isNull
	 */
	public Column(String columnName, ColumnType type, int size, Object autoValue, boolean isNull)
	{
		super();
		this.columnName = columnName;
		this.type = type;
		this.size = size;
		this.autoValue = autoValue == null ? null : autoValue;
		IsNull = isNull;
	}
}
