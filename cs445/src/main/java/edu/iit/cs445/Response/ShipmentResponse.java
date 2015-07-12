package edu.iit.cs445.Response;

import edu.iit.cs445.Management.ShipmentStatus;

public class ShipmentResponse implements java.io.Serializable {

	private static final long serialVersionUID = -4769307109518881548L;
	private int ID;
	private String selection_month;
	private ShipmentStatus status;
	private String ym;

	
	public ShipmentResponse(int ID,String selection_month,ShipmentStatus status) {
		this.setID(ID);
		this.setSelection_month(selection_month);
		this.setStatus(status);
	}


	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public String getSelection_month() {
		return selection_month;
	}


	public void setSelection_month(String selection_month) {
		this.selection_month = selection_month;
	}


	public ShipmentStatus getStatus() {
		return status;
	}


	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}


	public String getYm() {
		return ym;
	}


	public void setYm(String ym) {
		this.ym = ym;
	}
}
