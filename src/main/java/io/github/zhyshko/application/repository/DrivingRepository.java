package io.github.zhyshko.application.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.Driving;

@Repository
public interface DrivingRepository extends CrudRepository<Driving, Integer>{

	@Query("select d from Driving d inner join d.car where d.car = :car and d.dayOfDriving= :date")
	public Optional<Driving> findByCarAndDayOfDriving(Car car, LocalDate date);

}
