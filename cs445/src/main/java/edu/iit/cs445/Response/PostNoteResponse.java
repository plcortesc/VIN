package edu.iit.cs445.Response;

public class PostNoteResponse implements java.io.Serializable {

	private static final long serialVersionUID = -641256446848551715L;
	private int ID;
	
	public PostNoteResponse(int ID) {
		this.setID(ID);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
