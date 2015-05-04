package edu.iit.cs445.vin;

import java.util.ArrayList;

public class ShipNotesResponse implements java.io.Serializable {
	
	private static final long serialVersionUID = 526958137782900139L;
	private ArrayList<NoteResponse> snr;
	public ShipNotesResponse(ArrayList<NoteResponse> snr) {
		this.setSnr(snr);
	}
	public ArrayList<NoteResponse> getSnr() {
		return snr;
	}
	public void setSnr(ArrayList<NoteResponse> snr) {
		this.snr = snr;
	}

}
