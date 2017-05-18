package db;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月17日 下午2:03:45</p>
 * <p>类全名：db.Oracle</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Oracle extends DataBase
{
	/**
	 * 
	 */
	public Oracle()
	{
		this.driver = "oracle.jdbc.driver.OracleDriver";
		this.url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		this.user = "system";
		this.password = "root";
	}
}
