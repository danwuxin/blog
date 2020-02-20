/**
*时间：2014-4-26下午11:47:50
*
*作者：张国宝
*功能：Article Vo实体类
*/
package com.java.blog.vo;


import com.java.blog.entity.Article;

public class ArticleVo {
    //文章对象 
	private Article article;
	//文章评论数
	private int commentCount;
	
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
