package edu.iit.cs445.Response;

import java.util.ArrayList;

public class GetReceiptsResponse implements java.io.Serializable {

	private static final long serialVersionUID = 1114716210496372454L;
	private ArrayList<ReceiptResponse> rr;
	public GetReceiptsResponse(ArrayList<ReceiptResponse> rr) {
		this.setRr(rr);
	}
	public ArrayList<ReceiptResponse> getRr() {
		return rr;
	}
	public void setRr(ArrayList<ReceiptResponse> rr) {
		this.rr = rr;
	}

}
