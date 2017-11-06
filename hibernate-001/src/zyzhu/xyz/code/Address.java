package zyzhu.xyz.code;

import javax.persistence.Embeddable;


/**
 * <p>���⣺ Address</p>
 * <p>���ݣ� Address</p>
 * <p>����ʱ�䣺 2017��1��8��</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>��ȫ���� zyzhu.xyz.code.Address</p>
 * <p>���ߣ� Administrator</p>
 */
@Embeddable
public class Address
{

	private String postNumber;
	private String address;

	public Address() {
	}

	public Address(String postNumber, String address) {
		this.postNumber = postNumber;
		this.address = address;
	}
	public String getPostNumber()
	{
		return postNumber;
	}

	public void setPostNumber(String postNumber)
	{
		this.postNumber = postNumber;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String toString()
	{
		return "Address [postNumber=" + postNumber + ", address=" + address
				+ "]";
	}

}
