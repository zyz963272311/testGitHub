package _db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import _db.base.CodeType;
import _db.base.Column;
import _db.base.Struct;
/**
 * <p>标题：模拟数据库person表结构 </p>
 * <p>功能：模拟数据库person表，并设置列以及类型 </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 上午11:03:09</p>
 * <p>类全名：_db.PersonStruct</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class PersonStruct implements Struct
{
	Column						pID;
	Column						pName;
	Column						pAge;
	Column						psix;
	Object[]					primaryKey;
	Map<String,Object>			innerCode;
	Map<String,Object>			outterCode;
	public static PersonStruct	personStruct;

	private PersonStruct()
	{
	}

	public static synchronized PersonStruct getInstanse()
	{
		if (personStruct == null)
		{
			personStruct = new PersonStruct();
		}
		return personStruct;
	}

	@Override
	public List<Column> getInnerCodeType()
	{
		return (innerCode == null || innerCode.size() == 0) ? null : (List<Column>) innerCode.get("codeType");
	}

	@Override
	public List<Column> getOutterCodeType()
	{
		return (outterCode == null || outterCode.size() == 0) ? null : (List<Column>) outterCode.get("codeType");
	}

	public Column getpID()
	{
		return pID;
	}

	public void setpID(Column pID)
	{
		this.pID = pID;
	}

	public Column getpName()
	{
		return pName;
	}

	public void setpName(Column pName)
	{
		this.pName = pName;
	}

	public Column getpAge()
	{
		return pAge;
	}

	public void setpAge(Column pAge)
	{
		this.pAge = pAge;
	}

	public Column getPsix()
	{
		return psix;
	}

	public void setPsix(Column psix)
	{
		this.psix = psix;
	}

	@Override
	public void SetPrimaryKey(Object... primaryKey)
	{
		this.primaryKey = primaryKey;
	}

	@Override
	public Object[] getPrimaryKey()
	{
		return primaryKey;
	}

	@Override
	public void setInnerCodeType(String culumn, List<CodeType> codeType)
	{
		if (innerCode == null)
		{
			innerCode = new HashMap<String,Object>();
		}
		innerCode.clear();
		innerCode.put("culumn", culumn);
		innerCode.put("codeType", codeType);
	}

	@Override
	public void setOutterCodeType(String culumn, List<CodeType> codeType)
	{
		if (outterCode == null)
		{
			outterCode = new HashMap<String,Object>();
		}
		outterCode.clear();
		outterCode.put("culumn", culumn);
		outterCode.put("codeType", codeType);
	}
}