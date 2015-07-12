package edu.iit.cs445.Response;

public class NoteIDResponse implements java.io.Serializable {

	private static final long serialVersionUID = -2522906506558981436L;
	private String date;
	private String content;
	public NoteIDResponse(String date, String content) {
		this.setDate(date);
		this.setContent(content);
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	

}
