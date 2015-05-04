package edu.iit.cs445.vin;

import java.time.YearMonth;

import edu.iit.cs445.vin.ShipmentStatus;

public class ShipmentResponse implements java.io.Serializable {

	private static final long serialVersionUID = -4769307109518881548L;
	private int ID;
	private YearMonth selection_month;
	private ShipmentStatus status;
	private YearMonth ym;

	
	public ShipmentResponse(int ID,YearMonth selection_month,ShipmentStatus status) {
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


	public YearMonth getSelection_month() {
		return selection_month;
	}


	public void setSelection_month(YearMonth selection_month) {
		this.selection_month = selection_month;
	}


	public ShipmentStatus getStatus() {
		return status;
	}


	public void setStatus(ShipmentStatus status) {
		this.status = status;
	}


	public YearMonth getYm() {
		return ym;
	}


	public void setYm(YearMonth ym) {
		this.ym = ym;
	}
}
