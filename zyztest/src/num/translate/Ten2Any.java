package num.translate;

/**
 * <p>标题：10进制转换成其他进制数 </p>
 * <p>功能： 10进制转换成其他进制数</p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月3日 下午6:01:32</p>
 * <p>类全名：num.Ten2Any</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Ten2Any
{
	long			oldNum, transNum;
	StringBuffer	newNum;

	public Ten2Any(long oldNum, long transNum)
	{
		this.oldNum = oldNum;
		this.transNum = transNum;
	}

	public String operator()
	{
		long oldNumTemp = this.oldNum;
		newNum = new StringBuffer();
		while (oldNumTemp > 0)
		{
			switch ((int) (oldNumTemp % transNum))
			{
			case 10:
				newNum.append("A");
				break;
			case 11:
				newNum.append("B");
				break;
			case 12:
				newNum.append("C");
				break;
			case 13:
				newNum.append("D");
				break;
			case 14:
				newNum.append("E");
				break;
			case 15:
				newNum.append("F");
				break;
			case 16:
				newNum.append("G");
				break;
			case 17:
				newNum.append("H");
				break;
			case 18:
				newNum.append("I");
				break;
			case 19:
				newNum.append("J");
				break;
			case 20:
				newNum.append("K");
				break;
			case 21:
				newNum.append("L");
				break;
			case 22:
				newNum.append("M");
				break;
			case 23:
				newNum.append("N");
				break;
			case 24:
				newNum.append("O");
				break;
			case 25:
				newNum.append("P");
				break;
			case 26:
				newNum.append("Q");
				break;
			case 27:
				newNum.append("R");
				break;
			case 28:
				newNum.append("E");
				break;
			case 29:
				newNum.append("T");
				break;
			case 30:
				newNum.append("U");
				break;
			case 32:
				newNum.append("V");
				break;
			case 33:
				newNum.append("W");
				break;
			case 35:
				newNum.append("X");
				break;
			case 36:
				newNum.append("Y");
				break;
			case 37:
				newNum.append("Z");
				break;
			default:
				newNum.append((oldNumTemp % transNum));
				break;
			}
			oldNumTemp = oldNumTemp / transNum;
		}
		return first2Last(newNum.toString());
	}

	private String first2Last(String newNum)
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
