package com.sddevops.maven.productactive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ActivityLoggerServlet
 */
@WebServlet("/ActivityLoggerServlet")
public class ActivityLoggerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";
	//Step 2: Prepare list of SQL prepared statements to perform CRUD to our database
	private static final String INSERT_LOG_SQL = "INSERT INTO activityloggertable" + " (userId, activityName, activityDescription, startDateTime, endDateTime) VALUES " +
	" (?, ?, ?, ?, ?);";
	private static final String SELECT_LOG_BY_ID = "select id, userId, activityName, activityDescription, startDateTime, endDateTime from activityloggertable where id = ?";
	private static final String SELECT_LOG_BY_USERID = "select id, userId, activityName, activityDescription, startDateTime, endDateTime from activityloggertable where userId = ?";
	private static final String SELECT_ALL_LOG = "select * from activityloggertable ";
	private static final String DELETE_LOG_SQL = "delete from activityloggertable where id = ?;";
	private static final String UPDATE_LOG_SQL = "update activityloggertable set userId = ?, activityName= ?, activityDescription =?,startDateTime =?, endDateTime =? where id = ?;";
	//Step 3: Implement the getConnection method which facilitates connection to the database via JDBC
	protected Connection getConnection() {
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
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActivityLoggerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Step 4: Depending on the request servlet path, determine the function to invoke using the follow switch statement.
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/ActivityLoggerServlet/delete":
				deleteLog(request, response);
				break;
			case "/ActivityLoggerServlet/edit":
				showEditForm(request, response);
				break;
			case "/ActivityLoggerServlet/update":
				updateLog(request, response);
				break;
			case "/ActivityLoggerServlet/dashboard":
				listUsers(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}
	
	
	//Step 5: listUsers function to connect to the database and retrieve all users records
	private void listUsers(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException
	{
		List <ActivityLogger> logs = new ArrayList <>();
		HttpSession session = request.getSession(true);
		String idSession = (String) session.getAttribute("id");
		try (Connection connection = getConnection();
		// Step 5.1: Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG_BY_USERID);) {
		preparedStatement.setInt(1, Integer.parseInt(idSession));

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
		// Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
		request.setAttribute("userid", idSession);
		request.setAttribute("listActivityLog", logs);
		request.getRequestDispatcher("/ActivityLoggerPage.jsp").forward(request, response);
	}
	
	
	//method to get parameter, query database for existing user data and redirect to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, ServletException, IOException {
	//get parameter passed in the URL
	HttpSession session = request.getSession(true);
	String idSession = (String) session.getAttribute("id");
	int id = Integer.parseInt(request.getParameter("id"));
	ActivityLogger existingLog = new ActivityLogger(id, Integer.parseInt(idSession), "", "", "", "");
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
	// Step 2:Create a statement using connection object
	PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOG_BY_ID);) {
		preparedStatement.setInt(1, id);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();
		// Step 4: Process the ResultSet object
		while (rs.next()) {
		id = rs.getInt("id");
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
	//Step 5: Set existingUser to request and serve up the userEdit form
	request.setAttribute("userid", idSession);
	request.setAttribute("activityLog", existingLog);
	request.getRequestDispatcher("/EditActivityLog.jsp").forward(request, response);
	}
	
	//method to update the user table base on the form data
	private void updateLog(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	int oriId = Integer.parseInt(request.getParameter("oriId"));
	int oriUserId = Integer.parseInt(request.getParameter("oriUserId"));
	String activityName = request.getParameter("activityName");
	String activityDescription = request.getParameter("activityDescription");
	String startDateTime = request.getParameter("startDateTime");
	String endDateTime = request.getParameter("endDateTime");
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
	//Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
	response.sendRedirect("/ActivityLoggerServlet/dashboard");
	}
	
	
	//method to delete user
	private void deleteLog(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException {
	//Step 1: Retrieve value from the request
	int id = Integer.parseInt(request.getParameter("id"));
	//Step 2: Attempt connection with database and execute delete user SQL query
	try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_LOG_SQL);) {
		statement.setInt(1, id);
		int i = statement.executeUpdate();
	}
	//Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
	response.sendRedirect("/ActivityLoggerServlet/dashboard");
	}

}
