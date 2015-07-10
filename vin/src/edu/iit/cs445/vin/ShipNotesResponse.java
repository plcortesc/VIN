package edu.iit.cs445.vin;

import java.util.ArrayList;

public class ShipNotesResponse implements java.io.Serializable {
	
	private static final long serialVersionUID = 526958137782900139L;
	private ArrayList<NoteResponse> notes;
	public ShipNotesResponse(ArrayList<NoteResponse> snr) {
		this.setNotes(snr);
	}
	public ArrayList<NoteResponse> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<NoteResponse> snr) {
		this.notes = snr;
	}

}
