package test;

import java.util.Map;
/**
 * <p>标题： 测试Map的有序性与无序性</p>
 * <p>功能： </p>
 * <p>所属模块： 测试使用</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 测试用</p>
 * <p>创建日期：2016年8月25日 上午10:34:29</p>
 * <p>类全名：test.TestMap</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestMap
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String[] key = new String[20];
		String[] value = new String[20];
		for (int i = 0; i < key.length; i++)
		{
			key[i] = "key" + i;
			value[i] = "value" + (20 - i);
		}
		Map<Object,Object> map = new mapandlist.Map().getHashMap(key, value);
		for (int i = 0; i < map.size(); i++)
		{
			System.out.println("i=\t" + i + "\tkey=\t" + key[i] + "\tvalue=\t" + map.get(key[i]));
		}
	}
}
