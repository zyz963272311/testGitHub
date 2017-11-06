

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import zyzhu.xyz.code.Address;

/**
 * <p>标题： TestStudent</p>
 * <p>内容： TestStudent</p>
 * <p>创建时间： 2017年1月7日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： zyzhu.xyz.code.TestStudent</p>
 * <p>作者： Administrator</p>
 */
public class TestStudent
{

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	Student s;
	@Before
	public void init()
	{
		// 获取配置对象
		Configuration configuration = new Configuration().configure();
		// 获取服务注册类
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		// 创建会话工厂
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// 创建会话对象
		session = sessionFactory.openSession();
		// 开启事务
		transaction = session.beginTransaction();
	}

	@Test
	public void save()
	{
		Address address = new Address("110043", "辽宁");
		s = new Student(1, "赵玉柱", "男", "软件工程", address);
		session.save(s);
	}

	@Test
	public void select()
	{
		s = (Student) session.get(Student.class, 1);
		System.out.println(s.toString());
	}

	@Test
	public void update()
	{
		s = (Student) session.get(Student.class, 1);
		System.out.println(s.toString());
		s.setMajor("软件工程");
		session.update(s);
	}

	@Test
	public void delete()
	{
		session.delete("Student", 1);
	}
	@After
	public void onDestroy()
	{
		// 提交事务
		transaction.commit();
		// 关闭会话
		session.close();
		// 关闭会话工厂
		sessionFactory.close();
	}
}
