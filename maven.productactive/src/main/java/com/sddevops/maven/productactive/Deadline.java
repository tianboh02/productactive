package com.sddevops.maven.productactive;

import java.time.LocalDateTime;

public class Deadline {

	private int id;
	private int userId;
	private String title;
	private String deadline;
	
	public Deadline(int id, int userId, String title, String deadline) {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.deadline = deadline;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
}
