package _db.util;

import _db.base.ColumnType;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 上午11:45:07</p>
 * <p>类全名：_db.dbUtil</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class dbUtil
{
	public void validTypeAndSize(ColumnType columnType, int size)
	{
		if (columnType != null)
		{
			if (size < 0 || size > columnType.getMax())
			{
				new RuntimeException(columnType.getType() + "类型数据位数范围必须大于0且小于等于" + columnType.getMax()).printStackTrace();
			}
		} else
		{
			new RuntimeException("您输入的类型为空").printStackTrace();
		}
	}
}
