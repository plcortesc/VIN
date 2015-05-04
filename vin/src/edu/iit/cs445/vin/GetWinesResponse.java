package edu.iit.cs445.vin;

import java.util.ArrayList;

public class GetWinesResponse implements java.io.Serializable {

	private static final long serialVersionUID = -4214629200543633346L;
	private ArrayList<WineResponse> wrs;
	public GetWinesResponse(ArrayList<WineResponse> wrs) {
		this.setWrs(wrs);
	}
	public ArrayList<WineResponse> getWrs() {
		return wrs;
	}
	public void setWrs(ArrayList<WineResponse> wrs) {
		this.wrs = wrs;
	}

}
