package json;

import net.sf.json.JSONObject;
/**
 * <p>标题： JSON工具类</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月17日 上午10:54:32</p>
 * <p>类全名：json.JSONUtil</p>
 * 作者：x250-2
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
		json.put("a", 1);
		json.put("b", "bbb");
		json.put("test", null);
		System.out.println(json.toString());
		MyTest test = (MyTest) JSONObject.toBean(json, MyTest.class);
		System.out.println(test);
	}
}
