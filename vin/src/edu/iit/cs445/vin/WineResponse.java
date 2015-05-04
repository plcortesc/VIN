package edu.iit.cs445.vin;

public class WineResponse implements java.io.Serializable {

	private static final long serialVersionUID = -5819956706625637015L;
	private int ID;
	private String label_name;

	public WineResponse(int ID,String label_name) {
		this.setID(ID);
		this.setLabel_name(label_name);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}
}
