package design.pattern.oberver;

import java.util.Date;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 上午10:26:23</p>
 * <p>类全名：design.pattern.visitor.ObservreB</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ObservreB implements Observer
{
	private final String	observerName;

	public ObservreB(String observerName)
	{
		this.observerName = observerName;
	}

	@Override
	public void update(Date date, String context)
	{
		System.out.println("观察者名称：" + observerName + "时间：" + date + ",更新了文章【" + context + "】");
	}
}
