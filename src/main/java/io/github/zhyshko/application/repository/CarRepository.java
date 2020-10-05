package io.github.zhyshko.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.CarStatus;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer>{

	public Optional<List<Car>> findAllByStatusId(int status);
	
	@Query("select car "
			+ "from Car car "
			+ "inner join car.category cat "
			+ "join cat.translation cattransl inner "
			+ "join car.status carstatus "
			+ "where car.passengerCount= :passengerCount "
			+ "and carstatus.id= :status "
			+ "and (cattransl.text_EN= :category or cattransl.text_RU= :category or cattransl.text_UA= :category)")
	public Optional<List<Car>> findAllByPassengerCountAndCategoryAndStatus(int passengerCount, String category, int status);
	
	@Query("select car_status from CarStatus car_status where car_status.id= :statusId")
	public CarStatus findCarStatusById(int statusId);
	
}
