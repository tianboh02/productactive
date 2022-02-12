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
		
		PrintWriter out = response.getWriter(); 
		
		String t = request.getParameter("title");
		String c = request.getParameter("content");
		
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/productactive", "root", "password");
			
			PreparedStatement userIDAIStatement = con.prepareStatement("select max(id) from notetable");
			ResultSet rs = userIDAIStatement.executeQuery();
			
			if (rs.next()) {
					id = rs.getInt(1);
					id++;
				PreparedStatement ps = con.prepareStatement("insert into NOTETABLE values(?,?,?,?)");
				
				int u = Integer.parseInt(sessionid);
				ps.setLong(1, id);
				ps.setLong(2, u);
				ps.setString(3, t);
				ps.setString(4, c);
				
				int i = ps.executeUpdate();
				
				if (i > 0){
					response.sendRedirect("http://localhost:8090/maven.productactive/NotepadManagement/dashboard");
				}
			}
		}
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
