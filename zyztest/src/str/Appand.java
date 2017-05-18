package str;

import java.util.Scanner;
public class Appand
{
	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		StringBuffer str = new StringBuffer();
		StringBuffer str1 = new StringBuffer();
		while (true)
		{
			str1.setLength(0);
			str.setLength(0);
			System.out.println("\n\n\n\n\n======================================================");
			System.out.println("请输入末级菜单:");
			String strs = scanner.next();
			str.append(strs);
			System.out.println("请输入父级菜单对应的菜单号码:");
			String[] type = { "ICIP-企业端-PSP", "ICIP-监管端-AQSIP" };
			for (int i = 0; i < type.length; i++)
			{
				System.out.println("i=\t" + i + "\t菜单名=\t" + type[i]);
			}
			switch (scanner.nextInt())
			{
			case 0:
				str1.append("4520.PSP.").append(str);
				break;
			case 1:
				str1.append("4530.AQSIQ.").append(str);
				break;
			default:
				str1.append("error!没有此菜单父级");
				break;
			}
			System.out.println("末级菜单为:\t\t" + str + "\n添加父级后菜单为:\t\t" + str1);
			continue;
		}
	}
}
