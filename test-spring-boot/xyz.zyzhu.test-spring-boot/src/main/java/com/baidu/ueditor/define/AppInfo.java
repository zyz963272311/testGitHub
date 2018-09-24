package com.baidu.ueditor.define;

import java.util.HashMap;
import java.util.Map;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:09:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.define.AppInfo</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class AppInfo
{
	public static final int				SUCCESS					= 0;
	public static final int				MAX_SIZE				= 1;
	public static final int				PERMISSION_DENIED		= 2;
	public static final int				FAILED_CREATE_FILE		= 3;
	public static final int				IO_ERROR				= 4;
	public static final int				NOT_MULTIPART_CONTENT	= 5;
	public static final int				PARSE_REQUEST_ERROR		= 6;
	public static final int				NOTFOUND_UPLOAD_DATA	= 7;
	public static final int				NOT_ALLOW_FILE_TYPE		= 8;
	public static final int				INVALID_ACTION			= 101;
	public static final int				CONFIG_ERROR			= 102;
	public static final int				PREVENT_HOST			= 201;
	public static final int				CONNECTION_ERROR		= 202;
	public static final int				REMOTE_FAIL				= 203;
	public static final int				NOT_DIRECTORY			= 301;
	public static final int				NOT_EXIST				= 302;
	public static final int				ILLEGAL					= 401;
	public static Map<Integer,String>	info					= new HashMap<Integer,String>()
																{
																};

	public static String getStateInfo(int key)
	{
		return ((String) info.get(Integer.valueOf(key)));
	}
}
