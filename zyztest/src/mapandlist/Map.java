package mapandlist;

import java.util.HashMap;
/**
 * <p>标题： 测试map集合的有序性与无序性</p>
 * <p>功能： </p>
 * <p>所属模块： 测试</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 个人测试使用</p>
 * <p>创建日期：2016年8月25日 上午9:39:08</p>
 * <p>类全名：mapandlist.Map</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Map
{
	java.util.Map<Object,Object>	hashMap	= new HashMap<Object,Object>();

	public java.util.Map<Object,Object> getHashMap(String[] key, String[] value)
	{
		for (int i = 0; i < (key.length < value.length ? key.length : value.length); i++)
		{
			hashMap.put(key[i], value[i]);
		}
		return hashMap;
	}
}
