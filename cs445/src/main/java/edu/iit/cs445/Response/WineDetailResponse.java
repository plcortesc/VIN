package edu.iit.cs445.Response;

import edu.iit.cs445.Management.WineType;
import edu.iit.cs445.Management.WineVariety;

public class WineDetailResponse implements java.io.Serializable {

	private static final long serialVersionUID = -4203224287440284854L;
	private int ID;
	private WineVariety variety;
	private WineType wine_type;
	private String label_name;
	private String grape;
	private String region;
	private String country;
	private String maker;
	private String year;
	private int ratings_count;
	private float rating;
	
	public WineDetailResponse(int ID,WineVariety variety,WineType wine_type, String label_name,String grape,String region, String country,String maker,String year,int ratings_count, float rating) {
		this.setID(ID);
		this.setVariety(variety);
		this.setWine_type(wine_type);
		this.setLabel_name(label_name);
		this.setGrape(grape);
		this.setRegion(region);
		this.setCountry(country);
		this.setMaker(maker);
		this.setYear(year);
		this.setRatings_count(ratings_count);
		this.setRating(rating);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public WineVariety getVariety() {
		return variety;
	}

	public void setVariety(WineVariety variety) {
		this.variety = variety;
	}

	public WineType getWine_type() {
		return wine_type;
	}

	public void setWine_type(WineType wine_type) {
		this.wine_type = wine_type;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	public String getGrape() {
		return grape;
	}

	public void setGrape(String grape) {
		this.grape = grape;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getRatings_count() {
		return ratings_count;
	}

	public void setRatings_count(int ratings_count) {
		this.ratings_count = ratings_count;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

}
