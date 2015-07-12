package edu.iit.cs445.vin;

import java.util.Calendar;

import edu.iit.cs445.Management.IdGenerator;

public class Receipt implements java.io.Serializable {

	private static final long serialVersionUID = -2425490622663023546L;
	private int ID;
	private Calendar date;
	private int SID;
	private String receiver;
	private Subscriber s;
	private String name;
	
	public Receipt() {
		this.name ="pep";
	}

	
	public Receipt(Subscriber s, String name) {
		this.setID(IdGenerator.newID());
		this.setDate(Calendar.getInstance());
		this.setSID(s.getID());
		this.setReceiver(name);
		this.s = s;
		this.name = s.getName();

	}

	public int getID() {return ID;}

	public void setID(int iD) {ID = iD;}

	public Calendar getDate() {return date;}	

	public void setDate(Calendar date) {this.date = date;}

	public int getSID() {return SID;}

	public void setSID(int SID) {this.SID = SID;}
	
	public String getReceiver() {return receiver;}

	public void setReceiver(String receiver) {this.receiver = receiver;}


	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
