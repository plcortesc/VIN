package edu.iit.cs445.Management;

import java.util.ArrayList;

import edu.iit.cs445.vin.Address;
import edu.iit.cs445.Response.ModifySubsResponse;
import edu.iit.cs445.vin.Shipment;
import edu.iit.cs445.vin.Subscriber;
import edu.iit.cs445.vin.Wine;

public class ModifySubscriber implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4462879391662178310L;
	private String street, city, state, zip, name, email, phone, tw, fb;

	public ModifySubscriber(String street, String city, String state, String zip,
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
	public ModifySubsResponse modifyAccount(ArrayList<Subscriber> subs, int UID, MonthlySelectionType mst, String date, ArrayList<Shipment> shipments, ArrayList<Wine> wines) {
		Address a = new Address(street, city, state, zip);
		Subscriber s = new Subscriber (name, email, phone, a, fb, tw);

		ArrayList<Errors> errors = new ArrayList<Errors>();
		
		if (addressInBannedState()) {
			errors.add(new Errors(1009, "We may not ship to this state"));
		}
		if (userHasAccount(subs, s,UID)) {
			errors.add(new Errors(1017, "User already has an account"));
		} 
		
        if(!s.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
        	errors.add(new Errors(1003, "Invalid email address"));
		}
		
		if(a.getZip().length()!= 5){
			errors.add(new Errors(1011, "Bad ZIP code"));
		}
		
		if(userHasAccountEmail(subs, s,UID)){
			errors.add(new Errors(1014, "An account with this email address exists"));
		}
		
		if(!userHasAccount(subs, s,UID)&&!addressInBannedState()&&!userHasAccountEmail(subs, s,UID)
				&&!(a.getZip().length()!= 5)&&(s.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))){
			s.setID(UID);
			s.setWines(wines);
			s.setMst(mst);
			s.setShipments(shipments);
			s.setDateString(date);
			subs.add(s);
			return new ModifySubsResponse(s.getID(), null);
		}
		return new ModifySubsResponse(null, errors);
	}
	
	private boolean userHasAccount(ArrayList<Subscriber> subs, Subscriber s, Integer UID) {
		for(Subscriber sub: subs){
			if(sub.getID()!=UID){
				if(sub.getEmail().equals(s.getEmail())){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean userHasAccountEmail(ArrayList<Subscriber> subs, Subscriber s, Integer UID) {
		for(Subscriber sub: subs){
			if(sub.getID()!=UID){
				if(sub.getEmail().equals(s.getEmail())){
					return true;
				}
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
