package com.foodbuzz.service;

import com.foodbuzz.entity.Food;

public interface FoodService {
	Iterable<Food> findAllFood();
}
