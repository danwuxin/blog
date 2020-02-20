/**
 *时间：2014-4-18上午10:41:49
 *
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.test;


import com.java.blog.entity.User;
import com.java.blog.service.UserService;

public class myTest {
	public static void main1(String[] args) {
		// 模拟用户注册(账号重复)
		User user = new User();

		user.setAccount("abc");
		user.setPassword("123");
		user.setName("张三丰");
		user.setImageUrl("1.gif");
		UserService userService = new UserService();
		boolean result = userService.register(user);
		if (result && null == userService.getErrorMessage()) {
			System.out.println("注册成功");
		} else {
			System.out.println("注册失败[" + userService.getErrorMessage() + "]");
		}
	}
	
	
	
	public static void main(String[] args) {
		// 模拟用户登录
		String account = "abc";
		String password = "123";

		UserService userService = new UserService();
		User user = userService.login(account, password);
		if (user == null) {
			System.out.println("用户登录失败");
		} else {
			System.out.println("用户登录成功");
		}

	}

}
