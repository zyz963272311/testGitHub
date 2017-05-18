package design.pattern.adapter.object;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午2:34:42</p>
 * <p>类全名：design.pattern.adapter.object.Wrapper</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Wrapper implements Target
{
	private final Source	source;

	public Wrapper(Source source)
	{
		super();
		this.source = source;
	}

	@Override
	public void method1()
	{
		source.method1();
	}

	@Override
	public void method2()
	{
		System.out.println("I'm Wrapper.method2");
	}
}
