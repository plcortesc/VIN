package edu.iit.cs445.vin;

public enum MonthlySelectionType {
	AW ("All whites"),
	AR ("All reds"),
	RW ("Reds and Whites");
	
	private String description;
	private MonthlySelectionType(String ms) {
		this.description = ms;
	}
}
