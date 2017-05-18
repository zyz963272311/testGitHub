package db;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月16日 下午6:46:20</p>
 * <p>类全名：db.SQL</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DataBase
{
	String				driver		= "com.mysql.jdbc.Driver";
	String				url			= "jdbc:mysql://127.0.0.1:3309/zyztest";
	String				user		= "root";
	String				password	= "root";
	public Connection	conn;

	public DataBase()
	{
	}

	public Connection getConn()
	{
		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null)
			{
				System.out.println("连接数据库成功");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException("数据库连接异常" + e.getMessage());
		}
		return conn;
	}
}
