package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月27日 下午7:11:23</p>
 * <p>类全名：util.JSONUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class JSONUtil
{
	public static void main(String[] args)
	{
		JSONObject json = new JSONObject();
		JSONArray jsonarray = new JSONArray();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		json.put("name", "赵玉柱");
		json.put("age", 22);
		json.put("sex", "男");
		jsonarray.add(json);
		json.clear();
		json.put("name", "李旭");
		json.put("age", 22);
		json.put("sex", "男");
		jsonarray.add(json);
		System.out.println("json=" + json);
		System.out.println("jsonarray=" + jsonarray);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", "赵玉柱");
		map.put("age", 23);
		map.put("sex", "男");
		list.add(map);
		jsonarray.clear();
		jsonarray.add(map);
		map.clear();
		map.put("name", "李旭");
		map.put("age", 23);
		map.put("sex", "男");
		list.add(map);
		jsonarray.add(map);
		System.out.println("jsonarray=" + jsonarray);
		jsonarray.clear();
		jsonarray.add(list);
		System.out.println("jsonarray=" + jsonarray);
	}
}
