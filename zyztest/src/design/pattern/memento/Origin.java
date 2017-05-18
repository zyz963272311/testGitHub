package design.pattern.memento;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午4:35:02</p>
 * <p>类全名：design.pattern.memento.Origin</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//源
public class Origin
{
	private String	value;

	public Origin(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public Memento createMemento()
	{
		return new Memento(value);
	}

	public void restoreMemento(Memento memento)
	{
		this.value = memento.getValue();
	}
}
