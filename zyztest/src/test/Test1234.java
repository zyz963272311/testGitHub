package test;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月26日 下午8:28:00</p>
 * <p>类全名：test.Test1234</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test1234
{
	public static void main(String[] args)
	{
		Book book = new Book();
		book.setBookName("JAVA从入门到住院");
		book.setAuthor("神经病");
		book.setPrice(11.11);
		book.setStatus(1);
		book.print();
		book.sendBook();
		book.print();
		book.borrow();
		book.print();
		book.sendBook();
		book.print();
	}
}
class Book
{
	String	bookName;
	String	author;
	double	price;
	int		status;

	public void print()
	{
		System.out.println("=================图书信息====================");
		System.out.println("书名:" + bookName + "\n作者:" + author + "\n价格:" + price + "\n是否可借阅:" + (status == 1 ? "可借阅" : status == 2 ? "以借阅" : status == 3 ? "超期" : "没有此状态"));
	}

	public boolean borrow()
	{
		boolean flag = false;
		switch (this.status)
		{
		case 1:
			flag = true;
			status = 2;
			System.out.println("图书" + bookName + "借阅成功");
			break;
		case 2:
			System.out.println("当前图书为已借阅状态，不可重复借阅");
			break;
		case 3:
			System.out.println("当前图书为超期状态，不能被借阅");
			break;
		default:
			System.out.println("不应当存在的状态，请查证后");
			/*这个位置可以替换成抛出异常*/
		}
		return flag;
	}

	public boolean sendBook()
	{
		boolean flag = false;
		switch (this.status)
		{
		case 1:
			System.out.println("当前图书已经被归还，请勿重复归还");
			break;
		case 2:
			flag = true;
			status = 1;
			System.out.println("图书" + bookName + "归还成功");
			break;
		case 3:
			flag = true;
			status = 1;
			System.out.println("图书" + bookName + "归还成功");
			break;
		default:
			System.out.println("不应当存在的状态，请查证后");
			/*这个位置可以替换成抛出异常*/
		}
		return flag;
	}

	public String getBookName()
	{
		return bookName;
	}

	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
