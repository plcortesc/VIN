package edu.iit.cs445.Response;

import java.util.ArrayList;

import edu.iit.cs445.Management.Errors;

public class ModifySubsResponse implements java.io.Serializable {

	private static final long serialVersionUID = -6578652680339583408L;
	private Integer UID;
	private ArrayList<Errors> errors;
	public ModifySubsResponse(Integer UID,ArrayList<Errors> errors ) {
		this.setUID(UID);
		if(errors == null){
			this.setErrors(new ArrayList<Errors>());
		}
		else{this.setErrors(errors);}
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
