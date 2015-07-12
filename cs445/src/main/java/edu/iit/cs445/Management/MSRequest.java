package edu.iit.cs445.Management;

import java.util.ArrayList;

import edu.iit.cs445.vin.Wine;

public class MSRequest implements java.io.Serializable {

	private static final long serialVersionUID = -3065006902733637499L;
	private MonthlySelectionType type;
	private String selection_month;
	private ArrayList<Wine> wines;
	public MSRequest(MonthlySelectionType type, String selection_month, ArrayList<Wine> wines) {
		this.type=type;
		this.selection_month=selection_month;
		this.wines=wines;
	}
	public MonthlySelectionType getMst(){
		return type;
	}
	public String getYm(){
		return selection_month;
	}
	public ArrayList<Wine> getWines(){
		return wines;
	}
}
