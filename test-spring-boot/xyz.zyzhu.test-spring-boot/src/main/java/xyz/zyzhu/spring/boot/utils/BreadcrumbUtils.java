package xyz.zyzhu.spring.boot.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BreadcrumbNavigationWaper;
/**
 * <p>标题： 面包屑工具类</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月15日 下午10:48:58</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.BreadcrumbUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BreadcrumbUtils
{
	public static <T extends Object> BreadcrumbNavigationWaper<T> getBreadcrumb(Map<String,List<Map<String,Object>>> menuListMap, String nodeColumn, String nameColumn, String urlColumn,
			String strSplit)
	{
		return getBreadcrumb(menuListMap, nodeColumn, nameColumn, urlColumn, strSplit, null);
	}

	public static <T extends Object> BreadcrumbNavigationWaper<T> getBreadcrumb(Map<String,List<Map<String,Object>>> menuListMap, String nodeColumn, String nameColumn, String urlColumn,
			String strSplit, Class<T> clazz)
	{
		BreadcrumbNavigationWaper<T> navigation = new BreadcrumbNavigationWaper<T>();
		//node之间的关系
		Map<String,List<Map<String,Object>>> nodeRela = new TreeMap<>();
		Map<String,Map<String,Object>> rootNodes = new HashMap<>();
		//将所有的节点添加到rootnode中
		for (Entry<String,List<Map<String,Object>>> menuEntry : menuListMap.entrySet())
		{
			Map<String,Object> menuMap = menuEntry.getValue().get(0);
			String node = StrUtil.obj2str(menuMap.get(nodeColumn));
			if (StrUtil.isNoStrTrimNull(node))
			{
				rootNodes.put(node, menuMap);
				nodeRela.put(node, new ArrayList<>());
			}
		}
		//将所有的非rootnode的节点移除
		for (Entry<String,List<Map<String,Object>>> menuEntry : menuListMap.entrySet())
		{
			Map<String,Object> menuMap = menuEntry.getValue().get(0);
			String node = StrUtil.obj2str(menuMap.get(nodeColumn));
			int p = node.lastIndexOf(strSplit);
			if (p > 0)
			{
				String parentNode = node.substring(0, p);
				if (nodeRela.containsKey(parentNode))
				{
					List<Map<String,Object>> list = nodeRela.get(parentNode);
					list.add(menuMap);
					rootNodes.remove(node);
				} else
				{
					while ((p = parentNode.lastIndexOf(strSplit)) > 0)
					{
						parentNode = parentNode.substring(0, p);
						if (nodeRela.containsKey(parentNode))
						{
							List<Map<String,Object>> list = nodeRela.get(parentNode);
							list.add(menuMap);
							rootNodes.remove(node);
							break;
						}
					}
				}
			}
		}
		//此时rootNodes仅剩下跟节点
		//nodeRela已将所有的节点进行关联
		if (rootNodes.size() >= 1)
		{
			navigation.setNode("");
			navigation.setName("");
			navigation.setUrl("");
			for (Entry<String,Map<String,Object>> rootEntry : rootNodes.entrySet())
			{
				Map<String,Object> value = rootEntry.getValue();
				String node = StrUtil.obj2str(value.get(nodeColumn));
				String name = StrUtil.obj2str(value.get(nameColumn));
				String url = StrUtil.obj2str(value.get(urlColumn));
				BreadcrumbNavigationWaper<T> childNavigation = new BreadcrumbNavigationWaper<T>();
				if (clazz != null)
				{
					JSONObject json = (JSONObject) JSONObject.toJSON(value);
					T t = JSONObject.toJavaObject(json, clazz);
					childNavigation.set(t);
				}
				childNavigation.setName(name);
				childNavigation.setNode(node);
				childNavigation.setUrl(url);
				navigation.addChild(childNavigation);
				buildBreadcrumb(childNavigation, nodeRela, nodeColumn, nameColumn, urlColumn, node, clazz);
			}
		}
		return navigation;
	}

	/**
	 * 组装树形节点
	 * @param navigation
	 * @param nodeRela
	 * @param nodeColumn
	 * @param nameColumn
	 * @param urlColumn
	 * @param node
	 * 赵玉柱
	 */
	public static <T extends Object> void buildBreadcrumb(BreadcrumbNavigationWaper<T> navigation, Map<String,List<Map<String,Object>>> nodeRela, String nodeColumn, String nameColumn,
			String urlColumn, String node, Class<T> clazz)
	{
		List<Map<String,Object>> list = nodeRela.get(node);
		if (!list.isEmpty())
		{
			for (Map<String,Object> value : list)
			{
				String childNood = StrUtil.obj2str(value.get(nodeColumn));
				String name = StrUtil.obj2str(value.get(nameColumn));
				String url = StrUtil.obj2str(value.get(urlColumn));
				BreadcrumbNavigationWaper<T> childNavigation = new BreadcrumbNavigationWaper<T>();
				if (clazz != null)
				{
					JSONObject json = (JSONObject) JSONObject.toJSON(value);
					T t = JSONObject.toJavaObject(json, clazz);
					childNavigation.set(t);
				}
				childNavigation.setName(name);
				childNavigation.setNode(childNood);
				childNavigation.setUrl(url);
				navigation.addChild(childNavigation);
				buildBreadcrumb(childNavigation, nodeRela, nodeColumn, nameColumn, urlColumn, childNood, clazz);
			}
		}
	}
}
