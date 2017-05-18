package _db.base;

import java.util.List;
/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 下午4:10:18</p>
 * <p>类全名：_db.Status</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Struct
{
	public List<Column> getInnerCodeType();

	public List<Column> getOutterCodeType();

	public void SetPrimaryKey(Object... primaryKey);

	public Object[] getPrimaryKey();

	public void setInnerCodeType(String culumn, List<CodeType> codeType);

	public void setOutterCodeType(String culumn, List<CodeType> codeType);
}
