package edu.iit.cs445.vin;

import java.util.ArrayList;

public class ToDeliverResponse implements java.io.Serializable {

	private static final long serialVersionUID = -8250290217251638114L;
	private ArrayList<DeliverTo> dt;
	public ToDeliverResponse(ArrayList<DeliverTo> dt) {
		this.setDt(dt);
	}
	public ArrayList<DeliverTo> getDt() {
		return dt;
	}
	public void setDt(ArrayList<DeliverTo> dt) {
		this.dt = dt;
	}

}
