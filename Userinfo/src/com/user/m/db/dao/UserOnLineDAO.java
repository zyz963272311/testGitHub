package com.user.m.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.user.m.db.pojo.UserOnLine;
import com.user.m.db.tools.DBUtils;

public class UserOnLineDAO {
	private Connection con;
	public UserOnLineDAO(Connection con){
		this.con = con;
	}

	public int maxId(){
		int id = 0;
		String sql ="select max(id) from useronline";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			ResultSet rs = pstam.executeQuery();
			if(rs.next()){
				id = rs.getInt("max(id)");
			}
			
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("查询最大id的时候出错了" + e.getMessage());
		}
		
		
		
		return id;
	}
	public  boolean findUid(int uid){
		boolean b =false;
		String sql ="select * from useronline where uid = ?";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setInt(1, uid);
			ResultSet rs = pstam.executeQuery();
			if(rs.next()){
				b =true;
			}
			
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("向useronline临时表中查询数据出错了" + e.getMessage());
		}
		
		return b;
	}
	public boolean insert(UserOnLine u){
		boolean b =false;
		String sql ="insert into useronline values(?,?,?)";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setInt(1, u.getId());
			pstam.setInt(2, u.getUid());
			pstam.setTimestamp(3, u.getUtime());
			int size  = pstam.executeUpdate();
			if(size > 0){
				b = true;
			}
			DBUtils.close(null, pstam, null);
		} catch (SQLException e) {
			System.out.println("向useronline临时表中插入数据出错了" + e.getMessage());
		}
		
		return b;
	}
	public boolean delete(int uid){
		boolean b =false;
		String sql ="delete from useronline where uid = ?";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
	
			pstam.setInt(1, uid);
		
			int size  = pstam.executeUpdate();
			if(size > 0){
				b = true;
			}
			DBUtils.close(null, pstam, null);
		} catch (SQLException e) {
			System.out.println("向useronline临时表中删除数据出错了" + e.getMessage());
		}
		
		return b;
	}
}
