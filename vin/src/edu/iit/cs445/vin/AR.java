package edu.iit.cs445.vin;

public class AR extends MonthlySelection implements java.io.Serializable {

	private static final long serialVersionUID = -8233495499445980061L;

	public AR(int AID) {
		super();
		super.mst = MonthlySelectionType.AR;
		super.AID = AID;
	}
	
	public AR(int AID, String ym){
		super(ym);
		super.mst = MonthlySelectionType.AR;
		super.AID = AID;
	}
	
	@Override
	void addWine(Wine w) {
		// Make sure only red wines are added
		if(w.getVariety().equals(WineVariety.RED) && getMs().size()<6){
			getMs().add(w);
		}
	}

}
