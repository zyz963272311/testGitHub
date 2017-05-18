package design.pattern.builder;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月22日 下午3:39:11</p>
 * <p>类全名：design.pattern.builder.CarBuilder</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CarBuilder implements Builder
{
	private final Product	product	= new Product();

	@Override
	public void setPart(String name, String type)
	{
		product.setName(name);
		product.setType(type);
	}

	@Override
	public Product getProduct()
	{
		return product;
	}
}
