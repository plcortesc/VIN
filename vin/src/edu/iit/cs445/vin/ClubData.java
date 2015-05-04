package edu.iit.cs445.vin;

import java.util.ArrayList;

public class ClubData {
	
	public String clubName;
	public ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
	public ArrayList<Admin> admin = new ArrayList<Admin>();
	public ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
	public ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	
	public ClubData() {}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	
	public ArrayList<Subscriber> getSubs(){
		return this.subs;
	}
	
	public void setSubs(ArrayList<Subscriber> subs){
		this.subs = subs;
	}
	
	public ArrayList<Admin> getAdmin(){
		return admin;
	}
	
	public void setAdmin(ArrayList<Admin> admin){
		this.admin = admin;
	}
	
	public ArrayList<MonthlySelection> getMst(){
		return monthlySelection;
	}
	
	public void setMst(ArrayList<MonthlySelection> mst){
		this.monthlySelection = mst;
	}
	
	public ArrayList<Receipt> getReceipt(){
		return this.receipts;
	}
	
	public void setReceipt(ArrayList<Receipt> receipts){
		this.receipts = receipts;
	}

}
