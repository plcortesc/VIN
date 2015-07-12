package edu.iit.cs445.vin;

import java.util.Calendar;

import edu.iit.cs445.Management.IdGenerator;

public class Note implements java.io.Serializable {

	private static final long serialVersionUID = 1740864099621530866L;
	private int NID;
	private Calendar date;
	private String content;	
	
	public Note(){
    	this.NID = IdGenerator.newID();
	}
	public Note(String str){
		this.date = Calendar.getInstance();
		this.content=str;
    	this.NID = IdGenerator.newID();
	}
		
	public int getNID(){
	    return this.NID;
	}
	
	public void setNID(int ID){
		this.NID = ID;
	}

	public Calendar getDate(){
		return this.date;
	}

	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public boolean isMatch(String kw) {
        if (isMatchContent(kw) || isMatchYear(kw) || isMatchMonth(kw) || isMatchDay(kw)) {
                return true;
        } else return false;
	}

    private boolean isMatchContent(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return this.content.matches(regex);    
    }

    private boolean isMatchYear(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.YEAR)).matches(regex);
    }
    private boolean isMatchMonth(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.MONTH)).matches(regex);
    }
    private boolean isMatchDay(String kw) {
    	String regex = "(?i).*" + kw + ".*";
        return Integer.toString(this.date.get(Calendar.DAY_OF_MONTH)).matches(regex);
    }
}
