package edu.iit.cs445.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import edu.iit.cs445.Management.MonthlySelectionType;
import edu.iit.cs445.Management.ShipmentStatus;
import edu.iit.cs445.Management.WineRating;
import edu.iit.cs445.Management.WineType;
import edu.iit.cs445.Management.WineVariety;
import edu.iit.cs445.Response.AddSubsResponse;
import edu.iit.cs445.Response.AdminDetailResponse;
import edu.iit.cs445.Response.GetAdminResponse;
import edu.iit.cs445.Response.GetReceiptsResponse;
import edu.iit.cs445.Response.GetShipmentsResponse;
import edu.iit.cs445.Response.GetWineNotesResponse;
import edu.iit.cs445.Response.GetWinesResponse;
import edu.iit.cs445.Response.ModifySubsResponse;
import edu.iit.cs445.Response.MonthlySelectionResponse;
import edu.iit.cs445.Response.MstDetailResponse;
import edu.iit.cs445.Response.NoteIDResponse;
import edu.iit.cs445.Response.NoteResponseSearch;
import edu.iit.cs445.Response.PostMsResponse;
import edu.iit.cs445.Response.PostNoteResponse;
import edu.iit.cs445.Response.ReceiptDetailResponse;
import edu.iit.cs445.Response.RevenueResponse;
import edu.iit.cs445.Response.SearchResponse;
import edu.iit.cs445.Response.ShipNotesResponse;
import edu.iit.cs445.Response.ShipmentDetailResponse;
import edu.iit.cs445.Response.SubscriberResponse;
import edu.iit.cs445.Response.ToDeliverResponse;
import edu.iit.cs445.Response.WineDetailResponse;
import edu.iit.cs445.Response.WineResponse;
import edu.iit.cs445.vin.Address;
import edu.iit.cs445.vin.Admin;
import edu.iit.cs445.vin.Club;
import edu.iit.cs445.vin.MonthlySelection;
import edu.iit.cs445.vin.Note;
import edu.iit.cs445.vin.Receipt;
import edu.iit.cs445.vin.Shipment;
import edu.iit.cs445.vin.Subscriber;
import edu.iit.cs445.vin.Wine;

public class VinTest {

