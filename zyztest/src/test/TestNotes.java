package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import design.pattern.bridge.IJDBC;
/**
 * <p>标题： 读取java代码中的注释</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年6月23日 下午3:06:31</p>
 * <p>类全名：test.TestNotes</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestNotes
{
	/*像这个注释的形式，可跨行*/
	public static final int	TYPE1	= 1;
	//像这个注释的形式，可跨行
	public static final int	TYPE2	= 2;
	/**像这个注释的形式，可跨行*/
	public static final int	TYPE3	= 3;

	public static void main(String[] args) throws IOException
	{
		getNote1(IJDBC.class, TYPE3);
	}

	/**
	 * 据获取某个类中的某类型的全部注释
	 * @param clz 类
	 * @param type 类型
	 * @return
	 * 赵玉柱
	 * @throws IOException 
	 */
	private static String[][] getNote1(Class clz, int type) throws IOException
	{
		//		System.out.println(file.exists());
		//		System.out.println(clz);
		//		System.out.println(clz.getPackage().getName());
		System.out.println(System.getProperty("user.dir"));
		//		System.out.println(clz.getResource(""));
		System.out.println(clz.getClassLoader().getResourceAsStream("a.txt"));
		FileReader reader = new FileReader(System.getProperty("user.dir") + "\\src\\" + clz.getName().replace('.', '\\') + ".java");
		BufferedReader br = new BufferedReader(reader);
		System.out.println(br);
		String str = "";
		StringBuffer sb = new StringBuffer();
		boolean isStarted = false;
		while ((str = br.readLine()) != null)
		{
			sb.append(str).append("\n");
			if (type == 1)
			{
				/**/
				int idx = 0;
				if (!isStarted)
				{
					//开始标志位
					idx = str.indexOf("/*");
					if (idx >= 0 && str.indexOf("/**") < 0)
					{
						isStarted = true;
					}
				}
				if (isStarted)
				{
					idx = str.indexOf("*");
					if (idx < 0)
					{
						//说明是如下的形式
						//行    /*
						//行    
						//行    
						//行    */
						for (int i = 0; i < str.length(); i++)
						{
							//第一个非空格的位置
							if (str.charAt(i) != ' ')
							{
								idx = i;
							}
						}
					} else
					{
						idx++;
					}
					//结束标志位
					int endIdx = str.indexOf("*/");
					if (endIdx < 0)
					{
						//说明没有到结束标志位
						endIdx = str.length();
					} else
					{
						isStarted = false;
					}
					String noteStr = "";
					if (endIdx > idx)
					{
						noteStr = str.substring(idx, endIdx);
						System.out.println(noteStr);
					}
				}
			}
			if (type == 2)
			{
				int idx = 0;
				idx = str.indexOf("//");
				String noteStr = "";
				if (idx >= 0)
				{
					if (isStarted == false)
					{
						isStarted = true;
					}
					noteStr = str.substring(idx + 2);
					System.out.println(noteStr);
				} else
				{
					isStarted = false;
				}
			}
			if (type == 3)
			{
				/**/
				int idx = 0;
				if (!isStarted)
				{
					//开始标志位
					idx = str.indexOf("/**");
					if (idx >= 0)
					{
						isStarted = true;
						idx = idx + 2;
					}
				}
				if (isStarted)
				{
					idx = str.indexOf("*");
					if (idx < 0)
					{
						//说明是如下的形式
						//行    /**
						//行    
						//行    
						//行    */
						for (int i = 0; i < str.length(); i++)
						{
							//第一个非空格的位置
							if (str.charAt(i) != ' ')
							{
								idx = i;
							}
						}
					} else
					{
						for (int i = idx; i < str.length(); i++)
						{
							if (str.charAt(i) == '*')
							{
								idx++;
							} else
							{
								break;
							}
						}
						idx++;
					}
					//结束标志位
					int endIdx = str.indexOf("*/");
					if (endIdx < 0)
					{
						//说明没有到结束标志位
						endIdx = str.length();
					} else
					{
						isStarted = false;
					}
					String noteStr = "";
					if (endIdx > idx)
					{
						noteStr = str.substring(idx, endIdx);
						System.out.println(noteStr);
					}
				}
			}
		}
		System.out.println("=======================开始========================");
		System.out.println(sb);
		System.out.println("=======================结束========================");
		br.close();
		reader.close();
		//		System.out.println(clz.getResource("/"));
		return null;
	}
}
