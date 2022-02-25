package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class DeadlineJUnitTest {
	
	static int testId;
	

	@Test
	@Order(1) 
	void testGetDeadlineByUserFK() {

		List<Deadline> deadline = Deadline.getDeadlineByUserFK(3);
				
		assertTrue(!deadline.isEmpty());
		
		for(int i = 0; i < deadline.size(); i++) {
			assertEquals(3, deadline.get(i).getUserId());
        }
	}

	@Test
	@Order(2) 
	void testGetDeadlineById() {
		
		Deadline deadline = Deadline.getDeadlineById(19);
		
		assertTrue(deadline != null);
		assertEquals(19, deadline.getId());
	}

	@Test
	@Order(3) 
	void testUpdateDeadline() {
		
		List<Deadline> deadline1 = Deadline.getDeadlineByUserFK(3);
		
		int test = Deadline.updateDeadline(76, 3, "DevOps Submission", "2022-01-21T10:50");
		
		List<Deadline> deadline2 = Deadline.getDeadlineByUserFK(3);
		
		assertTrue(test > 0);
		assertEquals(deadline1.size(), deadline2.size());
	}

	@Test
	@Order(4) 
	void testCreateDeadline() {
		
		List<Deadline> deadline1 = Deadline.getDeadlineByUserFK(3);
		
		int test = Deadline.createDeadline(3, "Devops", "2022-02-11T22:00");
		
		List<Deadline> deadline2 = Deadline.getDeadlineByUserFK(3);
		
		assertTrue(test > 0);
		assertEquals((deadline1.size()+ 1), deadline2.size());
		
		
		//get the id of the new created deadline for to test the delete function later
		int Id = deadline2.size()-1 ;
		testId = deadline2.get(Id).getId();
	}

	@Test
	@Order(5) 
	void testDeleteDeadline() {
		List<Deadline> deadline1 = Deadline.getDeadlineByUserFK(3);
		
		int test = Deadline.deleteDeadline(testId);
		
		List<Deadline> deadline2 = Deadline.getDeadlineByUserFK(3);
		
		assertTrue(test > 0);
		assertEquals((deadline1.size() - 1), deadline2.size());
	}

}
