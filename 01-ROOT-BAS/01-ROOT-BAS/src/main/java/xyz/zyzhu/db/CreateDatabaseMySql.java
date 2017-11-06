
package xyz.zyzhu.db;

import java.util.List;
import java.util.Map;

/**
 * <p>标题： CreateDatabaseMySql</p>
 * <p>内容： CreateDatabaseMySql</p>
 * <p>创建时间： 2017年4月24日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.CreateDatabaseMySql</p>
 * <p>作者： Administrator</p>
 */
public class CreateDatabaseMySql extends CreateDatabase
{

	/**
	 * @param sysdef 系统 用来缓存
	 * @param tabdef 表表 用来缓存
	 * @param coldef 表结构表 用来缓存
	 */
	CreateDatabaseMySql(Map<String, String> sysdef, Map<String, List<Map<String, Object>>> tabdef,
			Map<String, List<Map<String, Object>>> coldef) {
		super(sysdef, tabdef, coldef);
	}

	@Override
	public void getDatabase(String filePath)
	{
		super.getDatabase(filePath);
	}


}
