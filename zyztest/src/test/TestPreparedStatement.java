package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 * <p>标题：测试PreparedStatement是否可以防止sql注入 </p>
 * <p>功能： </p>
 * <p>所属模块： ICIP企业端</p>
 * <p>版权： Copyright © 2016 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2016年11月22日 下午3:50:39</p>
 * <p>类全名：test.TestPreparedStatement</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class TestPreparedStatement
{
	public static void main(String[] args) throws Exception
	{
		Connection conn = null;
		String sql = "select * from table where id=? and name=?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(0, "321321 ' or 1-1");
		pstm.setString(1, " 看看结果");
	}
}
