package edu.iit.cs445.vin;

public class AW extends MonthlySelection implements java.io.Serializable {

	private static final long serialVersionUID = 7120772128100885557L;

	public AW(int AID) {
		super();
		super.mst = MonthlySelectionType.AW;
		super.AID = AID;
	}
	
	public AW(int AID, String ym){
		super(ym);
		super.mst = MonthlySelectionType.AW;
		super.AID = AID;
	}
	
	@Override
	void addWine(Wine w) {
		// Make sure only white wines are added
		if(w.getVariety().equals(WineVariety.WHITE) && getMs().size()<6){
			getMs().add(w);
		}

	}

}
