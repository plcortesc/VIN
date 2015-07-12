package edu.iit.cs445.Response;

import java.util.ArrayList;

public class SearchResponse implements java.io.Serializable {

	private static final long serialVersionUID = 6580117929067093703L;
	private ArrayList<WineResponse> wines;
	private ArrayList<ShipmentResponseSearch> shipments;
	private ArrayList<NoteResponseSearch> notes;

	public SearchResponse(ArrayList<WineResponse> wines,ArrayList<ShipmentResponseSearch> shipments,ArrayList<NoteResponseSearch> notes) {
		this.setWines(wines);
		this.setShipments(shipments);
		this.setNotes(notes);
	}

	public ArrayList<WineResponse> getWines() {
		return wines;
	}

	public void setWines(ArrayList<WineResponse> wines) {
		this.wines = wines;
	}

	public ArrayList<ShipmentResponseSearch> getShipments() {
		return shipments;
	}

	public void setShipments(ArrayList<ShipmentResponseSearch> shipments) {
		this.shipments = shipments;
	}

	public ArrayList<NoteResponseSearch> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<NoteResponseSearch> notes) {
		this.notes = notes;
	}

}
