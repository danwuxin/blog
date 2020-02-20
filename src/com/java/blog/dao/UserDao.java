/**
 *ʱ�䣺2014-4-18����10:08:22
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.dao;

import com.java.blog.db.DBhelper;
import com.java.blog.entity.User;
import com.java.blog.vo.UserVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDao {

	// ����һ���û�
	 /**
	  * �û��ٲ����˺��Ƿ��ظ���û���ظ�����add��������ע��
	  * sqlΪ����һ���û������
	  * */
	public int add(User user) {
		int result = 0;
		String sql = "insert into users(account,password,name,imageurl,age,gender,integrals)values(?,?,?,?,20,'��',100)";
		DBhelper helper = new DBhelper();
		result = helper.executeSql(sql, user.getAccount(), user.getPassword(),
				user.getName(), user.getImageUrl());
		return result;

	}

	// �����˺Ų�ѯ�û�
	/**
	 * �û�ע��ʱʹ��   ���Ƿ����˺��ظ�
	 * */
	public User findUser(String account) {
		User user = null;
		String sql = "select * from users where account=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql, account);
		try {
			if (rs.next()) {
				user = rs2User(rs);
			}

		} catch (Exception e) {
		}
		helper.close();
		return user;
	}

	// �����˺ź������ѯ�û�
	/**
	 * �û���¼   ����account password
	 * sql����Ǹ���account password�����ݿ��в�ѯ�û�  ����һ���û�
	 * */
	public User findUser(String account, String password) {
		User user = null;
		String sql = "select * from users where account=? and password=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql, account, password);
		try {
			if (rs.next()) {
				user = rs2User(rs);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		helper.close();
		return user;
	}

	/**
	 *  �����û���userid��ѯ�û�
	 * @param userid
	 * @author danwuxin
	 * return user
	 * */
	public User findUserById(int userid) {
		User user = null;
		String sql = "select * from users where userid=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql, userid);
		try {
			if (rs.next()) {
				user = rs2User(rs);
			}

		} catch (Exception e) {
		}
		helper.close();
		return user;
	}

	private User rs2User(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserid(rs.getInt("userid"));
		user.setAccount(rs.getString("account"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setIntegrals(rs.getInt("integrals"));
		user.setImageUrl(rs.getString("imageUrl"));
		user.setAge(rs.getInt("age"));
		user.setGender(rs.getString("gender"));
		return user;
	}
     /**
      * �����û�����Ϣ
      * */
	public int updateUser(User user) {
		int result = 0;
		String sql = "update users Set account=?,password=?,name=?,imageurl=?,age=?,gender=?,integrals=? where userid=?";
		DBhelper helper = new DBhelper();
		result = helper.executeSql(sql, user.getAccount(), user.getPassword(),
				user.getName(), user.getImageUrl(), user.getAge(),
				user.getGender(), user.getIntegrals(), user.getUserid());
		return result;
	}
    /**
     * ��ѯ�����Ծ������û�
     * */
	public List<UserVo> findTop5User() {
		List<UserVo> users = new ArrayList<UserVo>();
		String sql = "select UserId,count(*) as articleCount from articles GROUP BY UserId ORDER BY count(*) DESC limit 0,5";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql);
		try {
			while (rs.next()) {
				int userId = rs.getInt("UserId");
				User user = findUserById(userId);
				int articleCount = rs.getInt("articleCount");
				
				UserVo userVo = new UserVo();
				userVo.setUser(user);
				userVo.setArticleCount(articleCount);
				
				users.add(userVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return users;
	}

}
