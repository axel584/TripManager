package org.axel584.tripmanager.trip;

import java.util.Date;

public class TripRequest {

	private Date startTripDate;
	
	private String departureCity;
	
	private String arrivalCity;
	
	private TripDataRequest tripData;

	public Date getStartTripDate() {
		return startTripDate;
	}

	public void setStartTripDate(Date startTripDate) {
		this.startTripDate = startTripDate;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	public TripDataRequest getTripData() {
		return tripData;
	}

	public void setTripData(TripDataRequest tripData) {
		this.tripData = tripData;
	}
	
	
	
}
