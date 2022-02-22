package com.sddevops.maven.productactive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class User {
	private int id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the email
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param email the email to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 */
	public User(int id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// FOR SEPARATING THE FUNCTIONS

	// Connection
	public static String jdbcURL = "jdbc:mysql://localhost:3306/productactive";
	public static String jdbcUsername = "root";
	public static String jdbcPassword = "password";

	protected static Connection getConnection() {
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

	// To check if username already exists.
	public static boolean checkUsername(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root",
					"password");
			PreparedStatement ps = con.prepareStatement("select username from usertable where username=?");
			ps.setString(1, username);
			ResultSet usernameExist = ps.executeQuery();
			if (usernameExist.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return false;
	}

	// Register user
	public static int registerUser(String username, String password, String firstName, String lastName) {
		int i = 0;
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root",
					"password");
			PreparedStatement userIDAIStatement = con.prepareStatement("select max(id) from usertable");
			ResultSet rs = userIDAIStatement.executeQuery();

			if (rs.next()) {
				id = rs.getInt(1);
				id++;

				PreparedStatement ps = con.prepareStatement("insert into usertable values(?,?,?,?,?)");
				ps.setLong(1, id);
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, firstName);
				ps.setString(5, lastName);
				i = ps.executeUpdate();
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return i;
	}

	// Login User
	public static ResultSet loginUser(String usernameLogin, String passwordLogin) {
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
				return rs;
			}
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
	}

	// Get user info and store it in list
	public static List<User> getUserInformation(int userid) {
		// Define list
		List<User> userDetails = new ArrayList<>();
		// Establish connection
		try (Connection connection = getConnection();
				// Prepare statement
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select id, username, password, firstName, lastName from usertable where id = ?");) {
			preparedStatement.setInt(1, userid);
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				userDetails.add(new User(id, username, password, firstName, lastName));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return userDetails;
	}

	// Get info to pass to edit page
	public static User getUserInformationEditPage(int userid) {
		User existingUser = new User(userid, "", "", "", "");
		// Establish a Connection
		try (Connection connection = getConnection();
				// Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select id, username, password, firstName, lastName from usertable where id = ?");) {
			preparedStatement.setInt(1, userid);
			// Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Process the ResultSet object
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				existingUser = new User(id, username, password, firstName, lastName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existingUser;
	}

	// Update user
	public static int updateUser(String username, String password, String firstName, String lastName, int id) {
		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"update usertable set username= ?, password =?, firstName =?, lastName =? where id = ?;");) {
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, firstName);
			statement.setString(4, lastName);
			statement.setInt(5, id);
			i = statement.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	// Delete user
	public static int deleteUser(int userid) {
		int i = 0;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement("delete from usertable where id = ?;");) {
			statement.setInt(1, userid);
			i = statement.executeUpdate();
			return i;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
