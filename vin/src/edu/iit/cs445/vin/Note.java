package edu.iit.cs445.vin;

import java.util.Calendar;

public class Note implements java.io.Serializable {

	private static final long serialVersionUID = 1740864099621530866L;
	private int ID;
	private Calendar date;
	private String content;	
	
	public Note(String str){
		this.date = Calendar.getInstance();
		this.content=str;
    	this.ID = IdGenerator.newID();
	}
		
	public int getID(){
	    return this.ID;
	}

	public Calendar getDate(){
		return this.date;
	}
	public String getContent(){
		return this.content;
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
