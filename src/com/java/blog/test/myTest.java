/**
 *ʱ�䣺2014-4-18����10:41:49
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.test;


import com.java.blog.entity.User;
import com.java.blog.service.UserService;

public class myTest {
	public static void main1(String[] args) {
		// ģ���û�ע��(�˺��ظ�)
		User user = new User();

		user.setAccount("abc");
		user.setPassword("123");
		user.setName("������");
		user.setImageUrl("1.gif");
		UserService userService = new UserService();
		boolean result = userService.register(user);
		if (result && null == userService.getErrorMessage()) {
			System.out.println("ע��ɹ�");
		} else {
			System.out.println("ע��ʧ��[" + userService.getErrorMessage() + "]");
		}
	}
	
	
	
	public static void main(String[] args) {
		// ģ���û���¼
		String account = "abc";
		String password = "123";

		UserService userService = new UserService();
		User user = userService.login(account, password);
		if (user == null) {
			System.out.println("�û���¼ʧ��");
		} else {
			System.out.println("�û���¼�ɹ�");
		}

	}

}
