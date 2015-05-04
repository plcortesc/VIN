package edu.iit.cs445.vin;

import java.util.ArrayList;

public class MsWinePost {

	public ArrayList<Wine> wines = new ArrayList<Wine>();

	public MsWinePost() {}

	public void setWines(ArrayList<Wine> wines){
		this.wines = wines;
	}
	
	public ArrayList<Wine> getWines(){
		return this.wines;
	}
}
