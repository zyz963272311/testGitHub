package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年1月24日 上午10:41:52</p>
 * <p>类全名：test.TestSet</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestSet
{
	public static void main(String[] args)
	{
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(0b0001, 0b0001);
		map.put(0b0010, 0b0010);
		map.put(0b0100, 0b0100);
		map.put(0b1000, 0b1000);
		Set<Integer> keySets = map.keySet();
		for (Integer keySet : keySets)
		{
			System.out.println("key=" + keySet + "\thashCode=" + keySet.hashCode() + "\tv=" + map.get(keySet) + "\tHashCode=" + map.get(keySet));
		}
		Set<Map.Entry<Integer,Integer>> entrySets = map.entrySet();
		for (Map.Entry<Integer,Integer> entrySet : entrySets)
		{
			System.out.println("entry=" + entrySet + "\tentery.hashCode=" + entrySet.hashCode());
		}
	}
}
