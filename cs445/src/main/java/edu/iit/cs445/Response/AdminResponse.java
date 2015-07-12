package edu.iit.cs445.Response;

public class AdminResponse implements java.io.Serializable  {
	
	private static final long serialVersionUID = 3725690081941755531L;
	private int ID;
	private String name;
	private String create_date;
	private int created_by;
	
	public AdminResponse(int ID, String name, String date) {
		this.setID(ID);
		this.setName(name);
		this.create_date = date;
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
