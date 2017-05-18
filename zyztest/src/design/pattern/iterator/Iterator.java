package design.pattern.iterator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午3:57:11</p>
 * <p>类全名：design.pattern.iterator.Iterator</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Iterator
{
	//前移
	public Object previous();

	//后移
	public Object next();

	//是否还有下一个
	public boolean hasNext();

	//获取第一个
	public Object first();
}
