package edu.iit.cs445.Response;

import java.util.ArrayList;

import edu.iit.cs445.Management.DeliverTo;

public class ToDeliverResponse implements java.io.Serializable {

	private static final long serialVersionUID = -8250290217251638114L;
	private ArrayList<DeliverTo> deliver_to;
	public ToDeliverResponse(ArrayList<DeliverTo> dt) {
		this.setDeliver_to(dt);
	}
	public ArrayList<DeliverTo> getDeliver_to() {
		return deliver_to;
	}
	public void setDeliver_to(ArrayList<DeliverTo> dt) {
		this.deliver_to = dt;
	}

}
