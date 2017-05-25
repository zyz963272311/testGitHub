package com.liiwin.search;

import java.util.List;
/**
 * <p>标题：搜索查询接口 </p>
 * <p>功能：搜索查询接口 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月13日 下午4:11:21</p>
 * <p>类全名：search.Search</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Search
{
	/**
	 * 
	 * @param from 要查找的Object源数组
	 * @param o    查找o是否在from源数组中
	 * @return 若存在，返回下标数组，若不存在，返回-1，最后一位为查询时间
	 * x250-2
	 */
	public List<Long> search(Object[] from, Object o);

	/**
	 * 
	 * @param from 要查找的String源数组
	 * @param o    查找o是否在from源数组中
	 * @return 若存在，返回下标数组，若不存在，返回-1，最后一位为查询时间
	 * x250-2
	 */
	public List<Long> search(String[] from, Object o);

	/**
	 * 
	 * @param from 要查找的String类型数组
	 * @param o    查找子字符串o是否在from字符串数组中
	 * @return 若存在，返回下标，若不存子啊，返回-1，最后一位为查询时间
	 * x250-2
	 */
	public List<Long> search(String[] from, String o);

	/**
	 * 
	 * @param from 要查找的String字符串
	 * @param o    查找子字符串是否在源数组中
	 * @return 若存在，返回每一次出现的位置数组，若不存在，返回-1，最后一位为查询时间
	 * x250-2
	 */
	public List<Long> search(String from, String o);

	/**
	 * 
	 * @param from 要查找的String字符串
	 * @param o    查找o是否在源字符串中
	 * @return 若存在，返回每一次出现的位置数组，若不存在，返回-1，最后一位为查询时间
	 * x250-2
	 */
	public List<Long> search(String from, Object o);
}
