package io.github.zhyshko.application.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Driver;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Integer>{

	@Query("select d.driver from driving d inner join d.car where d.car =: car and d.dayOfDriving=: date")
	public Optional<Driver> findByCarAndDayOfDriving(Car car, LocalDate date);
	
}
