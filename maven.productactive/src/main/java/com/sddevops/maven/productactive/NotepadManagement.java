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
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class NotepadManagement
 */
@WebServlet("/NotepadManagement")
public class NotepadManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		private String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
		private String jdbcUsername = "root";
		private String jdbcPassword = "password";
		
		private static final String INSERT_NOTES_SQL = "INSERT INTO notetable" + " (id, userid, title, content) VALUES " + " (?, ?, ?);";
		private static final String SELECT_NOTES_BY_ID = "select id,userid,title,content from notetable where id =?";
		private static final String SELECT_NOTES_BY_USERID = "select id,userid,title,content from notetable where userid =?";
		private static final String SELECT_ALL_NOTES = "select * from notetable";
		private static final String DELETE_NOTES_SQL = "delete from notetable where id =?;";
		private static final String UPDATE_NOTES_SQL = "update notetable set id = ?, userid = ?, title = ?, content = ? where id = ?;";
		
		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			} 
			catch (SQLException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotepadManagement() {
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
						case "/NotepadManagement/delete":
						deleteUser(request, response);
						break;
						case "/NotepadManagement/edit":
						showEditForm(request, response);
						break;
						case "/NotepadManagement/update":
						updateUser(request, response);
						break;
						case "/NotepadManagement/dashboard":
						listNotes(request, response);
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
		doGet(request, response);
	}
	
	private void listNotes(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		List <Notes> notes = new ArrayList <>();
		HttpSession session = request.getSession(true);
		String sessionid = (String) session.getAttribute("id");
		int sesid = Integer.parseInt(sessionid);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_USERID);) {
				preparedStatement.setInt(1, sesid);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					int userid = rs.getInt("userid");
					String title = rs.getString("title");
					String content = rs.getString("content");
					notes.add(new Notes(id, userid, title, content));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		request.setAttribute("userid", sessionid);
		request.setAttribute("listNotes", notes);
		request.getRequestDispatcher("/NoteView.jsp").forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession(true);
		String sessionid = (String) session.getAttribute("id");
		int id = Integer.parseInt(request.getParameter("id"));
		Notes existingNote = new Notes(0, 0, "", "");
		
		try (Connection connection = getConnection(); 
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOTES_BY_ID);) {
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				id = rs.getInt("id");
				int userid = rs.getInt("userid");
				String title = rs.getString("title");
				String content = rs.getString("content");
				existingNote = new Notes(id, userid, title, content);
			}
			} catch (SQLException e) {
			System.out.println(e.getMessage());
			}
			request.setAttribute("userid", sessionid);
			request.setAttribute("note", existingNote);
			request.getRequestDispatcher("/NoteEdit.jsp").forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		int oriId = Integer.parseInt(request.getParameter("oriId"));
		int id = Integer.parseInt(request.getParameter("id"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_NOTES_SQL);) {
			statement.setInt(1, id);
			statement.setInt(2, userid);
			statement.setString(3, title);
			statement.setString(4, content);
			statement.setInt(5, oriId);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("/maven.productactive/NotepadManagement/dashboard");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setInt(1, id);
			int i = statement.executeUpdate();
		}
		response.sendRedirect("/maven.productactive/NotepadManagement/dashboard");
	}
}
