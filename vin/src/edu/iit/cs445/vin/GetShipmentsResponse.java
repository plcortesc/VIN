package edu.iit.cs445.vin;

import java.util.ArrayList;

public class GetShipmentsResponse implements java.io.Serializable {

	private static final long serialVersionUID = 4537171794631476737L;
	private ArrayList<ShipmentResponse> sr;
	public GetShipmentsResponse(ArrayList<ShipmentResponse> sr) {
		this.setSr(sr);
	}
	public ArrayList<ShipmentResponse> getSr() {
		return sr;
	}
	public void setSr(ArrayList<ShipmentResponse> sr) {
		this.sr = sr;
	}

}
