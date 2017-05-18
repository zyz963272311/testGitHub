package design.pattern.iterator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午4:03:37</p>
 * <p>类全名：design.pattern.iterator.MyIterator</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MyIterator implements Iterator
{
	private final Collection	collection;
	private int					pos	= -1;

	public MyIterator(Collection collection)
	{
		this.collection = collection;
	}

	@Override
	public Object previous()
	{
		if (pos > 0)
		{
			pos--;
		}
		return collection.get(pos);
	}

	@Override
	public Object next()
	{
		if (pos < collection.size() - 1)
		{
			pos++;
		}
		return collection.get(pos);
	}

	@Override
	public boolean hasNext()
	{
		if (pos < collection.size() - 1)
		{
			return true;
		}
		return false;
	}

	@Override
	public Object first()
	{
		return collection.get(0);
	}
}
