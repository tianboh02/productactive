package com.sddevops.maven.productactive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		response.setContentType("text/html"); // If not set, everything will be in plaintext
		// Step 1: Initialize a PrintWriter object to return the HTML values via the
		// response
		PrintWriter out = response.getWriter();

		// Step 2: retrieve the four parameters from the request from the web form
		String n = request.getParameter("username");
		String p = request.getParameter("password");
		String fn = request.getParameter("firstName");
		String ln = request.getParameter("lastName");

		// Step 3: attempt connection to database using JDBC, you can change the
		// username and password
		// accordingly using the phpMyAdmin > User Account dashboard
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root", "password");
			// Step 4: implement the sql query using prepared statement
			// (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
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
				// Step 7: check if the query had been successfully execute, return “You are
				// successfully
				// registered” via the response,
				if (i > 0) {
					response.sendRedirect("http://localhost:8090/maven.productactive/Login.jsp");
				}
			}
		}
		// Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
	}

}
