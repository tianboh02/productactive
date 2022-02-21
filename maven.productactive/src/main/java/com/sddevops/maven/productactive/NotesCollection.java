package com.sddevops.maven.productactive;

import java.util.*;

public class NotesCollection {
	
	private ArrayList<Notes> notes = new ArrayList<>();
    private int capacity;

    public NotesCollection() {
    	/*notes.add(new Notes(1, 4,"First Title", "First Content"));
    	notes.add(new Notes(2, 3,"Title 2", "Second Paragraph"));
    	notes.add(new Notes(3, 2,"Number three title", "Third Text"));
    	notes.add(new Notes(4, 1,"Fourth Title", "Fourth Story"));*/

        this.capacity = 20;
    }

    public NotesCollection(int capacity) {
        this.capacity = capacity;
    }

    public List<Notes> getNotes() {
        return notes;
    }

    public void addNotes(Notes note) {
    	if(notes.size() != capacity) {
    		notes.add(note);
    	}
    }
    
    public Notes findById(int id) {
    	for (Notes s : notes) { 		      
            if(s.getId() == (id)) return s;
       }
    	return null;
    }
    
    public Notes updateNotes(int id, int userid, String title, String content) {
    	for (Notes s : notes) { 		      
            if(s.getId() == (id))
            s.setId(id);
            s.setUserid(userid);
            s.setTitle(title);
            s.setContent(content);
       }
    	return null;
    }

    public Notes deleteNotes(int id) {
    	for (Notes s : notes) { 		      
            if(s.getId()==(id)) return s;
            notes.remove(s);
       }
    	return null;
    }

}
