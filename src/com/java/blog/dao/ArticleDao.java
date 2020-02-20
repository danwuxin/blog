package com.java.blog.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.blog.dao.UserDao;
import com.java.blog.db.DBhelper;
import com.java.blog.entity.Article;
import com.java.blog.entity.User;
import com.java.blog.util.IConstant;
import com.java.blog.vo.ArticleVo;


public class ArticleDao {
	/**
	 * 用户发表一篇文章 返回的是影响的行数
	 * sql语句是在articles表中插入一条记录
	 * */
	public int insert(Article article) {
		String sql = "insert into articles ( postTime,title,contents,clicks,userid) values(?,?,?,?,?)";
		return new DBhelper().executeSql(sql, article.getPostTime(),
				article.getTitle(), article.getContents(), article.getClicks(),
				article.getUser().getUserid());
	}

	/**
	 *
	 * 根据作者 查出该作者id的所有文章
	 *
	 * */
	public List<Article> findByUserId(int userid) {
		String sql = "select * from articles where userid = ?";
		DBhelper helper = new DBhelper();
		List<Article> list = new ArrayList<Article>();
		ResultSet rs = helper.executeQuery(sql, userid);
		try { // while 循环才可以查出全部的文章 不能在集合中用if 只能返回一条
			while (rs.next()) {

				Article article = rs2Article(rs);
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return list;
	}

	/**
	 *
	 * 根据文章id删除一篇文章deleteById
	 *
	 * */
	public int deleteById(int articleId) {
		String sql = "delete from articles where articleId=?";
		return new DBhelper().executeSql(sql, articleId);
	}

	/**
	 * 根据文章id 查出整片文章内容
	 * 要更新时需要的方法 根据要修改的文章的id就原先的文章查询出来
	 * */
	public Article findByArticleId(int articleId) {
		Article article = null;
		String sql = "select * from articles where articleId=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql, articleId);
		try {
			if (rs.next()) {
				article = rs2Article(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return article;
	}

	/**
	 * 更新文章
	 *
	 * */
	public int update(Article article) {
		String sql = "update articles set PostTime=?,title=?,contents=?,clicks=?,userid=? where articleId=?";
		return new DBhelper().executeSql(sql, article.getPostTime(),
				article.getTitle(), article.getContents(), article.getClicks(),
				article.getUser().getUserid(), article.getArticleId());
	}

	/**
	 *
	 * 查询所有人的所有文章
	 *
	 *
	 * */
	public List<ArticleVo> findAll() {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		String sql = "SELECT *,(SELECT COUNT(*) FROM comments WHERE comments.articleId=articles.articleId) AS commentCount FROM articles";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql);

		try {
			while (rs.next()) {
				// 构造Article对象
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// 构造ArticleVo对象
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// 添加vo到集合中
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}

	public List<ArticleVo> findArticleVoByUserId(int userId) {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		String sql = "SELECT *,(SELECT COUNT(*) FROM comments WHERE comments.articleId=articles.articleId) AS commentCount FROM articles where userid=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql,userId);

		try {
			while (rs.next()) {
				// 构造Article对象
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// 构造ArticleVo对象
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// 添加vo到集合中
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}



	public ArticleVo findArticleVoById(int articleId) {
		String sql = "select *,(select count(*) from comments where comments.articleId=articles.articleId) as commentCount from articles where articleId=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql,articleId);
		ArticleVo articleVo = null;
		try {
			if (rs.next()) {
				// 构造Article对象
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// 构造ArticleVo对象
				articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return articleVo;
	}
	/**
	 * 抽取方法 rs2Article
	 * */
	private Article rs2Article(ResultSet rs) throws SQLException {
		Article article = new Article();
		article.setArticleId(rs.getInt("articleid"));
		article.setClicks(rs.getInt("clicks"));
		article.setContents(rs.getString("contents"));
		article.setPostTime(rs.getTimestamp("postTime"));
		article.setTitle(rs.getString("title"));
		int userid = rs.getInt("userid");
		User user = new UserDao().findUserById(userid);
		article.setUser(user);
		return article;
	}
	/*--------------------------------------分页部分-------------------------------------------------------*/
	public int getArticleCount() {
		int result = 0;
		String sql="select count(*) from articles";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql);
		try {
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return result;
	}

	public List<ArticleVo> getArticleByPaging(int pageIndex) {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		String sql = "SELECT *,(SELECT COUNT(*) FROM comments WHERE comments.articleId=articles.articleId) AS commentCount FROM articles limit ?,?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql,(pageIndex - 1)
				* IConstant.PAGE_SIZE, IConstant.PAGE_SIZE);

		try {
			while (rs.next()) {
				// 构造Article对象
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// 构造ArticleVo对象
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// 添加vo到集合中
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}

	/**
	 * 某个用户的记录条数查询
	 * 根据userid查询出用户文章数
	 **/
	public int getArticleCount(int userId) {
		int result = 0;
		String sql="select count(*) from articles where userId=?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql,userId);
		try {
			if(rs.next()){
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		helper.close();
		return result;
	}

	public List<ArticleVo> getArticleByPaging(int pageIndex,int userId) {
		List<ArticleVo> list = new ArrayList<ArticleVo>();
		String sql = "SELECT *,(SELECT COUNT(*) FROM comments WHERE comments.articleId=articles.articleId) AS commentCount FROM articles where UserId=? limit ?,?";
		DBhelper helper = new DBhelper();
		ResultSet rs = helper.executeQuery(sql,userId,(pageIndex - 1)
				* IConstant.PAGE_SIZE, IConstant.PAGE_SIZE);

		try {
			while (rs.next()) {
				// 构造Article对象
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// 构造ArticleVo对象
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// 添加vo到集合中
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}


}
