package org.axel584.tripmanager.trip;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TripRepository extends MongoRepository<Trip,String>{
	
	@Query("{'user._id':?0}")
	public List<Trip> findByUser(String userId);

}
