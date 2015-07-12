package edu.iit.cs445.Management;

import java.util.ArrayList;

import edu.iit.cs445.Response.AddSubsResponse;
import edu.iit.cs445.vin.Address;
import edu.iit.cs445.vin.Subscriber;

public class AddSubscriber implements java.io.Serializable{

	private static final long serialVersionUID = -6753915611321375727L;
	
	private String street, city, state, zip, name, email, phone, tw, fb;

	public AddSubscriber(String street, String city, String state, String zip,
						 String name, String email, String phone, String tw, String fb) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.tw = tw;
		this.fb = fb;
	}
		
	public AddSubsResponse addAccount(ArrayList<Subscriber> subs, Integer UID) {
		Address a = new Address(street, city, state, zip);
		Subscriber s = new Subscriber (name, email, phone, a, fb, tw);
		ArrayList<Errors> errors = new ArrayList<Errors>();
		
		if (addressInBannedState()) {
			errors.add(new Errors(1009, "We may not ship to this state"));
		}
		if (userHasAccount(subs, s)) {
			errors.add(new Errors(1017, "User already has an account"));
		} 
		if(s.getName().equals("")){
			errors.add(new Errors(1000, "Name must be provided"));
		}
		
		if(s.getEmail().equals("")){
			errors.add(new Errors(1002, "Email must be provided"));
		}
		
        if(!s.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
        	errors.add(new Errors(1003, "Invalid email address"));
		}
		
		if(a.getStreet().equals("")){
			errors.add(new Errors(1004, "Address must be provided"));
		}
		
		if(a.getCity().equals("")){
			errors.add(new Errors(1006, "City must be provided"));
		}
		
		if(a.getState().equals("")){
			errors.add(new Errors(1008, "State must be provided"));
		}
		
		if(a.getZip().equals("")){
			errors.add(new Errors(1010, "ZIP code must be provided"));
		}
		
		if(a.getZip().length()!= 5){
			errors.add(new Errors(1011, "Bad ZIP code"));
		}
		
		if(s.getPhone().equals("")){			
			errors.add(new Errors(1012, "Phone number must be provided"));
		}
		
		if(userHasAccountEmail(subs, s)){
			errors.add(new Errors(1014, "An account with this email address exists"));
		}
		
		if(!userHasAccount(subs, s)&&!addressInBannedState()&&!userHasAccountEmail(subs, s)
				&&(!s.getName().equals(""))&&!s.getEmail().equals("")&&!s.getAddress().equals("")&&!a.getCity().equals("")
				&&!a.getState().equals("")&&!(a.getZip().length()!= 5)&&!a.getZip().equals("")&&!s.getPhone().equals("")&&
				(s.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))){
			subs.add(s);
			return new AddSubsResponse(s.getID(), null);
		}
		return new AddSubsResponse(null, errors);
	}
	
	private boolean userHasAccount(ArrayList<Subscriber> subs, Subscriber s) {
		for(Subscriber sub: subs){
			if(sub.getEmail().equals(s.getEmail())){
				return true;
			}
		}
		return false;
	}
	
	private boolean userHasAccountEmail(ArrayList<Subscriber> subs, Subscriber s) {
		for(Subscriber sub: subs){
			if(sub.getEmail().equals(s.getEmail())){
				return true;
			}
		}return false;
	}
	
	private boolean addressInBannedState() {
		if(this.state.equals("Alabama")) return true;
		else if(this.state.equals("Arkansas")) return true;
		else if(this.state.equals("Delaware")) return true;
		else if(this.state.equals("Kentuky")) return true;
		else if(this.state.equals("Massachusetts")) return true;
		else if(this.state.equals("Mississippi")) return true;
		else if(this.state.equals("Oklahoma")) return true;
		else if(this.state.equals("outh Dakota")) return true;
		else if(this.state.equals("Utah")) return true;
		else if(this.state.equals("Pensilvania")) return true;
		return false;
	}
}
