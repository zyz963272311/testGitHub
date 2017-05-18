package design.pattern.iterator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午4:01:41</p>
 * <p>类全名：design.pattern.iterator.MyCollection</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MyCollection implements Collection
{
	private Object[]	object	= null;

	public MyCollection(Object[] object)
	{
		this.object = object;
	}

	@Override
	public Iterator iterator()
	{
		return new MyIterator(this);
	}

	@Override
	public Object get(int index)
	{
		if (null == object)
		{
			return null;
		}
		return object[index];
	}

	@Override
	public int size()
	{
		if (null == object)
		{
			return 0;
		}
		return object.length;
	}
}
