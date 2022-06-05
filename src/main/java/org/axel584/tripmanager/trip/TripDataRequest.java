package org.axel584.tripmanager.trip;

import java.util.List;

public class TripDataRequest {
	
	private List<Integer> time;
	
	private List<Double> speed;
	
	private List<Double> latitude;
	
	private List<Double> longitude;

	public List<Integer> getTime() {
		return time;
	}

	public void setTime(List<Integer> time) {
		this.time = time;
	}

	public List<Double> getSpeed() {
		return speed;
	}

	public void setSpeed(List<Double> speed) {
		this.speed = speed;
	}

	public List<Double> getLatitude() {
		return latitude;
	}

	public void setLatitude(List<Double> latitude) {
		this.latitude = latitude;
	}

	public List<Double> getLongitude() {
		return longitude;
	}

	public void setLongitude(List<Double> longitude) {
		this.longitude = longitude;
	}
	
	

}
