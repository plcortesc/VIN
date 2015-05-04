package edu.iit.cs445.vin;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public abstract class MonthlySelection implements java.io.Serializable {

	private static final long serialVersionUID = 7768302311547457180L;
	protected MonthlySelectionType mst;
	private YearMonth ym;
	private Calendar date;
	protected int AID;
	private ArrayList<Wine> ms = new ArrayList<Wine>();
	private MonthlySelectionStatus status;
    private int ID;

	abstract void addWine(Wine w);

	public boolean isMatch(String kw) {
		Iterator<Wine> it = this.ms.iterator();
		while (it.hasNext()) {
			Wine w = it.next();
			if (w.isMatch(kw))
				return true;
		}
		return false;
	}

	// Regular selection
	public MonthlySelection() {
		this.ym = YearMonth.now().plusMonths(1); // next month's selection
		this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.AID = AID;
	}

	// Special selection for 1 month or more
	public MonthlySelection(String ym) { // Must be in the yyyy-mm format
		this.ym = YearMonth.parse(ym);
		this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.AID = AID;
	}

	public ArrayList<Wine> getMs() {
		return this.ms;
	}

	public void setMs(ArrayList<Wine> ms) {
		this.ms = ms;
	}

	public YearMonth getYm() {
		return this.ym;
	}

	public void setYm(YearMonth ym) {
		this.ym = ym;
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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public int getAID() {
		return AID;
	}

	public void setAID(int aID) {
		AID = aID;
	}
}
