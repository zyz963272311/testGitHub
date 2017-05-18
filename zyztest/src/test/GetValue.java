package test;

import util.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年4月7日 下午9:02:13</p>
 * <p>类全名：test.GetValue</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class GetValue
{
	/**
	 * 获取第index个括号内的公式
	 * @param formula 源公式
	 * @param index	第i个括号
	 * @param isThrow 是否抛出异常
	 * @return 
	 * 赵玉柱
	 */
	public static String getValue(String formula, int index, boolean isThrow)
	{
		int from = -1; //截取公式起点
		int to = -1;//截取公式终点，需要加一
		boolean flags = true; //是否还需要继续括号个数减一
		int left_right = 0;//index及之后到截取终点之前左括号-右括号的值
		try
		{
			if (index <= 0)
			{
				throw new RuntimeException("数字参数index必须为整数");
			}
			if (StrUtil.isStrTrimNull(formula))
			{
				throw new RuntimeException("公式为空");
			}
			int length = formula.length();
			int count_left = 0, count_right = 0;
			for (int i = 0; i < length; i++)
			{
				char c = formula.charAt(i);
				if (c == '(')
				{
					count_left++;
					if (index == count_left)
					{
						from = i;
					}
					if (from >= 0)
					{
						left_right++;
					}
				}
				if (c == ')')
				{
					count_right++;
					if (from >= 0 && left_right > 0)
					{
						left_right--;
					}
				}
				if (from >= 0 && left_right == 0 && flags)
				{
					to = i;
					flags = false;
				}
				if (count_right > count_left)
				{
					throw new RuntimeException("当前位置" + i + "右括号数量大于左括号个数，先写左括号，再写右括号");
				}
			}
			if (count_left != count_right)
			{
				throw new RuntimeException("公式括号数量不匹配");
			}
			if (from == -1)
			{
				throw new RuntimeException("括号对数小于给定的数字，无法取得结果");
			}
			String rst = formula.substring(from, to + 1);
			return rst;
		} catch (Exception ex)
		{
			if (isThrow)
			{
				throw new RuntimeException(ex.toString());
			}
			return null;
		}
	}

	public static void main(String[] args)
	{
		String str = "1+2+(3+4+5)+(6+7+(8+9))+(10-11)";
		System.out.println(str);
		System.out.println(getValue(str, 2, true));
	}
}
