package com.thecodeart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thecodeart.model.Restaurant;
import com.thecodeart.repository.IRestaurantRepository;


@Service
public class RestaurantService implements IRestaurantService {

	@Autowired
	private IRestaurantRepository repository;

	public List<Restaurant> findAll() {
		return repository.findAll();
	}

	public Optional<Restaurant> findById(Long id) {
		return repository.findById(id);
	}

	public Restaurant save(Restaurant Restaurant) {
		return repository.save(Restaurant);
	}

	public Restaurant delete(Long id) {
		Restaurant Restaurant = repository.findById(id).get();
		repository.deleteById(id);
		return Restaurant;
	}

	public Restaurant update(Long id, Restaurant Restaurant) {
		Restaurant db = repository.findById(id).get();
		db.setName(Restaurant.getName());
		db.setLocation(Restaurant.getLocation());
		return repository.save(db);
	}
}
