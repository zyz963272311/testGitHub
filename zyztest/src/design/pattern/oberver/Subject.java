package design.pattern.oberver;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月27日 上午10:02:22</p>
 * <p>类全名：design.pattern.visitor.Subject</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Subject
{
	//添加订阅者，观察者
	public void add(Observer observer);

	//删除订阅者，观察者
	public void delete(Observer observer);

	//通知所有观察者进行更新
	public void notifyAllObserver();

	//本身的操作
	public void option(String context);
}
