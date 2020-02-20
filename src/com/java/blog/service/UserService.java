/**
 *时间：2014-4-18上午10:31:42
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.service;

import com.java.blog.dao.UserDao;
import com.java.blog.entity.User;
import com.java.blog.vo.UserVo;

import java.util.List;



public class UserService {
	/**
	 * errorMessage   向界面返回错误信息
	 * */
	private String errorMessage = null;

	public String getErrorMessage() {
		return errorMessage;
	}

	private UserDao userDao = new UserDao();
	/**
	 * 用户注册业务
	 * 传入一个user对象
	 * 注册前先查一下账号是否重复
	 * 没有重复进行注册
	 * */
	public boolean register(User user) {
		int result = 0;
		User userTmp = userDao.findUser(user.getAccount());
		if (userTmp != null) {
			this.errorMessage = "用户账号重复";
			return false;
		}
		// 用户注册
		result = userDao.add(user);
		return (result > 0);

	}
	/**
	 * 用户登录功能业务
	 * 参数  account password
	 * */
	public User login(String account, String password) {
		return userDao.findUser(account, password);

	}
	/**
	 * 修改密码业务
	 * 修改前判断是不是真正的用户在操作
	 * */
	public boolean changePassword(String account, String oldPassword,
								  String newPassword) {

		// 先用原密码判断是不是真正的用户
		//重新登录一次   如果成功是真正的用户操作
		User user = userDao.findUser(account, oldPassword);
		// 判断user是否为空
		if (user == null) {
			errorMessage = "原密码错误";
			return false;
		}
		//检查成功  设置新的密码
		user.setPassword(newPassword);
		//更新用户信息
		int result = userDao.updateUser(user);
		if (result > 0) {
			return true;
		} else {
			errorMessage = "设置密码失败";
			return false;
		}

	}
	/**
	 * 更新用户信息业务
	 * 先根据用户id查出用户 再设置用户信息
	 * */
	public User updateUser(User user) {
		User originalUser = userDao.findUserById(user.getUserid());
		originalUser.setName(user.getName());
		originalUser.setGender(user.getGender());
		originalUser.setAge(user.getAge());
		originalUser.setImageUrl(user.getImageUrl());

		int result = userDao.updateUser(originalUser);
		if (result > 0) {
			return originalUser;
		} else {
			errorMessage = "更新个人信息失败。";
			return null;
		}
	}

	/**
	 *取出活跃活5个用户业务
	 *
	 **/
	public List<UserVo> findTop5() {
		return userDao.findTop5User();
	}

	/**
	 * 判断记住我，根据userid 根据userid重新从数据库中取用户信息
	 * */
	public User findByUserId(int userId) {
		return userDao.findUserById(userId);
	}
}