package num.translate;

/**
 * <p>标题： 将任意进制的数转换成10进制数</p>
 * <p>功能： 将任意进制的数转换成10进制数</p>
 * <p>所属模块： 赵玉柱</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 赵玉柱</p>
 * <p>创建日期：2016年9月7日 下午2:24:26</p>
 * <p>类全名：num.Any2Any</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Any2Any
{
	String			oldNum;
	long			firstTransNum, secondTransNum;
	StringBuffer	newNum	= new StringBuffer();
	Any2Ten			any2Ten;
	Ten2Any			ten2Any;

	public Any2Any(String oldNum, long firstTransNum, long secondTransNum)
	{
		this.oldNum = oldNum;
		this.firstTransNum = firstTransNum;
		this.secondTransNum = secondTransNum;
	}

	public String operator()
	{
		any2Ten = new Any2Ten(oldNum, firstTransNum);
		oldNum = any2Ten.operator();
		System.out.println("10进制数是" + oldNum);
		ten2Any = new Ten2Any(Long.parseLong(oldNum), secondTransNum);
		newNum.append(ten2Any.operator());
		return newNum.toString();
	}
}
