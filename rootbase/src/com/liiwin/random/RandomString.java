package com.liiwin.random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午5:40:14</p>
 * <p>类全名：random.RandomString</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface RandomString
{
	/**
	 * 获取一个随机的字符串
	 * @param length 字符串长度
	 * @param max 最大字符
	 * @return
	 * 赵玉柱
	 */
	String getRandomString(int length, char max);

	/**
	 * 获取一个随机字符串
	 * @param length 字符串长度
	 * @param min 最小字符
	 * @param max 最大字符
	 * @return
	 * 赵玉柱
	 */
	String getRandomString(int length, char min, char max);

	/**
	 * 获取一个随机字符串数据
	 * @param length 每个字符串的
	 * @param size 字符串数组长度
	 * @param max
	 * @return
	 * 赵玉柱
	 */
	String[] getRandomStringArray(int length, int size, char max);

	String[] getRandomStringArray(int length, int size, char min, char max);
}
