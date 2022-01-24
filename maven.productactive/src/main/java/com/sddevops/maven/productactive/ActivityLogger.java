package com.sddevops.maven.productactive;


public class ActivityLogger {

	private int id;
	private int userId;
	private String activityName;
	private String activityDescription;
	private String startDateTime;
	private String endDateTime;
	
	public ActivityLogger(int id, int userId, String activityName, String activityDescription,
			String startDateTime, String endDateTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
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
	public String getActivityDescription() {
		return activityDescription;
	}
	public void setActivityDescription(String activityDesription) {
		this.activityDescription = activityDesription;
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
