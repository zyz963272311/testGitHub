package test;

import java.util.Scanner;
import serializable.Serializable;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年10月9日 下午4:52:26</p>
 * <p>类全名：test.TestSerializable</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestSerializable
{
	public static void main(String[] args)
	{
		Serializable serializable = new Serializable();
		System.out.println("序列化");
		Scanner scanner = new Scanner(System.in);
		serializable.serializable("D:/test.txt");
		System.out.println("反序列化");
		serializable.deserialize("D:/test.txt");
	}
}
