package edu.iit.cs445.vin;

import java.util.ArrayList;

public class AddAdmin implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030448822833106110L;
	private String name;

	public AddAdmin(String name) {
		this.name = name;
	}

	public PostNoteResponse addAccount(ArrayList<Admin> admin){
		Admin ad = new Admin(name);
		admin.add(ad);
		return new PostNoteResponse(ad.getAID());
	}
}
