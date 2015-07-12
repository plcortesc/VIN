package edu.iit.cs445.Response;

public class ReceiptResponse implements java.io.Serializable {

	private static final long serialVersionUID = 4306223717409619033L;
	private int ID;
	private String date;
	private int subscriber;
	private String name;
	
	public ReceiptResponse(int ID,String date,int subscriber,String name) {
		this.setID(ID);
		this.setDate(date);
		this.setSubscriber(subscriber);
		this.setName(name);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(int subscriber) {
		this.subscriber = subscriber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