    //---------------------SUBSCRIBER/ADDRESS TEST--------------------------
    
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
    public void test_match_email() {
        Subscriber s = new Subscriber("John Doe", "johndoe@example.com", "(123) 456-7890",
                                      new Address("Madison", "Chicago", "Illinois", "60610"),"james", "jamestwitter");
        assertTrue(s.isMatch("johndoe@example.com"));
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
    
    @Test
    public void test_get_set(){
        Subscriber s = new Subscriber();
        Address a = new Address();
        Shipment shi = new Shipment();
        Wine w = new Wine();
        ArrayList<Shipment> ship = new ArrayList<Shipment>();
        ship.add(shi);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        wines.add(w);
        a.setStreet("Jackson");
        a.setCity("Chicago");
        a.setState("Illinois");
        a.setZip("09876");
        s.setID(1);
        assertEquals(s.getID(), 1);
        s.setName("james");
        assertEquals(s.getName(), "james");
        s.setEmail("james@hotmail.com");
        assertEquals(s.getEmail(), "james@hotmail.com");
        s.setPhone("3124325643");
        assertEquals(s.getPhone(), "3124325643");
        s.setAddress(a);
        assertEquals(s.getAddress().getStreet(), "Jackson");
        assertEquals(s.getAddress().getCity(), "Chicago");
        assertEquals(s.getAddress().getState(), "Illinois");
        assertEquals(s.getAddress().getZip(), "09876");
        s.setFacebook("james");
        assertEquals(s.getFacebook(), "james");
        s.setTwitter("jamestwitter");
        assertEquals(s.getTwitter(), "jamestwitter");
        s.setDateString("2015-08");
        assertEquals(s.getDate(), "2015-08");
        s.setShipments(ship);
        assertEquals(s.getShipments(), ship);
        s.setWines(wines);
        assertEquals(s.getWines(), wines);
        s.setMst(MonthlySelectionType.AR);
        assertEquals(s.getPreference(), MonthlySelectionType.AR);
        s.setPreference(MonthlySelectionType.AW);
        assertEquals(s.getPreference(), MonthlySelectionType.AW);
    }
    
    @Test
    public void test_add_shipment(){
        Subscriber s = new Subscriber();
        Wine w = new Wine();
        ArrayList<Shipment> ship = new ArrayList<Shipment>();
        ArrayList<Wine> wms = new ArrayList<Wine>();
        for(int i=0;i<6;i++) wms.add(w);
        MonthlySelection ms = new MonthlySelection();
        ms.setMs(wms);
        s.setPreference(MonthlySelectionType.AR);
        s.setShipments(ship);
        s.addShipment(ms);
        assertTrue(s.getShipments().size()!=0);
        s.addShipment(ms);
        assertTrue(s.getShipments().size()!=0);
    }
    
    @Test
    public void test_search_wine(){
        Subscriber s = new Subscriber();
        Wine w = new Wine();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        wines.add(w);
        s.setWines(wines);
        assertTrue(s.searchWine("").size()==wines.size());
        assertTrue(s.searchWine("Mission").size()==1);
        assertTrue(s.searchWine("hello").size()==0);
    }
    
    @Test
    public void test_search_shipment(){
        Subscriber s = new Subscriber();
        Shipment shi = new Shipment();
        ArrayList<Shipment> ship = new ArrayList<Shipment>();
        ship.add(shi);
        s.setShipments(ship);
        assertTrue(s.searchShipment("").size()==ship.size());
        assertTrue(s.searchShipment("PENDING").size()==1);
        assertTrue(s.searchShipment("hello").size()==0);
    }
    
    @Test
    public void test_search_note(){
        Subscriber s = new Subscriber();
        Shipment shi = new Shipment();
        Wine w = new Wine();
        Note n1 = new Note("Hello shipment");
        Note n2 = new Note("Hello wine");
        Note n3 = new Note("Hello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment"
                           + "Hello shipmentHello shipmentHello shipmentHello shipmentHello shipment");
        ArrayList<Shipment> ship = new ArrayList<Shipment>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        shi.addNote(n1);
        shi.addNote(n3);
        ship.add(shi);
        s.setShipments(ship);
        w.addNote(n2);
        w.addNote(n3);
        wines.add(w);
        s.setWines(wines);
        assertTrue(s.searchNote("").size()==(ship.size()+wines.size()));
        assertTrue(s.searchNote("Hello").size()==2);
    }
    
    //---------------------WINE TEST--------------------------
    
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
        w.getWID();
        float r = w.getRating();
        int addNewRating = 5;
        
        w.addRating(addNewRating);
        w.setVariety(WineVariety.ROSE);
        int n = w.getNumberOfRatings();
        float newRating = r * (n-1)/n + addNewRating/n;
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
    
    @Test
    public void test_update_note(){
        Wine w = new Wine();
        Note n1 = new Note("Hello wine");
        Note n2 = new Note("Hello wine updated");
        n1.setNID(20);
        w.addNote(n1);
        w.updateNote(20, n2);
        for(Note note: w.getNotes()){
            if(note.getNID()==20){
                assertEquals(n2.getContent(), note.getContent());
            }
        }
    }
    
    @Test
    public void test_remove_note(){
        Wine w = new Wine();
        Note n1 = new Note("Hello wine");
        n1.setNID(20);
        w.addNote(n1);
        w.removeNote(20);
        assertTrue(w.getNotes().size()==0);
    }
    
    //---------------------ADMIN TEST--------------------------
    @Test
    public void test_create_ms_RW(){
        Admin a = new Admin();
        String mst = "RW";
        String ym = "2015-7";
        Subscriber s1 = new Subscriber();
        Subscriber s2 = new Subscriber();
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        MonthlySelection ms1 = new MonthlySelection();
        MonthlySelection ms2 = new MonthlySelection();
        
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        monthlySelection.add(ms1);
        monthlySelection.add(ms2);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        for(int i=0;i<3;i++){
            wines.add(wr);
        }
        for(int i=0;i<3;i++){
            wines.add(ww);
        }
        
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        subs.add(s1);
        subs.add(s2);
        
        a.createMs(mst, ym, wines, monthlySelection, subs);
        
        assertEquals(monthlySelection.size(),3);
    }
    
    @Test
    public void test_create_ms_AR(){
        Admin a = new Admin();
        String mst = "AR";
        String ym = "2015-7";
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.AR);
        Subscriber s2 = new Subscriber();
        Wine ww = new Wine(WineVariety.RED, WineType.TABLE, "holaRED", "HelloRED", "Ni hao", "Spain", "Sterling", 2007);
        MonthlySelection ms1 = new MonthlySelection();
        MonthlySelection ms2 = new MonthlySelection();
        
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        monthlySelection.add(ms1);
        monthlySelection.add(ms2);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        for(int i=0;i<6;i++){
            wines.add(ww);
        }
        
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        subs.add(s1);
        subs.add(s2);
        
        a.createMs(mst, ym, wines, monthlySelection, subs);
        
        assertEquals(monthlySelection.size(),3);
        
    }
    
