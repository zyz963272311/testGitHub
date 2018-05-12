package com.liiwin.datasources;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;
/**
 * <p>标题： DataSources</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年1月19日 下午10:59:11</p>
 * <p>类全名：com.liiwin.datasources.DataSources</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DataSources implements DataSource
{
	@Override
	public PrintWriter getLogWriter() throws SQLException
	{
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException
	{
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException
	{
	}

	@Override
	public int getLoginTimeout() throws SQLException
	{
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException
	{
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException
	{
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException
	{
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException
	{
		return null;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException
	{
		return null;
	}
}
