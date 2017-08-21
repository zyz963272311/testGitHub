package xyz.zyzhu.pojo;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年8月11日 下午3:23:58</p>
 * <p>类全名：xyz.zyzhu.pojo.HelloResponsePojo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Hello1ResponsePojo extends BasResponsePojo
{
	private static final long	serialVersionUID	= 6887153133790145422L;
	private String				name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
