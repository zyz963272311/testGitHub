package com.liiwin.file.types;

import com.liiwin.file.utils.FileUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月24日 下午1:59:32</p>
 * <p>类全名：com.liiwin.file.types.FileTypeEnum</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public enum FileTypeEnum
{
	//可执行文件
	JAR(0, "jar"), EXE(1, "exe"), BAT(2, "bat"), C(3, "c"), CPP(4, "CPP"), //;
	//Office相关
	DOC(100, "doc"), DOCX(101, "docx"), WPS(102, "wps"), XLS(103, "xls"), XLSX(104, "xlsx"), PPT(105, "ppt"), PPTX(106, "pptx"), //
	//文本文件相关
	TXT(200, "txt"),
	//图片相关
	JPG(300, "jpg"), JPEG(301, "jpeg"), PNG(302, "png"), ICON(303, "icon"), GIF(304, "gif"),
	//视频相关
	MP4(400, "mp4"), AVI(401, "avi"),
	//编程前端相关
	HTML(500, "html"), HTM(501, "htm"), JS(502, "js"), CSS(503, "css"), JSP(504, "jsp"), FTL(505, "ftl");
	private int		type;
	private String	typename;

	private FileTypeEnum(int type, String typename)
	{
		this.type = type;
		this.typename = typename;
	}

	/**
	 * 校验文件名称对应的文件类型
	 * @param type
	 * @param name
	 * @return
	 * 赵玉柱
	 */
	public boolean checkFileType(String name)
	{
		String extName = FileUtil.getExtNameByFileName(name);
		if (StrUtil.isStrTrimNull(extName))
		{
			return false;
		}
		return getTypename().equals(extName.toLowerCase());
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getTypename()
	{
		return typename;
	}

	public void setTypename(String typename)
	{
		this.typename = typename;
	}
}
