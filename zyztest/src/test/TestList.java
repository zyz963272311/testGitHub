package test;

import java.util.List;
/**
 * <p>标题： 测试List有序性</p>
 * <p>功能： </p>
 * <p>所属模块：个人测试</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北赵玉柱</p>
 * <p>创建日期：2016年8月25日 上午9:45:29</p>
 * <p>类全名：test.TestList</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestList
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String[] str = new String[20];
		for (int i = 0; i < 20; i++)
		{
			str[i] = "I am String" + (20 - i);
		}
		mapandlist.List list = new mapandlist.List();
		List<String> listTemp = list.getArrayList(str);
		for (int i = 0; i < listTemp.size(); i++)
		{
			System.out.println("i=\t" + i + "\tlist集合:\t" + listTemp.get(i));
		}
		//Map<String,String> map = new HashMap<>();
	}
}
