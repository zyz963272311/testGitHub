package search;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月13日 下午4:17:12</p>
 * <p>类全名：search.SequelSearch</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class SequelSearch implements Search
{
	List<Long>	result	= new ArrayList<Long>();

	@Override
	public List<Long> search(Object[] from, Object o)
	{
		long start = System.currentTimeMillis();
		for (int i = 0; i < from.length; i++)
		{
			if (from[i].equals(o))
			{
				result.add((long) i);
			}
			if (i == (from.length - 1) && result.size() == 0)
			{
				result.add((long) -1);
			}
		}
		long end = System.currentTimeMillis();
		result.add(end - start);
		return result;
	}

	@Override
	public List<Long> search(String[] from, Object o)
	{
		return search(from, o.toString());
	}

	@Override
	public List<Long> search(String[] from, String o)
	{
		long start = System.currentTimeMillis();
		for (int i = 0; i < from.length; i++)
		{
			if (from[i].equals(o))
			{
				result.add((long) i);
			}
			if (i == (from.length - 1) && result.size() == 0)
			{
				result.add((long) -1);
			}
		}
		long end = System.currentTimeMillis();
		result.add(end - start);
		return result;
	}

	@Override
	public List<Long> search(String from, String o)
	{
		long start = System.currentTimeMillis();
		long end;
		if (from == null || from.length() == 0)
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		if (o == null || o.length() == 0)
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		if (from.length() <= o.length())
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		char[] tempFrom = from.toCharArray(), tempO = o.toCharArray();
		/**
		 * from.length是from字符串的长度
		 * o.length是字符串o的长度
		 * 两个长度相减，为总得比较次数
		 */
		for (int i = 0; i < from.length() - o.length(); i++)
		{
			/**
			 * 每一次的比较次数最多为j次
			 */
			for (int j = 0; j < o.length(); j++)
			{
				if (tempFrom[i + j] != tempO[j])
				{
					break;
				}
				if (j == o.length() - 1 && tempFrom[i + j] == tempO[j])
				{
					result.add((long) i);
				}
			}
			if (i == from.length() - o.length() && result.size() == 0)
			{
				result.add((long) -1);
			}
		}
		end = System.currentTimeMillis();
		result.add(end - start);
		return result;
	}

	@Override
	public List<Long> search(String from, Object typeO)
	{
		String o = typeO.toString();
		long start = System.currentTimeMillis();
		long end;
		if (from == null || from.length() == 0)
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		if (o == null || o.length() == 0)
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		if (from.length() <= o.length())
		{
			result.add((long) -1);
			end = System.currentTimeMillis();
			result.add(end - start);
			return result;
		}
		char[] tempFrom = from.toCharArray(), tempO = o.toCharArray();
		/**
		 * from.length是from字符串的长度
		 * o.length是字符串o的长度
		 * 两个长度相减，为总得比较次数
		 */
		for (int i = 0; i < from.length() - o.length(); i++)
		{
			/**
			 * 每一次的比较次数最多为j次
			 */
			for (int j = 0; j < o.length(); j++)
			{
				if (tempFrom[i + j] != tempO[j])
				{
					break;
				}
				if (j == o.length() - 1 && tempFrom[i + j] == tempO[j])
				{
					result.add((long) i);
				}
			}
			if (i == from.length() - o.length() && result.size() == 0)
			{
				result.add((long) -1);
			}
		}
		end = System.currentTimeMillis();
		result.add(end - start);
		return result;
	}
}
