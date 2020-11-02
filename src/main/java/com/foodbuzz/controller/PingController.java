package com.foodbuzz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping(path = "/ping")
	public String ping() {
		return "Pong !!!";
	}
}
