package xyz.zyzhu.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import xyz.zyzhu.comm.WorkSpace;
import xyz.zyzhu.util.StrUtil;


/**
 * <p>标题： DatabaseMySql</p>
 * <p>内容： DatabaseMySql</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.db.DatabaseMySql</p>
 * <p>作者： Administrator</p>
 */
public class DatabaseMySql extends Database
{

	public DatabaseMySql(String dbName) {
		super(dbName);
	}

	public DatabaseMySql(WorkSpace workspace) {
		super(workspace);
	}

	public Connection getConnection()
	{
		if (workspace == null)
		{
			throw new RuntimeException("未获得Connection,因为无法获取指定dbName的WorkSpace");
		}
		int type = StrUtil.obj2int(workspace.getType());
		String id = workspace.getId();
		if (type != 1)
		{
			throw new RuntimeException("根据dbName=【" + id + "】获取的workspace再配置中不是MySql");
		}
		String name = workspace.getName();
		String url = workspace.getUrl();
		String pass = workspace.getPass();
		try
		{
			this.conn = DriverManager.getConnection(url + "/" + id, name, pass);
			return conn;
		} catch (SQLException e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
}
