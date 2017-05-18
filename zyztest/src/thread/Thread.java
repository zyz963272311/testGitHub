package thread;

/**
 * <p>标题： 入境进区商品改单监听</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月25日 下午3:57:39</p>
 * <p>类全名：thread.Thread</p>
 * 作者：冯继亮
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Thread extends java.lang.Thread
{
	@Override
	public void run()
	{
		for (int i = 0; i < 6000; i++)
		{
			System.out.println(Thread.currentThread().getName() + i);
		}
	}
}
