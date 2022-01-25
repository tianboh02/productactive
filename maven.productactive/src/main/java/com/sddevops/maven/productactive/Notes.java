package com.sddevops.maven.productactive;

public class Notes {
	//private int id;
	//private int userid;
	//private String title;
	//private String content;
	
	protected int id;
	protected int userid;
	protected String title;
	protected String content;
	
	public Notes(int id, int userid, String title, String content) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
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
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	

}
