/**
*ʱ�䣺2014-4-27����5:08:01
*
*���ߣ��Ź���
*���ܣ��û�voʵ����
*/
package com.java.blog.vo;


import com.java.blog.entity.User;

public class UserVo {
    //��������
	private User user;
	//���µ����
	private int articleCount;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	
}
