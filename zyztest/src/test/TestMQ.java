package test;

import mq.MessageByMQ;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年12月15日 上午11:08:15</p>
 * <p>类全名：test.TestMQ</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestMQ
{
	public static void main(String[] args)
	{
		MessageByMQ.sendMessage("测试MQ");
		System.out.println(MessageByMQ.getMessage());
	}
}
