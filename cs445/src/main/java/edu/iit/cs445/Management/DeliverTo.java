package edu.iit.cs445.Management;

import edu.iit.cs445.vin.Address;

public class DeliverTo implements java.io.Serializable  {

	private static final long serialVersionUID = 2673501745369299508L;
	private String name;
	private String phone;
	private String email;
	private Address address;
	private MonthlySelectionType type;
	
	public DeliverTo(String name, String phone, String email, Address address, MonthlySelectionType mst) {
		this.setName(name);
		this.setPhone(phone);
		this.setEmail(email);
		this.setAddress(address);
		this.setType(mst);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public MonthlySelectionType getType() {
		return type;
	}

	public void setType(MonthlySelectionType type) {
		this.type = type;
	}

}
