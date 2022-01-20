package com.sddevops.maven.productactive;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class DeadlineServlet
 */
@WebServlet("/DeadlineServlet")
public class DeadlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	 private String jdbcUsername = "root";
	 private String jdbcPassword = "password";
	 
	 private static final String SELECT_ALL_DEADLINES = "select * from deadlinetable ";
	 private static final String SELECT_DEADLINE_BY_ID = "select * from deadlinetable where userId =?";
	 private static final String DELETE_DEADLINE_SQL = "delete from deadlinetable where name = ?;";
	 private static final String UPDATE_DEADLINE_SQL = "update deadlinetable set id = ?,userId= ?, title =?,deadline =? where id = ?;";
	 
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
    public DeadlineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 
		 String action = request.getServletPath();
		 try {
		 switch (action) {
//		 case "/UserServlet/delete":
//		 deleteDeadline(request, response);
//		 break;
//		 case "/UserServlet/edit":
//		 showEditForm(request, response);
//		 break;
//		 case "/UserServlet/update":
//		 updateDeadline(request, response);
//		 break;
		 case "/DeadlineServlet":
		 listDeadlines(request, response);
		 break;
		 }
		 } catch (SQLException ex) {
		 throw new ServletException(ex);
		 } 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listDeadlines(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
			List <Deadline> deadlines = new ArrayList <>();
			 try (Connection connection = getConnection();
			 // Step 5.1: Create a statement using connection object
			 PreparedStatement preparedStatement =
			connection.prepareStatement(SELECT_ALL_DEADLINES);) {
			 // Step 5.2: Execute the query or update query
			 ResultSet rs = preparedStatement.executeQuery();
			 // Step 5.3: Process the ResultSet object.
			 while (rs.next()) {
			 Integer id = rs.getInt("id");
			 Integer userId = rs.getInt("userId");
			 String title = rs.getString("title");
			 String strDeadline = rs.getString("deadline");
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 
			 LocalDateTime deadline = LocalDateTime.parse(strDeadline, formatter);
			 deadlines.add(new Deadline(id,userId, title, deadline));
			 }
			 } catch (SQLException e) {
			 System.out.println(e.getMessage());
			 }
//			 Step 5.4: Set the users list into the listUsers attribute to be pass to the userManagement.jsp
			request.setAttribute("listDeadlines", deadlines);
			request.getRequestDispatcher("/DeadlinePage.jsp").forward(request, response);
			}
	
//	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, ServletException, IOException {
//			//get parameter passed in the URL
//			String strId = request.getParameter("id");
//			var deadlineId = Integer.parseInt(strId);
//			Deadline existingDeadline = new Deadline(0, 0, "", null);
//			// Step 1: Establishing a Connection
//			try (Connection connection = getConnection();
//			// Step 2:Create a statement using connection object
//			PreparedStatement preparedStatement =
//			connection.prepareStatement(SELECT_DEADLINE_BY_ID);) {
//			preparedStatement.setInt(1, deadlineId);
//			// Step 3: Execute the query or update query
//			ResultSet rs = preparedStatement.executeQuery();
//			// Step 4: Process the ResultSet object
//			while (rs.next()) {
//			Integer id = rs.getInt("deadlineId");
//			Integer userId = rs.getInt("userId");
//			String title = rs.getString("title");
//			String strDeadline = rs.getString("deadline");
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
//			LocalDateTime deadline = LocalDateTime.parse(strDeadline, formatter);
//			
//			existingDeadline = new Deadline(id, userId, title, deadline);
//			}
//			} catch (SQLException e) {
//			System.out.println(e.getMessage());
//			}
//			//Step 5: Set existingUser to request and serve up the userEdit form
//			request.setAttribute("deadline", existingDeadline);
//			request.getRequestDispatcher("/deadlineEdit.jsp").forward(request, response);
//			}
//	
//	private void updateDeadline(HttpServletRequest request, HttpServletResponse response)
//			throws SQLException, IOException {
//			//Step 1: Retrieve value from the request
//			 String strId = request.getParameter("id");
//			 String strUserId = request.getParameter("userId");
//			 String title = request.getParameter("title");
//			 String strDeadline = request.getParameter("deadline");
//			 
//			 Integer id = Integer.parseInt(strId);
//			 Integer userId = Integer.parseInt(strUserId);
//			 
////			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
////			 LocalDateTime deadline = LocalDateTime.parse(strDeadline, formatter);
//			 
//			 //Step 2: Attempt connection with database and execute update user SQL query
//			 try (Connection connection = getConnection(); PreparedStatement statement =
//			connection.prepareStatement(UPDATE_DEADLINE_SQL);) {
//			 statement.setLong(1, id);
//			 statement.setLong(2, userId);
//			 statement.setString(3, title);
//			 statement.setString(4, strDeadline);
//			 statement.setLong(5, id);
//			 int i = statement.executeUpdate();
//			 }
//			 //Step 3: redirect back to UserServlet (note: remember to change the url to your project name)
//			 response.sendRedirect("http://localhost:8090/maven.productactive/DeadlineServlet/dashboard");
//			}
//	
//	//method to delete user
//	private void deleteDeadline(HttpServletRequest request, HttpServletResponse response)
//	throws SQLException, IOException {
//	//Step 1: Retrieve value from the request
//	 String strId = request.getParameter("id");
//	 Integer id = Integer.parseInt(strId);
//	 //Step 2: Attempt connection with database and execute delete user SQL query
//	 try (Connection connection = getConnection(); PreparedStatement statement =
//	connection.prepareStatement(DELETE_DEADLINE_SQL);) {
//		 statement.setLong(1, id);
//	 int i = statement.executeUpdate();
//	 }
//	 //Step 3: redirect back to UserServlet dashboard (note: remember to change the url to your project name)
//	 response.sendRedirect("http://localhost:8090/maven.productactive/DeadlineServlet/dashboard");
//	}

}
