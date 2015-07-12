package edu.iit.cs445.Response;

public class NoteResponseSearch implements java.io.Serializable {

	private static final long serialVersionUID = -466785871260280787L;
	private int ID;
	private String content;
	
	public NoteResponseSearch(int ID, String content) {
		this.setID(ID);
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

}
