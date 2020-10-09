package io.github.zhyshko.application.repository;

import java.time.LocalDate;
import java.util.List;
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
	
	
	@Query("select driving "
			+ "from Driving driving "
			+ "inner join driving.car car "
			+ "inner join car.category cat "
			+ "inner join cat.translation cattransl "
			+ "inner join car.status carstatus "
			+ "where car.passengerCount= :passengerCount "
			+ "and carstatus.id= :status "
			+ "and (cattransl.text_EN= :category or cattransl.text_RU= :category or cattransl.text_UA= :category) "
			+ "and driving.dayOfDriving = :dayOfDriving")
	public Optional<List<Driving>> findAllCarsByPassengerCountAndCategoryAndStatusAndDayOfDriving(int passengerCount, String category, int status, LocalDate dayOfDriving);
	
	@Query("select driving "
			+ "from Driving driving "
			+ "inner join driving.car car "
			+ "inner join car.status carstatus "
			+ "where carstatus.id= :status "
			+ "and driving.dayOfDriving = :dayOfDriving")
	public Optional<List<Driving>> findAllCarsByStatusAndDayOfDriving(int status, LocalDate dayOfDriving);

}
