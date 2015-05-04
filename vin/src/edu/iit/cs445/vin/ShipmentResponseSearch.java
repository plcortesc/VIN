package edu.iit.cs445.vin;

import java.time.YearMonth;

public class ShipmentResponseSearch implements java.io.Serializable {

	private static final long serialVersionUID = -1993916453449932907L;
	private int ID;
	private YearMonth ym;
	public ShipmentResponseSearch(int ID, YearMonth ym) {
		this.setID(ID);
		this.setYm(ym);
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public YearMonth getYm() {
		return ym;
	}
	public void setYm(YearMonth ym) {
		this.ym = ym;
	}

}
