package xyz.zyzhu.pojo;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年8月11日 下午3:22:33</p>
 * <p>类全名：xyz.zyzhu.pojo.HelloRequestPojo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class HelloRequestPojo extends BasRequestPojo
{
	private static final long	serialVersionUID	= -4494265262222786500L;
	private String				name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String getApiName()
	{
		return "spring.HelloWebService.sayHello";
	}

	@Override
	public Class<? extends BasResponsePojo> getRespClass()
	{
		return HelloResponsePojo.class;
	}
}
