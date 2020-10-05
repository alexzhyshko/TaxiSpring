package io.github.zhyshko.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.github.zhyshko.application.converter.entity2dto.CarEntityToDTOConverter;
import io.github.zhyshko.application.dto.Car;
import io.github.zhyshko.application.exception.NoCarsFoundException;
import io.github.zhyshko.application.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CarController {

	private final CarService carService;
	
	@GetMapping("/car/getAll")
	public ResponseEntity<List<Car>> getAllAvailableCars(@RequestHeader(required=true, name="User_Locale") String userLocale) {
		List<Car> cars = CarEntityToDTOConverter.convertToDto(this.carService.getAllAvailableCars(), userLocale);
		return new ResponseEntity<>(cars, HttpStatus.OK);
	}
	
	
	@ExceptionHandler({ NoCarsFoundException.class})
    public ResponseEntity<String> handleException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
	
}
