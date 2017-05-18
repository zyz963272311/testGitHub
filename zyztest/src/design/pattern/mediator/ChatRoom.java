package design.pattern.mediator;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月28日 上午10:53:20</p>
 * <p>类全名：design.pattern.mediator.ChatRoom</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ChatRoom implements Mediator
{
	@Override
	public void showMessage(User user, User user2, String message)
	{
		System.out.println("【" + user.getName() + "】发送个【" + user2.getName() + "】一条消息:" + message);
	}
}
