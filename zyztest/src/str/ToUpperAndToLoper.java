package str;

import java.util.Scanner;
public class ToUpperAndToLoper
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String str1 = null, str2 = null;
		;
		while (true)
		{
			System.out.println("请输入要转换的字符串");
			String str = scanner.nextLine();
			if (str == null || "".equals(str))
			{
				System.out.println("您输入的字符串为空");
				continue;
			}
			str1 = str.toUpperCase();
			str2 = str.toLowerCase();
			System.out.println("原本字符串为:\t" + str + "\n大写字符串为:\t" + str1 + "\n小写字符串为:\t" + str2 + "\n================================================================");
		}
	}
}
