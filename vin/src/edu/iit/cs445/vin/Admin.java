package edu.iit.cs445.vin;

import java.util.ArrayList;
import java.util.Calendar;

public class Admin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9145280382620746049L;
	private int AID;
	private String name;
	private String date;
	private MonthlySelection msAR;
	private MonthlySelection msAW;
	private MonthlySelection msRW;	

	public Admin(){
		this.setDate(Calendar.getInstance());
    	this.AID = IdGenerator.newID();
		this.name = "Default";
	}

	public Admin(String name){
    	this.setDate(Calendar.getInstance());
    	this.AID = IdGenerator.newID();
		this.name = name;
	}
	
	public int getAID(){
		return this.AID;
	}
	public void setAID(int ID){
		this.AID = ID;
	}
	public String getDate(){
		return this.date;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setDate(Calendar date) {
		this.date = Integer.toString(date.get(Calendar.YEAR))+"-"+
		Integer.toString(date.get(Calendar.MONTH)+1)+"-"+
		Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}
	
	public void setD(String date) {
		this.date = date;
	}
	
	public int createMs(String mst,String ym, ArrayList<Wine> wines, ArrayList<MonthlySelection> monthlySelection, ArrayList<Subscriber> subs){		
		// Tener en cuenta que se puede modificar
		
		MonthlySelection ms = null;
		boolean ms_ok = false;
		
		if(mst.equals("AR")) {
			msAR = new MonthlySelection(this.AID, ym, MonthlySelectionType.AR); 
			ms = msAR;
			for(Wine w:wines){
				if(w.getVariety()==WineVariety.RED||w.getVariety()==WineVariety.ROSE){
					ms.getMs().add(w);
				}
			}
			
		}else if(mst.equals("AW")) {
			msAW = new MonthlySelection(this.AID, ym, MonthlySelectionType.AW); 
			ms = msAW;
			for(Wine w:wines){
				if(w.getVariety()==WineVariety.WHITE){
					ms.getMs().add(w);
				}
			}
		}else if(mst.equals("RW")) {
			msRW = new MonthlySelection(this.AID, ym, MonthlySelectionType.RW); 
			ms = msRW;
			for(Wine w:wines){
				ms.getMs().add(w);
			}
		}
		
		if(ms.getMs().size()==6 && (mst.equals("AR")||mst.equals("AW"))){
			boolean repeated=false;
			for(MonthlySelection m_s:monthlySelection){
				if(m_s.getYm().equals(ym)) repeated=true; 
			}
			if(repeated==false) {
				monthlySelection.add(ms);
				for(Subscriber s: subs){
					if(s.getPreference()==MonthlySelectionType.AR) {
						s.addShipment(ms);
						ms_ok = true;
						//return ms.getID();
					}
				}
			}else System.out.println("There is already a Monthly Selection with this date");
		}if(ms.getMs().size()==6 && mst.equals("AW")){
			boolean repeated=false;
			for(MonthlySelection m_s:monthlySelection){
				if(m_s.getYm().equals(ym)) repeated=true; 
			}
			if(repeated==false) {
				monthlySelection.add(ms);
				for(Subscriber s: subs){
					if(s.getPreference()==MonthlySelectionType.AW){
						s.addShipment(ms);
						ms_ok = true;
						//return ms.getID();
					}
				}
			}else System.out.println("There is already a Monthly Selection with this date");
		}else if(ms.getMs().size()==6 && mst.equals("RW")){
			int wr=0,ww=0;
			for(Wine w: ms.getMs()){
				if(w.getVariety()==WineVariety.RED || w.getVariety()==WineVariety.ROSE) wr++;
				else if(w.getVariety()==WineVariety.WHITE) ww++;
			}
			if(wr==3 && ww==3) {

				boolean repeated=false;
				for(MonthlySelection m_s:monthlySelection){
					if(m_s.getYm().equals(ym)) repeated=true; 
				}
				if(repeated==false) {
					monthlySelection.add(ms);
					for(Subscriber s: subs){
						if(s.getPreference()==MonthlySelectionType.RW) {
							s.addShipment(ms);
							ms_ok = true;
							//return ms.getID();
						}
					}
				}else System.out.println("There is already a Monthly Selection with this date");
			}
			
		}if(ms_ok) return ms.getID();
		else return -1;
	}
	
	public void modifyNextMs(String mst, int UID, ArrayList<Subscriber> subs){
		for(Subscriber s: subs){
			if(s.getID()==UID){
				for(Shipment sh: s.getShipments()){
					if(sh.getStatus()==ShipmentStatus.PENDING){
						s.getShipments().remove(sh);
						if(mst.equals("AR")) s.addShipment(msAR);						
						else if(mst.equals("AW")) s.addShipment(msAW);
						else if(mst.equals("RW")) s.addShipment(msRW);
					}
				}
			}
		}
	}
	
	public void modifyMs(String mst, int UID, ArrayList<Subscriber> subs){
		for(Subscriber s: subs){
			if(s.getID()==UID){
				for(Shipment sh: s.getShipments()){
					if(sh.getStatus()==ShipmentStatus.PENDING){
						s.getShipments().remove(sh);
						if(mst.equals("AR")) {
							s.addShipment(msAR); 
							s.setMst(MonthlySelectionType.AR);						
						}
						else if(mst.equals("AW")) {
							s.addShipment(msAW);
							s.setMst(MonthlySelectionType.AW);						
						}
						else if(mst.equals("RW")) {
							s.addShipment(msRW);
							s.setMst(MonthlySelectionType.RW);						
						}
					}
				}
			}
		}
	}
}