    @Test
    public void test_admin_get_set(){
        Admin a = new Admin("Pepe");
        a.setD("2015-07");
        a.setAID(23);
        assertEquals(a.getDate(),"2015-07");
        assertEquals(a.getName(),"Pepe");
        assertEquals(a.getAID(),23);
        a.setName("Pedro");
        assertEquals(a.getName(),"Pedro");
    }
    
    @Test
    public void test_create_ms_AW(){
        Admin a = new Admin("Pepe");
        String mst = "AW";
        String ym = "2015-7";
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.AW);
        Subscriber s2 = new Subscriber();
        Wine ww = new Wine(WineVariety.WHITE, WineType.TABLE, "holaHITE", "HelloWHITE", "Ni hao", "Spain", "Sterling", 2007);
        MonthlySelection ms1 = new MonthlySelection();
        MonthlySelection ms2 = new MonthlySelection();
        
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        monthlySelection.add(ms1);
        monthlySelection.add(ms2);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        for(int i=0;i<6;i++){
            wines.add(ww);
        }
        
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        subs.add(s1);
        subs.add(s2);
        
        a.createMs(mst, ym, wines, monthlySelection, subs);
        
        assertEquals(monthlySelection.size(),3);
        
        a.createMs(mst, ym, wines, monthlySelection, subs);
        assertEquals(monthlySelection.size(),3);
    }
    
    
    //---------------------MONTHLYSELECTION TEST--------------------------
    
    @Test
    public void test_MS_get_set(){
        MonthlySelection ms = new MonthlySelection();
        ms.setMst(MonthlySelectionType.AR);
        ms.setID(23);
        ms.setAID(43);
        ms.setDate(Calendar.getInstance());
        assertEquals(ms.getAID(),43);
        assertEquals(ms.getID(),23);
    }
    
    //---------------------NOTE TEST--------------------------
    @Test
    public void test_note_get_set(){
        Note n = new Note();
        n.setContent("Hello");
        assertEquals(n.getContent(), "Hello");
    }
    
    @Test
    public void test_note_isMatch(){
        Note n = new Note("Hello");
        assertTrue(n.isMatch("he"));
        assertTrue(n.isMatch("2015"));
        assertFalse(n.isMatch("ik"));
    }
    
    //---------------------RECEIPT TEST--------------------------
    @Test
    public void test_receipt_get_set(){
        Subscriber s = new Subscriber();
        Receipt r = new Receipt(s,"James");
        Receipt r2 = new Receipt();
        r2.setName("Jamison");
        r.setID(20);
        r.setName(s.getName());
        r.setReceiver("Pete");
        r.setSID(23);
        assertEquals(r.getName(), s.getName());
        assertEquals(r.getID(), 20);
        assertEquals(r.getSID(), 23);
        assertEquals(r.getReceiver(), "Pete");
        assertEquals(r2.getName(), "Jamison");
        
    }
    //---------------------SHIPMENT TEST-------------------------
    
    @Test
    public void test_shipment_get_set(){
        Shipment sh = new Shipment();
        sh.setDeliveryfee(25);
        sh.setMsDeliveryfee(54);
        sh.setMst(MonthlySelectionType.AR);
        sh.setSize(2);
        sh.setStatus(ShipmentStatus.PENDING);
        assertEquals(sh.getDeliveryfee(), 25);
        assertEquals(sh.getMsDeliveryfee(), 54);
        assertEquals(sh.getMst(), MonthlySelectionType.AR);
        assertEquals(sh.getSize(), 2);
        assertEquals(sh.getStatus(), ShipmentStatus.PENDING);
    }
    
    @Test
    public void test_shipment_remove_note(){
        Shipment sh = new Shipment();
        Note n = new Note("hello");
        n.setNID(23);
        sh.getNotes().add(n);
        sh.removeNote(23);
        assertEquals(sh.getNotes().size(), 0);
    }
    
    @Test
    public void test_shipment_update_note(){
        Shipment sh = new Shipment();
        Note n = new Note("hello");
        n.setNID(23);
        sh.getNotes().add(n);
        sh.updateNote(23, new Note("Bye!"));
        assertEquals(sh.getNotes().size(), 1);
        assertEquals(sh.getNotes().get(0).getContent(),"Bye!");
    }
    
    //---------------------CLUB TEST-------------------------
    
    @Test
    public void test_club_add_subscriber(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s1 = new Subscriber();
        Subscriber bad_email = new Subscriber("Peterd", "pau", "3332123456", new Address("Madison", "Chicago", "Illinois", "44444"), "fbook", "twter");
        Subscriber bad_state_zip = new Subscriber("Peter", "pau@hotmail.com", "3332123456", new Address("Madison", "Philadelphia", "Pensilvania", "4444"), "facebook", "twitter");
        
        AddSubsResponse asr1 = c.addSubscriber(subs, s1.getAddress().getStreet(),s1.getAddress().getCity(), s1.getAddress().getState(), s1.getAddress().getZip(),
                                               s1.getName(), s1.getEmail(), s1.getPhone(), s1.getTwitter(), s1.getFacebook());
        assertTrue(asr1.getErrors().size()==0);
        assertTrue(asr1.getUID()!=null);
        
        AddSubsResponse asr2 = c.addSubscriber(subs, s1.getAddress().getStreet(),s1.getAddress().getCity(), s1.getAddress().getState(), s1.getAddress().getZip(),
                                               s1.getName(), s1.getEmail(), s1.getPhone(), s1.getTwitter(), s1.getFacebook());
        assertTrue(asr2.getErrors().size()!=0);
        assertTrue(asr2.getUID()==null);
        
        AddSubsResponse asr3 = c.addSubscriber(subs, bad_email.getAddress().getStreet(),bad_email.getAddress().getCity(), bad_email.getAddress().getState(), bad_email.getAddress().getZip(),
                                               bad_email.getName(), bad_email.getEmail(), bad_email.getPhone(), bad_email.getTwitter(), bad_email.getFacebook());
        assertTrue(asr3.getErrors().size()!=0);
        assertTrue(asr3.getUID()==null);
        
        
        AddSubsResponse asr4 = c.addSubscriber(subs, bad_state_zip.getAddress().getStreet(),bad_state_zip.getAddress().getCity(), bad_state_zip.getAddress().getState(), bad_state_zip.getAddress().getZip(),
                                               bad_state_zip.getName(), bad_state_zip.getEmail(), bad_state_zip.getPhone(), bad_state_zip.getTwitter(), bad_state_zip.getFacebook());
        assertTrue(asr4.getErrors().size()!=0);
        assertTrue(asr4.getUID()==null);
        
        AddSubsResponse asr5 = c.addSubscriber(subs,"","","","","","","","","");
        assertTrue(asr5.getErrors().size()!=0);
        assertTrue(asr5.getUID()==null);
    }
    
    @Test
    public void test_club_modify_subscriber(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s1 = new Subscriber();
        s1.setID(23);
        subs.add(s1);
        
        ModifySubsResponse msr1 = c.setSubscriber(23, "Peter", "pa@gmail.com", "0000003333", "tw",
                                                  "fb","Madison", "Chicago", "Illinois", "55555", subs);
        assertTrue(msr1.getErrors().size()==0);
        assertTrue(msr1.getUID()==23);
        
        ModifySubsResponse msr2 = c.setSubscriber(23, "Peter", "pa", "0000003333", "tw",
                                                  "fb","Madison", "Philadelphia", "Pensilvania", "5555", subs);
        assertTrue(msr2.getErrors().size()!=0);
        assertTrue(msr2.getUID()==null);
        
        Subscriber s2 = new Subscriber();
        s2.setID(22);
        subs.add(s2);
        ModifySubsResponse msr3 = c.setSubscriber(23, "Peter", "jane.doe@example.com", "0000003333", "tw",
                                                  "fb","Madison", "Philadelphia", "Pensilvania", "5555", subs);
        assertTrue(msr3.getErrors().size()!=0);
        assertTrue(msr3.getUID()==null);
    }
    
    @Test
    public void test_club_print_subscriber(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s1 = new Subscriber();
        s1.setFacebook("fb");
        s1.setTwitter("tw");
        s1.setID(23);
        subs.add(s1);
        
        SubscriberResponse sr = c.printSubscriber(23, subs);
        assertEquals(sr.getAddress(),s1.getAddress());
        assertEquals(sr.getEmail(),s1.getEmail());
        assertEquals(sr.getFacebook(),s1.getFacebook());
        assertEquals(sr.getName(),s1.getName());
        assertEquals(sr.getPhone(), s1.getPhone());
        assertEquals(sr.getTwitter(),s1.getTwitter());
    }
    
    @Test
    public void test_club_search(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        wr.addNote(new Note("Wi"));
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){
            wines.add(wr);
        }
        for(int i=0;i<3;i++){
            wines.add(ww);
        }
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        s1.getShipments().get(0).addNote(new Note("Shi"));
        subs.add(s1);
        
        SearchResponse sr1 = c.search(23, Integer.toString(SID), subs);
        assertEquals(sr1.getShipments().size(),0);
        
        SearchResponse sr2 = c.search(23, "Wi", subs);
        assertEquals(sr2.getNotes().size(),1);
        
        SearchResponse sr3 = c.search(23, "Mission", subs);
        assertEquals(sr3.getWines().size(),1);
    }
    
    @Test
    public void test_club_print_subs_shipment(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        subs.add(s1);
        
        GetShipmentsResponse gsr = c.printSubShipment(23,subs);
        assertEquals(gsr.getShipments().size(),1);
    }
    
    @Test
    public void test_club_print_subs_shipment_SID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2016-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        wr.addNote(new Note("Wi"));
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        subs.add(s1);
        
        String date = Integer.toString(s1.getShipments().get(0).getDate().get(Calendar.DAY_OF_MONTH))+
        "/"+Integer.toString(s1.getShipments().get(0).getDate().get(Calendar.MONTH))+
        "/"+Integer.toString(s1.getShipments().get(0).getDate().get(Calendar.YEAR));
        ShipmentDetailResponse sdr = c.printSubShipSID(23,SID,subs);
        assertEquals(sdr.getDate(),date);
        assertEquals(sdr.getYm(),ms.getYm());
        assertEquals(sdr.getMst(),s1.getPreference());
        assertEquals(sdr.getStatus(), ShipmentStatus.PENDING);
    }
    
    @Test
    public void test_club_print_shipment_note(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        Note note = new Note("Shi");
        s1.getShipments().get(0).addNote(note);
        subs.add(s1);
        
        ShipNotesResponse snr = c.printShipNote(23, SID,subs);
        assertEquals(snr.getNotes().get(0).getContent(),note.getContent());
    }
    
    @Test
    public void test_club_post_shipment_note(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        Note note = new Note("Shi");
        int NID = note.getNID();
        subs.add(s1);
        
        PostNoteResponse pnr = c.postShipmentNote(23,SID, note,subs);
        assertEquals(pnr.getID(), NID);
    }
    
    @Test
    public void test_club_print_shipment_note_nid(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        Note note = new Note("Shi");
        int NID = note.getNID();
        s1.getShipments().get(0).addNote(note);
        subs.add(s1);
        String date = Integer.toString(note.getDate().get(Calendar.MONTH))+
        "/"+Integer.toString(note.getDate().get(Calendar.DAY_OF_MONTH))+
        "/"+Integer.toString(note.getDate().get(Calendar.YEAR));
        
        NoteIDResponse nr = c.printShipNoteNID(23,SID,NID,subs);
        assertEquals(nr.getContent(),note.getContent());
        assertEquals(nr.getDate(),date);
    }
    
    @Test
    public void test_club_update_shipment_note_nid(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        Note note = new Note("Shi");
        int NID = note.getNID();
        s1.getShipments().get(0).addNote(note);
        subs.add(s1);
        
        c.setShipNoteNID(23, SID, NID, new Note("Hello"),subs);
        assertEquals(s1.getShipments().get(0).getNotes().get(0).getContent(),"Hello");
    }
    
    @Test
    public void test_club_remove_shipment_note_nid(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        int SID = s1.getShipments().get(0).getSID();
        Note note = new Note("Shi");
        int NID = note.getNID();
        s1.getShipments().get(0).addNote(note);
        subs.add(s1);
        
        c.removeShipNoteNID(23,SID,NID,subs);
        
        assertEquals(s1.getShipments().get(0).getNotes().size(),0);
    }
    
    @Test
    public void test_club_print_subs_wines(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        subs.add(s1);
        
        GetWinesResponse gwr = c.printWines(23, subs);
        assertEquals(gwr.getWrs().get(0).getLabel_name(), wr.getLabelName());
        assertEquals(gwr.getWrs().get(1).getLabel_name(), ww.getLabelName());
        assertEquals(gwr.getWrs().size(),2);
    }
    
    @Test
    public void test_club_print_wines_WID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s1 = new Subscriber();
        s1.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        int WID = wr.getWID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s1.setID(23);
        s1.addShipment(ms);
        subs.add(s1);
        
        WineResponse wresp = c.printWinesWID(23,WID,subs);
        assertEquals(wresp.getID(),WID);
        assertEquals(wresp.getLabel_name(),wr.getLabelName());
    }
    
    @Test
    public void test_club_print_wine_notes(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Note n = new Note("Wine");
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        int WID = ww.getWID();
        ww.addNote(n);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        GetWineNotesResponse gwnr = c.printWineNotes(22,WID,subs);
        assertEquals(gwnr.getNotes().get(0).getContent(),"Wine");
    }
    
    @Test
    public void test_club_post_wine_notes(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Note n = new Note("Wine");
        int NID = n.getNID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        int WID = ww.getWID();
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        PostNoteResponse pnr = c.postWinesNote(22, WID, n,subs);
        assertEquals(pnr.getID(),NID);
    }
    
    @Test
    public void test_club_print_wine_note_ID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Note n = new Note("Wine");
        int NID = n.getNID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        int WID = ww.getWID();
        ww.addNote(n);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        NoteResponseSearch nrs = c.printWineNoteNID(22,WID, NID,subs);
        assertEquals(nrs.getContent(),"Wine");
        assertEquals(nrs.getID(),NID);
    }
    
    @Test
    public void test_club_update_wine_note_NID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Note n = new Note("Wine");
        int NID = n.getNID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        int WID = ww.getWID();
        ww.addNote(n);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        c.setWineNoteNID(22, WID, NID, new Note("New wine"),subs);
        assertEquals(subs.get(0).getWines().get(1).getNotes().get(0).getContent(),"New wine");
    }
    
    @Test
    public void test_club_remove_wine_note_NID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        Note n = new Note("Wine");
        int NID = n.getNID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        int WID = ww.getWID();
        ww.addNote(n);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        c.removeWineNoteNID(22,WID,NID,subs);
        assertEquals(subs.get(0).getWines().get(1).getNotes().size(),0);
    }
    
    @Test
    public void test_club_print_rating(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        int WID = wr.getWID();
        wr.addRating(30);
        wr.addRating(40);
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        WineRating wrat = c.printWineRating(22,WID,subs);
        assertTrue(wrat.getRating()==35);
    }
    
    @Test
    public void test_club_post_rating(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        int WID = wr.getWID();
        
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        int rating = 30;
        c.postWineRating(22, WID, rating, subs);
        
        assertTrue(subs.get(0).getWines().get(0).getRating()==rating);
    }
    
    @Test
    public void test_club_add_admin(){
        Club c = new Club();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        
        assertTrue(admin.size()==0);
        
        PostNoteResponse pnr = c.addAdmin("Admin1",admin);
        assertTrue(admin.size()!=0);
        assertNotNull(pnr.getID());
    }
    
    @Test
    public void test_club_print_admin(){
        Club c = new Club();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        admin.add(new Admin());
        
        GetAdminResponse gar = c.printAdmin(admin);
        assertEquals(gar.getAdmins().get(0).getName(), "Default");
    }
    
    @Test
    public void test_club_update_admin(){
        Club c = new Club();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        Admin a = new Admin();
        admin.add(a);
        
        c.setAdmin("New Admin", a.getAID(), admin);
        assertEquals(admin.get(0).getName(),"New Admin");
    }
    
    @Test
    public void test_club_print_admin_AID(){
        Club c = new Club();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        Admin a = new Admin();
        admin.add(a);
        
        AdminDetailResponse adr = c.printAdminAID(a.getAID(),admin);
        assertEquals(adr.getID(),a.getAID());
        assertEquals(adr.getCreated_by(),a.getAID());
        assertEquals(adr.getName(),a.getName());
        assertEquals(adr.getCreate_date(), a.getDate());
    }
    
    @Test
    public void test_club_print_admin_revenue(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        ArrayList<Wine> wines = new ArrayList<Wine>();
        ArrayList<Receipt> receipts = new ArrayList<Receipt>();
        
        Subscriber s2 = new Subscriber();
        s2.setPreference(MonthlySelectionType.RW);
        MonthlySelection ms = new MonthlySelection(0, "2014-05", MonthlySelectionType.AR);
        
        Wine wr = new Wine();
        wr.addRating(30);
        wr.addRating(40);
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        ms.setMs(wines);
        s2.setID(22);
        s2.addShipment(ms);
        subs.add(s2);
        
        c.postReceipt(s2.getShipments().get(0).getSID(), "Rachel", 23, receipts, subs);
        
        RevenueResponse rr = c.adminRevenue(subs);
        assertEquals(rr.getDelivery_revenue(),8);
        assertEquals(rr.getUnits_delivered(),6);
        assertEquals(rr.getWine_revenue(),70);
        
    }
    
    @Test
    public void test_club_post_ms(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s = new Subscriber();
        subs.add(s);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        Admin a = new Admin();
        admin.add(a);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        
        PostMsResponse pmr = c.postMs("RW", "2015-10", wines,monthlySelection, admin, subs);
        assertTrue(pmr.getMID()!=-1);
        
        MonthlySelectionResponse msr = c.printMs(monthlySelection);
        assertEquals(msr.getMonthly_selection().get(0).getMst(), s.getPreference());
        
        MstDetailResponse mdr = c.printMsMID(monthlySelection.get(0).getID(),monthlySelection);
        assertEquals(mdr.getID(),monthlySelection.get(0).getID());
        assertEquals(mdr.getCreated_by(),a.getAID());
        assertEquals(mdr.getCreate_date(), monthlySelection.get(0).getDate());
        assertEquals(mdr.getYm(), monthlySelection.get(0).getYm());
    }
    
    @Test
    public void test_club_partner(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s = new Subscriber();
        subs.add(s);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        Admin a = new Admin();
        admin.add(a);
        
        Wine wr = new Wine();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        
        c.postMs("RW", "2015-7", wines,monthlySelection, admin, subs);
        
        ToDeliverResponse tdr = c.shipmentsToDeliver(subs);
        assertEquals(tdr.getDeliver_to().get(0).getName(), s.getName());
        
        ArrayList<Receipt> receipts = new ArrayList<Receipt>();
        
        PostNoteResponse pnr = c.postReceipt(subs.get(0).getShipments().get(0).getSID(), "Peter", 24, receipts,subs);
        assertTrue(pnr.getID()!=-1);
        
        GetReceiptsResponse grr = c.printReceipts(receipts);
        assertEquals(grr.getRr().get(0).getName(),s.getName());
        assertEquals(grr.getRr().get(0).getSubscriber(), s.getID());
        
        ReceiptDetailResponse rdr = c.printReceiptRID(receipts.get(0).getID(), receipts);
        assertEquals(rdr.getReceiver(), "Peter");
        assertEquals(rdr.getName(), s.getName());
        assertEquals(rdr.getSubscriber(), s.getID());
    }
    
    @Test
    public void test_club_print_w_WID(){
        Club c = new Club();
        ArrayList<Subscriber> subs = new ArrayList<Subscriber>();
        Subscriber s = new Subscriber();
        subs.add(s);
        ArrayList<Wine> wines = new ArrayList<Wine>();
        ArrayList<Admin> admin = new ArrayList<Admin>();
        ArrayList<MonthlySelection> monthlySelection = new ArrayList<MonthlySelection>();
        Admin a = new Admin();
        admin.add(a);
        
        Wine wr = new Wine();
        int WID = wr.getWID();
        Wine ww = new Wine(WineVariety.WHITE, WineType.SWEET, "hola", "Hello", "Ni hao", "Spain", "Sterling", 2000);
        for(int i=0;i<3;i++){wines.add(wr);}
        for(int i=0;i<3;i++){wines.add(ww);}
        
        c.postMs("RW", "2015-7", wines,monthlySelection, admin, subs);
        WineDetailResponse wdr = c.printAllWinesWID(WID, subs);
        assertEquals(wdr.getCountry(),wr.getCountry());
        assertEquals(wdr.getGrape(),wr.getGrape());
        assertEquals(wdr.getID(),WID);
        assertEquals(wdr.getLabel_name(),wr.getLabelName());
        assertEquals(wdr.getMaker(),wr.getMaker());
        assertTrue(wdr.getRating()==wr.getRating());
        assertEquals(wdr.getRatings_count(),wr.getNumberOfRatings());
        assertEquals(wdr.getRegion(),wr.getRegion());
        assertEquals(wdr.getVariety(),wr.getVariety());
        assertEquals(wdr.getWine_type(),wr.getType());
        assertEquals(wdr.getYear(),wr.getYear());
    }
    
}