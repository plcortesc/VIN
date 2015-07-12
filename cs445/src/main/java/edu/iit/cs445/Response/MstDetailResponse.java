package edu.iit.cs445.Response;

import java.util.ArrayList;

import edu.iit.cs445.Management.MonthlySelectionType;

public class MstDetailResponse implements java.io.Serializable {

	private static final long serialVersionUID = -7733501797015474691L;
	private int ID;
	private MonthlySelectionType mst;
	private String ym;
	private String create_date;
	private int created_by;
	private ArrayList<WineResponse> wines;
	
	public MstDetailResponse(int ID, MonthlySelectionType mst, String ym, String create_date, int created_by, ArrayList<WineResponse> wines){
		this.setMst(mst);
		this.setYm(ym);
		this.setWines(wines);
		this.ID = ID;
		this.created_by = created_by;
		this.create_date = create_date;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public MonthlySelectionType getMst() {
		return mst;
	}

	public void setMst(MonthlySelectionType mst) {
		this.mst = mst;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public ArrayList<WineResponse> getWines() {
		return wines;
	}

	public void setWines(ArrayList<WineResponse> wines) {
		this.wines = wines;
	}

}
