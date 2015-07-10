package edu.iit.cs445.vin;

import static org.junit.Assert.*;

import java.time.Year;

import org.junit.Test;

public class WineTest {

	@Test
	public void test_characteristics_of_default_new_wine() {
		Wine w = new Wine();
		assertEquals(WineVariety.RED, w.getVariety());
		assertEquals(WineType.TABLE, w.getType());
		assertEquals("The Mission", w.getLabelName());
		assertEquals("Cabernet Sauvignon", w.getGrape());
		assertEquals("Napa", w.getRegion());
		assertEquals("USA", w.getCountry());
		assertEquals("Sterling", w.getMaker());
		assertEquals("2011", w.getYear());
	}
	
	@Test
	public void test_characteristics_of_new_wine() {
		int y = 2012;
		Wine w = new Wine(WineVariety.WHITE, WineType.SPARKLING, "Dom Perignon", "", "Champagne", "France", "Moet & Chandon", y);
		assertEquals(WineVariety.WHITE, w.getVariety());
		assertEquals(WineType.SPARKLING, w.getType());
		assertEquals("Dom Perignon", w.getLabelName());
		assertEquals("", w.getGrape());
		assertEquals("Champagne", w.getRegion());
		assertEquals("France", w.getCountry());
		assertEquals("Moet & Chandon", w.getMaker());
		assertEquals("2012", w.getYear());
	}
	
// Since Wine.ratings is static and the order in which unit-tests are executed
// is not predictable, write the tests in a way that's independent of all that.
	@Test
	public void test_get_number_of_user_ratings_after_adding_a_rating() {
		Wine w = new Wine();
		int n = w.getNumberOfRatings();
		w.addRating(1);
		assertEquals(n+1, w.getNumberOfRatings());
	}
	
	@Test
	public void test_get_number_of_user_ratings_after_adding_two_ratings() {
		Wine w = new Wine();
		int n = w.getNumberOfRatings();
		w.addRating(1);
		w.addRating(2);
		assertEquals(n+2, w.getNumberOfRatings());
	}
		
	@Test
	public void test_average_ratings_after_adding_a_rating() {
		Wine w = new Wine();
		float r = w.getRating();
		int addNewRating = 5;
		
		w.addRating(addNewRating);
		int n = w.getNumberOfRatings();
		float newRating = r * (n-1)/n + addNewRating/n;
		assertEquals(newRating, w.getRating(), 0.01);
	}

	@Test
	public void test_average_ratings_after_adding_two_ratings() {
		Wine w = new Wine();
		float r = w.getRating();
		
		int addNewRating_1 = 5;		
		w.addRating(addNewRating_1);
		int n = w.getNumberOfRatings();
		float newRating = r * (n-1)/n + addNewRating_1/n;
		
		int addNewRating_2 = 2;
		w.addRating(addNewRating_2);
		n = w.getNumberOfRatings();
		newRating = r * (n-1)/n + addNewRating_1/n;
		
		assertEquals(newRating, w.getRating(), 0.01);
	}
	
	@Test
	public void test_search_match() {
		Wine w = new Wine();
		assertTrue(w.isMatch("mIs"));
		assertTrue(w.isMatch("ReD"));
		assertTrue(w.isMatch("table"));
	}
	
	@Test
	public void test_search_fail() {
		Wine w = new Wine();
		assertFalse(w.isMatch("blah-blah-blah"));
	}
}
