package design.pattern.builder;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月22日 下午3:40:32</p>
 * <p>类全名：design.pattern.builder.Director</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Director
{
	private final Builder	builder	= new CarBuilder();

	//CarA的建造者
	public Product getCarAProduct()
	{
		builder.setPart("山地车", "100");
		return builder.getProduct();
	}

	//CarB的建造者
	public Product getCarBProduct()
	{
		builder.setPart("凤凰牌", "125");
		return builder.getProduct();
	}
}
