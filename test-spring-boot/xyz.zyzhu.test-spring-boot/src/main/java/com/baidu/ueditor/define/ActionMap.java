package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:07:54</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.define.ActionMap</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ActionMap
{
	public static final Map<String,Integer> mapping = new HashMap<String,Integer>()
	{
	};
	static
	{
		mapping.put("config", ActionMap.CONFIG);
		mapping.put("uploadimage", ActionMap.UPLOAD_IMAGE);
		mapping.put("uploadscrawl", ActionMap.UPLOAD_SCRAWL);
		mapping.put("listimage", ActionMap.LIST_IMAGE);
		mapping.put("uploadfile", ActionMap.UPLOAD_FILE);
		mapping.put("listfile", ActionMap.LIST_FILE);
		mapping.put("uploadvideo", ActionMap.UPLOAD_VIDEO);
	}
	public static final int	CONFIG			= 0;
	public static final int	UPLOAD_IMAGE	= 1;
	public static final int	UPLOAD_SCRAWL	= 2;
	public static final int	UPLOAD_VIDEO	= 3;
	public static final int	UPLOAD_FILE		= 4;
	public static final int	CATCH_IMAGE		= 5;
	public static final int	LIST_FILE		= 6;
	public static final int	LIST_IMAGE		= 7;

	public static int getType(String key)
	{
		return ((Integer) mapping.get(key)).intValue();
	}
}
