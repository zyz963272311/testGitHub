package util;

import java.util.List;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月4日 下午9:45:18</p>
 * <p>类全名：util.ArrayUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ArrayUtil
{
	public static <T extends Object> T[] toArray(List<?> list, Class<T> clazz)
	{
		if (list == null)
		{
			return null;
		}
		return list.toArray((T[]) java.lang.reflect.Array.newInstance(clazz, list.size()));
	}
}
