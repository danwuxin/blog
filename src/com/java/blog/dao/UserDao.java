package com.java.blog.dao;

import com.java.blog.db.DBhelper;
import com.java.blog.entity.User;
import com.java.blog.vo.UserVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class UserDao {

	// 增加一个用户
	/**
	 * 用户再查完账号是否重复后，没有重复调用add（）进行注册
	 * sql为增加一个用户的语句
	 * */
	public int add(User user) {
		int result = 0;
		String sql = "insert into users(account,password,name,imageurl,age,gender,integrals)values(?,?,?,?,20,'男',100)";
		DBhelper helper = new DBhelper();
		result = helper.executeSql(sql, user.getAccount(), user.getPassword(),
				user.getName(), user.getImageUrl());
		return result;

	}

	// 依据账号查询用户
	/**
	 * 用户注册时使用   查是否有账号重复
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

	// 依据账号和密码查询用户
	/**
	 * 用户登录   参数account password
	 * sql语句是根据account password从数据库中查询用户  返回一个用户
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
	 *  依据用户的userid查询用户
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
	 * 更新用户的信息
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
	 * 查询出最活跃的五个用户
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
