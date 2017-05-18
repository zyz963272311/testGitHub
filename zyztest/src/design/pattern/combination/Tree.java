package design.pattern.combination;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月23日 下午6:03:08</p>
 * <p>类全名：design.pattern.combination.Tree</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Tree
{
	TreeNode	root;

	/**
	 * 
	 */
	public Tree(String name)
	{
		root = new TreeNode(name);
	}

	public TreeNode getRoot()
	{
		return this.root;
	}
}
