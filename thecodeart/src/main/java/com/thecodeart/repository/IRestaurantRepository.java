package com.thecodeart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thecodeart.model.Restaurant;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long>{

}
