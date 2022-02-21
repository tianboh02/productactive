package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterServletTest {
	
	// Arrange
	private RegisterServlet rs;

	@BeforeEach
	void setUp() throws Exception {
		// Arrange
		rs = new RegisterServlet();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// Test for registration of user
	@Test
	void testRegisterUser() throws ServletException, IOException {
		// Act
		rs.registerUser("JUnitUser1", "password", "Firstus", "Lastus");
		
		// Assert
		assertNotNull(rs);
		
	}

}
