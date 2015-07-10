package edu.iit.cs445.vin;

import java.util.ArrayList;

public class GetAdminResponse implements java.io.Serializable {

	private static final long serialVersionUID = 2934227982203296788L;
	private ArrayList<AdminResponse> admins;
	public GetAdminResponse(ArrayList<AdminResponse> admins) {
		this.setAdmins(admins);
	}
	public ArrayList<AdminResponse> getAdmins() {
		return this.admins;
	}
	public void setAdmins(ArrayList<AdminResponse> admins) {
		this.admins = admins;
	}

}
