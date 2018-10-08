package xyz.zyzhu.spring.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.liiwin.utils.MapUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.BreadcrumbNavigationRequest;
import xyz.zyzhu.spring.boot.model.BreadcrumbNavigationWaper;
import xyz.zyzhu.spring.boot.utils.BreadcrumbUtils;
/**
 * <p>标题： 面包屑Controller</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月31日 上午10:12:24</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.AotoCodeController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/BreadNavigat")
public class BreadcrumbNavigationController
{
	@RequestMapping(path = "/Navication", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public BreadcrumbNavigationWaper<Object> getNavication(BreadcrumbNavigationRequest request)
	{
		if (request == null)
		{
			return null;
		}
		String node = request.getNode();
		String nodeComumn = StrUtil.obj2str(request.getNodeColumn(), "node");
		String nameComumn = StrUtil.obj2str(request.getNameColumn(), "mname");
		String urlComumn = StrUtil.obj2str(request.getUrlColumn(), "url");
		String strSplit = StrUtil.obj2str(request.getStrSplit(), ".");
		String menuTable = request.getMenuTable();
		if (StrUtil.isStrTrimNull(menuTable))
		{
			throw new RuntimeException("菜单表名不可位空");
		}
		BootDatabase db = BootDatabasePoolManager.getReadDatabaseByTable(menuTable);
		String sql = "select " + nodeComumn + "," + nameComumn + "," + urlComumn + " from " + menuTable;
		String[] split = nodeComumn.trim().split(" ");
		String nc = split[split.length - 1];
		split = nameComumn.trim().split(" ");
		String namec = split[split.length - 1];
		split = urlComumn.trim().split(" ");
		String uc = split[split.length - 1];
		Map<String,Object> queryParams = new HashMap<>();
		if (StrUtil.isNoStrTrimNull(node))
		{
			sql = sql + " where " + nc + "=:" + nc;
			queryParams.put(nc, node);
		}
		List<Map<String,Object>> menuList = db.getListMapFromSql(sql, queryParams);
		if (menuList == null || menuList.isEmpty())
		{
			return null;
		}
		Map<String,List<Map<String,Object>>> hashMap = MapUtil.buildMapByList(menuList, nc, null);
		Map<String,List<Map<String,Object>>> treeMap = new TreeMap<>(hashMap);
		BreadcrumbNavigationWaper<Object> breadcrumb = BreadcrumbUtils.getBreadcrumb(treeMap, nc, namec, uc, strSplit);
		BootDatabasePoolManager.close(db);
		return breadcrumb;
	}

	/**
	 * 获取面包屑
	 * @param request
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/NavicationOne", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public BreadcrumbNavigationWaper<Object> getNavicationOne(BreadcrumbNavigationRequest request)
	{
		if (request == null)
		{
			return null;
		}
		String node = request.getNode();
		request.setNode(null);
		BreadcrumbNavigationWaper<Object> navication = getNavication(request);
		if (navication != null)
		{
			List<BreadcrumbNavigationWaper<Object>> children = navication.getChildren();
			BreadcrumbNavigationWaper<Object> result = navication(children, node);
			return result;
		}
		return null;
	}

	/**
	 * 递归进行数据的重新组装
	 * @param list
	 * @param node
	 * @return
	 * 赵玉柱
	 */
	public BreadcrumbNavigationWaper<Object> navication(List<BreadcrumbNavigationWaper<Object>> list, String node)
	{
		if (list == null || list.isEmpty() || StrUtil.isStrTrimNull(node))
		{
			return null;
		}
		List<BreadcrumbNavigationWaper<Object>> collect = list.stream().filter(s -> StrUtil.isStrLike(node, s.getNode() + "%")).collect(Collectors.toList());
		if (collect == null || collect.isEmpty())
		{
			return null;
		}
		BreadcrumbNavigationWaper<Object> result = collect.get(0);
		List<BreadcrumbNavigationWaper<Object>> children = result.getChildren();
		if (children != null && !children.isEmpty())
		{
			BreadcrumbNavigationWaper<Object> navication = navication(children, node);
			if (navication != null)
			{
				result.set(navication);
			}
		}
		return result;
	}
}
