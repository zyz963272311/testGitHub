
package xyz.zyzhu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import xyz.zyzhu.comm.WorkSpace;

/**
 * <p>标题： Database</p>
 * <p>内容： Database</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.db.Database</p>
 * <p>作者： Administrator</p>
 */
public abstract class Database implements IDatabase
{

	Connection conn = null;
	WorkSpace workspace;

	public Database(String dbName) {
		workspace = new WorkSpace(dbName);
	}

	public Database(WorkSpace workspace) {
		this.workspace = workspace;
	}

	public void insert(String sql)
	{
		update(sql);
	}

	public void delete(String sql)
	{
		update(sql);
	}

	public int update(String sql)
	{
		int resule = 0;
		PreparedStatement stmt = null;
		if (conn != null)
		{
			try
			{
				stmt = conn.prepareStatement(sql);
				resule = stmt.executeUpdate();
				return resule;
			} catch (SQLException e)
			{
				throw new RuntimeException(e.getMessage());
			}
		} else
		{
			throw new RuntimeException("connection链接为空");
		}
	}

	public ResultSet sqlSelect(String sql)
	{
		ResultSet resultSet = null;
		PreparedStatement stmt = null;
		if (conn != null)
		{
			try
			{
				stmt = conn.prepareStatement(sql);
				resultSet = stmt.executeQuery();
			} catch (SQLException e)
			{
				throw new RuntimeException(e.getMessage());
			}
		} else
		{
			throw new RuntimeException("connection链接为空");
		}
		return resultSet;
	}

	public void close()
	{
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException ex)
		{
			ex.printStackTrace();
		}
	}
}
