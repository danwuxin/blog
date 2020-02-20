/**
 *ʱ�䣺2014-4-18����9:41:20
 *
 *���ߣ��Ź���
 *���ܣ�TODO
 */
package com.java.blog.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBhelper {

	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://10.255.105.101:3306/blog?useUnicode=true&characterEncoding=utf-8";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;

	/**
	 * �������ݿ�����
	 * */ 
	private void loadDriver() {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �������ݿ�
	 * */
	public Connection getconnection() {
		loadDriver();
		try {
			connection = DriverManager.getConnection(URL,"root","132wisdom");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	
	/**
	 * ���ܣ���ɾ��
	 * �������������sql����object����
	 * �����Ӱ������� ���ؽ����int
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
	 * ���ܣ���ѯ  ���ؽ����
	 * �������������sql����object����
	 * �����Ӱ������� ���ؽ�������
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
	 * �ر���Դ
	 * �ȹر�rs-preparedStatement-connection
	 * ˳�����ڶ����˳��
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