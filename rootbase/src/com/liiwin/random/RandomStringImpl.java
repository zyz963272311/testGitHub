package com.liiwin.random;

import java.util.Arrays;
import java.util.Random;
/**
 * <p>标题： 生成一个随机的字符串或字符串数组</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午5:46:46</p>
 * <p>类全名：random.RandomStringImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class RandomStringImpl implements RandomString
{
	@Override
	public String getRandomString(int length, char max)
	{
		return getRandomString(length, Character.MIN_VALUE, max);
	}

	@Override
	public String getRandomString(int length, char min, char max)
	{
		if (length < 0)
		{
			throw new IllegalArgumentException("参数错误：长度非负");
		}
		if (min > max)
		{
			char temp = min;
			min = max;
			max = temp;
		}
		final char[] assemChars = assemChars(min, max);
		return getRandomString(length, assemChars);
	}

	@Override
	public String getRandomString(int length, char[] assemChars)
	{
		int size = assemChars.length;
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			sb.append(assemChars[random.nextInt(size)]);
		}
		return sb.toString();
	}

	@Override
	public String[] getRandomStringArray(int length, int size, char max)
	{
		return getRandomStringArray(length, size, Character.MIN_VALUE, max);
	}

	@Override
	public String[] getRandomStringArray(int length, int size, char min, char max)
	{
		if (min > max)
		{
			char temp = min;
			min = max;
			max = temp;
		}
		String[] result = new String[size];
		final char[] assemChars = assemChars(min, max);
		for (int i = 0; i < size; i++)
		{
			result[i] = getRandomString(length, assemChars);
		}
		return result;
	}

	/**
	 * 根据最大字符，最小字符返回缓冲数组
	 * @param min
	 * @param max
	 * @return
	 * 赵玉柱
	 */
	private char[] assemChars(char min, char max)
	{
		int size = max - min + 1;
		if (size <= 0)
		{
			return null;
		}
		char[] assemChars = new char[size];
		for (int i = 0; i < size; i++)
		{
			assemChars[i] = (char) (min + i);
		}
		return assemChars;
	}

	public static void main(String[] args)
	{
		RandomString string = new RandomStringImpl();
		long start = System.currentTimeMillis();
		System.out.println(string.getRandomString(1000, 'A', 'Z'));
		System.out.println("生成随机字符串时间" + (System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
		String[] result = string.getRandomStringArray(1000, 100000, 'A', 'Z');
		String[] copyResult = Arrays.copyOf(result, result.length);
		System.out.println("生成随机字符串数组时间" + (System.currentTimeMillis() - start));
		for (int i = 0; i < result.length; i++)
		{
			//System.out.println("第" + (i + 1) + "条\t" + result[i]);
		}
		start = System.currentTimeMillis();
		Arrays.sort(result);
		System.out.println("arrays排序时间" + (System.currentTimeMillis() - start));
		for (int i = 0; i < result.length; i++)
		{
			//System.out.println("第" + (i + 1) + "条\t" + result[i]);
		}
		start = System.currentTimeMillis();
		new com.liiwin.sort.QuickSort().sort(copyResult);
		System.out.println("QuickSort排序时间" + (System.currentTimeMillis() - start));
		for (int i = 0; i < copyResult.length; i++)
		{
			//System.out.println("第" + (i + 1) + "条\t" + copyResult[i]);
		}
	}
}
