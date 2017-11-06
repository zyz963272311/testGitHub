package xyz.zyzhu.db;



/**
 * <p>标题： CreateDatabase</p>
 * <p>内容： CreateDatabase</p>
 * <p>创建时间： 2017年4月24日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： xyz.zyzhu.CreateDatabase</p>
 * <p>作者： Administrator</p>
 */
public interface ICreateDatabase
{

	// 创建数据库
	void getDatabase(String filePath);

	// 创建表
	void createTable(String filePath);

	// 创建表结构
	void createCol(String filepath);
}
