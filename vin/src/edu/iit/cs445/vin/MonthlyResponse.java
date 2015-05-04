package edu.iit.cs445.vin;

import java.time.YearMonth;

import edu.iit.cs445.vin.MonthlySelectionType;

public class MonthlyResponse implements java.io.Serializable {

	private static final long serialVersionUID = -2637171385387587792L;
	private int ID;
	private YearMonth ym;
	private MonthlySelectionType mst;
	
	public MonthlyResponse(int ID, YearMonth ym, MonthlySelectionType mst){
		this.setID(ID);
		this.setYm(ym);
		this.setMst(mst);
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

	public MonthlySelectionType getMst() {
		return mst;
	}

	public void setMst(MonthlySelectionType mst) {
		this.mst = mst;
	}
}
