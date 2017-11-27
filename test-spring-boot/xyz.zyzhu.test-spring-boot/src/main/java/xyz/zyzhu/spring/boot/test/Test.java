package xyz.zyzhu.spring.boot.test;

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
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年11月27日 下午8:24:07</p>
 * <p>类全名：xyz.zyzhu.spring.boot.test.Test</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test
{
	public static void main(String[] args)
	{
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		try
		{
			System.out.println(PinyinHelper.toHanYuPinyinString("赵玉柱", format, "", false));
		} catch (BadHanyuPinyinOutputFormatCombination e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
		//		System.out.println(PinyinHelper.toHanyuPinyinStringArray('赵'));
	}
}