package edu.iit.cs445.vin;

public class ReceiptDetailResponse implements java.io.Serializable {

	private static final long serialVersionUID = -8888899128371332943L;
	private int ID;
	private String time;
	private String date;
	private int subscriber;
	private String name;
	private String receiver;
	
	public ReceiptDetailResponse(int ID,String time, String date,int subscriber,String name,String receiver) {
		this.setID(ID);
		this.setTime(time);
		this.setDate(date);
		this.setSubscriber(subscriber);
		this.setName(name);
		this.setReceiver(receiver);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

}
