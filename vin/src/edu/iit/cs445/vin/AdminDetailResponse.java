package edu.iit.cs445.vin;

public class AdminDetailResponse implements java.io.Serializable {

	private static final long serialVersionUID = -1274647469062672531L;
	private int ID;
	private String name;
	private String create_date;
	private int created_by;
	
	public AdminDetailResponse(int ID, String name, String create_date, int created_by){
		this.setID(ID);
		this.setName(name);
		this.setCreate_date(create_date);
		this.setCreated_by(created_by);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
