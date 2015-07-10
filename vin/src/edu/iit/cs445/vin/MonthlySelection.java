package edu.iit.cs445.vin;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class MonthlySelection implements java.io.Serializable {

	private static final long serialVersionUID = 7768302311547457180L;
	protected MonthlySelectionType mst;
	private String ym;
	private String date;
	protected int AID;
	private ArrayList<Wine> ms = new ArrayList<Wine>();
	private MonthlySelectionStatus status;
    private int ID;
    
	// Regular selection
	public MonthlySelection() {
		this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
		this.setYm("2014-09");
	}

	// Special selection for 1 month or more
	public MonthlySelection(int AID, String ym, MonthlySelectionType mst ) { // Must be in the yyyy-mm format
		this.setYm(ym);
		this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.AID = AID;
    	this.mst = mst;
	}
	
	public boolean isMatch(String kw) {
		Iterator<Wine> it = this.ms.iterator();
		while (it.hasNext()) {
			Wine w = it.next();
			if (w.isMatch(kw))
				return true;
		}
		return false;
	}

	public ArrayList<Wine> getMs() {
		return this.ms;
	}

	public void setMs(ArrayList<Wine> ms) {
		this.ms = ms;
	}


	
	public void setMst(MonthlySelectionType mst){
		this.mst = mst;
	}
	
	public MonthlySelectionType getMst(){
		return this.mst;
	}

	public MonthlySelectionStatus getStatus() {
		return this.status;
	}

	public void setStatus(MonthlySelectionStatus s) {
		this.status = s;
	}
	
	public void printMs(){
		for(Wine w: ms){
			w.printWine();
		}
	}

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = Integer.toString(date.get(Calendar.YEAR))+"-"+Integer.toString(date.get(Calendar.MONTH))+"-"+
				Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}

	public int getAID() {
		return AID;
	}

	public void setAID(int aID) {
		AID = aID;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
}
