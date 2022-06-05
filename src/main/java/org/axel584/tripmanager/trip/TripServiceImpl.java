package org.axel584.tripmanager.trip;

import java.util.List;
import java.util.Optional;

import org.axel584.tripmanager.user.User;
import org.axel584.tripmanager.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

	Logger logger = LoggerFactory.getLogger(TripServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TripRepository tripRepository;
	
	@Override
	public Trip createTrip(String userId,TripRequest tripRequest) {
		Trip trip = new Trip();
		logger.trace("departure City : {}",tripRequest.getDepartureCity());
		trip.setDepartureCity(tripRequest.getDepartureCity());
		trip.setArrivalCity(tripRequest.getArrivalCity());
		trip.setStartTripDate(tripRequest.getStartTripDate());
		if (tripRequest.getTripData()!=null) {
			trip.setDuration(computeDuration(tripRequest.getTripData().getTime()));
			trip.setAccelerationMax(computeAccelerationMax(tripRequest.getTripData().getSpeed()));
			trip.setMeanSpeed(computeMeanSpeed(tripRequest.getTripData().getSpeed()));
			trip.setDistance(computeDistanceFromBeginToArrival(tripRequest.getTripData().getLatitude(),tripRequest.getTripData().getLongitude()));
		}
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			trip.setUser(user.get());
		}
		return tripRepository.save(trip);
	}

	public List<Trip> getAllTripsByUser(String userId) {
		return tripRepository.findByUser(userId);
	}
	
	@Override
	public Trip updateCity(UpdateCityRequest updateCityRequest) {
		Optional<Trip> trip = tripRepository.findById(updateCityRequest.getTripId());
		if (trip.isPresent()) {
			logger.trace("trouve : {}",trip.get().getStartTripDate());
			trip.get().setDepartureCity(updateCityRequest.getDepartureCity());
			trip.get().setArrivalCity(updateCityRequest.getArrivalCity());
			return tripRepository.save(trip.get());
		} else {
			logger.error("trip introuvable : {}",updateCityRequest.getTripId());
			return null;
		}
	}
	
	public Integer computeDuration(List<Integer> time) {
		Integer duration = time.size();
		logger.trace("duration : {}",duration);
		return duration;
	}
	
	public Double computeAccelerationMax(List<Double> speed) {
		// avec un seul point, on ne peut pas calculer d'accélération
		if (speed==null || speed.size()<=1) {
			return 0.0;
		}
		// l'accélération correspond à la valeur de la vitesse divisée par la valeur précédente
		Double maxSpeed=0.0;
		for(int i=1;i<speed.size();i++) {
			if (speed.get(i)/speed.get(i-1)>maxSpeed) {
				maxSpeed = speed.get(i)/speed.get(i-1);
			}
		}
		logger.trace("vitesse max : {}",maxSpeed);
		return maxSpeed;
	}
	
	public Double computeDistanceFromBeginToArrival(List<Double> latitude,List<Double> longitude) {
		if (latitude==null || longitude==null) {
			return null;
		}
		if (latitude.size()<=1 || longitude.size()<=1) {
			return 0.0;
		}
		// si les tableaux n'ont pas la même taille, on retourne null (on lève une exception ?)
		if (latitude.size()!=longitude.size()) {
			logger.error("taille des latitudes et longitudes différents");
			return null;
		}
		return computeDistance(latitude.get(0), latitude.get(latitude.size()-1), longitude.get(0), longitude.get(longitude.size()-1));
	}
	
	public Double computeMeanSpeed(List<Double> speeds) {
		Double sum=0.0;
		for(Double speed:speeds) {
	        sum += speed;
		}
		logger.trace("somme des vitesses : {}",sum);
		return sum/speeds.size();
	    
	}
	
	// honteusement repompé sur : https://prograide.com/pregunta/75209/calculer-la-distance-entre-deux-points-en-utilisant-la-latitude-et-la-longitude
	private Double computeDistance(Double lat1,Double lat2,Double lon1,Double lon2) {
		final int R = 6371; // Radius of the earth 
		double latDistance = Math.toRadians(lat2 - lat1); 
		double lonDistance = Math.toRadians(lon2 - lon1); 
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2); 
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); 
		double distance = R * c * 1000; // convert to meters double
		distance = Math.pow(distance, 2); 
		logger.debug("distance (au carré) : {}",distance);
		return Math.sqrt(distance);
		
		
	}


	
	

}
