package com.sddevops.maven.productactive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class Notes {
	//private int id;
	//private int userid;
	//private String title;
	//private String content;
	
	protected int id;
	protected int userid;
	protected String title;
	protected String content;
	
	public Notes(int id, int userid, String title, String content) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	private static String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	private static String jdbcUsername = "root";
	private static String jdbcPassword = "password";
	
	private static final String INSERT_NOTES_SQL = "INSERT INTO notetable" + " (id, userid, title, content) VALUES " + " (?, ?, ?, ?);";
	private static final String SELECT_NOTES_BY_ID = "select id,userid,title,content from notetable where id =?";
	private static final String SELECT_NOTES_BY_USERID = "select id,userid,title,content from notetable where userid =?";
	private static final String DELETE_NOTES_SQL = "delete from notetable where id =?;";
	private static final String UPDATE_NOTES_SQL = "update notetable set id = ?, userid = ?, title = ?, content = ? where id = ?;";
	
	protected static Connection getConnection() {
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
	
	public static int sessionId(HttpSession session) {
		String sessionId = (String) session.getAttribute("id");
		int sesId = Integer.parseInt(sessionId);
		
		return sesId;
	}
	
	public static int addNotes(int id, int userid, String title, String content) {
		
		String t = title;
		String c = content;
		int i = 0;
		
		try {
			id = 1;
			Connection con = getConnection();
			
			PreparedStatement userIDAIStatement = con.prepareStatement("select max(id) from notetable");
			ResultSet rs = userIDAIStatement.executeQuery();
			
			if (rs.next()) {
					id = rs.getInt(1);
					id++;
				PreparedStatement ps = con.prepareStatement(INSERT_NOTES_SQL);
				
				int u = userid;
				ps.setLong(1, id);
				ps.setLong(2, u);
				ps.setString(3, t);
				ps.setString(4, c);
				
				i = ps.executeUpdate();
			}
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return i;
	}
	
	public static List<Notes> getNotesByUserId(int sesid) {
		List <Notes> notes = new ArrayList <>();
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
		return notes;
	}
	
	public static Notes getNotesById(int id) {
		Notes existingNote = new Notes(id, 0, "", "");
		
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
		return existingNote;
	}
	
	public static int editNotes(int id, int userid, String title, String content, int oriId) {
		
		oriId = id;
		int i = 0;
		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(UPDATE_NOTES_SQL);) {
				statement.setInt(1, id);
				statement.setInt(2, userid);
				statement.setString(3, title);
				statement.setString(4, content);
				statement.setInt(5, oriId);
				i = statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return i;
	}
	
	public static int deleteNotes(int id) {
		int i = 0;
		try (Connection connection = getConnection(); 
			PreparedStatement statement = connection.prepareStatement(DELETE_NOTES_SQL);) {
			statement.setInt(1, id);
			i = statement.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		return i;
	}

}
