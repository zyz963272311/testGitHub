package design.pattern.templete;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午2:46:59</p>
 * <p>类全名：design.pattern.templete.Day</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class Day
{
	//每个人每一天需要做的事情
	public final void process()
	{
		startUp();
		breakFirst();
		toWork();
		lunch();
		backHome();
		supper();
		sleep();
	}

	public void startUp()
	{
		System.out.println("start up");
	}

	//早餐
	public abstract void breakFirst();

	//去工作的交通工具
	public abstract void toWork();

	//午饭
	public abstract void lunch();

	//回家的交通方式
	public abstract void backHome();

	//晚饭
	public abstract void supper();

	public void sleep()
	{
		System.out.println("sleep");
	}
}
