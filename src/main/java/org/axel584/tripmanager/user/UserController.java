package org.axel584.tripmanager.user;

import java.util.List;

import javax.validation.Valid;

import org.axel584.tripmanager.trip.Trip;
import org.axel584.tripmanager.trip.TripController;
import org.axel584.tripmanager.trip.TripRequest;
import org.axel584.tripmanager.trip.TripService;
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
public class UserController {
		
		Logger logger = LoggerFactory.getLogger(UserController.class);
		
		
		@Autowired
		private UserServiceImpl userService;
		
		// #1 : One endpoint to create a new user
		@Operation(summary="Create New user",description="Create a new user")
		@PostMapping(path="/user")
		@ResponseStatus(HttpStatus.CREATED)
		public User createUser(@Valid @RequestBody User user) {
			return userService.createUser(user);
		}


}
