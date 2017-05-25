package com.liiwin.random;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月28日 下午4:30:32</p>
 * <p>类全名：random.Random</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Random
{
	/**
	 * 随机生成一个Object类型的对象
	 * @param max Object对象的最大值，并且可以记录要生成的数据类型x&lt;max
	 * @return 返回生成的数据
	 * x250-2
	 */
	Object getRandomObject(Object max);

	/**
	 * 随机生成一个Object类型的对象，
	 * @param min Object对象的最小值
	 * @param max Object对象的最大值 min&lt;=x&lt;max
	 * @return 返回生成的数据
	 * x250-2
	 */
	Object getRandomObject(Object min, Object max);

	/**
	 * 随机生成一个Object对象数组
	 * @param o Object对象空数组
	 * @param max Object对象最大值o[i]&lt;max
	 * @param length 数组长度
	 * @return 返回生成数组的时间
	 * x250-2
	 */
	long getRandomObject(Object[] o, Object max, int length);

	/**
	 * 随机生成Object对象数组
	 * @param o Object对象空数组
	 * @param min Object对象最小值
	 * @param max Object对象最大值min&lt;o[i]&lt;max
	 * @param length 数组长度
	 * @return 返回生成数组的时间
	 * x250-2
	 */
	long getRandomObject(Object[] o, Object min, Object max, int length);
}
