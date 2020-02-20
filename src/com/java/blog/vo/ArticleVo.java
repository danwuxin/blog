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
