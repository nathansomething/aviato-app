package com.aviato.pojos;
import java.io.Serializable;
import java.util.Date;

public class FlightData implements Serializable {

	private static final long serialVersionUID = 1L;
	private String startingLocation;
	private String destination;
	private Date travelDate;
	private Float price;
	
	public String getStartingLocation() {
		return startingLocation;
	}
	public void setStartingLocation(String startingLocation) {
		this.startingLocation = startingLocation;
	}
	
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
}
