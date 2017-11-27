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
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
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
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
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
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		return getHanYuPinYinArray(c, format);
	}

	public static String[] getHanYuPinYinArray(char c, HanyuPinyinOutputFormat format) throws BadHanyuPinyinOutputFormatCombination
	{
		return PinyinHelper.toHanyuPinyinStringArray(c, format);
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
		return getFirstChar(result, pinyin, pinyinArrayMap, charArray, 0, 0, 0);
	}

	public static String getFirstChar(StringBuffer result, String pinyin, Map<Character,String[]> pinyinArrayMap, char[] charArray, int h, int v, int offset)
	{
		String py = pinyinArrayMap.get(charArray[h])[v];
		if (pinyin.startsWith(py, offset))
		{
			result.append(py.charAt(0));
			if (h + 1 == charArray.length && offset == pinyin.length())
			{
				return result.toString();
			}
			getFirstChar(result, pinyin, pinyinArrayMap, charArray, h + 1, v, offset + py.length());
		} else
		{
		}
		return null;
	}

	public static Map<Character,String[]> getHanYuPinYinArrayMap(String str) throws BadHanyuPinyinOutputFormatCombination
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
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
}