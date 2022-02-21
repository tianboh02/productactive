package com.sddevops.maven.productactive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Comparator;
import java.util.Objects;

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

	public static int registerUser(String username, String password, String firstName, String lastName) {
		int i = 0;
		try {
			int id = 1;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productactive", "root",
					"password");
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
				ps.setString(2, username);
				ps.setString(3, password);
				ps.setString(4, firstName);
				ps.setString(5, lastName);
				// Step 6: perform the query on the database using the prepared statement
				i = ps.executeUpdate();
			}
		}
		// Step 8: catch and print out any exception
		catch (Exception exception) {
			System.out.println(exception);
		}
		return i;
	}
}
