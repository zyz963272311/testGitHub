package com.user.m.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.user.m.db.pojo.Userinfo;
import com.user.m.db.tools.DBUtils;

public class UserinfoDAO {

	Connection con = null;
	public UserinfoDAO(Connection con){
		this.con = con;
	}
	public boolean findName(Userinfo u){
		boolean b =false;
		String sql = "select * from userinfo where name = ?";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setString(1, u.getName());
			ResultSet rs = pstam.executeQuery();
			if(rs.next()){
				b =true;
				u.setId(rs.getInt("id"));
				u.setAge(rs.getInt("age"));
				u.setPass(rs.getString("pass"));
				u.setMail(rs.getString("email"));
				u.setPower(rs.getString("power"));
			}
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("findName����������" + e.getMessage());
		}
		
		return b;   
	}
	public boolean dropuser(int id){
		boolean b =false;
		String sql = "update  userinfo set state=0 where id = ?";
		try {
			
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setInt(1,id);
			int size  = pstam.executeUpdate();
			if(size > 0){
				b = true;
			}
			DBUtils.close(null, pstam, null);
		} catch (SQLException e) {
			System.out.println("dropuser����������" + e.getMessage());
		}
		return b; 
	}
	public int count(){
		int sum = 0;
		String sql = "select count(*) from userinfo";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			ResultSet rs = pstam.executeQuery();
			if(rs.next()){
				sum = rs.getInt("count(*)");
			}
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("count����������" + e.getMessage());
		}
		return sum;
	}
	public  List<Userinfo> select(int start,int length){
		List<Userinfo> l = new ArrayList<Userinfo>();
		String sql = "select * from userinfo limit?,?";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setInt(1, start);
			pstam.setInt(2, length);
			ResultSet rs = pstam.executeQuery();
			while(rs.next()){
				Userinfo u = new Userinfo();
				u.setId(rs.getInt("id"));
				u.setAge(rs.getInt("age"));
				u.setName(rs.getString("name"));
				u.setXingbie(rs.getString("xingbie"));
				u.setPass(rs.getString("pass"));
				u.setMail(rs.getString("email"));
				u.setPower(rs.getString("power"));
				u.setAihao(rs.getString("aihao"));
				u.setChengshi(rs.getString("chengshi"));
				u.setGexingqianming(rs.getString("gexingqianming"));
				u.setCreatetime(rs.getTimestamp("createtime"));
				u.setState(rs.getInt("state"));
				l.add(u);
			}
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("select����������" + e.getMessage());
		}
		
		
		
		return l;
	}
	
	public int maxId(){
		int id = 0;
		String sql = "select max(id) from userinfo";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			ResultSet rs = pstam.executeQuery();
			if(rs.next()){
				id = rs.getInt("max(id)");
			}
			DBUtils.close(null, pstam, rs);
		} catch (SQLException e) {
			System.out.println("findName����������" + e.getMessage());
		}
		return id;
	}
	
	public boolean insert(Userinfo u){
		boolean b =false;
		String sql ="insert into userinfo (id,name,pass,age,email,power,aihao,chengshi,gexingqianming,xingbie,createtime,state)  values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstam = con.prepareStatement(sql);
			pstam.setInt(1, u.getId());
			pstam.setString(2, u.getName());
			pstam.setString(3, u.getPass());
			pstam.setInt(4, u.getAge());
			pstam.setString(5, u.getMail());
			pstam.setString(6, u.getPower());
			pstam.setString(7, u.getAihao());
			pstam.setString(8,u.getChengshi());
			pstam.setString(9, u.getGexingqianming());
			pstam.setString(10, u.getXingbie());
			pstam.setTimestamp(11, u.getCreatetime());
			pstam.setInt(12,u.getState());
			int i = pstam.executeUpdate();
			if(i > 0){
				b = true;
			}
			DBUtils.close(null, pstam,null);
		} catch (SQLException e) {
			System.out.println("insert����������" + e.getMessage());
		}
		return b;
	}
}
