package str;

/**
 * <p>标题： 计算各种字符个数</p>
 * <p>功能： 计算各种字符个数</p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月3日 下午4:13:04</p>
 * <p>类全名：str.CountCharacter</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CountCharacter
{
	/**中文字符 */
	private int	chCharacter		= 0;
	/**英文字符 */
	private int	enCharacter		= 0;
	/**空格 */
	private int	spaceCharacter	= 0;
	/**数字 */
	private int	numberCharacter	= 0;
	/**其他字符 */
	private int	otherCharacter	= 0;

	/***
	 * 统计字符串中中文，英文，数字，空格等字符个数
	 * @param str 需要统计的字符串
	 */
	public void count(String str)
	{
		if (null == str || str.equals(""))
		{
			System.out.println("字符串为空");
			return;
		}
		for (int i = 0; i < str.length(); i++)
		{
			char tmp = str.charAt(i);
			if ((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z'))
			{
				enCharacter++;
			} else if ((tmp >= '0') && (tmp <= '9'))
			{
				numberCharacter++;
			} else if (tmp == ' ')
			{
				spaceCharacter++;
			} else if (isChinese(tmp))
			{
				chCharacter++;
			} else
			{
				otherCharacter++;
			}
		}
		System.out.println("字符串:" + str + "");
		System.out.println("中文字符有:" + chCharacter);
		System.out.println("英文字符有:" + enCharacter);
		System.out.println("数字有:" + numberCharacter);
		System.out.println("空格有:" + spaceCharacter);
		System.out.println("其他字符有:" + otherCharacter);
	}

	/***
	 * 判断字符是否为中文
	 * @param ch 需要判断的字符
	 * @return 中文返回true，非中文返回false
	 */
	private boolean isChinese(char ch)
	{
		//获取此字符的UniCodeBlock
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(ch);
		//  GENERAL_PUNCTUATION 判断中文的“号  
		//  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号  
		//  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号 
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
		{
			System.out.println(ch + " 是中文");
			return true;
		}
		return false;
	}
}
