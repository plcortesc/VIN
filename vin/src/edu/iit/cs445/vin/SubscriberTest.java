package edu.iit.cs445.vin;

import static org.junit.Assert.*;

import org.junit.Test;

public class SubscriberTest {

    @Test
    public void test_search_match_name() {
    	Subscriber s = new Subscriber();
    	assertTrue(s.isMatch("jane"));
    }
    
    @Test
    public void test_search_fails() {
    	Subscriber s = new Subscriber();
    	assertFalse(s.isMatch("smith"));
    }
    
    @Test
    public void test_search_match_email() {
    	Subscriber s = new Subscriber();
    	assertTrue(s.isMatch("example.com"));
    }       

    @Test
    public void test_search_match_phone_partial() {
    	Subscriber s = new Subscriber();
    	assertTrue(s.isMatch("7890"));
    }
    
    @Test
    public void test_formatted_phone_number_matches() {
    	Subscriber s = new Subscriber();
    	assertTrue(s.isMatch("456-7890"));
    }
    
    @Test
    public void test_search_match_phone_full() {
    	Subscriber s = new Subscriber("John Doe", "johndoe@example.com", "(123) 456-7890", new Address());
    	assertTrue(s.isMatch("1234567890"));
    }
    
    @Test
    public void test_default_monthly_preference() {
    	Subscriber s = new Subscriber();
    	assertEquals(MonthlySelectionType.RW, s.getPreference());
    }
    
    @Test
    public void test_changing_the_monthly_preference() {
    	Subscriber s = new Subscriber();
    	s.setPreference(MonthlySelectionType.AR);
    	assertEquals(MonthlySelectionType.AR, s.getPreference());
    }
}
