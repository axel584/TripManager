package org.axel584.tripmanager.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.axel584.tripmanager.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TripServiceTest {
	
	Logger logger = LoggerFactory.getLogger(TripServiceTest.class);
	
	@Autowired
	private TripService tripService;
	
	@Test
	public void maxAccelerationTest() {
		// given
		List<Double> speed = new ArrayList<Double>();
		speed.add(10.0);
		speed.add(20.0);
		speed.add(5.0);
		speed.add(8.0);
		// when
		Double maxSpeed = tripService.computeAccelerationMax(speed);
		
		//then
		assertEquals(2.0,maxSpeed);
	}
	
	@Test
	public void meanSpeedTest() {
		// given
		List<Double> speed = new ArrayList<Double>();
		speed.add(10.0);
		speed.add(20.0);
		speed.add(5.0);
		speed.add(9.0);
		// when
		Double meanSpeed = tripService.computeMeanSpeed(speed);
		
		//then
		assertEquals(11.0,meanSpeed);
	}
	
	@Test
	public void durationTest() {
		// given
		List<Integer> time = new ArrayList<Integer>();
		time.add(1);
		time.add(2);
		time.add(3);
		time.add(4);
		// when
		Integer duration = tripService.computeDuration(time);
		
		//then
		assertEquals(4,duration);
	}
	
	
	

}
