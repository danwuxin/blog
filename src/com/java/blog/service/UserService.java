/**
 *ʱ�䣺2014-4-18����10:31:42
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.service;

import com.java.blog.dao.UserDao;
import com.java.blog.entity.User;
import com.java.blog.vo.UserVo;

import java.util.List;



public class UserService {
    /**
     * errorMessage   ����淵�ش�����Ϣ
     * */
	private String errorMessage = null;

	public String getErrorMessage() {
		return errorMessage;
	}

	private UserDao userDao = new UserDao();
   /**
    * �û�ע��ҵ��
    * ����һ��user����
    * ע��ǰ�Ȳ�һ���˺��Ƿ��ظ�
    * û���ظ�����ע��
    * */
	public boolean register(User user) {
		int result = 0;
		User userTmp = userDao.findUser(user.getAccount());
		if (userTmp != null) {
			this.errorMessage = "�û��˺��ظ�";
			return false;
		}
		// �û�ע��
		result = userDao.add(user);
		return (result > 0);

	}
    /**
     * �û���¼����ҵ��
     * ����  account password
     * */
	public User login(String account, String password) {
		return userDao.findUser(account, password);

	}
    /**
     * �޸�����ҵ��
     * �޸�ǰ�ж��ǲ����������û��ڲ���
     * */
	public boolean changePassword(String account, String oldPassword,
			String newPassword) {

		// ����ԭ�����ж��ǲ����������û�
		//���µ�¼һ��   ����ɹ����������û�����
		User user = userDao.findUser(account, oldPassword);
		// �ж�user�Ƿ�Ϊ��
		if (user == null) {
			errorMessage = "ԭ�������";
			return false;
		}
		//���ɹ�  �����µ�����  
		user.setPassword(newPassword);
		//�����û���Ϣ
		int result = userDao.updateUser(user);
		if (result > 0) {
			return true;
		} else {
			errorMessage = "��������ʧ��";
			return false;
		}

	}
    /**
     * �����û���Ϣҵ��
     * �ȸ����û�id����û� �������û���Ϣ
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
			errorMessage = "���¸�����Ϣʧ�ܡ�";
			return null;
		}
	}

	/** 
	 *ȡ����Ծ��5���û�ҵ��
	 * 
	 **/
	public List<UserVo> findTop5() {
		return userDao.findTop5User();
	}

	/**
	 * �жϼ�ס�ң�����userid ����userid���´����ݿ���ȡ�û���Ϣ
	 * */
	public User findByUserId(int userId) {
		return userDao.findUserById(userId);
	}
}