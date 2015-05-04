package edu.iit.cs445.vin;

import java.util.ArrayList;

public class GetWineNotesResponse implements java.io.Serializable {

	private static final long serialVersionUID = 492858515591357421L;
	private ArrayList<NoteResponseSearch> nrs;
	public GetWineNotesResponse(ArrayList<NoteResponseSearch> nrs) {
		this.setNrs(nrs);
	}
	public ArrayList<NoteResponseSearch> getNrs() {
		return nrs;
	}
	public void setNrs(ArrayList<NoteResponseSearch> nrs) {
		this.nrs = nrs;
	}

}
