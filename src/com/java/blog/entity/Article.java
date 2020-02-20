/**
 *ʱ�䣺2014-4-20����2:50:50
 *  ����ʵ����
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.entity;

import java.util.Date;
public class Article {
    //����id int����
	private int articleId;
	//���±���  string ����
	private String title;
	//��������  string ����
	private String contents;
	//���·�������   date����
	private Date postTime;
	//���µ������  int����
	private int clicks;
	//��������   ����Userʵ����
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
	//��ȡ����ǰ100�����ݷŵ�indexҳ����ʾ
	public String getShortcontents() {
		if (contents.length() > 100) {
			return this.contents.substring(0, 100) + "���������¼��鿴ȫ��";
		}
		return contents;
	}
	//��ȡ�̱���  ����ʮ���־���...����
	public String getShortTitle() {
		if (title.length() > 10) {
			return this.title.substring(0, 10) + "...";
		}
		return title;
	}

}
