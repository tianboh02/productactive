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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// Step 1: Prepare list of variables used for database connections
	private String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	// Step 3: Implement the getConnection method which facilitates connection to
	// the database via JDBC
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
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/AccountServlet/delete":
				deleteUser(request, response);
				break;
			case "/AccountServlet/edit":
				showEditForm(request, response);
				break;
			case "/AccountServlet/update":
				updateUser(request, response);
				break;
			case "/AccountServlet/userPage":
				getUserDetails(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// To connect to the database and retrieve users information
	private void getUserDetails(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(true);
		String idSession = (String) session.getAttribute("id");
		
		List<User> userDetails = User.getUserInformation(Integer.parseInt(idSession));
		
		// Set the user details list into the attribute to be pass to the account page
		request.setAttribute("userid", idSession);
		request.setAttribute("userDetails", userDetails);
		request.getRequestDispatcher("/AccountPage.jsp").forward(request, response);
	}

	// Method to get parameter, query database for existing user data and redirect
	// to user edit page
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// Get parameter passed in the URL
		HttpSession session = request.getSession(true);
		String idSession = (String) session.getAttribute("id");
		int id = Integer.parseInt(request.getParameter("id"));

		User existingUser = User.getUserInformationEditPage(id);
		
		// Set existingUser to request and serve up the userEdit form
		request.setAttribute("userid", idSession);
		request.setAttribute("currentUser", existingUser);
		request.getRequestDispatcher("/EditUser.jsp").forward(request, response);
	}

	// Method to update the user table base on the form data
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		int oriId = Integer.parseInt(request.getParameter("oriId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		User.updateUser(username, password, firstName, lastName, oriId);
		
		// Step 3: redirect back to UserServlet (note: remember to change the url to
		// your project name)
		response.sendRedirect("/maven.productactive/AccountServlet/userPage");
	}

	// Method to delete user
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// Retrieve value from the request
		int id = Integer.parseInt(request.getParameter("id"));

		User.deleteUser(id);
		
		// Redirect back to home and clear attributes from session
		HttpSession session = request.getSession(true);
		session.removeAttribute("userid");
		session.removeAttribute("userDetails");
		session.removeAttribute("id");
		response.sendRedirect("/maven.productactive/HomePage.jsp");
	}
}
