package edu.iit.cs445.vin;

public class RW extends MonthlySelection implements java.io.Serializable {

	private static final long serialVersionUID = -6585063575851104799L;

	public RW(int AID) {
		super();
		super.mst = MonthlySelectionType.RW;
		super.AID = AID;
	}
	public RW(int AID, String ym){
		super(ym);
		super.mst = MonthlySelectionType.AW;
		super.AID = AID;
	}
	
	@Override
	void addWine(Wine w) {
		// Make sure 3 white wines and 3 red wines (red and rose) per box
		int reds=0, whites=0;
		for(Wine rw: getMs()){
			if(rw.getVariety().equals(WineVariety.WHITE)){
				whites++;
			}else{reds++;}
		}
		if(getMs().size()<6){
			if(w.getVariety().equals(WineVariety.WHITE) && whites<3){
				getMs().add(w);
			}else System.out.println("Only red or rose wine available");
			if((w.getVariety().equals(WineVariety.RED)||
					  w.getVariety().equals(WineVariety.ROSE)) && reds<3){
				getMs().add(w);
			}else System.out.println("Only white wine available");
		}else System.out.println("Full box, create a new one");
	}

}
