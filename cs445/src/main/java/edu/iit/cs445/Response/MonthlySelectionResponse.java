package edu.iit.cs445.Response;

import java.util.ArrayList;

public class MonthlySelectionResponse implements java.io.Serializable {

	private static final long serialVersionUID = 6623424831616487766L;
	private ArrayList<MonthlyResponse> monthly_selection;

	public MonthlySelectionResponse(ArrayList<MonthlyResponse> mst){
		this.setMonthly_selection(mst); 
	}

	public ArrayList<MonthlyResponse> getMonthly_selection() {
		return monthly_selection;
	}

	public void setMonthly_selection(ArrayList<MonthlyResponse> monthly_selection) {
		this.monthly_selection = monthly_selection;
	}

}
