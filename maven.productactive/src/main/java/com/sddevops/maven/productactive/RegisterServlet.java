package com.sddevops.maven.productactive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch (action) {
			default:
				checkUserLoggedIn(request, response);
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
		response.setContentType("text/html"); // If not set, everything will be in plaintext
		// Step 1: Initialize a PrintWriter object to return the HTML values via the
		PrintWriter out = response.getWriter();

		// Step 2: retrieve the four parameters from the request from the web form
		String n = request.getParameter("username");
		String p = request.getParameter("password");
		String fn = request.getParameter("firstName");
		String ln = request.getParameter("lastName");

		// Step 3: attempt connection to database using JDBC, you can change the
		// username and password
		// accordingly using the phpMyAdmin > User Account dashboard

		int i = User.registerUser(n, p, fn, ln);
		
		if (i > 0) {
			response.sendRedirect("/maven.productactive/LoginServlet");
		}
	}

	// Check if user is logged in
	private void checkUserLoggedIn(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession(true);
		String id = (String) session.getAttribute("id");
		request.setAttribute("userid", id);
		// If there is a user logged in
		if (id != null) {
			request.getRequestDispatcher("/HomeServlet").forward(request, response);
		}
		// If no user is logged in, redirect to register
		else {
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		}
	}

	// Register function for JUnit test
	public boolean registerUser(String username, String password, String firstName, String lastName)
			throws ServletException, IOException {
		// Assign parameters
		String n = username;
		String p = password;
		String fn = firstName;
		String ln = lastName;

		// Database connection
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root",
					"password");
			// Execute SQL queries
			PreparedStatement userIDAIStatement = con.prepareStatement("select max(id) from usertable");
			ResultSet rs = userIDAIStatement.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				id++;

				PreparedStatement ps = con.prepareStatement("insert into usertable values(?,?,?,?,?)");
				// Step 5: parse in the data retrieved from the web form request into the
				// prepared statement
				// accordingly
				ps.setLong(1, id);
				ps.setString(2, n);
				ps.setString(3, p);
				ps.setString(4, fn);
				ps.setString(5, ln);
				// Step 6: perform the query on the database using the prepared statement
				int i = ps.executeUpdate();
				if (i > 0) {
					return true;
				}
			}
		}
		// Catch for error
		catch (Exception exception) {
			System.out.println(exception);
		}
		return false;
	}
}
