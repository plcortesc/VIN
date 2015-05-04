package edu.iit.cs445.vin;

import java.util.ArrayList;

public class AddSubsResponse implements java.io.Serializable {

	private static final long serialVersionUID = -6578652680339583408L;
	private Integer UID;
	private ArrayList<Errors> errors;
	public AddSubsResponse(Integer UID,ArrayList<Errors> errors ) {
		this.setUID(UID);
		this.setErrors(errors);
	}
	public Integer getUID() {
		return UID;
	}
	public void setUID(Integer uID) {
		UID = uID;
	}
	public ArrayList<Errors> getErrors() {
		return errors;
	}
	public void setErrors(ArrayList<Errors> errors) {
		this.errors = errors;
	}

}
