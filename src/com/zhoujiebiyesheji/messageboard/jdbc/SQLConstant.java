package com.zhoujiebiyesheji.messageboard.jdbc;

public class SQLConstant {
		// 添加公告
		public static final String addMessage = "INSERT INTO bmessage(author,info,published)  VALUES(?,?,?)";
		// 返回所有公告
		public static final String getAllMessage = "SELECT * FROM bmessage";
		// 从用户名返回用户，用于判断用户名是否存在
		public static final String getUserByName = "SELECT * FROM buser WHERE userName=?";
		// 用户注册
		public static final String addUser = "INSERT INTO buser(userName,password,gender,email,birthdate) VALUES(?,?,?,?,?)";
		// 修改用户注册信息
		public static final String updateUser = "UPDATE buser SET password=?,gender=?,email=?,birthdate=? WHERE id=?";
		// 从ID返回用户
		public static final String getUserByID = "SELECT * FROM buser WHERE id=?";
		// 登录查询
		public final static String loginQuery = "SELECT id FROM buser WHERE userName=? and password=?";
		// 用户所有的信息
		public final static String getMessageByUserID = "SELECT bmessage.id,bmessage.info,bmessage.published,buser.userName FROM bmessage inner join buser on buser.id=bmessage.author WHERE author=?";
		//从信息ID返回信息，联合查询
		public final static String getMessageByMessageID = "SELECT bmessage.id,bmessage.info,bmessage.published,buser.userName FROM bmessage inner join buser on buser.id=bmessage.author WHERE bmessage.id=? and buser.id=?";
		// 修改信息
		public final static String modifyMessage = "UPDATE bmessage SET info= ? WHERE id=?";
		// 删除信息
		public final static String deletMessage = "DELETE FROM bmessage WHERE id=?";
		
	}

