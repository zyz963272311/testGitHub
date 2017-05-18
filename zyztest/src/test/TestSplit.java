package test;

/**
 * <p>标题： TestSplit</p>
 * <p>功能： </p>
 * <p>所属模块： ICIP/PSP/AQSIQ</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月29日 下午2:56:38</p>
 * <p>类全名：test.TestSplit</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestSplit
{
	/**
	 * <?xml version="1.0" encoding="ISO-8859-1"?><root>	<item id="0"><![CDATA[64]]></item>	<item id="1"><![CDATA[fbbbb4]]></item>	<item id="2"><![CDATA[123456]]></item>	<item id="3"><![CDATA[fbbbb4@qq.com]]></item>	<item id="4"><![CDATA[0]]></item></root>
	 */
	public static void main(String[] args)
	{
		String xmlStr = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><root>	<item id=\"0\"><![CDATA[64]]></item>	<item id=\"1\"><![CDATA[fbbbb4]]></item>	<item id=\"2\"><![CDATA[123456]]></item>	<item id=\"3\"><![CDATA[fbbbb4@qq.com]]></item>	<item id=\"4\"><![CDATA[0]]></item></root>";
		String[] haveRights = xmlStr.split("<!" + "\\[");//获得内容]>....
		for (int i = 1; i < haveRights.length; i++)
		{
			String content = haveRights[i].split("]>")[0];//舍掉]>右侧
			String iWant = content.substring(content.indexOf("[") + 1, content.indexOf("]"));
			System.out.println("Content=" + content + "\tIWant=" + iWant);
		}
	}
}
