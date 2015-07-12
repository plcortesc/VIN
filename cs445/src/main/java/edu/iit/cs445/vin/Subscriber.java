package edu.iit.cs445.vin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import edu.iit.cs445.Management.IdGenerator;
import edu.iit.cs445.Management.MonthlySelectionType;
import edu.iit.cs445.Response.NoteResponseSearch;
import edu.iit.cs445.Response.ShipmentResponseSearch;
import edu.iit.cs445.Response.WineResponse;


public class Subscriber implements java.io.Serializable {

	private static final long serialVersionUID = 169056067626574235L;
    private int ID;
	private String name, email, phone, twitter, facebook;
    private String date;
	private Address address;
    private MonthlySelectionType mst;
    private ArrayList<Shipment> shipments;
	private ArrayList<Wine> wines;

    
    public Subscriber() {
    	this.ID = IdGenerator.newID();
    	this.name = "Jane Doe";
    	this.email = "jane.doe@example.com";
    	this.phone = "1234567890";
    	this.address = new Address();
    	this.facebook="sdaf";
    	this.twitter = "sad";
    	this.setDate(Calendar.getInstance());
    	this.mst = MonthlySelectionType.RW;
    	this.shipments = new ArrayList<Shipment>();
    	this.wines = new ArrayList<Wine>();
    }
    public Subscriber (String name, String email, String phone, Address address) {
    	this.ID = IdGenerator.newID();
    	this.name = name;
    	this.email = email;
    	this.phone = phone.replaceAll("[\\s\\-()]", ""); // drop all non-digit characters
    	this.address = address;
    	this.twitter = "";
    	this.facebook = "";
    	this.setDate(Calendar.getInstance());
    	this.mst = MonthlySelectionType.RW;
    	this.shipments = new ArrayList<Shipment>();
    	this.wines = new ArrayList<Wine>();
    }
    public Subscriber (String name, String email, String phone, Address address, String fb, String tw) {
    	this.ID = IdGenerator.newID();
    	this.name = name;
    	this.email = email;
    	this.phone = phone.replaceAll("[\\s\\-()]", ""); // drop all non-digit characters
    	this.address = address;
    	this.twitter = tw;
    	this.facebook = fb;
    	this.setDate(Calendar.getInstance());
    	this.mst = MonthlySelectionType.RW;
    	this.shipments = new ArrayList<Shipment>();
    	this.wines = new ArrayList<Wine>();
    }
    
    public int getID() {return this.ID;}
    public void setID(int ID){this.ID = ID;}
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
    public void setEmail(String email){this.email = email;}
    public String getEmail(){return this.email;}
    public void setPhone(String phone){this.phone = phone;}
    public String getPhone(){return this.phone;}
    public void setAddress(Address address){this.address = address;}
    public Address getAddress(){return this.address;}
    public void setFacebook(String fb){this.facebook = fb;}
    public String getFacebook(){return this.facebook;}
    public void setTwitter(String tw){this.twitter = tw;}
    public String getTwitter(){return this.twitter;}
    public String getDate() {return date;}
	public void setDate(Calendar date) {this.date = Integer.toString(date.get(Calendar.YEAR))+"-"+
										Integer.toString(date.get(Calendar.MONTH)+1)+"-"+
										Integer.toString(date.get(Calendar.DAY_OF_MONTH));}
    public void setDateString(String date){this.date = date;}
    public void setMst(MonthlySelectionType mst){this.mst = mst;}
    
    public MonthlySelectionType getPreference() {return this.mst;}
    public void setPreference(MonthlySelectionType t) {this.mst = t;}
    public ArrayList<Shipment> getShipments(){return shipments;}
    public void setShipments(ArrayList<Shipment> shipments){ this.shipments = shipments;}
    public ArrayList<Wine> getWines() {return wines;}
	public void setWines(ArrayList<Wine> wines) {this.wines = wines;}
    
	public void addShipment(MonthlySelection ms){
    	Shipment s = new Shipment(this.mst, ms);
		if(this.shipments.size()==0){
			shipments.add(s);
		}else{
			boolean repeated=false;
			for(Shipment shi:this.shipments){
	    		if(ms.getYm().equals(shi.getYm())) repeated=true;
			}if(repeated==false) this.shipments.add(s);

		}
		
		if(this.wines.size()==0){
			wines.add(ms.getMs().get(0));
		}
		for(Wine w: ms.getMs()){
			if(addWine(w)) wines.add(w);
		}
    }
    
    public boolean addWine(Wine w){
    	boolean add = true;
    	for(Wine wine:wines){
    		if(w.getLabelName().equals(wine.getLabelName())
						&& w.getVariety()==wine.getVariety()
						&& w.getType()==wine.getType()){
    			add = false;
    		}
    	}return add;
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
	
	public ArrayList<WineResponse> searchWine(String kw) {
		ArrayList<WineResponse> coll = new ArrayList<WineResponse>();
		if(wines != null){
			Iterator<Wine> i = wines.iterator();
			if(kw.equals("")) {
				while(i.hasNext()) {
					Wine w = i.next();
					coll.add(new WineResponse(w.getWID(),w.getLabelName()));
				}
			}else {
				while(i.hasNext()) {
					Wine w = i.next();
					if(w.isMatch(kw)) {
						coll.add(new WineResponse(w.getWID(),w.getLabelName()));
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
				if(s.getNotes()!=null){
					Iterator<Note> is = s.getNotes().iterator();
					if(kw.equals("")) {
						while(is.hasNext()) {
							Note n = is.next();
							coll.add(new NoteResponseSearch(n.getNID(),n.getContent()));
						}
					}else {
						while(is.hasNext()) {
							Note n = is.next();
							if(n.isMatch(kw)) {
								coll.add(new NoteResponseSearch(n.getNID(),n.getContent()));
							}
						}
					}	
					if(coll.size() == 0) System.out.println("");
				}
			}
		}
		if(wines!=null){
			for(Wine w:wines){
				if(w.getNotes()!=null){
					Iterator<Note> iw = w.getNotes().iterator();
					if(kw.equals("")) {
						while(iw.hasNext()) {
							Note n = iw.next();
							coll.add(new NoteResponseSearch(n.getNID(),n.getContent()));
						}
					}else {
						while(iw.hasNext()) {
							Note n = iw.next();
							if(n.isMatch(kw)) {
								coll.add(new NoteResponseSearch(n.getNID(),n.getContent()));
							}
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
					coll.add(new ShipmentResponseSearch(s.getSID(),s.getYm()));
				}
			}else {
				while(i.hasNext()) {
					Shipment s = i.next();
					if(s.isMatch(kw)) {
						coll.add(new ShipmentResponseSearch(s.getSID(),s.getYm()));
					}
				}
			}	
			if(coll.size() == 0) System.out.println("");
		}
		return coll;
	}

}
