import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import zyzhu.xyz.code.Address;




/**
 * <p>���⣺ Student</p>
 * <p>���ݣ� Student</p>
 * <p>����ʱ�䣺 2017��1��7��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyzhu.xyz.code.Student</p>
 * <p>���ߣ� Administrator</p>
 */
@Entity
@Table(name = "STUDENT")
public class Student
{

	@Id
	private int id;
	private String name;
	private String sex;
	private String major;
	private Address address;

	public Student() {
	}

	/**
	 * @param id
	 * @param name
	 * @param sex
	 * @param major
	 */
	public Student(int id, String name, String sex, String major,
			Address address) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.address = address;
	}
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public String getMajor()
	{
		return major;
	}

	public void setMajor(String major)
	{
		this.major = major;
	}

	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	@Override
	public String toString()
	{
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", major=" + major + "]" + this.address.toString();
	}
}
