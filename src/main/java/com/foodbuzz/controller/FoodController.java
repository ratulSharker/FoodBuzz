package com.foodbuzz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbuzz.entity.Food;
import com.foodbuzz.service.FoodService;

@RestController
public class FoodController {

	@Autowired
	private FoodService foodService;

	@GetMapping(path = "/foods")
	public Iterable<Food> getAllFood() {
		return this.foodService.findAllFood();
	}
}
