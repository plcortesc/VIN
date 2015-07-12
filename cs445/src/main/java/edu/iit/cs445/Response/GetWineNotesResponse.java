package edu.iit.cs445.Response;

import java.util.ArrayList;

public class GetWineNotesResponse implements java.io.Serializable {

	private static final long serialVersionUID = 492858515591357421L;
	private ArrayList<NoteResponseSearch> notes;
	public GetWineNotesResponse(ArrayList<NoteResponseSearch> nrs) {
		this.setNotes(nrs);
	}
	public ArrayList<NoteResponseSearch> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<NoteResponseSearch> nrs) {
		this.notes = nrs;
	}

}
