package edu.iit.cs445.Management;

public enum MonthlySelectionType {
	AW ("All whites"),
	AR ("All reds"),
	RW ("Reds and Whites");
	
	private String description;
	private MonthlySelectionType(String ms) {
		this.description = ms;
	}
}
