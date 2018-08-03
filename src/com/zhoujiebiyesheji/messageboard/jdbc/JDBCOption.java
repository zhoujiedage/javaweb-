package com.zhoujiebiyesheji.messageboard.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhoujiebiyesheji.messageboard.tools.Common;

public class JDBCOption {
	private static DBConfig dbConfig = null;
	static {
		dbConfig = new DBConfig();
		try {
			Class.forName(dbConfig.getDriver());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 通用更新操作
	 * @param sql 语句
	 * @param paramters 动态参数
	 * @return
	 */
	public static int update(String sql, Object ...paramters) {
		int returnValue = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;

		try {
			conn = DriverManager.getConnection(dbConfig.getUrl(),
					dbConfig.getUserName(), dbConfig.getPassword());
			preparedStatement = conn.prepareStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			returnValue = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			Common.free(conn, preparedStatement, null);
		}
		return returnValue;
	}
	/**
	 * 通用查询操作
	 * @param sql 语句	
	 * @param paramters 动态参数
	 * @return
	 */
	public static List query(String sql, Object ...paramters) {
		List returnValue = new ArrayList();
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		try {
			conn = DriverManager.getConnection(dbConfig.getUrl(),
					dbConfig.getUserName(), dbConfig.getPassword());
			preparedStatement = conn.prepareStatement(sql);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			rs= preparedStatement.executeQuery();
			Common.getList(returnValue, rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			Common.free(conn, preparedStatement, rs);
		}
		return returnValue;
	}
	/**
	 * 用于add 返回新加入的ID值
	 * 
	 * @param sql
	 * @return 新加入ID值
	 * @throws SQLException
	 */
	public static int insertGetID(String sql, Object ...paramters) {
		int returnValue = 0;
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(dbConfig.getUrl(),
					dbConfig.getUserName(), dbConfig.getPassword());
			preparedStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < paramters.length; i++) {
				preparedStatement.setObject(i + 1, paramters[i]);
			}
			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				returnValue = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Common.free(conn, preparedStatement, null);
		}
		return returnValue;
		
	}
	}