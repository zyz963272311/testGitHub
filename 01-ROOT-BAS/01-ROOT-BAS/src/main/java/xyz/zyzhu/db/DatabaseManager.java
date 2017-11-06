package xyz.zyzhu.db;

import xyz.zyzhu.comm.WorkSpace;
import xyz.zyzhu.util.StrUtil;

/**
 * <p>标题： DatabaseFactory</p>
 * <p>内容： DatabaseFactory</p>
 * <p>创建时间： 2017年4月25日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.db.DatabaseFactory</p>
 * <p>作者： Administrator</p>
 */
public class DatabaseManager
{
	public static Database getDatabase(String dbName)
	{
		Database database = null;
		WorkSpace workSpace = new WorkSpace(dbName);
		String id = workSpace.getId();
		if (StrUtil.isStrNull(id))
		{
			throw new RuntimeException("根据workspaceID=【" + dbName + "】获取不到Workspace配置");
		}
		int type = StrUtil.obj2int(workSpace.getType());
		if (type == 1)
		{
			database = new DatabaseMySql(workSpace);
		}
		if (database == null)
		{
			throw new RuntimeException("根据workspaceID=【" + dbName + "】获取的workspaceType=【" + type + "】未知的类型");
		}
		return database;
	}
}
