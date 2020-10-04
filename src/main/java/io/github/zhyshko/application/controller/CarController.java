package io.github.zhyshko.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

	@GetMapping("car/getAll")
	public void getAllCars() {
		
	}
	
}
