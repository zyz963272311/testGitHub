

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
 * <p>���⣺ TestStudent</p>
 * <p>���ݣ� TestStudent</p>
 * <p>����ʱ�䣺 2017��1��7��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyzhu.xyz.code.TestStudent</p>
 * <p>���ߣ� Administrator</p>
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
		// ��ȡ���ö���
		Configuration configuration = new Configuration().configure();
		// ��ȡ����ע����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(configuration.getProperties())
				.buildServiceRegistry();
		// �����Ự����
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		// �����Ự����
		session = sessionFactory.openSession();
		// ��������
		transaction = session.beginTransaction();
	}

	@Test
	public void save()
	{
		Address address = new Address("110043", "����");
		s = new Student(1, "������", "��", "�������", address);
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
		s.setMajor("�������");
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
		// �ύ����
		transaction.commit();
		// �رջỰ
		session.close();
		// �رջỰ����
		sessionFactory.close();
	}
}
