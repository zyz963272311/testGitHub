package xyz.zyzhu.spring.boot.model;

/**
 * <p>标题： 面包屑请求参数</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月15日 下午2:15:45</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.BreadcrumbNavigationRequest</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BreadcrumbNavigationRequest extends BasObject
{
	private static final long	serialVersionUID	= 2128876462214092015L;
	private String				node;										//当前节点
	private String				nodeColumn;									//当前节点
	private String				nameColumn;									//当前节点
	private String				urlColumn;									//当前节点
	private String				menuTable;									//菜单的表名
	private String				strSplit;									//node之间的分隔符，用于生成导航栏

	public String getNode()
	{
		return node;
	}

	public void setNode(String node)
	{
		//setValue("node", node);
		this.node = node;
	}

	public String getMenuTable()
	{
		return menuTable;
	}

	public void setMenuTable(String menuTable)
	{
		//setValue("menuTable", menuTable);
		this.menuTable = menuTable;
	}

	public String getStrSplit()
	{
		return strSplit;
	}

	public String getNodeColumn()
	{
		return nodeColumn;
	}

	public void setNodeColumn(String nodeColumn)
	{
		//setValue("nodeColumn", nodeColumn);
		this.nodeColumn = nodeColumn;
	}

	public String getNameColumn()
	{
		return nameColumn;
	}

	public void setNameColumn(String nameColumn)
	{
		//setValue("nameColumn", nameColumn);
		this.nameColumn = nameColumn;
	}

	public String getUrlColumn()
	{
		return urlColumn;
	}

	public void setUrlColumn(String urlColumn)
	{
		//setValue("urlColumn", urlColumn);
		this.urlColumn = urlColumn;
	}

	public void setStrSplit(String strSplit)
	{
		//setValue("strSplit", strSplit);
		this.strSplit = strSplit;
	}
}
