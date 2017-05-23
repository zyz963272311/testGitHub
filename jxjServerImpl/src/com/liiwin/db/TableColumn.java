package com.liiwin.db;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 下午4:25:54</p>
 * <p>类全名：db.TableColumn</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TableColumn
{
	public TableColumn()
	{
	}

	String	columnname;
	String	tablename;
	String	title;
	/**见java.sql.Types类*/
	int		sqltype;
	int		flags;
	int		displayFlag	= 1;
	int		width;
}
