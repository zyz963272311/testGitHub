package xyz.zyzhu.test;

import java.sql.Connection;
import java.sql.ResultSet;
import xyz.zyzhu.db.Database;
import xyz.zyzhu.db.DatabaseManager;

/**
 * <p>标题： TestDatabase</p>
 * <p>内容： TestDatabase</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.test.TestDatabase</p>
 * <p>作者： Administrator</p>
 */
public class TestDatabase
{

	public static void main(String[] args)
	{
		Database database = DatabaseManager.getDatabase("conf");
		Connection conn = database.getConnection();
		ResultSet rs = database.sqlSelect("select * from sysdef");
		database.close();
		System.out.println();
	}
}
