package com.thecodeart.service;

import java.util.List;
import java.util.Optional;

import com.thecodeart.model.Restaurant;

public interface IRestaurantService {

	List<Restaurant> findAll();

	Optional<Restaurant> findById(Long id);

	Restaurant save(Restaurant restaurant);

	Restaurant update(Long id, Restaurant restaurant);

	Restaurant delete(Long id);
}