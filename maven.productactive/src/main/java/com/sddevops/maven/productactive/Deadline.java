package com.sddevops.maven.productactive;

import java.time.LocalDateTime;

public class Deadline {

	private int id;
	private int userid;
	private String title;
	private LocalDateTime date;
	
	public Deadline(int id, int userid, String title, LocalDateTime date) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
