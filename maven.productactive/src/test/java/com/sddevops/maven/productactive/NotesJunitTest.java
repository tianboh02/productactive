package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class NotesJunitTest {

	@Test
	void testAddNotes() {
		List<Notes> testsc = Notes.getNotesByUserId(10);
		
		int NOTES_COLLECTION_SIZE = testsc.size();
		int addNote = Notes.addNotes(10, 10, "Second", "Second");
		assertTrue(addNote > 0);
		assertEquals(Notes.getNotesByUserId(10).size(), NOTES_COLLECTION_SIZE + 1);
	}

	@Test
	void testGetNotesByUserId() {
		List<Notes> testsc = Notes.getNotesByUserId(10);
		
		int NOTES_COLLECTION_SIZE = testsc.size();
		assertTrue(!testsc.isEmpty());
		assertEquals(testsc.size(),NOTES_COLLECTION_SIZE);
	}

	@Test
	void testGetNotesById() {
		Notes testsc = Notes.getNotesById(10);
		
		assertTrue(testsc != null);
		assertEquals(10, testsc.getId());
	}

	@Test
	void testEditNotes() {
		List<Notes> testsc = Notes.getNotesByUserId(2);
		
		int NOTES_COLLECTION_SIZE = testsc.size();
		
		int editNote = Notes.editNotes(3, 2, "Test Title Edited", "Test Content Edited", 3);
		assertTrue(editNote > 0);
		assertEquals(NOTES_COLLECTION_SIZE, testsc.size());
	}

	@Test
	void testDeleteNotes() {
		List<Notes> testsc = Notes.getNotesByUserId(10);
		
		int NOTES_COLLECTION_SIZE = testsc.size();
		
		int deleteNote = Notes.deleteNotes(6);
		
		testsc = Notes.getNotesByUserId(10);
		
		assertTrue(deleteNote > 0);
		assertEquals((NOTES_COLLECTION_SIZE - 1), testsc.size());
	}

}
