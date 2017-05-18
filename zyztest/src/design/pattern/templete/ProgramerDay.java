package design.pattern.templete;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月24日 下午2:52:06</p>
 * <p>类全名：design.pattern.templete.ProgramerDay</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ProgramerDay extends Day
{
	String	obj	= "Programer";

	@Override
	public void breakFirst()
	{
		System.out.println(obj + "的早餐是【豆浆，油条】");
	}

	@Override
	public void toWork()
	{
		System.out.println(obj + "的去工作的方式是【打车】");
	}

	@Override
	public void lunch()
	{
		System.out.println(obj + "的午餐是【米饭】");
	}

	@Override
	public void backHome()
	{
		System.out.println(obj + "的回家方式是【做地铁】");
	}

	@Override
	public void supper()
	{
		System.out.println(obj + "的晚饭是【面条】");
	}
}
