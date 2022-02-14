package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ActivityLoggerJUnitTest {
	
	private ActivityLoggerServlet al;
	private ActivityLogger l1, l2, l3, l4, l5;
	private final int ACTIVITY_LOGGER_SIZE = 5;

	/**
	* @throws java.lang.Exception
	*/
	@BeforeEach
	void setUp() throws Exception {
		al = new ActivityLoggerServlet();
		l1 = new ActivityLogger(1, 2, "English", "paper 1", "18/7/2020 3:20 pm", "18/7/2020 3:30 pm");
		l2 = new ActivityLogger(2, 10, "Math", "paper 1", "15/7/2020 11:20 pm", "16/7/2020 12:30 am");
		l3 = new ActivityLogger(3, 2, "Science", "paper 1", "18/7/2020 7:20 pm", "18/7/2020 8:30 pm");
		l4 = new ActivityLogger(4, 3, "History", "paper 1", "18/7/2020 1:20 pm", "18/7/2020 4:10 pm");
		l5 = new ActivityLogger(5, 2, "English", "paper 2", "19/7/2020 3:20 pm", "19/7/2020 3:50 pm");
	}


	@Test
	void testListUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testShowEditForm() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLog() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteLog() {
		fail("Not yet implemented");
	}

}
