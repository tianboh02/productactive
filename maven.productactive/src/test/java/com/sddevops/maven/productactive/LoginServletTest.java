package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginServletTest {
	
	// Arrange
	private LoginServlet ls;

	@BeforeEach
	void setUp() throws Exception {
		// Arrange
		ls = new LoginServlet();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLoginFunction() throws ServletException, IOException {
		// Act
		boolean isLogin = ls.loginFunction("a", "b");
		
		// Assert
		assertTrue(isLogin);
	}

}
