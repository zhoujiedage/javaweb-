package com.zhoujiebiyesheji.messageboard.jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
	String driver;//驱动
	String url;//路径
	String userName;//用户名
	String password;//密码
	
	public DBConfig() {
		super();
		InputStream is=null;
		try {
			//is=new FileInputStream("c:\\property\\jdbc.properties");
			is = this.getClass().getClassLoader().getResourceAsStream("/jdbc.properties");
			Properties p=new Properties();
			p.load(is);
			this.driver=p.getProperty("jdbc.driver");
			this.url=p.getProperty("jdbc.url");
	        this.userName=p.getProperty("jdbc.username");
	        this.password=p.getProperty("jdbc.password");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(is!=null)
			{
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated constructor stub
	}
	public String getDriver() {
		return driver;
	}
	public String getUrl() {
		return url;
	}
	public String getUserName() {
		return userName;
	}
	public String getPassword() {
		return password;
	}
	
}