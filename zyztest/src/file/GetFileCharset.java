package file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
/**
 * 
 * <p>标题： 测试获取文件类型</p>
 * <p>功能： </p>
 * <p>所属模块： 自己测试用</p>
 * <p>版权： Copyright © 2016 ZYZ</p>
 * <p>公司: 赵玉柱</p>
 * <p>创建日期：2016年8月24日 下午2:25:06</p>
 * <p>类全名：file.GetFileCharset</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class GetFileCharset
{
	public String getFileCharset(String fileName) throws Exception
	{
		String result = "";
		@SuppressWarnings("resource")
		BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
		int p = (bin.read() << 8) + bin.read();
		switch (p)
		{
		case 0xefbb:
			result = "UTF-8";
			break;
		case 0xfffe:
			result = "Unicode";
			break;
		case 25700:
			result = "Unicode";
			break;
		default:
			result = "DEFAULT:" + p;
			break;
		}
		return result;
	}
}
