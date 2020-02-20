package com.java.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhelper {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;

	/**
	 * 加载数据库驱动
	 * */
	private void loadDriver() {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 链接数据库
	 * */
	public Connection getconnection() {
		loadDriver();
		try {
			connection = DriverManager.getConnection(URL,"root","root");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}


	/**
	 * 功能：增删改
	 * 参数：传入的是sql语句和object对象
	 * 结果：影响的行数 返回结果是int
	 * */
	public int executeSql(String sql, Object... values) {
		int result = 0;
		try {
			connection = getconnection();
			preparedStatement = connection.prepareStatement(sql);
			if (null != values) {
				for (int i = 1; i <= values.length; i++) {
					preparedStatement.setObject(i, values[i - 1]);
				}

			}
			result = preparedStatement.executeUpdate();
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 功能：查询  返回结果集
	 * 参数：传入的是sql语句和object对象
	 * 结果：影响的行数 返回结果结果集
	 * */
	public ResultSet executeQuery(String sql, Object... values) {

		try {
			connection = getconnection();
			preparedStatement = connection.prepareStatement(sql);
			if (null != values) {
				for (int i = 1; i <= values.length; i++) {
					preparedStatement.setObject(i, values[i - 1]);
				}
			}
			rs = preparedStatement.executeQuery();
		} catch (Exception e) {
		}
		return rs;
	}

	/**
	 * 关闭资源
	 * 先关闭rs-preparedStatement-connection
	 * 顺序：由内而外的顺序
	 * */
	public void close() {
		close(rs);
		close(preparedStatement);
		close(connection);
	}

	private void close(Connection connection) {
		if (null != connection) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void close(PreparedStatement preparedStatement) {
		if (null != preparedStatement) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	private void close(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
