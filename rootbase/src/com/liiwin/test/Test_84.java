package com.liiwin.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月28日 下午10:23:31</p>
 * <p>类全名：com.liiwin.test.Test_84</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test_84
{
	public static void main(String[] args)
	{
		Map<String,String> a = new HashMap<String,String>();
		List<Map<String,String>> b = new ArrayList<>();
		a.put("a", "a");
		b.add(a);
		for (Map<String,String> e : b)
		{
			e.put("b", "b");
		}
		System.out.println(a);
		System.out.println(b);
	}
}
