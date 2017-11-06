
package xyz.zyzhu.db;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * <p>标题： Database</p>
 * <p>内容： Database</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.db.IDatabase</p>
 * <p>作者： Administrator</p>
 */
public interface IDatabase
{

	public Connection getConnection();

	public void insert(String sql);

	public void delete(String sql);

	public int update(String sql);

	public ResultSet sqlSelect(String sql);

	public void close();
}
