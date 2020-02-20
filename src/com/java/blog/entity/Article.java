/**
 *时间：2014-4-20下午2:50:50
 *  文章实体类
 *作者：张国宝
 *功能：TODO
 */
package com.java.blog.entity;

import com.java.blog.entity.User;

import java.util.Date;
public class Article {
	//文章id int类型
	private int articleId;
	//文章标题  string 类型
	private String title;
	//文章内容  string 类型
	private String contents;
	//文章发表日期   date类型
	private Date postTime;
	//文章点击次数  int类型
	private int clicks;
	//文章作者   引用User实体类
	private User user;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getClicks() {
		return clicks;
	}

	public void setClicks(int clicks) {
		this.clicks = clicks;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	//截取文章前100字内容放到index页面显示
	public String getShortcontents() {
		if (contents.length() > 100) {
			return this.contents.substring(0, 100) + "。。。请登录后查看全文";
		}
		return contents;
	}
	//截取短标题  超过十个字就用...代替
	public String getShortTitle() {
		if (title.length() > 10) {
			return this.title.substring(0, 10) + "...";
		}
		return title;
	}

}
