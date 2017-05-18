package num.translate;

/**
 * <p>标题：将任意进制的数转换成10进制数 </p>
 * <p>功能：将任意进制的数转换成10进制数</p>
 * <p>所属模块： 赵玉柱自用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 赵玉柱</p>
 * <p>创建日期：2016年9月6日 下午2:52:36</p>
 * <p>类全名：num.Any2Ten</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Any2Ten
{
	String			oldNum;
	long			transNum;
	StringBuffer	newNum	= new StringBuffer();

	public Any2Ten(String oldNum, long transNum)
	{
		this.oldNum = oldNum;
		this.transNum = transNum;
	}

	public String operator()
	{//BigInteger
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] oldNumType = (oldNum).toCharArray();
		long newNumber = 0;
		for (int i = 0; i < oldNumType.length; i++)
		{
			long num = chars.indexOf(oldNumType[oldNumType.length - i - 1]);
			newNumber += num * power((int) transNum, i);
		}
		newNum.append("" + newNumber);
		return newNum.toString();
	}

	/**
	 * 
	 * @param base 底数
	 * @param index 指数
	 * @return
	 * x250-2
	 */
	long power(int base, int index)
	{
		long pow = 1;
		for (int i = 0; i <= index; i++)
		{
			if (i == 0)
			{
				pow = 1;
				continue;
			}
			pow *= base;
		}
		return pow;
	}

	String first2Last(String newNum)
	{
		StringBuffer sb = new StringBuffer();
		char[] newNumChar = new char[newNum.length()];
		newNumChar = newNum.toCharArray();
		char type;
		for (int i = 0; i < newNumChar.length / 2; i++)
		{
			type = newNumChar[i];
			newNumChar[i] = newNumChar[newNumChar.length - 1 - i];
			newNumChar[newNumChar.length - 1 - i] = type;
		}
		for (int i = 0; i < newNumChar.length; i++)
		{
			sb.append(newNumChar[i]);
		}
		newNum = sb.toString();
		return newNum;
	}
}
