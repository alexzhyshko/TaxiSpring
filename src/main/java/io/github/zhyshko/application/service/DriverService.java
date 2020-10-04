package io.github.zhyshko.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Driver;
import io.github.zhyshko.application.exception.DriverForCarNotFound;
import io.github.zhyshko.application.repository.DriverRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {

	private final DriverRepository driverReposirory;
	
	public Driver getDriverByCarDate(Car car, LocalDate date) {
		return this.driverReposirory.findByCarAndDayOfDriving(car, date).orElseThrow(()->new DriverForCarNotFound("Could not find a driver for the car for "+date));
	}
	
	public Driver getDriverByCarToday(Car car) {
		return getDriverByCarDate(car, LocalDate.now());
	}
	
}
