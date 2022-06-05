package org.axel584.tripmanager.trip;

import java.util.List;

import javax.validation.Valid;

import org.axel584.tripmanager.user.LoginRequest;
import org.axel584.tripmanager.user.User;
import org.axel584.tripmanager.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Trip Manager Api")
@RequestMapping("/v1/trip-manager")
public class TripController {
	
	Logger logger = LoggerFactory.getLogger(TripController.class);
	
	@Autowired
	private TripService tripService;
	
	// #3 : One endpoint to save a trip 
	@Operation(summary="Save a trip",description="Save a trip")
	@PostMapping(path="/user/{userId}/trip")
	public Trip createTrip(@PathVariable String userId,@Valid @RequestBody TripRequest trip) {
		return tripService.createTrip(userId,trip);
	}
	
	// #4 : One endpoint to get all trips for a user.
	@Operation(summary="Get all trip from a user",description="Get all trip from a user")
	@GetMapping(path="/user/{userId}/trip")
	public List<Trip> getAllTripsByUser(@PathVariable String userId) {
		logger.trace(userId);
		return tripService.getAllTripsByUser(userId);
	}
	
	// #5 : One endpoint to update departure and arrival city of an existing trip 
	@Operation(summary="Update the cities of a trip",description="Update the cities of a trip")
	@PutMapping(path="/user/{userId}/trip")
	public Trip getUpdateTrip(@PathVariable String userId,@Valid @RequestBody UpdateCityRequest updateCityRequest) {
		logger.trace(updateCityRequest.getTripId());
		logger.trace(updateCityRequest.getDepartureCity());
		logger.trace(updateCityRequest.getArrivalCity());
		
		return tripService.updateCity(updateCityRequest);
	}


}
