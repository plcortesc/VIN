package edu.iit.cs445.Response;

import java.util.ArrayList;

public class GetShipmentsResponse implements java.io.Serializable {

	private static final long serialVersionUID = 4537171794631476737L;
	private ArrayList<ShipmentResponse> sr;
	public GetShipmentsResponse(ArrayList<ShipmentResponse> sr) {
		this.setShipments(sr);
	}
	public ArrayList<ShipmentResponse> getShipments() {
		return sr;
	}
	public void setShipments(ArrayList<ShipmentResponse> sr) {
		this.sr = sr;
	}

}
