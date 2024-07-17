package com.thecodeart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.thecodeart.model.Restaurant;
import com.thecodeart.service.ApiKeyUtil;
import com.thecodeart.service.IRestaurantService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestaurantController {

	@Autowired
	private IRestaurantService service;

	private ApiKeyUtil apiKeyUtil = new ApiKeyUtil();

	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(@RequestHeader("X-API-Key") String apiKey) {
		if (!apiKeyUtil.isValidApiKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key. Access denied.");
		}
		try {
			List<Restaurant> restaurants = service.findAll();
			return ResponseEntity.ok(restaurants);
		} catch (Exception e) {
			String msg = "An error occurred while trying to get all restaurant data.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id, @RequestHeader("X-API-Key") String apiKey) {
		if (!apiKeyUtil.isValidApiKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key. Access denied.");
		}
		try {
			Optional<Restaurant> restaurant = service.findById(id);
			if (restaurant.isPresent()) {
				return ResponseEntity.ok(restaurant.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found.");
			}
		} catch (Exception e) {
			String msg = "An error occurred while trying to get restaurant data.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}

	@PostMapping("/post")
	public ResponseEntity<?> save(@RequestBody Restaurant restaurant, @RequestHeader("X-API-Key") String apiKey) {
		if (!apiKeyUtil.isValidApiKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key. Access denied.");
		}
		try {
			Restaurant savedRestaurant = service.save(restaurant);
			return ResponseEntity.ok(savedRestaurant);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("An error occurred while trying to save restaurant data.");
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id, @RequestHeader("X-API-Key") String apiKey) {
		if (!apiKeyUtil.isValidApiKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key. Access denied.");
		}
		try {
			Restaurant deletedRestaurant = service.delete(id);
			return ResponseEntity.ok(deletedRestaurant);
		} catch (Exception e) {
			String msg = "An error occurred while trying to delete restaurant data.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Restaurant restaurant,
			@RequestHeader("X-API-Key") String apiKey) {
		if (!apiKeyUtil.isValidApiKey(apiKey)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid API key. Access denied.");
		}
		try {
			Restaurant updatedRestaurant = service.update(id, restaurant);
			return ResponseEntity.ok(updatedRestaurant);
		} catch (Exception e) {
			String msg = "An error occurred while trying to update restaurant data.";
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
		}
	}

}
