package com.sddevops.maven.productactive;

import java.awt.JobAttributes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import static javax.swing.JOptionPane.showMessageDialog;

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
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setLocation(0, 0);
		
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
			if (rs.next()) {
				// Assign data retrieved from SQL query
				int id = rs.getInt(1);
				String username = rs.getString(2);
				// HttpSession session = request.getSession(true);
				// session.setAttribute("username", username);
				// session.setAttribute("id", id);
				RequestDispatcher rd = request.getRequestDispatcher("HomePage.jsp");
				rd.forward(request, response);
				JOptionPane.showMessageDialog(frame, "Welcome back, " + username + " " + id);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
				JOptionPane.showMessageDialog(frame, "Wrong username or password");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		frame.dispose();
	}
}
