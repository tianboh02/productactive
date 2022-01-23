package com.sddevops.maven.productactive;

import java.io.IOException;
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

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
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
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			default:
				setUserHome(request, response);
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
	
	private void setUserHome(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
				request.getRequestDispatcher("/HomePage.jsp").forward(request, response);
			}

}
