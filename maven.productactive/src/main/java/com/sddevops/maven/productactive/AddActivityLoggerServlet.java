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
		String action = request.getServletPath();
		try {
			switch (action) {
			default:
				goAddLogPage(request, response);
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
		response.setContentType("text/html");
		//Step 1: Initialize a PrintWriter object to return the html values via the response
		PrintWriter out = response.getWriter();
		//Step 2: retrieve the four parameters from the request from the web form
		HttpSession session = request.getSession(true);
		String idSession = ActivityLogger.getSessionUserId(session);
		int userId = Integer.parseInt(idSession);
		String n = request.getParameter("activity_name");
		String d = request.getParameter("activity_description");
		String s = request.getParameter("activity_start");
		String e = request.getParameter("activity_end");
		
		
		int i = ActivityLogger.addActivityLog(userId, n, d, s, e);
		
		if (i > 0){
			response.sendRedirect("/maven.productactive/ActivityLoggerServlet/dashboard");
		}
		
		
	}
	
	private void goAddLogPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException
			{
				HttpSession session = request.getSession(true);
				String idSession = (String) session.getAttribute("id");
	
				request.setAttribute("userid", idSession);
				request.getRequestDispatcher("/AddActivityLog.jsp").forward(request, response);
			}

}
