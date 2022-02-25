package com.sddevops.maven.productactive;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class DeadlineServlet
 */
@WebServlet("/DeadlineServlet")
public class DeadlineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private String jdbcUsername = "root";
	private String jdbcPassword = "password";

	private static final String CREATE_DEADLINE = "insert into deadlinetable(userId, title, deadline) values(?,?,?)";
	private static final String SELECT_ALL_DEADLINES = "select * from deadlinetable ";
	private static final String SELECT_DEADLINE_BY_ID = "select * from deadlinetable where id =?";
	private static final String SELECT_DEADLINE_BY_USERID = "select * from deadlinetable where userId =?";
	private static final String DELETE_DEADLINE_SQL = "delete from deadlinetable where id = ?;";
	private static final String UPDATE_DEADLINE_SQL = "update deadlinetable set id = ?,userId= ?, title =?,deadline =? where id = ?;";

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
	public DeadlineServlet() {
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
			case "/DeadlineServlet/delete":
				deleteDeadline(request, response);
				break;
			case "/DeadlineServlet/create":
				showCreateForm(request, response);
				break;
			case "/DeadlineServlet/insert":
				CreateDeadline(request, response);
				break;
			case "/DeadlineServlet/edit":
				showEditForm(request, response);
				break;
			case "/DeadlineServlet/update":
				updateDeadline(request, response);
				break;
			case "/DeadlineServlet/dashboard":
				listDeadlines(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
	
	private void listDeadlines(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		HttpSession session = request.getSession(true);
		String strUserId = (String) session.getAttribute("id");
		Integer userFK = Integer.parseInt(strUserId);
		
		List <Deadline> deadlines = Deadline.getDeadlineByUserFK(userFK);

		request.setAttribute("userid", strUserId);
		request.setAttribute("listDeadlines", deadlines);
		request.getRequestDispatcher("/DeadlinePage.jsp").forward(request, response);
	}
	

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		HttpSession session = request.getSession(true);
		String strUserId = (String) session.getAttribute("id");
		String strId = request.getParameter("id");
		Integer deadlineId = Integer.parseInt(strId);
		
		Deadline existingDeadline = Deadline.getDeadlineById(deadlineId);

		request.setAttribute("userid", strUserId);
		request.setAttribute("deadline", existingDeadline);
		request.getRequestDispatcher("/deadlineEdit.jsp").forward(request, response);
	}

	private void updateDeadline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String strId = request.getParameter("id");
		String strUserId = request.getParameter("userId");
		String title = request.getParameter("title");
		String strDeadline = request.getParameter("deadline");

		Integer id = Integer.parseInt(strId);
		Integer userId = Integer.parseInt(strUserId);

		Deadline.updateDeadline(id, userId, title, strDeadline);
		
		response.sendRedirect("/maven.productactive/DeadlineServlet/dashboard");
	}

	private void showCreateForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {

		HttpSession session = request.getSession(true);
		String strUserId = (String) session.getAttribute("id");

		request.setAttribute("userid", strUserId);
		request.getRequestDispatcher("/deadlineEdit.jsp").forward(request, response);
	}
	

	private void CreateDeadline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		HttpSession session = request.getSession(true);
		String strUserId = (String) session.getAttribute("id");
		Integer userFK = Integer.parseInt(strUserId);

		String title = request.getParameter("title");
		String strDeadline = request.getParameter("deadline");

		int i = Deadline.createDeadline(userFK, title,strDeadline);
		
		if (i > 0){
			response.sendRedirect("/maven.productactive/DeadlineServlet/dashboard");
		}
	}
	

	private void deleteDeadline(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		// Step 1: Retrieve value from the request
		String strId = request.getParameter("id");
		Integer id = Integer.parseInt(strId);

		Deadline.deleteDeadline(id);
		response.sendRedirect("/maven.productactive/DeadlineServlet/dashboard");
	}

}
