package com.user.m.db.tools;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	private static Properties p = new Properties();
	static{
		try {
			p.load(DBUtils.class.getResourceAsStream("dbinit.properties"));
		} catch (IOException e) {
			System.out.println("文件未找到");
		}
	}
	
    
	public static Connection   getConnetion(){
        //第一个方法建立数据库的连接
		Connection  con = null;
		//获取配置文件中的数据库连接项
		String dbtype = p.getProperty("dbtype").toLowerCase().trim();
		String dbname = p.getProperty("dbname").toLowerCase().trim();
		String ip = p.getProperty("ip").toLowerCase().trim();
		String port = p.getProperty("port").toLowerCase().trim();
		String username = p.getProperty("username").toLowerCase().trim();
		String password = p.getProperty("password").toLowerCase().trim();
		if(dbtype.equals("oracle")){
			//连接oracle
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("驱动未加载到工程中");
			} 
		
			StringBuffer s = new StringBuffer();
			s.append("jdbc:oracle:thin:@");
			s.append(ip);
			s.append(":");
			s.append(port);
			s.append(":");
			s.append(dbname);
//			orcl为数据库的SID 
			try {
				con= DriverManager.getConnection(s.toString(),username,password);
			} catch (SQLException e) {
				System.out.println("数据库连接项有问题，数据库连接失败");
			} 
		}
		if(dbtype.equals("mysql")){
			//连接msyql
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			} catch (Exception e) {
				System.out.println("驱动未导入到工程中");
			} 
		
			StringBuffer s = new StringBuffer();
			s.append("jdbc:mysql://");
			s.append(ip);
			s.append(":");
			s.append(port);
			s.append("/");
			s.append(dbname);
			s.append("?user=");
			s.append(username);
			s.append("&password=");
			s.append(password);
			s.append("&useUnicode=true&characterEncoding=utf8");
			//			myDB为数据库名 
			try {
				con= DriverManager.getConnection(s.toString());
			} catch (SQLException e) {
				System.out.println("数据库连接项有问题，数据库连接失败");
			} 
		}
		if(dbtype.equals("sqlserver")){
			//连接sqlserver
			try {
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			} catch (Exception e) {
				System.out.println("驱动未导入到工程中");
			} 

			StringBuffer s = new StringBuffer();
			s.append("jdbc:microsoft:sqlserver://");
			s.append(ip);
			s.append(":");
			s.append(port);
			s.append(";DatabaseName=");
			s.append(dbname);
//			mydb为数据库 
	
			try {
				con= DriverManager.getConnection(s.toString(),username,password);
			} catch (SQLException e) {
				System.out.println("数据库连接项有问题，数据库连接失败");
			} 
		}
		
		return con;
	}
	
	
	//第二个方法断开数据库连接
	public static void close(Connection con,PreparedStatement pstam,ResultSet  rs){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstam!= null){
			try {
				pstam.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
