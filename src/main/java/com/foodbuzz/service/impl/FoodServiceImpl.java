package com.foodbuzz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbuzz.entity.Food;
import com.foodbuzz.repository.FoodRepository;
import com.foodbuzz.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Override
	public Iterable<Food> findAllFood() {
		return this.foodRepository.findAll();
	}

}
