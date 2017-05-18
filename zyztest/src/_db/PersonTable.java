package _db;

import java.util.List;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 下午4:12:00</p>
 * <p>类全名：_db.util.PersonTable</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PersonTable
{
	private static PersonTable	personTable;

	private PersonTable()
	{
	}

	public static synchronized PersonTable getInstance()
	{
		if (personTable == null)
		{
			personTable = new PersonTable();
		}
		return personTable;
	}

	List<Person>	personList;
}
