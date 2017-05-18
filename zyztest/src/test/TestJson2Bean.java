package test;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年3月6日 上午9:35:45</p>
 * <p>类全名：test.TestJson2Bean</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestJson2Bean
{
	public static void main(String[] args)
	{
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i <= 5; i++)
		{
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("name", "zyz");
			map1.put("age", 13);
			jsonArray.add(map1);
		}
		for (int i = 0; i < jsonArray.size(); i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject = jsonArray.getJSONObject(i);
			Bean1 bean = (Bean1) JSONObject.toBean(jsonObject, Bean1.class);
			System.out.println("i=" + i + bean.toString());
		}
		System.out.println("testBean2Json");
		JSONObject json = new JSONObject();
		Bean1 bean = new Bean1();
		bean.setName("zzz");
		bean.setAge(11);
		json = JSONObject.fromObject(bean);
		System.out.println(json);
		json.remove("name");
		System.out.println(json);
	}
}
