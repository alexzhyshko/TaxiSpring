package io.github.zhyshko.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.repository.CarRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

	private final CarRepository carRepository;
	
	public List<Car> getAllAvailableCarsLocalized(String userLocale){
		
	}
	
	public Car getNearestCarByPlacesCountAndCategory(int placesCount, String category, String userLocale) {
		
	}
	
	public Car getCarByOrderId(int orderid, String userLocale) {
		
	}
	
}
