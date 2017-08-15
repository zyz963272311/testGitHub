package test;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import net.sf.json.JSONObject;
import util.ArrayUtil;
/**
 * <p>标题： 测试类</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年2月14日 下午5:13:59</p>
 * <p>类全名：test.Test_84</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test_84
{
	private final Map<String,String>	map	= new HashMap<>();

	public static void main(String[] args) throws NoSuchMethodException, SecurityException
	{
		System.out.println(System.getProperty("user.dir"));
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void test10()
	{
		List<String> listA = new ArrayList<>();
		List<String> listB = new ArrayList<>();
		String[] a = { "0", "a", "b", "c", "d", "e", "f", "g" };
		String[] b = { "-1", "a", "3", "c", "d", "h", "i", "j" };
		for (String _a : a)
		{
			listA.add(_a);
		}
		for (String _b : b)
		{
			listB.add(_b);
		}
		System.out.println(listA);
		System.out.println(listB);
		List<String> listC = new ArrayList<>();
		for (int i = listB.size() - 1; i >= 0; i--)
		{
			String lB = listB.get(i);
			for (int j = 0; j < listA.size(); j++)
			{
				String lA = listA.get(j);
				if (lA.equals(lB))
				{
					//break;
					listB.remove(j);
				}
			}
		}
		System.out.println(listC);
		System.out.println(listB);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main7()
	{
		Map<String,BigDecimal> t = new HashMap<String,BigDecimal>();
		for (int i = 0; i < 10; i++)
		{
			t.put("key" + i, new BigDecimal(i));
		}
		t.put("key" + 10, new BigDecimal(5));
		MyComp comp = new MyComp(t);
		TreeMap<String,BigDecimal> tm = new TreeMap<>(comp);
		TreeMap<String,BigDecimal> tm1 = new TreeMap<>(comp);
		tm.putAll(t);
		Set<String> keys = t.keySet();
		for (String key : keys)
		{
			tm1.put(key, t.get(key));
		}
		System.out.println(t);
		System.out.println(tm);
		System.out.println(tm1);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main6()
	{
		List<String> list = new ArrayList<>();
		list.add(0, "111");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
		list.add(1, "222");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
		list.add(0, "333");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
		list.add(0, "444");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
		list.add(0, "555");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
		list.add(0, "666");
		for (String s : list)
		{
			System.out.println(s);
		}
		System.out.println("=====================");
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main10()
	{
		List<Object> test = new ArrayList<>();
		//		String[] t = test.toArray((String[]) java.lang.reflect.Array.newInstance(String.class, test.size()));
		String[] t = ArrayUtil.toArray(test, String.class);
		System.out.println(t + "\t" + t.length);
		for (int i = 0; i < t.length; i++)
		{
			System.out.println("sssssssssss" + t[i]);
		}
	}

	private void test()
	{
		for (int i = 0; i < 10; i++)
		{
			map.put("key" + i, "value" + i);
		}
		test1();
	}

	private void test1()
	{
		Set<String> keys = map.keySet();
		for (String key : keys)
		{
			System.out.println(map.get(key));
		}
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main0()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2007);
		calendar.set(Calendar.MONTH, Calendar.MARCH);
		calendar.set(Calendar.DATE, 30);
		System.out.println(dateFormat.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(dateFormat.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(dateFormat.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(dateFormat.format(calendar.getTime()));
	}

	void testFinal()
	{
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main4()
	{
		List<Map<String,Object>> list = new ArrayList<>();
		for (int i = 0; i < 5; i++)
		{
			Map<String,Object> map = new HashMap<>();
			map.put("" + i + "", i);
			map.put("a", "a");
			map.put("b", "b");
			map.put("c", "c");
			list.add(map);
		}
		System.out.println(list);
		for (Map<String,Object> map : list)
		{
			map.put("d", "d");
		}
		System.out.println(list);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main5()
	{
		Good good = new Good();
		Class<Good> cls = Good.class;
		Method[] msds = cls.getDeclaredMethods();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", "good_name");
		map.put("2", 2);
		try
		{
			for (Method m : msds)
			{
				System.out.println(m.getName());
				if (m.getName().equals("setGood_name"))
				{
					m.invoke(good, map.get("1"));
				}
				if (m.getName().equals("setIma_url"))
				{
					m.invoke(good, map.get("2"));
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println(good);
	}

	/**
	 * @throws NoSuchMethodException
	 * 赵玉柱
	 */
	private static void main3() throws NoSuchMethodException
	{
		Bean1 b = new Bean1();
		Class<Bean1> cls = Bean1.class;
		Method m = cls.getDeclaredMethod("getAge");
		System.out.println(m.getName());
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main2()
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("111", 111);
		map.put("222", 222);
		System.out.println(map.size());
		System.out.println(map);
		Object v = map.remove("111");
		System.out.println(v);
		System.out.println(map.size());
		System.out.println(map);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void main1()
	{
		JSONObject obj = new JSONObject();
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("good_name", "2");
		map.put("likes", "3");
		map.put("ima_url", 321);
		map.put("integral", "321");
		list.add(map);
		obj.put("list", list);
		List<Map<String,Object>> arr = (List<Map<String,Object>>) obj.get("list");
		Good good = new Good();
		List<Good> goodList = new ArrayList<>();
		//这里也可以写成setObject(arr, Good.class, goodList);
		setObject(arr, good.getClass(), goodList);
		System.out.println(arr);
		System.out.println(goodList);
	}

	/**
	 * 
	 * @param arr	源list数组
	 * @param cls	要转换成的类型
	 * @param objectList	要转换到的list集合，里面放的是cls类型的对象
	 * <br>这种方式是为了让这个方法可以公用
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Object> void setObject(List<Map<String,Object>> arr, Class cls, List<T> objectList)
	{
		if (null == arr || arr.size() == 0)
		{
			//说明传递过来的list集合为空
			return;
		}
		for (Map<String,Object> objectMap : arr)
		{
			if (null == objectMap || objectMap.size() == 0)
			{
				//说明list集合中数据为空
				return;
			}
			Set<String> keys = objectMap.keySet();
			if (null == keys || keys.size() == 0)
			{
				//说明llist集合中元素为空，等同于上一个
				return;
			}
			Object t = null;
			try
			{
				t = cls.newInstance();
			} catch (Exception e1)
			{
				throw new RuntimeException("报错内容", e1);
			}
			Method[] methods = cls.getDeclaredMethods();
			if (null == methods || methods.length == 0)
			{
				//说明类中没有定义过方法，不能进行操作
				return;
			}
			Map<String,Method> methodMap = new HashMap<>();
			for (Method m : methods)
			{
				//将方法方法放map中，相当于准备数据，continekey效率比for循环的查找方式要快很多
				methodMap.put(m.getName(), m);
			}
			for (String key : keys)
			{
				String firstChar = key.substring(0, 1).toUpperCase();
				String otherChars = key.substring(1);
				try
				{
					//set方法的第一个参数大写key为abc，set方法应该为setAbc，即前补set，并且set后的第一个首字母大写
					String msdName = "set" + firstChar + otherChars;
					//如果方法名跟msdMap中的某一个方法名相同，则说明这个方法名是要寻找的方法名，因为是set方法，所以没有返回值
					if (methodMap.containsKey(msdName))
					{
						//获取方法
						Method method = methodMap.get(msdName);
						//执行方法，第一个参数是执行的对象，相当于Good t = new Good();
						//第二个参数是参数列表，即这个方法锁接收的参数，比如setABC(String a,String b String c),参数列表就是abc的值
						method.invoke(t, objectMap.get(key));
					}
				} catch (Exception e)
				{
					throw new RuntimeException("报错内容", e);
				}
			}
			//将对象塞到list中，list是对象，传递的是地址，所以，这里面不需要将list返回
			objectList.add((T) t);
		}
	}
}
class Good
{
	private String	good_name;
	private String	likes;
	private int		ima_url;
	private String	integral;

	public String getGood_name()
	{
		return good_name;
	}

	public void setGood_name(String good_name)
	{
		this.good_name = good_name;
	}

	public String getLikes()
	{
		return likes;
	}

	public void setLikes(String likes)
	{
		this.likes = likes;
	}

	public int getIma_url()
	{
		return ima_url;
	}

	public void setIma_url(int ima_url)
	{
		this.ima_url = ima_url;
	}

	public String getIntegral()
	{
		return integral;
	}

	public void setIntegral(String integral)
	{
		this.integral = integral;
	}

	@Override
	public String toString()
	{
		return "Good [good_name=" + good_name + ", likes=" + likes + ", ima_url=" + ima_url + ", integral=" + integral + "]";
	}
}
class A
{
	private int	a;

	public int getA()
	{
		return a;
	}

	public void setA(int a)
	{
		//setValue("a", a);
		this.a = a;
	}

	@Override
	public String toString()
	{
		return "a=" + a;
	}
}
class B extends A
{
	private int	b;

	public int getB()
	{
		return b;
	}

	public void setB(int b)
	{
		//setValue("b", b);
		this.b = b;
	}
}
class MyComp implements Comparator<String>
{
	Map<String,BigDecimal>	base;

	public MyComp(Map<String,BigDecimal> base)
	{
		this.base = base;
	}

	@Override
	public int compare(String o1, String o2)
	{
		return base.get(o2).compareTo(base.get(o1));
	}
}
