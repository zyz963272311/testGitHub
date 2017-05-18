package counter;

/**
 * <p>标题： 序列数</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月15日 下午4:43:01</p>
 * <p>类全名：counter.CounterUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CounterUtil
{
	private static CounterUtil	counterUtil	= new CounterUtil();

	/**
	 * 
	 */
	public CounterUtil()
	{
	}

	public static CounterUtil getInstance()
	{
		synchronized (counterUtil)
		{
			if (null == counterUtil)
			{
				counterUtil = new CounterUtil();
			}
		}
		return counterUtil;
	}

	public synchronized long nextServial(String tableName, String columnName, CounterUtil counterUtil)
	{
		String sql = null;
		try
		{
			sql = "select max(" + columnName + ") as " + columnName + " from " + tableName;
		} catch (Exception e)
		{
		} finally
		{
		}
		return 1;
	}
}
