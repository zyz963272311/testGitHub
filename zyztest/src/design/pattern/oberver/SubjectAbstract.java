package design.pattern.oberver;

import java.util.Date;
import java.util.Vector;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 上午10:09:01</p>
 * <p>类全名：design.pattern.visitor.SubjectAbstract</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class SubjectAbstract implements Subject
{
	private String				context;
	private Date				date;
	private Vector<Observer>	observers;

	@Override
	public void add(Observer observer)
	{
		if (observers == null)
		{
			observers = new Vector<Observer>();
		}
		if (observer != null)
		{
			observers.add(observer);
		}
	}

	@Override
	public void delete(Observer observer)
	{
		if (observer == null)
		{
			return;
		}
		if (observers == null)
		{
			System.out.println("当前公众号被订阅这为空，无法删除");
		}
		observers.remove(observer);
	}

	@Override
	public void notifyAllObserver()
	{
		for (Observer observer : observers)
		{
			observer.update(this.getDate(), this.getContext());
		}
	}

	@Override
	public void option(String context)
	{
		if (context == null || "".equals(context))
		{
			return;
		}
		setContext(context);
		setDate(new Date());
		notifyAllObserver();
	}

	public String getContext()
	{
		return context;
	}

	public void setContext(String contect)
	{
		this.context = contect;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}
