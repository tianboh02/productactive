package com.sddevops.maven.productactive;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";

	private static final String CREATE_DEADLINE = "insert into deadlinetable(userId, title, deadline) values(?,?,?)";
	private static final String SELECT_ALL_DEADLINES = "select * from deadlinetable ";
	private static final String SELECT_DEADLINE_BY_ID = "select * from deadlinetable where id =?";
	private static final String SELECT_DEADLINE_BY_USERID = "select * from deadlinetable where userId =?";
	private static final String DELETE_DEADLINE_SQL = "delete from deadlinetable where id = ?;";
	private static final String UPDATE_DEADLINE_SQL = "update deadlinetable set id = ?,userId= ?, title =?,deadline =? where id = ?;";
	
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
	
	public static String getSessionUserId(HttpSession session) {
		String idSession = (String) session.getAttribute("id");
		
		return idSession;
	}
	
	public static List<Deadline> getDeadlineByUserFK(int userFK) {

		List<Deadline> deadlines = new ArrayList<>();
		try (Connection connection = getConnection();
				// Step 5.1: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEADLINE_BY_USERID);) {

			preparedStatement.setInt(1, userFK);
			// Step 5.2: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 5.3: Process the ResultSet object.
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer userId = rs.getInt("userId");
				String title = rs.getString("title");
				String deadline = rs.getString("deadline");

				deadlines.add(new Deadline(id, userId, title, deadline));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return deadlines;
	}
	
	
	public static Deadline getDeadlineById(int deadlineId) {


		Deadline existingDeadline = new Deadline(0, 0, "", null);
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEADLINE_BY_ID);) {
			preparedStatement.setInt(1, deadlineId);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				Integer id = rs.getInt("id");
				Integer userId = rs.getInt("userId");
				String title = rs.getString("title");
				String strDeadline = rs.getString("deadline");
				existingDeadline = new Deadline(id, userId, title, strDeadline);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return existingDeadline;
	}
	
	public static int updateDeadline(int id, int userFk, String title, String deadline){

		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_DEADLINE_SQL);) {
			statement.setLong(1, id);
			statement.setLong(2, userFk);
			statement.setString(3, title);
			statement.setString(4, deadline);
			statement.setLong(5, id);
			i = statement.executeUpdate();
			return i;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}
	
	
	public static int createDeadline(int userFK, String title, String strDeadline) {
		
		int i = 0;
		
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(CREATE_DEADLINE);) {

			statement.setLong(1, userFK);
			statement.setString(2, title);
			statement.setString(3, strDeadline);

			i = statement.executeUpdate();
			return i;
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return i;
	}
	
	
	public static int deleteDeadline(int id) {

		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_DEADLINE_SQL);) {
			statement.setLong(1, id);
			i = statement.executeUpdate();
			return i;
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return i;
	}
	
	
}


