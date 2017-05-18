package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * <p>标题： TestJSon</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月29日 下午4:09:27</p>
 * <p>类全名：test.TestJSon</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestJSon
{
	public static void main(String[] args)
	{
		//		Map<String,Object> map = new HashMap<String,Object>();
		//		map.put("aaa", "321");
		//		map.put("ccc", "333");
		//		Map<String,Object> subMap = new HashMap<String,Object>();
		//		subMap.put("subaaa", "321321");
		//		subMap.put("subewq", "32132131");
		//		map.put("sub", subMap);
		//		System.out.println(map);
		//		JSONObject json = JSONObject.fromObject(map);
		//		System.out.println(json);
		//		Map<String,Object> outMap = new HashMap<String,Object>();
		//		TestJSon test = new TestJSon();
		//		test.json2Map(json, outMap);
		//		test.syso(outMap);
		JSONObject jsonObject = new JSONObject();
		System.out.println(jsonObject.isEmpty());
		System.out.println(jsonObject == null);
		jsonObject.put("21", null);
		System.out.println(jsonObject.isEmpty());
		jsonObject.put("21", "321");
		System.out.println(jsonObject.isEmpty());
		System.out.println(jsonObject);
		System.out.println("------------------jsonarray-----------");
		JSONArray jsonArray = new JSONArray();
		System.out.println(jsonArray.isEmpty());
		jsonArray.add(null);
		System.out.println(jsonArray.isEmpty());
		System.out.println(jsonArray);
		jsonArray.add(jsonObject);
		System.out.println(jsonArray.isEmpty());
		System.out.println(jsonArray);
	}

	private Map<String,Object> json2Map(JSONObject json, Map<String,Object> outMap)
	{
		Iterator<String> nameItr = json.keys();
		String name;
		while (nameItr.hasNext())
		{
			name = nameItr.next();
			if (isJson(json.getString(name)))
			{
				JSONObject subJson = JSONObject.fromObject(json.getString(name));
				Map<String,Object> subMap = new HashMap<String,Object>();
				outMap.put("name", json2Map(subJson, subMap));
			} else
			{
				outMap.put(name, json.getString(name));
			}
		}
		System.out.println(outMap);
		return outMap;
	}

	private static boolean isJson(Object obj)
	{
		try
		{
			JSONObject.fromObject(obj);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	private void syso(Map<String,Object> map)
	{
		System.out.print("{");
		for (String key : map.keySet())
		{
			Object obj = map.get(key);
			if (obj instanceof Map)
			{
				System.out.print("{key:" + key + "\tvalue:");
				syso((Map<String,Object>) obj);
			} else
			{
				System.out.print("key:" + key + "\tvalue:" + obj + "\t");
			}
		}
		System.out.print("}");
	}
}
