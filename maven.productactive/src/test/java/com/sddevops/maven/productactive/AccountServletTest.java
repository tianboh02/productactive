package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountServletTest {
	
	// Arrange
	private AccountServlet as;

	@BeforeEach
	void setUp() throws Exception {
		// Arrange
		as = new AccountServlet();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testUpdateFunction() throws SQLException, IOException {
		// Act
		User originalUser = new User(5, "t", "y", "u", "i");
		User user = as.updateFunction(5, "t", "y", "u", "ChangedLastName");
		
		// Arrange
		assertNotEquals(originalUser, user);
	}

	@Test
	void testDeleteFunction() throws SQLException, IOException {
		// Act
		User deletedUser = as.deleteFunction(6);
		
		// Arrange
		assertNull(deletedUser);
	}

}
