package edu.iit.cs445.vin;

import java.util.ArrayList;
import java.util.Calendar;

public class Shipment implements java.io.Serializable {
	
	private static final long serialVersionUID = 3869858428222298577L;
	private MonthlySelectionType mst;	
	private ArrayList<Note> notes;
	private Calendar date;
	private int SID;
	private MonthlySelection ms;
	private String ym;
	private int size;
	private ShipmentStatus status;
	private int deliveryfee;
	private int msDeliveryfee;
	
	public Shipment(){
		this.SID = IdGenerator.newID();
    	this.date = Calendar.getInstance();
    	this.status = ShipmentStatus.PENDING;
    	this.ms = new MonthlySelection();
    	this.ym = ms.getYm();
    	this.setDeliveryfee(8);	
    	this.notes = new ArrayList<Note>();
    }
	
	public Shipment(MonthlySelectionType mst, MonthlySelection ms){
		this.size = 1;
    	this.SID = IdGenerator.newID();
    	this.date = Calendar.getInstance();
    	this.mst = mst;
    	this.status = ShipmentStatus.PENDING;
    	this.ms = ms;
    	this.ym = ms.getYm();
    	this.setDeliveryfee(8);
    	this.notes = new ArrayList<Note>();
    	switch(mst){
	    	case AR:setMsDeliveryfee(50);break;
	    	case AW:setMsDeliveryfee(60);break;
	    	case RW:setMsDeliveryfee(70);break;
    	}
	}
	
	public void setDate(Calendar date){
		this.date = date.getInstance(); 
	}
	
	public Calendar getDate(){
		return this.date;
	}
	
	public void addNote(Note n){
//		if(n.getContent().length()<128){
//			System.out.println("Minimum note length: 128 characters");
//		} else 
		if(n.getContent().length()>1024){
			System.out.println("Maximum note length: 1024 characters");
		}else{
			System.out.println("Congratulations! Note added");
			this.notes.add(n);
		}
	}	
	
	public void updateNote(int NID, Note n){
		for(int i=0; i<notes.size(); i++){
			if(notes.get(i).getNID()==NID){
				notes.set(i, n);
				notes.get(i).setNID(NID);
			}
		}
	}
	
	public void removeNote(int NID){
		for(int i=0; i<notes.size(); i++){
			if(notes.get(i).getNID()==NID){
				notes.remove(i);
			}
		}
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public void setMst(MonthlySelectionType mst){
    	this.mst=mst;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String getYm(){
		return this.ym;
	}
	
	public ShipmentStatus getStatus(){
		return this.status;
	}
	
	public void setStatus(ShipmentStatus status){
		this.status = status;
	}
	
	public MonthlySelectionType getMst(){
		return this.mst;
	}
	
	public ArrayList<Wine> getMsWines(){
		return ms.getMs();
	}
	
	public int getSID(){
		return this.SID;
	}
	
	public boolean isMatch(String kw) {
        if (isMatchStatus(kw) || isMatchYear(kw) || isMatchMonth(kw) || isMatchDay(kw)) {
                return true;
        } else return false;
	}
	    
    private boolean isMatchStatus(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.status.name().matches(regex);   
    }
    
    private boolean isMatchYear(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.YEAR)).matches(regex);
    }
    private boolean isMatchMonth(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.MONTH)).matches(regex);
    }
    private boolean isMatchDay(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.DAY_OF_MONTH)).matches(regex);
    }

	public int getDeliveryfee() {
		return deliveryfee;
	}

	public void setDeliveryfee(int deliveryfee) {
		this.deliveryfee = deliveryfee;
	}

	public int getMsDeliveryfee() {
		return msDeliveryfee;
	}

	public void setMsDeliveryfee(int msDeliveryfee) {
		this.msDeliveryfee = msDeliveryfee;
	}
}
