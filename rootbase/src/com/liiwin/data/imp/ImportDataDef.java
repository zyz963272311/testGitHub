package com.liiwin.data.imp;

import com.liiwin.data.comm.DataDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年5月7日 下午4:44:43</p>
 * <p>类全名：com.liiwin.data.imp.ImportDataDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class ImportDataDef extends DataDef
{
	private static final long	serialVersionUID	= 1L;

	@Override
	public Integer getIEFlags()
	{
		return 1;
	}
}
