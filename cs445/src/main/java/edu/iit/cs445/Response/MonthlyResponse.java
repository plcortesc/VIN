package edu.iit.cs445.Response;


import edu.iit.cs445.Management.MonthlySelectionType;

public class MonthlyResponse implements java.io.Serializable {

	private static final long serialVersionUID = -2637171385387587792L;
	private int ID;
	private String ym;
	private MonthlySelectionType mst;
	
	public MonthlyResponse(int ID, String ym, MonthlySelectionType mst){
		this.setID(ID);
		this.setYm(ym);
		this.setMst(mst);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public MonthlySelectionType getMst() {
		return mst;
	}

	public void setMst(MonthlySelectionType mst) {
		this.mst = mst;
	}
}
