package edu.iit.cs445.vin;

import java.util.ArrayList;
import java.util.Calendar;

public class Admin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9145280382620746049L;
	private int ID;
	private String name;
	private Calendar date;


	public Admin(String name){
		this.date = Calendar.getInstance();
		this.ID = IdGenerator.newID();
		this.name = name;
	}
	
	public int getID(){
		return this.ID;
	}
	public void setID(int ID){
		this.ID = ID;
	}
	public Calendar getDate(){
		return this.date;
	}
	
	public void setDate(Calendar date){
		this.date = date;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void createMs(String mst,String ym, ArrayList<Wine> wines, ArrayList<MonthlySelection> monthlySelection){
		MonthlySelection ms;
		switch(mst){
	    	case "AR": ms = new AR(this.ID, ym); break;
	    	case "AW": ms = new AW(this.ID, ym); break;
	    	case "RW": ms = new RW(this.ID, ym); break;
	    	default: ms = new RW(this.ID, ym); break;
		}
		for(Wine w:wines){ms.addWine(w);}
		monthlySelection.add(ms);
	}
}
