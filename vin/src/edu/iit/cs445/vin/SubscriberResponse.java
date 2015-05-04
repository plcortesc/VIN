package edu.iit.cs445.vin;

import edu.iit.cs445.vin.Address;

public class SubscriberResponse implements java.io.Serializable {

	private static final long serialVersionUID = -5410664538676976947L;
	private String email;
	private String name;
	private String phone;
	private Address address;
	private String facebook;
	private String twitter;
	public SubscriberResponse(String email, String name, String phone, Address address, String facebook,String twitter) {
		this.setEmail(email);
		this.setName(name);
		this.setPhone(phone);
		this.setAddress(address);
		this.setFacebook(facebook);
		this.setTwitter(twitter);
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

}
