package design.pattern.flyweight;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 上午10:46:54</p>
 * <p>类全名：design.pattern.flyweight.FlyOrder</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FlyOrder implements Order
{
	//coffee的口味
	private final String	fly;

	/**
	 * 在构造方法中初始化所有的共享参数
	 */
	public FlyOrder(String fly)
	{
		this.fly = fly;
	}

	@Override
	public void sell()
	{
		System.out.println("卖出了一杯" + fly + "口味的咖啡");
	}
}
