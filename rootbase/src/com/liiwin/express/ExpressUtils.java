package com.liiwin.express;

import java.util.HashMap;
import java.util.Map;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.config.BasConfig;
import com.liiwin.http.HttpClientUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： 快递信息Util类</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年6月4日 下午4:38:13</p>
 * <p>类全名：com.liiwin.express.ExpressUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ExpressUtils
{
	/**快递100企业查询*/
	public static final int	KUAIDI100_SEARCH	= 1;
	/**查询get*/
	public static final int	METHOD_GET			= 1;
	/**查询post*/
	public static final int	METHOD_POST			= 2;

	/**
	 * 根据快递单号获取快递信息
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getExpMessage(Map<String,Object> params)
	{
		return getExpMessage(params, 1);
	}

	/**
	 * 根据快递单号和快递公司获取快递信息
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getExpMessageByPostidAndComCode(Map<String,Object> params)
	{
		return getExpMessageByPostidAndComCode(params, 1);
	}

	/**
	 * 根据快递单号和快递公司获取快递信息
	 * @param params
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getExpMessageByPostidAndComCode(Map<String,Object> params, int type)
	{
		String result = null;
		String expSearchUrl = getExpSearchUrl(params, type);
		int expSearchMethod = getExpSearchMethod(type);
		switch (expSearchMethod)
		{
		case METHOD_GET:
			result = HttpClientUtil.sendHttpGet(expSearchUrl);
			break;
		case METHOD_POST:
			result = HttpClientUtil.sendHttpPost(expSearchUrl);
			break;
		default:
			throw new RuntimeException("不支持的请求方法");
		}
		if (StrUtil.isStrTrimNull(result))
		{
			return null;
		}
		JSONObject parseObject = JSONObject.parseObject(result);
		return parseObject;
	}

	/**
	 * 根据快递单号获取快递信息
	 * @param params
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getExpMessage(Map<String,Object> params, int type)
	{
		String comcode = getComCode(params, type);
		params.put("comnum", comcode);
		return getExpMessageByPostidAndComCode(params, type);
	}

	/**
	 * 根据快递单号获取默认的快递企业
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static String getComCode(Map<String,Object> params)
	{
		return getComCode(params, 1);
	}

	/**
	 * 根据快递单号获取默认快递企业   即快递企业的第一条
	 * @param params
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getComCode(Map<String,Object> params, int type)
	{
		JSONObject allComMessage = getAllComMessage(params, type);
		String comcode = null;
		if (allComMessage.containsKey("auto"))
		{
			JSONArray comCodeArray = allComMessage.getJSONArray("auto");
			if (!comCodeArray.isEmpty())
			{
				JSONObject comCodeJson = comCodeArray.getJSONObject(0);
				if (comCodeJson.containsKey("comCode"))
				{
					comcode = comCodeJson.getString("comCode");
				}
			}
		}
		return comcode;
	}

	/**
	 * 根据快递单号获取全部快递企业信息
	 * @param params
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getAllComMessage(Map<String,Object> params)
	{
		return getAllComMessage(params, 1);
	}

	/**
	 * 获取当前快递单号对应的全部快递企业
	 * @param params
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static JSONObject getAllComMessage(Map<String,Object> params, int type)
	{
		String searchUrl = getComSearchUrl(params, type);
		int comSearchMethod = getComSearchMethod(type);
		String result = null;
		switch (comSearchMethod)
		{
		case METHOD_GET:
			result = HttpClientUtil.sendHttpGet(searchUrl);
			break;
		case METHOD_POST:
			result = HttpClientUtil.sendHttpPost(searchUrl);
			break;
		default:
			throw new RuntimeException("不支持的请求方法");
		}
		if (StrUtil.isStrTrimNull(result))
		{
			return null;
		}
		JSONObject parseObject = JSONObject.parseObject(result);
		return parseObject;
	}

	/**
	 * 获取快递信息URL params中需要有key postid对应快递单号，comnum对应快递企业
	 * @param params
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getExpSearchUrl(Map<String,Object> params, int type)
	{
		//获取网站
		String searchWebSet = getSearchWebSet(type);
		//获取子url
		String expSearchChildUrl = getExpSearchChildUrl(type);
		//获取参数
		String expSearchFilter = getExpSearchFilter(type);
		//参数进行转换
		expSearchFilter = StrUtil.replaceMacro(expSearchFilter, params);
		return searchWebSet + "/" + expSearchChildUrl + "?" + expSearchFilter;
	}

	/**
	 * 获取快递企业信息 params中需要有key positid对应快递单号
	 * @param params 
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getComSearchUrl(Map<String,Object> params, int type)
	{
		//获取网站
		String searchWebSet = getSearchWebSet(type);
		//获取子url
		String comSearchChildUrl = getComSearchChildUrl(type);
		//获取参数
		String comSearchFilter = getComSearchFilter(type);
		//参数进行转换
		comSearchFilter = StrUtil.replaceMacro(comSearchFilter, params);
		return searchWebSet + "/" + comSearchChildUrl + "?" + comSearchFilter;
	}

	/**
	 * 获取快递信息查询的网站
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getSearchWebSet(int type)
	{
		switch (type)
		{
		case 1:
			return BasConfig.getPropertie("KUAIDI100");
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取快递企业信息查询的部分URL
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getComSearchChildUrl(int type)
	{
		switch (type)
		{
		case 1:
			return BasConfig.getPropertie("KUAIDI100-GET-EXP-COM");
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取快递企业查询的方法 1：get ；其他 2：post，默认1
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static int getComSearchMethod(int type)
	{
		switch (type)
		{
		case 1:
			return StrUtil.obj2int(BasConfig.getPropertie("KUAIDI100-GET-EXP-COM-METHOD"), 1);
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取快递企业查询的参数
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getComSearchFilter(int type)
	{
		switch (type)
		{
		case 1:
			return BasConfig.getPropertie("KUAIDI100-GET-EXP-COM-FILTER");
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取子路径
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getExpSearchChildUrl(int type)
	{
		switch (type)
		{
		case 1:
			return BasConfig.getPropertie("KUAIDI100-GET-EXP-MSG");
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取快递信息执行方法 get或post
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static int getExpSearchMethod(int type)
	{
		switch (type)
		{
		case 1:
			return StrUtil.obj2int(BasConfig.getPropertie("KUAIDI100-GET-EXP-MSG-METHOD"), 1);
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 获取快递信息的执行参数
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	public static String getExpSearchFilter(int type)
	{
		switch (type)
		{
		case 1:
			return BasConfig.getPropertie("KUAIDI100-GET-EXP-MSG-FILTER");
		default:
			throw new RuntimeException("暂不支持的类型" + type);
		}
	}

	/**
	 * 测试方法
	 * @param args
	 * 赵玉柱
	 */
	public static void main(String[] args)
	{
		Map<String,Object> params = new HashMap<>();
		params.put("postid", "540368551819");
		String comCode = getComCode(params);
		JSONObject allComMessage = getAllComMessage(params);
		JSONObject expMessage = getExpMessage(params);
		System.out.println(allComMessage);
		System.out.println(expMessage);
		System.out.println(comCode);
	}
}
