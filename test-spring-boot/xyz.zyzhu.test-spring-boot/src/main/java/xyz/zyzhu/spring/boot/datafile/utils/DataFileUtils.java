package xyz.zyzhu.spring.boot.datafile.utils;

import xyz.zyzhu.spring.boot.model.DataimpgDef;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月6日 下午4:18:18</p>
 * <p>类全名：xyz.zyzhu.spring.boot.datafile.utils.DataFileUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataFileUtils
{
	/**
	 * 数据导入导出定义是否是插入
	 * @param def
	 * @return
	 * 赵玉柱
	 */
	public static boolean isImpdefInsert(DataimpgDef def)
	{
		if (def == null)
		{
			return false;
		}
		Integer savemode = def.getSavemode();
		if (savemode == null)
		{
			return false;
		}
		return (savemode & 1) > 0;
	}

	/**
	 * 数据导入导出定义是否是更新
	 * @param def
	 * @return
	 * 赵玉柱
	 */
	public static boolean isImpdefUpdate(DataimpgDef def)
	{
		if (def == null)
		{
			return false;
		}
		Integer savemode = def.getSavemode();
		if (savemode == null)
		{
			return false;
		}
		return (savemode & 2) > 0;
	}

	/**
	 * 数据导入之前是否先删除原数据
	 * @param def
	 * @return
	 * 赵玉柱
	 */
	public static boolean isImpdefBeforeExec(DataimpgDef def)
	{
		if (def == null)
		{
			return false;
		}
		Integer savemode = def.getSavemode();
		if (savemode == null)
		{
			return false;
		}
		return (savemode & 4) > 0;
	}
}
