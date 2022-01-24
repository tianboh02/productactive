package com.sddevops.maven.productactive;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddActivityLoggerServlet
 */
@WebServlet("/AddActivityLoggerServlet")
public class AddActivityLoggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddActivityLoggerServlet() {
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
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		HttpSession session = request.getSession(true);
		String idSession = (String) session.getAttribute("id");
		int userId = Integer.parseInt(idSession);
		String n = request.getParameter("activity_name");
		String d = request.getParameter("activity_description");
		String s = request.getParameter("activity_start");
		String e = request.getParameter("activity_end");
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/productactive", "root", "password");
			//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
			PreparedStatement ps = con.prepareStatement("insert into activityloggertable(userId, activityName, activityDescription, startDateTime, endDateTime) values(?,?,?,?,?)");
			//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
			ps.setInt(1, userId);
			ps.setString(2, n);
			ps.setString(3, d);
			ps.setString(4, s);
			ps.setString(5, e);
			//Step 6: perform the query on the database using the prepared statement
			int i = ps.executeUpdate();
			//Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
			if (i > 0){
				response.sendRedirect("http://localhost:8090/maven.productactive/ActivityLoggerServlet/dashboard");
			}
		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		doGet(request, response);
		
	}

}
