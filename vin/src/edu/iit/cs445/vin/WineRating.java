package edu.iit.cs445.vin;

public class WineRating implements java.io.Serializable {

	private static final long serialVersionUID = 6658521528553059206L;
	private float rating;
	public WineRating(float rating) {
		this.setRating(rating);
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}

}
