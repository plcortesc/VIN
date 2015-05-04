package edu.iit.cs445.vin;

public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 2354449740962166606L;
	private String street, city, state, zip;
    
    public Address() {
        this.street = "123 Main ST, Apt 2F";
        this.city = "Anytown";
        this.state = "Anystate";
        this.zip = "12345";
    }
    
    public Address(String street, String city, String state, String zip) {
    	this.street = street;
    	this.city = city;
    	this.state = state;
    	this.zip = zip;
    }
    
    public void setStreet(String street){this.street = street;}
	public String getStreet(){return this.street;}	
	public void setCity(String city){this.city = city;}
	public String getCity(){return this.city;}
	public void setState(String state){this.state = state;}
	public String getState(){return this.state;}
	public void setZip(String zip){this.zip = zip;}
	public String getZip(){return this.zip;}

}
