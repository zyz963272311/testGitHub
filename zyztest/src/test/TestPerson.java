package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import collection.Person;
/**
 * <p>标题：测试Collections接口的部分方法 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月13日 下午2:51:40</p>
 * <p>类全名：test.TestPerson</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestPerson
{
	public static void main(String[] args)
	{
		List<Person> personList = new ArrayList<Person>();
		personList.add(new Person(15, "赵玉柱", 10));
		personList.add(new Person(20, "赵玉柱", 10));
		personList.add(new Person(15, "赵玉柱", 15));
		personList.add(new Person(23, "赵玉", 10));
		personList.add(new Person(15, "赵三", 10));
		personList.add(new Person(15, "你猜", 10));
		testUnmodifiableList(Collections.unmodifiableList(personList));
		personList = new ArrayList<Person>();
		personList.add(new Person(15, "赵玉柱", 10));
		personList.add(new Person(20, "赵玉柱", 10));
		personList.add(new Person(15, "赵玉柱", 15));
		personList.add(new Person(23, "赵玉", 10));
		personList.add(new Person(15, "赵三", 10));
		personList.add(new Person(15, "赵你猜", 10));
		personList.add(new Person(15, "玉柱", 10));
		personList.add(new Person(20, "玉柱", 10));
		personList.add(new Person(15, "玉柱", 15));
		personList.add(new Person(23, "玉", 10));
		personList.add(new Person(15, "三", 10));
		personList.add(new Person(15, "你猜", 10));
		personList.add(new Person(15, "王玉柱", 10));
		personList.add(new Person(20, "王玉柱", 10));
		personList.add(new Person(15, "王玉柱", 15));
		personList.add(new Person(23, "王玉", 10));
		personList.add(new Person(15, "王三", 10));
		personList.add(new Person(15, "王你猜", 10));
		personList.add(new Person(15, "x玉柱", 10));
		personList.add(new Person(20, "x玉柱", 10));
		personList.add(new Person(15, "x玉柱", 15));
		personList.add(new Person(23, "x玉", 10));
		personList.add(new Person(15, "x三", 10));
		personList.add(new Person(15, "x你猜", 10));
		testSort(personList);
		personList = new ArrayList<Person>();
		personList.add(new Person(15, "赵玉柱", 10));
		personList.add(new Person(20, "赵玉柱", 10));
		personList.add(new Person(15, "赵玉柱", 15));
		personList.add(new Person(23, "赵玉", 10));
		personList.add(new Person(15, "赵三", 10));
		personList.add(new Person(15, "赵你猜", 10));
		personList.add(new Person(15, "玉柱", 10));
		personList.add(new Person(20, "玉柱", 10));
		personList.add(new Person(15, "玉柱", 15));
		personList.add(new Person(23, "玉", 10));
		personList.add(new Person(15, "三", 10));
		personList.add(new Person(15, "你猜", 10));
		personList.add(new Person(15, "王玉柱", 10));
		personList.add(new Person(20, "王玉柱", 10));
		personList.add(new Person(15, "王玉柱", 15));
		personList.add(new Person(23, "王玉", 10));
		personList.add(new Person(15, "王三", 10));
		personList.add(new Person(15, "王而我却而我却而委屈儿武器而我却而我企鹅武器而我却而我企鹅武器你猜", 10));
		personList.add(new Person(15, "x玉柱", 10));
		personList.add(new Person(20, "x玉柱", 10));
		personList.add(new Person(15, "x玉柱", 15));
		personList.add(new Person(23, "x玉", 10));
		personList.add(new Person(15, "x三", 10));
		personList.add(new Person(15, "x你猜", 10));
		testSortByHashCode(personList);
	}

	private static void testSort(List<Person> personList)
	{
		System.out.println(personList.size());
		show(personList);
		long start = System.currentTimeMillis();
		Collections.sort(personList, new Comparator<Person>()
		{
			@Override
			public int compare(Person o1, Person o2)
			{
				int result = o1.getId() - o2.getId();
				result = result == 0 ? o1.getName().compareTo(o2.getName()) : result;
				result = result == 0 ? o1.getAge() - o2.getAge() : result;
				return result;
			}
		});
		long end = System.currentTimeMillis();
		System.out.println("成员比较时间" + (end - start));
		System.out.println("==================分割线===============");
		show(personList);
	}

	private static void testSortByHashCode(List<Person> personList)
	{
		System.out.println(personList.size());
		show(personList);
		long start = System.currentTimeMillis();
		Collections.sort(personList, new Comparator<Person>()
		{
			@Override
			public int compare(Person o1, Person o2)
			{
				int result = o1.hashCode() - o2.hashCode();
				return result;
			}
		});
		long end = System.currentTimeMillis();
		System.out.println("hasCode比较时间" + (end - start));
		System.out.println("==================分割线===============");
		show(personList);
	}

	/**
	 * 测试 Collections.unmodifiableList 方法不允许对list进行修改，当修改，将会报错
	 * @param personList
	 * x250-2
	 */
	private static void testUnmodifiableList(List<Person> personList)
	{
		//下面这行代码不被允许
		//personList.add(new Person(11, "eqeq", 111));
	}

	private static void show(List<Person> personList)
	{
		Iterator<Person> iter = personList.iterator();
		while (iter.hasNext())
		{
			System.out.println(iter.next());
		}
	}
}
