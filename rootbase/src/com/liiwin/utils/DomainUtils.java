package com.liiwin.utils;

import java.io.Serializable;
import com.alibaba.fastjson.JSONObject;
/**
 * <p>标题： Domain工具类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年9月23日 下午11:11:21</p>
 * <p>类全名：com.liiwin.utils.DomainUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DomainUtils
{
	public static <F extends Serializable,T extends Serializable> T domain2Domain(F f, Class<T> clazz)
	{
		if (f == null || clazz == null)
		{
			return null;
		}
		JSONObject josn = (JSONObject) JSONObject.toJSON(f);
		T t = JSONObject.toJavaObject(josn, clazz);
		return t;
	}
}
