package edu.iit.cs445.Response;

public class NoteResponse implements java.io.Serializable {

	private static final long serialVersionUID = -4096509257738777096L;
	private int ID;
	private String content;
	private String date;
		
	public NoteResponse(int ID,String date, String content) {
		this.setID(ID);
		this.setDate(date);
		this.setContent(content);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
