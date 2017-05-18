package test;

import util.Base64;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月16日 上午10:21:41</p>
 * <p>类全名：test.TestBase64</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestBase64
{
	public static void main(String[] args)
	{
		String decode = Base64.decode("cXdldHlqdWtsOw==");
		String encode = Base64.encode("qwetyjukl;");
	}
}
