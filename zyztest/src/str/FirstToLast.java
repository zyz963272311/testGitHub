package str;

/**
 * <p>标题：字符串顺序颠倒 </p>
 * <p>功能： 字符串顺序颠倒</p>
 * <p>所属模块： 测试用</p>
 * <p>版权： 赵玉柱</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月30日 上午10:16:26</p>
 * <p>类全名：str.FirstToLast</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class FirstToLast
{
	public static void first2last(Object[] o)
	{
		if (o == null || "".equals(o[0]))
		{
			System.out.println("您输入的字符串为空");
			return;
		}
		for (int i = 0; i < (o.length + 1) / 2; i++)
		{
			Object temp = o[i];
			o[i] = o[o.length - i - 1];
			o[o.length - i - 1] = temp;
		}
	}
}
