package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import search.Search;
import search.SequelSearch;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年9月13日 下午4:43:28</p>
 * <p>类全名：test.TestSearch</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestSearch
{
	/**
	 * @param args
	 * @author x250-2
	 */
	@SuppressWarnings({ "resource", "null" })
	public static void main(String[] args)
	{
		String[] searchStr = { "顺序查找" };
		List<Long> list = new ArrayList<Long>();
		String[] typeStr = { "int", "long", "float", "double", "char", "boolean", "byte", "short", "Str[]", "String" };
		int size, method, type;
		Object o;
		Scanner scanner;
		Search search;
		while (true)
		{
			System.out.println("=====================START====================");
			System.out.println("请按照菜单序号选择一种查找方式");
			scanner = new Scanner(System.in);
			//打印查找方式菜单
			for (int i = 0; i < searchStr.length; i++)
			{
				System.out.println("菜单:\t" + searchStr[i] + "\t序号:\t" + i);
			}
			//输入打印方式
			method = scanner.nextInt();
			//对打印方式进行选择
			switch (method)
			{
			case 0:
				search = new SequelSearch();
				break;
			default:
				System.err.println("您输入的序号不在菜单内，请重新程序");
				continue;
			}
			//输入数组或字符串大小
			System.out.println("请输入源数组或字符串的大小");
			size = scanner.nextInt();
			System.out.println("请按照菜单序号输入源数组的类型");
			//打印数据类型，用来对数据进行输入
			for (int i = 0; i < typeStr.length; i++)
			{
				System.out.println("数据类型:\t" + typeStr[i] + "\t序号:\t" + i);
			}
			type = scanner.nextInt();
			//初始化Object数组，String数组，String字符串，并设置为空，防止提交错误
			Object[] from = null;
			String[] from1 = null;
			String fromStr = null;
			/**
			 * 对方式进行选择，
			 * 其中0-7这8类基本类型使用Objext进行数据的获取，
			 * 8 String数组类型用String数组方式获取数据，
			 * 9 String类型用String类型获取数据
			 * 其他的数字说明输入错误，将进行红字提示进行重新输入
			 * @author x250-2
			 */
			switch (type)
			{
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				from = getObject(from, size, type);
				break;
			case 8:
				from1 = getObject(from1, size);
				break;
			case 9:
				fromStr = getObject(fromStr, size);
				break;
			default:
				System.err.println("您输入的菜单序号不被包含在数据类型中");
				continue;
			}
			/**
			 * 先对获取的数据进行打印，
			 * 若数据不为空
			 * 对于数组方式，由于打印比较耗时，
			 * 若数组长度大于1000，将仅仅打印最后的1000条记录
			 * 若数组长度小于等于1000，将全部打印
			 * 对于字符串方式，将全部打印成一行
			 * 锁数据均为空，说明没有选择任意一种方式，将用红字提示，数据为空
			 * @author x250-2
			 */
			if (from != null && from.length > 0)
			{
				for (int i = (size > 1000 ? size - 1000 : 0); i < size; i++)
				{
					System.out.println(i + "\t\t" + from[i]);
				}
			} else if (from1 != null && from1.length > 0)
			{
				for (int i = (size > 1000 ? size - 1000 : 0); i < size; i++)
				{
					System.out.println(i + "\t\t" + from1[i]);
				}
			} else if (fromStr != null || from.length > 0)
			{
				System.out.println(fromStr);
			} else
			{
				System.err.println("您输入的数据为空");
			}
			System.out.println("请输入要查找的数据");
			/**
			 * 将根据type类型对将要查询的数据进行获取。
			 * 0-7这8种数据将用随机数的方式进行获取数据
			 * 其中char类型与String类型，通过charStr字符数组的随机数位置，进行字符的获取
			 * @author x250-2
			 */
			o = getNextObject(type);
			/**
			 * 获取数据之后将依照之前对数据是否为空的检查，
			 * 若数据不为空，将按照各自的方式进行数据的查找
			 * 8种基本数据类型，将用Search.search(Object[] from,Object o)的方式进行数据的搜索
			 * 字符串数组数据类型，江将用Search.search(String[] from, Object o)的方式进行数据的搜索
			 * 字符串数据类型，将用Search.search(String from,Object o)的方式进行数据的搜索
			 * @author x250-2
			 */
			if (from != null && from.length > 0)
			{
				list = search.search(from, o);
				for (int i = (size > 1000 ? size - 1000 : 0); i < size; i++)
				{
					System.out.println(i + "\t\t" + from[i]);
				}
			} else if (from1 != null && from1.length > 0)
			{
				list = search.search(from1, o);
				for (int i = (size > 1000 ? size - 1000 : 0); i < size; i++)
				{
					System.out.println(i + "\t\t" + from1[i]);
				}
			} else if (fromStr != null || fromStr.length() > 0)
			{
				list = search.search(fromStr, o);
			} else
			{
				System.err.println("您输入的数据为空");
			}
			System.out.println("\r\n查找数据为" + o);
			/**
			 * 获取查找之后的list集合，
			 * 集合的前list.size-1位为查找到的数据对应数组或字符串的位置，
			 * 最后一位存放的查找时间，
			 * 若没有查找到要查找的内容，集合的第一位将返回-1，作为没有查找到数据的标志。
			 * @author x250-2
			 */
			if (list.get(0) != -1)
			{
				System.out.println("查到个数为" + (list.size() - 1));
				if (from != null && from.length > 0)
				{
					for (int i = 0; i < (list.size() - 1); i++)
					{
						System.out.println("序号:\t" + i + "\t位置:\t" + Integer.parseInt(list.get(i).toString()) + "\t值为:\t" + from[Integer.parseInt(list.get(i).toString())]);
					}
				} else if (from1 != null && from1.length > 0)
				{
					for (int i = 0; i < (list.size() - 1); i++)
					{
						System.out.println("序号:\t" + i + "\t位置:\t" + Integer.parseInt(list.get(i).toString()) + "\t值为:\t" + from1[Integer.parseInt(list.get(i).toString())]);
					}
				} else if (fromStr != null || fromStr.length() > 0)
				{
					for (int i = 0; i < (list.size() - 1); i++)
					{
						System.out.println("序号:\t" + i + "\t位置:\t" + Integer.parseInt(list.get(i).toString()) + "\t值为:\t" + o.toString());
					}
				}
			} else
			{
				System.out.println("查到的个数为0个");
			}
			//打印查找的时间
			System.out.println("查找时间为" + list.get(list.size() - 1) + "毫秒");
			System.out.println("======================END=====================\r\n\r\n");
		}
	}

	/**
	 * 将通过Random的内置函数进行7种基本类型随机数的获取，
	 * 对于Char类型，将用charStr作为模板，通过随机数，进行数据的获取，
	 * @param from 源Object数组
	 * @param size 源Object数组的长度
	 * @param type 源Object数组元素的数据类型
	 * @return 返回一个Object数组<br>
	 * @author x250-2
	 */
	static Object[] getObject(Object[] from, int size, int type)
	{
		from = new Object[size];
		//基本数据类型通过随机数进行控制
		char[] charStr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		Random random = new Random();
		switch (type)
		{
		case 0:
			//int
			for (int i = 0; i < from.length; i++)
			{
				from[i] = random.nextInt(Integer.MAX_VALUE);
			}
			break;
		case 1:
			//long
			for (int i = 0; i < from.length; i++)
			{
				from[i] = random.nextLong();
			}
			break;
		case 2:
			//float
			for (int i = 0; i < from.length; i++)
			{
				from[i] = (float) (random.nextFloat() * Float.MAX_VALUE);
			}
			break;
		case 3:
			//double
			for (int i = 0; i < from.length; i++)
			{
				from[i] = (random.nextDouble()) * Double.MAX_VALUE;
			}
			break;
		case 4:
			//char
			for (int i = 0; i < from.length; i++)
			{
				from[i] = charStr[random.nextInt(charStr.length)];
			}
			break;
		case 5:
			//boolean
			for (int i = 0; i < from.length; i++)
			{
				from[i] = random.nextBoolean();
			}
			break;
		case 6:
			//byte
			for (int i = 0; i < from.length; i++)
			{
				from[i] = (byte) (random.nextInt(Byte.MAX_VALUE));
			}
			break;
		case 7:
			//short
			for (int i = 0; i < from.length; i++)
			{
				from[i] = (short) (random.nextInt(Short.MAX_VALUE));
			}
			break;
		}
		return from;
	}

	/**
	 * 将用charStr作为模板，通过随机数，进行数据的获取.
	 * @param fromStr 源字符串
	 * @param size    源字符串的长度
	 * @return 返回一个字符串
	 * @author x250-2
	 */
	static String getObject(String fromStr, int size)
	{
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		char[] charStr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		for (int i = 0; i < size; i++)
		{
			buffer.append(charStr[random.nextInt(charStr.length)]);
		}
		fromStr = buffer.toString();
		return fromStr;
	}

	/**
	 * 将用charStr作为模板，通过随机数，进行数据的获取.
	 * @param from 源字符串数组
	 * @param size 字符串数组元素个数
	 * @return 返回from字符串数组
	 * @author x250-2
	 */
	static String[] getObject(String[] from, int size)
	{
		Random random = new Random();
		from = new String[size];
		char[] charStr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		for (int i = 0; i < from.length; i++)
		{
			StringBuffer buffer = new StringBuffer();
			//添加每一个随机字符串
			for (int j = 0; j < random.nextInt(9) + 1; j++)
			{
				buffer.append(charStr[random.nextInt(charStr.length)]);
			}
			from[i] = buffer.toString();
			buffer.setLength(0);
		}
		return from;
	}

	/**
	 * 按照数据类型type进行搜索元素的获取
	 * @param type 数据类型代码<pre>0-7为基本数据类型代码</pre><pre>8-9为字符串数据类型代码</pre>
	 * @return
	 * x250-2
	 */
	static Object getNextObject(int type)
	{
		/*
		char[] charStr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		*/
		Object o = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		switch (type)
		{
		case 0:
			o = scanner.nextInt();
			break;
		case 1:
			o = scanner.nextLong();
			break;
		case 2:
			o = scanner.nextFloat();
			break;
		case 3:
			o = scanner.nextDouble();
			break;
		case 4:
			//此处写法必须如此，不然，o将获得的是String类型数据。
			String str = scanner.next();
			char[] tempChar = str.toCharArray();
			o = tempChar[0];
			break;
		case 5:
			o = scanner.nextBoolean();
			break;
		case 6:
			o = scanner.nextByte();
			break;
		case 7:
			o = scanner.nextShort();
			break;
		case 8:
		case 9:
			o = scanner.next();
			break;
		}
		return o;
	}
}
