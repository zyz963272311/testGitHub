package zyzhu.xyz.code;

import javax.persistence.Embeddable;


/**
 * <p>标题： Address</p>
 * <p>内容： Address</p>
 * <p>创建时间： 2017年1月8日</p>
 * <p>copyRight @ zyzhu.xyz 2017</p>
 * <p>类全名： zyzhu.xyz.code.Address</p>
 * <p>作者： Administrator</p>
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
