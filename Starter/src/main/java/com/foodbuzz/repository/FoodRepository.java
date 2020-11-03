package com.foodbuzz.repository;

import org.springframework.data.repository.CrudRepository;

import com.foodbuzz.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {

}
