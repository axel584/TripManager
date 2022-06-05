package org.axel584.tripmanager.trip;

import java.util.List;

import javax.validation.Valid;

public interface TripService {

	Trip createTrip(String userId, TripRequest tripRequest);

	List<Trip> getAllTripsByUser(String userId);

	Trip updateCity(UpdateCityRequest updateCityRequest);

	Integer computeDuration(List<Integer> time);
	Double computeAccelerationMax(List<Double> speed);
	Double computeDistanceFromBeginToArrival(List<Double> latitude,List<Double> longitude);
	Double computeMeanSpeed(List<Double> speeds);

}
