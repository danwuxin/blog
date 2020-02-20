/**
*时间：2014-4-27下午5:08:01
*
*作者：张国宝
*功能：用户vo实体类
*/
package com.java.blog.vo;


import com.java.blog.entity.User;

public class UserVo {
    //文章作者
	private User user;
	//文章点击数
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
