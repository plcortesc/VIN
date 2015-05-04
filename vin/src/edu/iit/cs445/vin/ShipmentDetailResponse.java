package edu.iit.cs445.vin;

import java.time.YearMonth;
import java.util.ArrayList;

import edu.iit.cs445.vin.MonthlySelectionType;
import edu.iit.cs445.vin.ShipmentStatus;

public class ShipmentDetailResponse implements java.io.Serializable {
	
	private static final long serialVersionUID = 3536590418821279106L;
	private ShipmentStatus status;
	private YearMonth ym;
	private String date;
	private MonthlySelectionType mst;
	private ArrayList<WineResponse> wines;
	
	public ShipmentDetailResponse(YearMonth ym,ShipmentStatus status, String date, MonthlySelectionType mst, ArrayList<WineResponse> wines ) {
		this.setYm(ym);
		this.setStatus(status);
		this.setDate(date);
		this.setMst(mst);
		this.setWines(wines);
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MonthlySelectionType getMst() {
		return mst;
	}

	public void setMst(MonthlySelectionType mst) {
		this.mst = mst;
	}

	public ArrayList<WineResponse> getWines() {
		return wines;
	}

	public void setWines(ArrayList<WineResponse> wines) {
		this.wines = wines;
	}

}
