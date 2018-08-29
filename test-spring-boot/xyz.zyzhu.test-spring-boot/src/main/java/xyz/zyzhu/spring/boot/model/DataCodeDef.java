package xyz.zyzhu.spring.boot.model;

import javax.persistence.Table;
import com.liiwin.annotation.FieldDef;
/**
 * <p>标题： 码表定义</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月28日 下午2:39:56</p>
 * <p>类全名：xyz.zyzhu.spring.boot.model.DataCodeDef</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Table(name = "datacodedef")
public class DataCodeDef extends BasModel
{
	private static final long	serialVersionUID	= 1280635547085841371L;
	//内码
	@FieldDef(defaultValue = "AutoCode:dicode_______")
	private String				icode;
	//外码
	@FieldDef()
	private String				code;
	//查询条件
	@FieldDef()
	private String				queryfilter;
	//表名
	@FieldDef()
	private String				tablename;
	//排序字段
	@FieldDef()
	private String				ordercolumns;

	public String getIcode()
	{
		return icode;
	}

	public void setIcode(String icode)
	{
		setValue("icode", icode);
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		setValue("code", code);
	}

	public String getQueryfilter()
	{
		return queryfilter;
	}

	public void setQueryfilter(String queryfilter)
	{
		setValue("queryfilter", queryfilter);
	}

	public String getTablename()
	{
		return tablename;
	}

	public void setTablename(String tablename)
	{
		setValue("tablename", tablename);
	}

	public String getOrdercolumns()
	{
		return ordercolumns;
	}

	public void setOrdercolumns(String ordercolumns)
	{
		setValue("ordercolumns", ordercolumns);
	}
}
