package com.sddevops.maven.productactive;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";
	private static final String SELECT_LOG_BY_ID = "select id, userId, activityName, activityDescription, startDateTime, endDateTime from activityloggertable where id = ?";
	private static final String SELECT_LOG_BY_USERID = "select id, userId, activityName, activityDescription, startDateTime, endDateTime from activityloggertable where userId = ?";
	private static final String DELETE_LOG_SQL = "delete from activityloggertable where id = ?;";
	private static final String UPDATE_LOG_SQL = "update activityloggertable set userId = ?, activityName= ?, activityDescription =?,startDateTime =?, endDateTime =? where id = ?;";
	private static final String ADD_LOG_SQL = "insert into activityloggertable(userId, activityName, activityDescription, startDateTime, endDateTime) values(?,?,?,?,?)";
	
	
	protected static Connection getConnection() {
		Connection connection = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
		return connection;
	}
	
	public static String getUserId(HttpSession session) {
		String idSession = (String) session.getAttribute("id");
		
		if(idSession.isEmpty() || idSession.equals("")) {
			return null;
		}
		
		return idSession;
	}
	
	public static List<ActivityLogger> getActivityLogByUserid(int userid) {
		List <ActivityLogger> logs = new ArrayList <>();
		
		try (Connection connection = getConnection();
		// Step 5.1: Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG_BY_USERID);) {
		preparedStatement.setInt(1, userid);

		// Step 5.2: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				int userId = rs.getInt("userId");
				String activityName = rs.getString("activityName");
				String activityDescription = rs.getString("activityDescription");
				String startDateTime = rs.getString("startDateTime");
				String endDateTime = rs.getString("endDateTime");
				logs.add(new ActivityLogger(id, userId, activityName, activityDescription, startDateTime, endDateTime));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return logs;
	}
	
	public static ActivityLogger getActivityLogByid(int logid, int userid) {
		ActivityLogger existingLog = new ActivityLogger(logid, 0 , "", "", "", "");
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
		// Step 2:Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG_BY_ID);) {
			preparedStatement.setInt(1, logid);
			// Step 3: Execute the query or update query
			ResultSet rs  = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
			int id = rs.getInt("id");
			int userId = rs.getInt("userId");
			String activityName = rs.getString("activityName");
			String activityDescription = rs.getString("activityDescription");
			String startDateTime = rs.getString("startDateTime");
			String endDateTime = rs.getString("endDateTime");
			existingLog = new ActivityLogger(id, userId, activityName, activityDescription, startDateTime, endDateTime);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return existingLog;
	}
	
	public static String addActivityLog(int userid, String name, String description, String start, String end) {

		String n = name;
		String d = description;
		String s = start;
		String e = end;
		int i = 0;
		
		try (Connection connection = getConnection(); 
			PreparedStatement ps = connection.prepareStatement(ADD_LOG_SQL);) {
			ps.setInt(1, userid);
			ps.setString(2, n);
			ps.setString(3, d);
			ps.setString(4, s);
			ps.setString(5, e);
			//Step 6: perform the query on the database using the prepared statement
			i = ps.executeUpdate();
			return Integer.toString(i);

		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
				
	}
	
	public static ActivityLogger editActivityLog(int id, int userid, String name, String description, String start, String end) {
		int oriId = id;
		int oriUserId = userid;
		String activityName = name;
		String activityDescription = description;
		String startDateTime = start;
		String endDateTime = end;
		//Step 2: Attempt connection with database and execute update user SQL query
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_LOG_SQL);) {
			statement.setInt(1, oriUserId);
			statement.setString(2, activityName);
			statement.setString(3, activityDescription);
			statement.setString(4, startDateTime);
			statement.setString(5, endDateTime);
			statement.setInt(6, oriId);
			int i = statement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ActivityLogger deleteActivityLog(int logid) {
		//Step 1: Retrieve value from the request
		int id = logid;
		//Step 2: Attempt connection with database and execute delete user SQL query
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_LOG_SQL);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	
	
}
