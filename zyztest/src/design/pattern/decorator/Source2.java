package design.pattern.decorator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午3:03:12</p>
 * <p>类全名：design.pattern.decorator.Source2</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Source2 implements Sourceable
{
	private final Sourceable	source;

	public Source2(Sourceable source)
	{
		super();
		this.source = source;
	}

	@Override
	public void method1()
	{
		System.out.println("开始装饰");
		source.method1();
		System.out.println("结束装饰");
	}
}
