package _db.base;

/**
 * <p>标题： </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月23日 上午11:16:22</p>
 * <p>类全名：_db.Table</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public interface Table
{
	public String setPrimaryKey(Object... primaryKey);

	public void getPrimaryKey();

	public void setInnerCodeType();

	public void setOutterCodeType();
}
