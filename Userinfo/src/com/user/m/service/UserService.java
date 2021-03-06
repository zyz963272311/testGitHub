package com.user.m.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.user.m.db.dao.UserOnLineDAO;
import com.user.m.db.dao.UserinfoDAO;
import com.user.m.db.pojo.UserOnLine;
import com.user.m.db.pojo.Userinfo;
import com.user.m.db.tools.DBUtils;
import com.user.m.service.myexception.QiangBiException;
import com.user.m.service.myexception.UserNameException;
import com.user.m.service.myexception.UserOnlineException;

public class UserService {

	public boolean denglu(Userinfo u) throws UserOnlineException{//������û���������
		boolean b =false;
		Connection con = DBUtils.getConnetion();
		try {
			if(con == null|| con.isClosed())
			{
				throw new RuntimeException("获取DB链接失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserinfoDAO ud = new UserinfoDAO(con);
		Userinfo haha = new Userinfo();
		haha.setName(u.getName());
		boolean b2 = ud.findName(haha);
		if(b2){
			//�û�����ȷ
			if(u.getPass().equals(haha.getPass())){
				UserOnLineDAO uld = new UserOnLineDAO(con);
				boolean bonline = uld.findUid(haha.getId());
				if(bonline == false){
					b =true;
					u.setId(haha.getId());
					u.setPower(haha.getPower());
					u.setAge(haha.getAge());
					u.setMail(haha.getMail());
					//�û��������붼��ȷ
					UserOnLine ul = new UserOnLine();
					ul.setId(uld.maxId()+1);
					ul.setUid(haha.getId());
					Date  d =new Date();
					Timestamp t = new Timestamp(d.getTime());
					ul.setUtime(t);
					uld.insert(ul);
				}else{
					throw new UserOnlineException();
				}
			}
		}
		DBUtils.close(con,null, null);
		return b;
	}
	public boolean dropuser(int id){
		Connection con = DBUtils.getConnetion();
		UserinfoDAO uld = new UserinfoDAO(con);
		boolean b  = uld.dropuser(id);
		DBUtils.close(con,null, null);
		return b;
	}
	public boolean deleteUserOnLine(int uid){
		Connection con = DBUtils.getConnetion();
		UserOnLineDAO uld = new UserOnLineDAO(con);
		boolean b  = uld.delete(uid);
		DBUtils.close(con,null, null);
		return b;
	}
	public int pageSum(){
		Connection con = DBUtils.getConnetion();
		UserinfoDAO ud = new UserinfoDAO(con);
		int count = ud.count();
		int sum =0;
		if(count%10 ==0){
			sum = count/10;
		}else{
			sum = count/10 + 1;
		}
		DBUtils.close(con,null, null);
		return sum;
	}
	public boolean addUser(Userinfo u ) throws UserNameException, QiangBiException{//u������ֻ��8����Ϣ��ȱ��3����Ϣ
		boolean b = false;
		/**
		 * ��һ������: �ж��û����Ƿ��ظ�
		 * �ڶ������ǣ� �ϳ�����
		 * ���������ǣ����뵽���ݿ���
		 */
		Connection con = DBUtils.getConnetion();
		UserinfoDAO ud = new UserinfoDAO(con);
		Userinfo haha  = new Userinfo();
		haha.setName(u.getName());
		boolean b2 = ud.findName(haha);
		if(b2){
			throw new UserNameException();
		}
		int i  = u.getGexingqianming().indexOf("ɵX");
		if(i != -1){
			throw new QiangBiException();
		}
		//----------------------------�ϳ�ʣ�����Ϣ
		int id = ud.maxId();
		u.setState(1);
		u.setId(id+1);
		u.setPower("��ͨ�û�");
		Date d= new Date();
		Timestamp t= new Timestamp(d.getTime());
		u.setCreatetime(t);
		b = ud.insert(u);
		DBUtils.close(con,null, null);
		return b ;
	}
	
	public List<Userinfo> selectUser(int pageid){
		List l = null;
		int start = (pageid-1)*10;
		int length = 10;
		Connection con = DBUtils.getConnetion();
		UserinfoDAO ud = new UserinfoDAO(con);
		l = ud.select(start, length);
		DBUtils.close(con,null, null);
		return l;
	}
}
