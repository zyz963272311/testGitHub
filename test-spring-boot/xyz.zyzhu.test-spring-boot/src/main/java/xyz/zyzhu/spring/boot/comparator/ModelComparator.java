package xyz.zyzhu.spring.boot.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.utils.ObjectUtils;
/**
 * <p>标题： Model排序比较器</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午3:32:17</p>
 * <p>类全名：xyz.zyzhu.spring.boot.comparator.ModelComparator</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ModelComparator<T extends BasModel> implements Comparator<T>
{
	/**
	 * 比较的列
	 */
	List<CompareColumn> compareColumns;

	public ModelComparator()
	{
		this("");
	}

	public ModelComparator(String compareColumns)
	{
		if (compareColumns != null && StrUtil.isNoStrTrimNull(compareColumns))
		{
			List<CompareColumn> list = new ArrayList<>();
			String[] split = StrUtil.split(compareColumns, ',');
			for (String columns : split)
			{
				String trim = columns.trim();
				boolean asc = true;
				if (trim.toLowerCase().endsWith(" desc"))
				{
					asc = false;
				}
				String[] split2 = StrUtil.split(trim, ' ');
				for (String column : split2)
				{
					if (StrUtil.isNoStrTrimNull(column) && !column.toLowerCase().equals("asc") && !column.toLowerCase().equals("desc"))
					{
						CompareColumn compareColumn = new CompareColumn(column, asc);
						list.add(compareColumn);
					}
				}
			}
			this.compareColumns = list;
		}
	}

	public ModelComparator(List<CompareColumn> compareColumns)
	{
		this.compareColumns = compareColumns;
	}

	@Override
	public int compare(T o1, T o2)
	{
		if (o1 == o2)
		{
			return 0;
		}
		if (o1 == null)
		{
			return -1;
		}
		if (o2 == null)
		{
			return 1;
		}
		if (compareColumns == null || compareColumns.isEmpty())
		{
			return o1.compareTo(o2);
		}
		Map<String,Object> mapValues1 = o1.getMapValues();
		Map<String,Object> mapValues2 = o2.getMapValues();
		for (CompareColumn column : compareColumns)
		{
			if (column != null && StrUtil.isNoStrTrimNull(column.getColumn()))
			{
				boolean asc = column.isAsc();
				Object obj1 = mapValues1.get(column.getColumn());
				Object obj2 = mapValues2.get(column.getColumn());
				int comp = ObjectUtils.compareObject(obj1, obj2);
				if (comp != 0)
				{
					return asc ? comp : (1 - comp);
				}
			}
		}
		return 0;
	}

	/**
	 * 
	 * <p>标题： 比较的列</p>
	 * <p>功能： </p>
	 * <p>所属模块： boot</p>
	 * <p>版权： Copyright © 2018 SNSOFT</p>
	 * <p>公司: 赵玉柱练习</p>
	 * <p>创建日期：2018年8月28日 下午5:21:15</p>
	 * <p>类全名：xyz.zyzhu.spring.boot.comparator.CompareColumn</p>
	 * 作者：赵玉柱
	 * 初审：
	 * 复审：
	 * 监听使用界面:
	 * @version 8.0
	 */
	public static class CompareColumn
	{
		//比较的列名
		private String	column;
		//是否正序排序
		private boolean	asc	= true;

		public CompareColumn(String column)
		{
			this(column, true);
		}

		public CompareColumn(String column, boolean asc)
		{
			if (StrUtil.isStrTrimNull(column))
			{
				throw new RuntimeException("列名不可为空");
			}
			this.column = column;
			this.asc = asc;
		}

		public String getColumn()
		{
			return column;
		}

		public void setColumn(String column)
		{
			this.column = column;
		}

		public boolean isAsc()
		{
			return asc;
		}

		public void setAsc(boolean asc)
		{
			this.asc = asc;
		}
	}
}
