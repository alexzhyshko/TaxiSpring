package io.github.zhyshko.application.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Driver;
import io.github.zhyshko.application.exception.DriverForCarNotFoundException;
import io.github.zhyshko.application.repository.DrivingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverService {

	private final DrivingRepository drivingReposirory;
	
	public Driver getDriverByCarDate(Car car, LocalDate date) {
		return this.drivingReposirory.findByCarAndDayOfDriving(car, date).orElseThrow(()->new DriverForCarNotFoundException("Could not find a driver for the car for "+date)).getDriver();
	}
	
	public Driver getDriverByCarToday(Car car) {
		return getDriverByCarDate(car, LocalDate.now());
	}
	
}
