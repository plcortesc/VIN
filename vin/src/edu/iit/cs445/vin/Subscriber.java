package edu.iit.cs445.vin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class Subscriber implements java.io.Serializable {

	private static final long serialVersionUID = 169056067626574235L;
	private String name, email, phone, twitter, facebook;
    private Address address;
    private MonthlySelectionType mst;
    private int ID;
    private String date;
    private ArrayList<Shipment> shipments;
	private ArrayList<Wine> wines;

    
    public Subscriber() {
    	this.name = "Jane Doe";
    	this.email = "jane.doe@example.com";
    	this.phone = "1234567890";
    	this.address = new Address();
    	this.mst = MonthlySelectionType.RW;
    	this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.facebook="sdaf";
    	this.twitter = "sad";
    	this.shipments = null;
    	this.wines = null;
    }
    public Subscriber (String name, String email, String phone, Address address) {
    	this.name = name;
    	this.email = email;
    	this.phone = phone.replaceAll("[\\s\\-()]", ""); // drop all non-digit characters
    	this.address = address;
    	this.mst = MonthlySelectionType.RW;
    	this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.twitter = "";
    	this.facebook = "";
    }
    public Subscriber (String name, String email, String phone, Address address, String fb, String tw) {
    	this.name = name;
    	this.email = email;
    	this.phone = phone.replaceAll("[\\s\\-()]", ""); // drop all non-digit characters
    	this.address = address;
    	this.mst = MonthlySelectionType.RW;
    	this.setDate(Calendar.getInstance());
    	this.ID = IdGenerator.newID();
    	this.twitter = tw;
    	this.facebook = fb;
    }
    
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return this.email;}
    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return this.phone;}
    public void setAddress(Address address){this.address = address;}
    public Address getAddress(){return this.address;}
    public void setMst(MonthlySelectionType mst){this.mst = mst;}
    public MonthlySelectionType getMst(){return this.mst;}
    public void setFacebook(String fb){this.facebook = fb;}
    public String getFacebook(){return this.facebook;}
    public void setTwitter(String tw){this.twitter = tw;}
    public String getTwitter(){return this.twitter;}
    public ArrayList<Shipment> getShipments(){return shipments;}
	public String getDate() {return date;}
	public void setDate(Calendar date) {this.date = Integer.toString(date.get(Calendar.YEAR))+"-"+
										Integer.toString(date.get(Calendar.MONTH)+1)+"-"+
										Integer.toString(date.get(Calendar.DAY_OF_MONTH));}
    
    public void addShipment(){
    	Shipment s = new Shipment(this.mst);
    	for(Shipment shi:shipments){
    		if(shi.getDate().get(Calendar.YEAR)!=Calendar.getInstance().get(Calendar.YEAR) 
    		   || shi.getDate().get(Calendar.MONTH)!=Calendar.getInstance().get(Calendar.MONTH)){
    			shipments.add(s);
    		}
    	}
    }
    
    public void addModifyShipment(Calendar date, int size, MonthlySelectionType mst){
    	Shipment s = new Shipment(this.mst);
    	s.setSize(size);
    	s.setDate(date);
    	for(Shipment shi:shipments){
    		if(shi.getDate().get(Calendar.YEAR)==Calendar.getInstance().get(Calendar.YEAR) 
    		   && shi.getDate().get(Calendar.MONTH)==Calendar.getInstance().get(Calendar.MONTH)){
    			shipments.remove(shi);
    			shipments.add(s);
    		}
    	}
    }
    
    private boolean isMatchName(String kw) {
    	String regex = "(?i).*" + kw + ".*";
    	return this.name.matches(regex);
    }

    private boolean isMatchEmail(String kw) {
    	String regex = "(?i).*" + kw + ".*";
    	return this.email.matches(regex);
    }

    private boolean isMatchPhone(String kw) {
    	String s = kw.replaceAll("[\\s\\-()]", ""); // drop all non-digit characters from search string
    	String regex = "(?i).*" + s + ".*";
    	return this.phone.matches(regex);
    }
    public boolean isMatch(String kw) {
    	if (isMatchName(kw) || isMatchEmail(kw) || isMatchPhone(kw)) {
    		return true;
    	} else return false;
    }

    public int getID() {
    	return this.ID;
    }
    public void setID(int ID){
    	this.ID = ID;
    }
    
    public MonthlySelectionType getPreference() {
    	return mst;
    }
    
    public void setPreference(MonthlySelectionType t) {
    	this.mst = t;
    }
    
    public void postNote(int SID,Note n){
    	for(int i=0; i<shipments.size();i++){
			if(shipments.get(i).getID()==SID){
				shipments.get(i).addNote(n);
			}
    	}
    }
    
	public ArrayList<Wine> getWines() {
		return wines;
	}

	public void setWines(ArrayList<Wine> wines) {
		this.wines = wines;
	}
	
	public ArrayList<WineResponse> searchWine(String kw) {
		ArrayList<WineResponse> coll = new ArrayList<WineResponse>();
		if(wines != null){
			Iterator<Wine> i = wines.iterator();
			if(kw.equals("")) {
				while(i.hasNext()) {
					Wine w = i.next();
					coll.add(new WineResponse(w.getID(),w.getLabelName()));
				}
			}else {
				while(i.hasNext()) {
					Wine w = i.next();
					if(w.isMatch(kw)) {
						coll.add(new WineResponse(w.getID(),w.getLabelName()));
					}
				}
			}	
		}
		if(coll.size() == 0) System.out.println("");
		return coll;
	}
	
	public ArrayList<NoteResponseSearch> searchNote(String kw) {
		ArrayList<NoteResponseSearch> coll = new ArrayList<NoteResponseSearch>();
		if(shipments!=null){
			for(Shipment s:shipments){
				Iterator<Note> is = s.getNotes().iterator();
				if(kw.equals("")) {
					while(is.hasNext()) {
						Note n = is.next();
						coll.add(new NoteResponseSearch(n.getID(),n.getContent()));
					}
				}else {
					while(is.hasNext()) {
						Note n = is.next();
						if(n.isMatch(kw)) {
							coll.add(new NoteResponseSearch(n.getID(),n.getContent()));
						}
					}
				}	
				if(coll.size() == 0) System.out.println("");
			}
		}
		if(wines!=null){
			for(Wine w:wines){
				Iterator<Note> iw = w.getNotes().iterator();
				if(kw.equals("")) {
					while(iw.hasNext()) {
						Note n = iw.next();
						coll.add(new NoteResponseSearch(n.getID(),n.getContent()));
					}
				}else {
					while(iw.hasNext()) {
						Note n = iw.next();
						if(n.isMatch(kw)) {
							coll.add(new NoteResponseSearch(n.getID(),n.getContent()));
						}
					}
				}	
				if(coll.size() == 0) System.out.println("");
			}
		}
		
		return coll;
	}
	
	public ArrayList<ShipmentResponseSearch> searchShipment(String kw) {
		ArrayList<ShipmentResponseSearch> coll = new ArrayList<ShipmentResponseSearch>();
		if(shipments!=null){
			Iterator<Shipment> i = shipments.iterator();
			if(kw.equals("")) {
				while(i.hasNext()) {
					Shipment s = i.next();
					coll.add(new ShipmentResponseSearch(s.getID(),s.getYm()));
				}
			}else {
				while(i.hasNext()) {
					Shipment s = i.next();
					if(s.isMatch(kw)) {
						coll.add(new ShipmentResponseSearch(s.getID(),s.getYm()));
					}
				}
			}	
			if(coll.size() == 0) System.out.println("");
		}
		return coll;
	}

}
