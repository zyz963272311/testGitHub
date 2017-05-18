package design.pattern.combination;

import java.util.Enumeration;
import java.util.Vector;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午5:31:25</p>
 * <p>类全名：design.pattern.combination.TreeNode</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TreeNode
{
	private String					name;
	private TreeNode				parent;
	private final Vector<TreeNode>	childen	= new Vector<TreeNode>();

	public TreeNode(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public TreeNode getParent()
	{
		return parent;
	}

	public void setParent(TreeNode parent)
	{
		this.parent = parent;
	}

	public Enumeration<TreeNode> getChilden()
	{
		return childen.elements();
	}

	public void add(TreeNode node)
	{
		childen.add(node);
		node.setParent(this);
	}

	public void remove(TreeNode node)
	{
		childen.remove(node);
	}

	@Override
	public String toString()
	{
		return "TreeNode [name=" + name + ", parent=" + parent.getName() + ", childen=" + childen + "]";
	}
}
