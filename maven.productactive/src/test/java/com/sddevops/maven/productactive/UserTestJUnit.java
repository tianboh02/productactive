/**
 * 
 */
package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

/**
 * @author jiray
 *
 */
class UserTestJUnit {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#checkUsername(java.lang.String)}.
	 */
	@Test
	void testCheckUsername() {
		// Act
		boolean userExists = User.checkUsername("notExistingUser");
		// Assert
		assertFalse(userExists == true);
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#registerUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testRegisterUser() {
		// Act
		int registeredUser = User.registerUser("rushia69", "password", "Uruha", "Rushia");
		// Assert
		assertTrue(registeredUser > 0);
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#loginUser(java.lang.String, java.lang.String)}.
	 * @throws SQLException 
	 */
	@Test
	void testLoginUser() throws SQLException {
		// Act
		ResultSet loginStatus = User.loginUser("a", "b");
		// Assert
		assertTrue(!loginStatus.equals(null));
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#getUserInformation(int)}.
	 */
	@Test
	void testGetUserInformation() {
		// Act
		List<User> userInformation = User.getUserInformation(1);
		// Assert
		assertTrue(!userInformation.isEmpty());
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#getUserInformationEditPage(int)}.
	 */
	@Test
	@Order(4)
	void testGetUserInformationEditPage() {
		// Act
		User userInformation = User.getUserInformationEditPage(1);
		// Assert
		assertTrue(!userInformation.equals(null));
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#updateUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	void testUpdateUser() {
		// Act
		int updatedUser = User.updateUser("q", "w", "Mafumafu", "Rushia", 5);
		// Assert
		assertTrue(updatedUser > 0);
	}

	/**
	 * Test method for {@link com.sddevops.maven.productactive.User#deleteUser(int)}.
	 */
	@Test
	void testDeleteUser() {
		// Act
		int deleteUser = User.deleteUser(6);
		// Assert
		assertTrue(deleteUser > 0);
	}

}
