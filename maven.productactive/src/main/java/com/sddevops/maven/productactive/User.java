package com.sddevops.maven.productactive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;
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
		}
		catch (Exception exception) {
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
		}
		catch (Exception exception) {
			System.out.println(exception);
		}
		return null;
	}
}
