package com.liiwin.db;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月17日 下午2:26:21</p>
 * <p>类全名：db.SQLServer</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SQLServer extends DataBase
{
	public SQLServer()
	{
		this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		this.url = "jdbc:sqlserver://localhost:1433;DatabaseName=DB";
		this.user = "sa";
		this.password = "123456";
	}
}
