package mapandlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
/**
 * <p>标题： map与list的有序与无序作用</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 赵玉柱</p>
 * <p>创建日期：2016年8月25日 上午9:39:16</p>
 * <p>类全名：mapandlist.List</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class List
{
	java.util.List<String>	arrayList	= new ArrayList<String>();
	java.util.List<String>	linkedList	= new LinkedList<String>();
	java.util.List<String>	vector		= new Vector<String>();

	public java.util.List<String> getArrayList(String[] str)
	{
		for (int i = 0; i < str.length; i++)
		{
			arrayList.add(str[i]);
		}
		return arrayList;
	}

	public java.util.List<String> getLinkedList(String[] str)
	{
		return linkedList;
	}

	public java.util.List<String> getVector(String[] str)
	{
		return vector;
	}
}
