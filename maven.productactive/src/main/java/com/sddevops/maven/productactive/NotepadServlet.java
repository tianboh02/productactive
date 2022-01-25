package com.sddevops.maven.productactive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class NotepadServlet
 */
@WebServlet("/NotepadServlet")
public class NotepadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotepadServlet() {
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
			default:
				checkuserid(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		String sessionid = (String) session.getAttribute("id");
		
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter(); 
		
		//Step 2: retrieve the four parameters from the request from the web form
		String t = request.getParameter("title");
		String c = request.getParameter("content");
		
		//Step 3: attempt connection to database using JDBC, you can change the username and password accordingly using the phpMyAdmin > User Account dashboard
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/productactive", "root", "password");
			
			//Step 4: implement the sql query using prepared statement (https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
			PreparedStatement userIDAIStatement = con.prepareStatement("select max(id) from notetable");
			ResultSet rs = userIDAIStatement.executeQuery();
			
			if (rs.next()) {
					id = rs.getInt(1);
					id++;
				PreparedStatement ps = con.prepareStatement("insert into NOTETABLE values(?,?,?,?)");
				
				//Step 5: parse in the data retrieved from the web form request into the prepared statement accordingly
				int u = Integer.parseInt(sessionid);
				ps.setLong(1, id);
				ps.setLong(2, u);
				ps.setString(3, t);
				ps.setString(4, c);
				
				//Step 6: perform the query on the database using the prepared statement
				int i = ps.executeUpdate();
				
				//Step 7: check if the query had been successfully execute, return “You are successfully registered” via the response,
				if (i > 0){
					response.sendRedirect("http://localhost:8090/maven.productactive/NotepadManagement/dashboard");
				}
			}
		}
		//Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
			out.close();
		}
		
	}

	private void checkuserid(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
				HttpSession session = request.getSession(true);
				String id = (String) session.getAttribute("id");
				request.setAttribute("userid", id);
				request.getRequestDispatcher("/NotepadPage.jsp").forward(request, response);
			}
}
