package com.zhoujiebiyesheji.messageboard.DataBean;
import java.util.Date;

public class Bmessage {
	Integer id;
	Buser author;
	String info;
	Date published;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Buser getAuthor() {
		return author;
	}
	public void setAuthor(Buser author) {
		this.author = author;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Date getPublished() {
		return published;
	}
	public void setPublished(Date published) {
		this.published = published;
	}
}
