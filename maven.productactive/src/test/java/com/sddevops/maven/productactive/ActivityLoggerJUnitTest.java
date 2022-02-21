//package com.sddevops.maven.productactive;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import com.sddevops.maven.productactive.ActivityLogger;
//
//class ActivityLoggerJUnitTest {
//	
// //	private int[] userid = {10,10,3,6,10};
////	private String[] activityName = {"English", "English", "Math", "Science", "Devops"};
////	private String[] activityDescription = {"Paper 1", "Paper 2", "TYS topic 5", "Lab experiement theory", "Project 2"};
////	private String[] activityStart = {"2022-01-11T13:30", "2022-01-11T18:00", "2022-01-13T14:20", "2022-01-20T09:30", "2022-02-18T17:38"};
////	private String[] activityEnd = {"2022-01-11T15:00", "2022-01-11T19:30", "2022-01-13T15:40", "2022-01-20T10:00", "2022-02-18T23:57"};
//	
//	static int testIdForDelete;
//
//
//
//	
//	@BeforeAll
//	public static void init(){
//		ActivityLogger.addActivityLog(10, "English", "Paper 1", "2022-01-11T13:30", "2022-01-11T15:00");
//		
//		List<ActivityLogger> testRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//		
//		int lastIndexInList = testRetrieveByUID.size()-1 ;
//		testIdForDelete = testRetrieveByUID.get(lastIndexInList).getId();
//
//	}
//	
//
//	@Test
//	void testGetActivityLogByUserid() {
//		//act
//		List<ActivityLogger> testRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//				
//		//assert
//		//assert that song collection is not empty after invoking getsongs function.
//		assertTrue(!testRetrieveByUID.isEmpty());
//		
//		//check if all userid in the list is equals to the expected one
//		for(int i = 0; i < testRetrieveByUID.size(); i++) {
//			assertEquals(10, testRetrieveByUID.get(i).getUserId());
//        }
//	}
//
//	@Test
//	void testGetActivityLogByid() {
//		ActivityLogger testRetrieveByID = ActivityLogger.getActivityLogByid(1);
//		
//		assertTrue(testRetrieveByID != null);
//		assertEquals(1, testRetrieveByID.getId());
//	}
//
//	@Test
//	void testAddActivityLog() {
//		List<ActivityLogger> testBeforeRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//		int beforeListSize = testBeforeRetrieveByUID.size(); 
//		
//		int testAddLog = ActivityLogger.addActivityLog(10, "Devops", "presentation slides", "2022-02-11T20:00", "2022-02-11T22:00");
//		
//		List<ActivityLogger> testAfterRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//		int afterListSize = testAfterRetrieveByUID.size();
//		
//		assertTrue(testAddLog > 0);
//		assertTrue((beforeListSize + 1) == afterListSize);
//		
//	}
//
//	@Test
//	void testEditActivityLog() {
//		List<ActivityLogger> testBeforeRetrieveByUID = ActivityLogger.getActivityLogByUserid(1);
//		int beforeListSize = testBeforeRetrieveByUID.size(); 
//		
//		int testEditLog = ActivityLogger.editActivityLog(4, 1, "English", "Practice Paper", "2022-01-21T09:20", "2022-01-21T10:50");
//		
//		List<ActivityLogger> testAfterRetrieveByUID = ActivityLogger.getActivityLogByUserid(1);
//		int afterListSize = testAfterRetrieveByUID.size();
//		
//		assertTrue(testEditLog > 0);
//		assertEquals(beforeListSize, afterListSize);
//	}
//
//	@Test
//	void testDeleteActivityLog() {
//		List<ActivityLogger> testBeforeRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//		int beforeListSize = testBeforeRetrieveByUID.size(); 
//		
//		int testDeleteLog = ActivityLogger.deleteActivityLog(testIdForDelete);
//		
//		List<ActivityLogger> testAfterRetrieveByUID = ActivityLogger.getActivityLogByUserid(10);
//		int afterListSize = testAfterRetrieveByUID.size();
//		
//		assertTrue(testDeleteLog > 0);
//		assertTrue((beforeListSize - 1) == afterListSize);
//	}
//
//}
