package design.pattern.memento;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 下午4:35:41</p>
 * <p>类全名：design.pattern.memento.Storage</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Storage
{
	private Memento	memento;

	public Storage(Memento memento)
	{
		this.memento = memento;
	}

	public Memento getMemento()
	{
		return memento;
	}

	public void setMemento(Memento memento)
	{
		this.memento = memento;
	}
}
