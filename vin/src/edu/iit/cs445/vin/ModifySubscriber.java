package edu.iit.cs445.vin;

import java.util.ArrayList;

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
		if (userHasAccount(subs, s, UID)) {
			errors.add(new Errors(1017, "User already has an account"));
		} 
		if(s.getName()==null){
			errors.add(new Errors(1000, "Name must be provided"));
		}
		
		if(s.getEmail()==null){
			errors.add(new Errors(1002, "Email must be provided"));
		}
		
		if(s.getAddress() == null){
			errors.add(new Errors(1004, "Address must be provided"));
		}
		
		if(a.getCity()==null){
			errors.add(new Errors(1006, "City must be provided"));
		}
		
		if(a.getState()==null){
			errors.add(new Errors(1008, "State must be provided"));
		}
		
		if(a.getZip()==null){
			errors.add(new Errors(1010, "ZIP code must be provided"));
		}
		
		if(a.getZip().length()!= 5){
			errors.add(new Errors(1011, "Bad ZIP code"));
		}
		
		if(s.getPhone()==null){			
			errors.add(new Errors(1012, "Phone number must be provided"));
		}
		
		if(userHasAccountEmail(subs, s, UID)){
			errors.add(new Errors(1014, "An account with this email address exists"));
		}
		
		if(!userHasAccount(subs, s, UID)&&!addressInBannedState()&&!userHasAccountEmail(subs, s, UID)
				&&(s.getName()!=null)&&s.getEmail()!=null&&s.getAddress()!=null&&a.getCity()!=null
				&&a.getState()!=null&&!(a.getZip().length()!= 5)&&a.getZip()!=null&&s.getPhone()!=null){
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
				}else if(sub.getPhone().equals(s.getPhone())){
					return true;
				}else if(sub.getFacebook()!=null && !sub.getFacebook().equals("")  && s.getFacebook()!=null){
					if(sub.getFacebook().equals(s.getFacebook())){
						return true;
					}
				}else if(sub.getTwitter()!=null && !sub.getTwitter().equals("") && s.getTwitter()!=null){
					if(sub.getTwitter().equals(s.getTwitter())){
						return true;
					}
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
		switch(this.state){
			case "Alabama":return true;
			case "Arkansas":return true;
			case "Delaware":return true;
			case "Kentuky":return true;
			case "Massachusetts":return true;
			case "Mississippi":return true;
			case "Oklahoma":return true;
			case "Pensilvania":return true;
			case "South Dakota":return true;
			case "Utah":return true;
			default:return false;
		}
	}
}
