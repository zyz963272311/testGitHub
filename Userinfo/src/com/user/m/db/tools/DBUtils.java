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
			System.out.println("�ļ�δ�ҵ�");
		}
	}
	
    
	public static Connection   getConnetion(){
        //��һ�������������ݿ������
		Connection  con = null;
		//��ȡ�����ļ��е����ݿ�������
		String dbtype = p.getProperty("dbtype").toLowerCase().trim();
		String dbname = p.getProperty("dbname").toLowerCase().trim();
		String ip = p.getProperty("ip").toLowerCase().trim();
		String port = p.getProperty("port").toLowerCase().trim();
		String username = p.getProperty("username").toLowerCase().trim();
		String password = p.getProperty("password").toLowerCase().trim();
		if(dbtype.equals("oracle")){
			//����oracle
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("����δ���ص�������");
			} 
		
			StringBuffer s = new StringBuffer();
			s.append("jdbc:oracle:thin:@");
			s.append(ip);
			s.append(":");
			s.append(port);
			s.append(":");
			s.append(dbname);
//			orclΪ���ݿ��SID 
			try {
				con= DriverManager.getConnection(s.toString(),username,password);
			} catch (SQLException e) {
				System.out.println("���ݿ������������⣬���ݿ�����ʧ��");
			} 
		}
		if(dbtype.equals("mysql")){
			//����msyql
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			} catch (Exception e) {
				System.out.println("����δ���뵽������");
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
			//			myDBΪ���ݿ��� 
			try {
				con= DriverManager.getConnection(s.toString());
			} catch (SQLException e) {
				System.out.println("���ݿ������������⣬���ݿ�����ʧ��");
			} 
		}
		if(dbtype.equals("sqlserver")){
			//����sqlserver
			try {
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			} catch (Exception e) {
				System.out.println("����δ���뵽������");
			} 

			StringBuffer s = new StringBuffer();
			s.append("jdbc:microsoft:sqlserver://");
			s.append(ip);
			s.append(":");
			s.append(port);
			s.append(";DatabaseName=");
			s.append(dbname);
//			mydbΪ���ݿ� 
	
			try {
				con= DriverManager.getConnection(s.toString(),username,password);
			} catch (SQLException e) {
				System.out.println("���ݿ������������⣬���ݿ�����ʧ��");
			} 
		}
		
		return con;
	}
	
	
	//�ڶ��������Ͽ����ݿ�����
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
