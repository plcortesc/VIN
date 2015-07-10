package edu.iit.cs445.vin;

import java.util.ArrayList;

public class ClubData {
	
	public final String clubName = "VIN";
	public ArrayList<Subscriber> subscribers;
	public ArrayList<Admin> admins;
	public ArrayList<MonthlySelection> monthlySelection;
	public ArrayList<Receipt> receipts;
	
	public ClubData() {
		this.subscribers = new ArrayList<Subscriber>();
		this.admins = new ArrayList<Admin>();
		this.monthlySelection = new ArrayList<MonthlySelection>();
		this.receipts = new ArrayList<Receipt>();
	}

	public String getClubName() {
		return this.clubName;
	}
	
	public ArrayList<Subscriber> getSubscribers(){
		return this.subscribers;
	}
	
	public void setSubscribers(ArrayList<Subscriber> subscribers){
		this.subscribers = subscribers;
	}
	
	public ArrayList<Admin> getAdmins(){
		return this.admins;
	}
	
	public void setAdmins(ArrayList<Admin> admin){
		this.admins = admin;
	}
	
	public ArrayList<MonthlySelection> getMonthlySelection(){
		return this.monthlySelection;
	}
	
	
	public ArrayList<Receipt> getReceipts(){
		return this.receipts;
	}
	
	public void setReceipts(ArrayList<Receipt> receipts){
		this.receipts = receipts;
	}

}
