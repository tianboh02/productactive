package com.sddevops.maven.productactive;


public class ActivityLogger {

	private int id;
	private int userId;
	private String activityName;
	private String activityDesription;
	private String startDateTime;
	private String endDateTime;
	
	public ActivityLogger(int id, int userId, String activityName, String activityDesription,
			String startDateTime, String endDateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.activityName = activityName;
		this.activityDesription = activityDesription;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
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
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityDesription() {
		return activityDesription;
	}
	public void setActivityDesription(String activityDesription) {
		this.activityDesription = activityDesription;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	
}
