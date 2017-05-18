package file;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.CharSetUtils;
/**
 * <p>标题： 测试apache-lang包中的*Utils类的作用</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月21日 上午11:12:45</p>
 * <p>类全名：file.CommonLang</p>
 * 作者：x250-2
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class CommonLang
{
	/**
	 * 测试commons-lang包
	 * @param args
	 * x250-2
	 */
	public static void main(String[] args)
	{
		testArrayUtils();
		testBooleanUtils();
		testCharSetUtils();
	}

	/**
	 * 测试CharSetUtils
	 * x250-2
	 */
	private static void testCharSetUtils()
	{
		squeeze();
		//keep();
		//delete();
		//System.out.println("test");
		//count();
	}

	/**
	 * CharSetUtils.squeeze(String str,String set) 合并str中出现的每一个set元素
	 * CharSetUtils.squeeze(String str,String[] set) 合并str中出现的每一个set元素
	 * x250-2
	 */
	private static void squeeze()
	{
		String str = CharSetUtils.squeeze("3211111113213222222222222221333333321", "3 1");
		System.out.println(str);
	}

	/**
	 * CharSetUtils.keep(String str,String set) 删除str中除了set中含有的元素外的其他元素，并返回
	 * CharSetUtils.keep(String str,String[] set) 删除str中除了set中含有的元素外的其他元素，并返回
	 * x250-2
	 */
	private static void keep()
	{
		String str = CharSetUtils.keep("321321321", "124");
		System.out.println(str);
		str = CharSetUtils.keep("321321321", new String[] { "12", "34" });
		System.out.println(str);
	}

	/**
	 * CharSetUtils.delete(String str,String set)删除str中的set元素
	 * CharSetUtils.delete(String str,String[] set)删除str中包含set的各个元素
	 * x250-2
	 */
	private static void delete()
	{
		String str = CharSetUtils.delete("321321321321", "3");
		System.out.println(str);
		str = CharSetUtils.delete("321321321321", new String[] { "3", "2" });
		System.out.println(str);
	}

	/**
	 * CharSetUtils.count(String str,String set)返回Str中包含set的个数
	 * CharSetUtils.count(String str,String[] set)返回Str中包含set所有元素的个数
	 * x250-2
	 */
	private static void count()
	{
		int count = CharSetUtils.count("321321321321", "3");
		System.out.println(count);
		count = CharSetUtils.count("321321321321", new String[] { "1", "2" });
		System.out.println(count);
	}

	/**
	 * @param b
	 * 测试BooleanUtils工具类,内部没有什么特殊
	 * x250-2
	 */
	private static void testBooleanUtils()
	{
	}

	/**
	 * 测试ArrayUtils
	 * x250-2
	 */
	private static void testArrayUtils()
	{
		boolean[] boolArray = new boolean[5];
		//objAndPrim();
		//contains(boolArray);
		//clone(boolArray);
		//remove(boolArray);
		//add();
	}

	/**
	 * ArrayUtils.toObject() 将基本数据类型数组转换成包装类型数组
	 * ArrayUtils.toPrimitive() 将包装类型数组转换成基本数据类型数组
	 * x250-2
	 */
	private static void objAndPrim()
	{
		Boolean[] objArray = new Boolean[] { true, false, true, false, true };
		boolean[] boolArray = new boolean[] { true, false, true, false, true };
		ArrayUtils.toPrimitive(objArray);
		ArrayUtils.toObject(boolArray);
	}

	/**
	 * @param boolArray
	 * ArrayUtils.contains(Object[] arr,Object value) 查找arr中是否有value这个元素
	 * x250-2
	 */
	private static void contains(boolean[] boolArray)
	{
		if (ArrayUtils.contains(boolArray, false))
		{
			System.out.println(true);
		}
	}

	/**
	 * @param boolArray
	 * ArrayUtils.clone(Object[] arr) 将arr克隆
	 * x250-2
	 */
	private static void clone(boolean[] boolArray)
	{
		boolean[] cloneArray;
		cloneArray = ArrayUtils.clone(boolArray);
		for (int i = 0; i < cloneArray.length; i++)
		{
			System.out.println("cloneArray[i]=" + cloneArray[i] + "boolArray[i]" + boolArray[i]);
		}
	}

	/**
	 * @param boolArray
	 * ArrayUtils.remove(Object[] arr,int idx) 删除arr中第idx个元素
	 * ArrayUtils.remove(Object[] arr,Object element) 删除arr中第一次出现的element元素
	 * x250-2
	 */
	private static void remove(boolean[] boolArray)
	{
		boolArray = ArrayUtils.removeElement(boolArray, true);
		for (int j = 0; j < boolArray.length; j++)
		{
			System.out.println(boolArray[j]);
		}
	}

	/**
	 * ArrayUtils.add(Object[] arr,Object element) 在arr后直接添加一个element
	 * ArrayUtils.add(Object[] arr,int idx,Object element) 在arr的第idx位置插入element
	 * ArrayUtils.addAll(Object[] arr,Object[] elements) 在arr后接入elements
	 * ArrayUtils.add
	 * x250-2
	 */
	private static void add(boolean[] boolArray)
	{
		boolArray = ArrayUtils.addAll(boolArray, new boolean[] { true, true, true });
		for (int j = 0; j < boolArray.length; j++)
		{
			System.out.println(boolArray[j]);
		}
	}
}
