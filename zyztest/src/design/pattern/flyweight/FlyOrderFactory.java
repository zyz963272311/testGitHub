package design.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 上午10:49:41</p>
 * <p>类全名：design.pattern.flyweight.FlyOrderFactory</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FlyOrderFactory
{
	private final Map<String,Order>	flyPool	= new HashMap<String,Order>();
	private static FlyOrderFactory	factory	= new FlyOrderFactory();

	private FlyOrderFactory()
	{
	}

	public static FlyOrderFactory getInstance()
	{
		return factory;
	}

	public Order getOrder(String fly)
	{
		Order order = null;
		if (flyPool.containsKey(fly))
		{
			order = flyPool.get(fly);
		} else
		{
			order = new FlyOrder(fly);
			flyPool.put(fly, order);
		}
		return order;
	}

	public int getFlyOrderMade()
	{
		return flyPool.size();
	}
}
