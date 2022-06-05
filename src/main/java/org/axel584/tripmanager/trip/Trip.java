package org.axel584.tripmanager.trip;

import java.util.Date;

import org.axel584.tripmanager.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Trip")
public class Trip {
	
	@Id
	private String _id;
	
	private Date startTripDate;
	
	private Double distance;
	
	private Integer duration;
	
	private Double meanSpeed;
	
	private Double accelerationMax;
	
	private String departureCity;
	
	private String arrivalCity;

	private User user;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Date getStartTripDate() {
		return startTripDate;
	}

	public void setStartTripDate(Date startTripDate) {
		this.startTripDate = startTripDate;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Double getMeanSpeed() {
		return meanSpeed;
	}

	public void setMeanSpeed(Double meanSpeed) {
		this.meanSpeed = meanSpeed;
	}

	public Double getAccelerationMax() {
		return accelerationMax;
	}

	public void setAccelerationMax(Double accelerationMax) {
		this.accelerationMax = accelerationMax;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}
