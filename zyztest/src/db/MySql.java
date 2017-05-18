package db;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月16日 下午6:46:13</p>
 * <p>类全名：db.MySql</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MySql extends DataBase
{
	public MySql()
	{
		this.driver = "com.mysql.jdbc.Driver";
		this.url = "jdbc:mysql://127.0.0.1:3309/zyztest";
		this.user = "root";
		this.password = "root";
	}
}
