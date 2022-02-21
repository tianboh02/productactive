package com.sddevops.maven.productactive;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotesCollectionTest {
	
	private NotesCollection nc;
	private Notes n1;
	private Notes n2;
	private Notes n3;
	private Notes n4;
	private Notes newNote;
	private final int NOTES_COLLECTION_SIZE = 4;

	@BeforeEach
	void setUp() throws Exception {
		nc = new NotesCollection();
		n1 = new Notes(1, 4,"First Title", "First Content");
		n2 = new Notes(2, 3,"Title 2", "Second Paragraph");
		n3 = new Notes(3, 2,"Number three title", "Third Text");
		n4 = new Notes(4, 1,"Fourth Title", "Fourth Story");
		newNote = new Notes(5, 5, "Fifth", "Fifth Content");
		
		nc.addNotes(n1);
		nc.addNotes(n2);
		nc.addNotes(n3);
		nc.addNotes(n4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetNotes() {
		List<Notes> testsc = nc.getNotes();
		
		assertTrue(!testsc.isEmpty());
		assertEquals(testsc.size(),NOTES_COLLECTION_SIZE);
	}

	@Test
	void testAddNotes() {
		List<Notes> testsc = nc.getNotes();
		
		assertEquals(testsc.size(), NOTES_COLLECTION_SIZE);
		nc.addNotes(newNote);
		assertEquals(nc.getNotes().size(), NOTES_COLLECTION_SIZE+1);
	}

	@Test
	void testFindById() {
		nc.findById(2);
	}

	@Test
	void testUpdateNotes() {
		nc.updateNotes(1, 4, "First Title Edited", "First Content Edited");
	}

	@Test
	void testDeleteNotes() {
		nc.deleteNotes(1);
	}

}
