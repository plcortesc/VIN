package edu.iit.cs445.vin;

import java.time.Year;
import java.util.ArrayList;

public class Wine implements java.io.Serializable {

	private static final long serialVersionUID = 3571681978009979283L;
	private WineVariety wv;
	private WineType wt;
	private String labelName;
	private String grape;	// e.g. Merlot, Chardonnay, Riesling, etc.
	private String region;	// e.g. Napa, Russian Valley, etc.
	private String country; // e.g. France, USA, Australia, Chile
	private String maker;	// the wine maker, e.g. Sterling, Krupp Brother, etc.
	private Year year;		// Vintage year
	private static int numberOfRatings;
	private static float rating = 0;
	private int ID;
	private ArrayList<Note> notes;

	public Wine() {
		this.wv = WineVariety.RED;
		this.wt = WineType.TABLE;
		this.labelName = "The Mission";
		this.grape = "Cabernet Sauvignon";
		this.region = "Napa";
		this.country = "USA";
		this.maker = "Sterling";
		this.year = Year.parse("2011");
		this.ID = IdGenerator.newID();
	}

	public Wine(WineVariety v, WineType t, String ln, String g, String r, String c, String m, Year y) {
		this.wv = v;
		this.wt = t;
		this.labelName = ln;
		this.grape = g;
		this.region = r;
		this.country = c;
		this.maker = m;
		this.year = y;
		this.ID = IdGenerator.newID();
	}
	
	public int getID(){
		return this.ID;
	}

	public WineVariety getVariety() {
		return this.wv;
	}
	
	public WineType getType() {
		return this.wt;
	}

	public String getLabelName() {
		return this.labelName;
	}

	public String getGrape() {
		return this.grape;
	}

	public String getRegion() {
		return this.region;
	}
	
	public String getCountry() {
		return this.country;
	}

	public String getMaker() {
		return this.maker;
	}
	
	public String getYear() {
		return this.year.toString();
	}

	public int getNumberOfRatings() {
		return this.numberOfRatings;
	}
	
	public float getRating() {
		return this.rating;
	}
	
	public void addRating(int r) {
		numberOfRatings = numberOfRatings + 1;
		rating = rating*((float)(numberOfRatings - 1)/numberOfRatings) + (float)r/numberOfRatings;
	}
	
	public boolean isMatch(String kw) {
        if (isMatchVariety(kw) || isMatchType(kw) || isMatchLabel(kw) || isMatchGrape(kw) || isMatchRegion(kw) || isMatchCountry(kw) || isMatchMaker(kw) || isMatchYear(kw)) {
                return true;
        } else return false;
	}
	    
    private boolean isMatchVariety(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.wv.name().matches(regex);
    }

    private boolean isMatchType(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.wt.name().matches(regex);
    }
    
    private boolean isMatchLabel(String kw) {
        String regex = "(?i).*" + kw + ".*";
        return this.labelName.matches(regex);
    }
    
    private boolean isMatchGrape(String kw) {
        String regex = "(?i).*" + kw + ".*";
        return this.grape.matches(regex);
    }
    
    private boolean isMatchRegion(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.region.matches(regex);
    }

    private boolean isMatchCountry(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.country.matches(regex);    
    }

    private boolean isMatchMaker(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.maker.matches(regex);   
    }

    private boolean isMatchYear(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.year.toString().matches(regex);
    }
    
    public void addNote(Note n){
		if(n.getContent().length()<128){
			System.out.println("Minimum note length: 128 characters");
		} else if(n.getContent().length()>1024){
			System.out.println("Maximum note length: 1024 characters");
		}
		System.out.println("Congratulations! Note added");
		this.notes.add(n);
	}	
    
    public void updateNote(int NID, Note n){
		for(int i=0; i<notes.size(); i++){
			if(notes.get(i).getID()==NID){
				notes.set(i, n);
			}
		}
	}
    
    public void removeNote(int NID){
		for(int i=0; i<notes.size(); i++){
			if(notes.get(i).getID()==NID){
				notes.remove(i);
			}
		}
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
    
    public void printWine(){
    	String str="ID: "+this.ID+"\nLabel name: "+this.labelName;
    	System.out.println(str);
    }
    
    public void printDetailedWine(){
    	String str="ID: "+this.ID+"\nVariety: "+this.wv+"\nType: "+this.wt+"\nLabel name: "+this.labelName+
    				"\nGrape: "+this.grape+"\nRegion: "+this.region+"\nCountry: "+this.country+"\nMaker: "+
    				this.maker+"\nYear: "+this.year+"\nRating count: "+getNumberOfRatings()+"\nRating: "+ getRating();
    	System.out.println(str);
    }
}
