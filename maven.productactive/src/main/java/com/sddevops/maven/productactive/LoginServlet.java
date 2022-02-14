package com.sddevops.maven.productactive;

import java.awt.JobAttributes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		response.setContentType("text/html");
		// Initialize a PrintWriter object to return the HTML values via the
		// PrintWriter out = response.getWriter();
		// Initialize JFrame for showing message box

		// Get parameters from the request from the web form
		String usernameLogin = request.getParameter("username");
		String passwordLogin = request.getParameter("password");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root",
					"password");
			// Implement the SQL query using prepared statement
			PreparedStatement checkLoginStatement = con
					.prepareStatement("select * from usertable where username=? and password=?");
			// Set the value for the SQL query
			checkLoginStatement.setString(1, usernameLogin);
			checkLoginStatement.setString(2, passwordLogin);
			ResultSet rs = checkLoginStatement.executeQuery();
			// If user exists
			if (rs.next()) {
				// Assign data retrieved from SQL query
				int id = rs.getInt(1);
				String username = rs.getString(2);
				HttpSession session = request.getSession(true);
				session.setAttribute("id", Integer.toString(id));
				// RequestDispatcher rd = request.getRequestDispatcher("/HomeServlet.java");
				// rd.forward(request, response);
				response.sendRedirect("/maven.productactive/HomeServlet");
			}
			// If user does not exist
			else {
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println(e);
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
		// If no user is logged in, redirect to login
		else {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}
}
