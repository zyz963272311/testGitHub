package com.liiwin.utils;

import java.util.HashMap;
import java.util.Map;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * <p>标题： 拼音工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年11月27日 下午8:41:15</p>
 * <p>类全名：com.liiwin.utils.PinYinUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PinYinUtils
{
	/**
	 * 根据汉字获取拼音
	 * @param str
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * 赵玉柱
	 */
	public static String toHanYuPinYin(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		String separate = "";
		boolean retain = true;
		return toHanYuPinYin(str, format, separate, retain);
	}

	/**
	 * 根据汉字获取拼音
	 * @param str
	 * @param format
	 * @param separate
	 * @param retain
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * 赵玉柱
	 */
	public static String toHanYuPinYin(String str, HanyuPinyinOutputFormat format, String separate, boolean retain) throws BadHanyuPinyinOutputFormatCombination
	{
		return PinyinHelper.toHanYuPinyinString(str, format, separate, retain);
	}

	public static String[] getHanYuPinYinArray(char c) throws BadHanyuPinyinOutputFormatCombination
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		return getHanYuPinYinArray(c, format);
	}

	public static String[] getHanYuPinYinArray(char c, HanyuPinyinOutputFormat format) throws BadHanyuPinyinOutputFormatCombination
	{
		return PinyinHelper.toHanyuPinyinStringArray(c, format);
	}

	/**
	 * 获取首字母并大写
	 * @param str
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 * 赵玉柱
	 */
	public static String getFirstUpperChar(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		return getFirstChar(str).toUpperCase();
	}

	/**
	 * 获取首字母
	 * @param str
	 * 赵玉柱
	 * @throws BadHanyuPinyinOutputFormatCombination 
	 */
	public static String getFirstChar(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		//获取全拼
		String pinyin = toHanYuPinYin(str);
		char[] charArray = str.toCharArray();
		//获取汉字单字拼音
		Map<Character,String[]> pinyinArrayMap = getHanYuPinYinArrayMap(str);
		StringBuffer result = new StringBuffer();
		return getFirstChar(result, pinyin, pinyinArrayMap, charArray, 0, 0, 0, 0, 0);
	}

	public static String getFirstChar(StringBuffer result, String pinyin, Map<Character,String[]> pinyinArrayMap, char[] charArray, int h, int v, int _h, int _v, int offset)
	{
		String py = null;
		String[] pyA = pinyinArrayMap.get(charArray[h]);
		//支持字符与数字
		if (pyA.length == 0)
		{
			py = new String(new char[] { charArray[h] });
		} else
		{
			py = pyA[v];
		}
		if (pinyin.startsWith(py, offset))
		{
			result.append(py.charAt(0));
			if (h + 1 == charArray.length && offset + py.length() == pinyin.length())
			{
				return result.toString();
			}
			return getFirstChar(result, pinyin, pinyinArrayMap, charArray, h + 1, 0, h, v, offset + py.length());
		} else
		{
			if (v + 1 == pinyinArrayMap.get(charArray[h]).length)
			{
				result.setLength(result.length() - (h - _h));
				offset -= pinyinArrayMap.get(charArray[_h])[_v].length();
				if (_v + 1 == pinyinArrayMap.get(charArray[_h]).length)
				{
					return getFirstChar(result, pinyin, pinyinArrayMap, charArray, _h + 1, 0, _h, _v, offset);
				} else
				{
					return getFirstChar(result, pinyin, pinyinArrayMap, charArray, _h, _v + 1, _h, _v, offset);
				}
			}
			return getFirstChar(result, pinyin, pinyinArrayMap, charArray, h, v + 1, _h, _v, offset);
		}
		//		return result.toString();
	}

	public static Map<Character,String[]> getHanYuPinYinArrayMap(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		return getHanYuPinYinArrayMap(str, format);
	}

	public static Map<Character,String[]> getHanYuPinYinArrayMap(String str, HanyuPinyinOutputFormat format) throws BadHanyuPinyinOutputFormatCombination
	{
		Map<Character,String[]> resultMap = new HashMap<>();
		if (StrUtil.isStrTrimNull(str))
		{
			return null;
		}
		for (Character c : str.toCharArray())
		{
			String[] pinyin = getHanYuPinYinArray(c, format);
			resultMap.put(c, pinyin);
		}
		return resultMap;
	}

	public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination
	{
		String str = "西安。 2s、/";
		System.out.println(toHanYuPinYin(str));
		System.out.println(getFirstChar(str));
		System.out.println(getFirstUpperChar(str));
	}
}
