package io.github.zhyshko.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CarController {

	
	private final CarService carService;
	
	@GetMapping("car/getAll")
	public ResponseEntity<List<Car>> getAllAvailableCars(@RequestHeader(required=true) String userLocale) {
		List<Car> cars = this.carService.getAllAvailableCarsLocalized(userLocale);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
}
