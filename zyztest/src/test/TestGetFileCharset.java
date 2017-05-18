package test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import file.GetFileCharset;
/**
 * <p>标题： 入境进区商品改单监听</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年8月24日 下午2:12:49</p>
 * <p>类全名：test.TestGetFileCharset</p>
 * 作者：冯继亮
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestGetFileCharset
{
	/**
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		GetFileCharset getFileCharset = new GetFileCharset();
		List<String> list = new ArrayList<>();
		List<Long> list1 = new ArrayList<>();
		File file = new File("D:\\mytest\\src\\test");
		File[] folder = file.listFiles();
		for (int i = 0; i < folder.length; i++)
		{
			if (folder[i].isFile())
			{
				list.add(folder[i].getPath());
				list1.add(folder[i].length());
			}
		}
		try
		{
			for (int i = 0; i < list.size(); i++)
			{
				System.out.println("=====================================");
				System.out.println("fileName=\t" + list.get(i) + "\tfileSize=" + list1.get(i));
				System.out.println(getFileCharset.getFileCharset(list.get(i)));
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			throw new RuntimeException("报错内容", e);
		}
	}
}
