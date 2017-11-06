package xyz.zyzhu.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>标题： CreateDatabaseFactory</p>
 * <p>内容： CreateDatabaseFactory</p>
 * <p>创建时间： 2017年4月24日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.CreateDatabaseFactory</p>
 * <p>作者： Administrator</p>
 */
public class CreateDatabaseFactory
{


	public static CreateDatabase getInstance(int type)
	{
		CreateDatabase createDatabase = null;
		Map<String, String> stsdef = new HashMap<String, String>();
		Map<String, List<Map<String, Object>>> tabdef = new HashMap<String, List<Map<String, Object>>>();
		Map<String, List<Map<String, Object>>> coldef = new HashMap<String, List<Map<String, Object>>>();
		if ((type & 1) != 0)
			createDatabase = new CreateDatabaseMySql(stsdef, tabdef, coldef);
		return createDatabase;
	}

	public static void main(String[] args)
	{
		CreateDatabase createDatabase = getInstance(1);
		createDatabase.getDatabase("CreateDatabase01_conf.txt");
	}
}
