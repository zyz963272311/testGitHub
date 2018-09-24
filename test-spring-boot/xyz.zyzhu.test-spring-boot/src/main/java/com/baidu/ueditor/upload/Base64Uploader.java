package com.baidu.ueditor.upload;

import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:03:53</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.upload.Base64Uploader</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Base64Uploader
{
	public static State save(String content, Map<String,Object> conf)
	{
		byte[] data = decode(content);
		long maxSize = ((Long) conf.get("maxSize")).longValue();
		if (!(validSize(data, maxSize)))
		{
			return new BaseState(false, 1);
		}
		String suffix = FileType.getSuffix("JPG");
		String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));
		savePath = savePath + suffix;
		String physicalPath = ((String) conf.get("rootPath")) + savePath;
		State storageState = StorageManager.saveBinaryFile(data, physicalPath);
		if (storageState.isSuccess())
		{
			storageState.putInfo("url", PathFormat.format(savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}
		return storageState;
	}

	private static byte[] decode(String content)
	{
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length)
	{
		return (data.length <= length);
	}
}
