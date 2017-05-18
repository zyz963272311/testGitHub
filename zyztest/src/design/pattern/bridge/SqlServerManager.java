package design.pattern.bridge;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午5:17:57</p>
 * <p>类全名：design.pattern.bridge.MySQLManager</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SqlServerManager extends JDBCManager
{
	public SqlServerManager(IJDBC jdbc)
	{
		this.jdbc = jdbc;
	}

	@Override
	public Object getConn()
	{
		System.out.println("SqlServerManager.getConn");
		return super.getConn();
	}

	@Override
	public int updqte(String sql)
	{
		System.out.println("SqlServerManager.update");
		return super.updqte(sql);
	}
}
