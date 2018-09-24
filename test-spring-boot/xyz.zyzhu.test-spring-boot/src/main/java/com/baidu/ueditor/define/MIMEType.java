package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:17:16</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.define.MIMEType</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class MIMEType
{
	public static final Map<String,String> types = new HashMap<String,String>()
	{
	};

	public static String getSuffix(String mime)
	{
		return ((String) types.get(mime));
	}
}
