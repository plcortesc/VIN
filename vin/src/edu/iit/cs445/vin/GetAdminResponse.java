package edu.iit.cs445.vin;

import java.util.ArrayList;

public class GetAdminResponse implements java.io.Serializable {

	private static final long serialVersionUID = 2934227982203296788L;
	private ArrayList<AdminResponse> ar;
	public GetAdminResponse(ArrayList<AdminResponse> ar) {
		this.setAr(ar);
	}
	public ArrayList<AdminResponse> getAr() {
		return ar;
	}
	public void setAr(ArrayList<AdminResponse> ar) {
		this.ar = ar;
	}

}
