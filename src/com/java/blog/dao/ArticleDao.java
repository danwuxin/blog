/**
 *ʱ�䣺2014-4-20����3:06:49
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.dao;

import com.java.blog.db.DBhelper;
import com.java.blog.entity.Article;
import com.java.blog.entity.User;
import com.java.blog.util.IConstant;
import com.java.blog.vo.ArticleVo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ArticleDao {
	/**
	 * �û�����һƪ���� ���ص���Ӱ�������
	 * sql�������articles���в���һ����¼
	 * */
	public int insert(Article article) {
		String sql = "insert into articles ( postTime,title,contents,clicks,userid) values(?,?,?,?,?)";
		return new DBhelper().executeSql(sql, article.getPostTime(),
				article.getTitle(), article.getContents(), article.getClicks(),
				article.getUser().getUserid());
	}

	/**
	 * 
	 * �������� ���������id����������
	 * 
	 * */
	public List<Article> findByUserId(int userid) {
		String sql = "select * from articles where userid = ?";
		DBhelper helper = new DBhelper();
		List<Article> list = new ArrayList<Article>();
		ResultSet rs = helper.executeQuery(sql, userid);
		try { // while ѭ���ſ��Բ��ȫ�������� �����ڼ�������if ֻ�ܷ���һ��
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
	 * ��������idɾ��һƪ����deleteById
	 * 
	 * */
	public int deleteById(int articleId) {
		String sql = "delete from articles where articleId=?";
		return new DBhelper().executeSql(sql, articleId);
	}

	/**
	 * ��������id �����Ƭ��������
	 * Ҫ����ʱ��Ҫ�ķ��� ����Ҫ�޸ĵ����µ�id��ԭ�ȵ����²�ѯ����
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
	 * ��������
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
	 * ��ѯ�����˵���������
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
				// ����Article����
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// ����ArticleVo����
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// ���vo��������
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
				// ����Article����
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// ����ArticleVo����
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// ���vo��������
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
				// ����Article����
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// ����ArticleVo����
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
	 * ��ȡ���� rs2Article
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
/*--------------------------------------��ҳ����-------------------------------------------------------*/
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
				// ����Article����
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// ����ArticleVo����
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// ���vo��������
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}
	
	/**
	 * ĳ���û��ļ�¼������ѯ
	 * ����userid��ѯ���û�������
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
				// ����Article����
				Article article = rs2Article(rs);
				int commentCount = rs.getInt("commentCount");

				// ����ArticleVo����
				ArticleVo articleVo = new ArticleVo();
				articleVo.setArticle(article);
				articleVo.setCommentCount(commentCount);
				// ���vo��������
				list.add(articleVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		helper.close();
		return list;
	}
	

}
