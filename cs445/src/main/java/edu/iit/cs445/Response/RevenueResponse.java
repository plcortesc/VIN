package edu.iit.cs445.Response;

public class RevenueResponse implements java.io.Serializable {

	private static final long serialVersionUID = -6765670523876928919L;
	private int units_delivered;
	private int wine_revenue;
	private int delivery_revenue;
	
	public RevenueResponse(int units_delivered, int wine_revenue, int delivery_revenue) {
		this.setUnits_delivered(units_delivered);
		this.setWine_revenue(wine_revenue);
		this.setDelivery_revenue(delivery_revenue);
	}

	public int getUnits_delivered() {
		return units_delivered;
	}

	public void setUnits_delivered(int units_delivered) {
		this.units_delivered = units_delivered;
	}

	public int getWine_revenue() {
		return wine_revenue;
	}

	public void setWine_revenue(int wine_revenue) {
		this.wine_revenue = wine_revenue;
	}

	public int getDelivery_revenue() {
		return delivery_revenue;
	}

	public void setDelivery_revenue(int delivery_revenue) {
		this.delivery_revenue = delivery_revenue;
	}

}
