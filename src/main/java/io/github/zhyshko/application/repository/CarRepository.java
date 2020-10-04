package io.github.zhyshko.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.github.zhyshko.application.entity.Car;

@Repository
public interface CarRepository extends CrudRepository<Car, Integer>{

	public Optional<List<Car>> findAllByStatus(int status);
	
	public Optional<List<Car>> findAllByPassengerCountAndCategoryAndStatus(int passengerCount, String category, int status);
	
	public Optional<Car> findByOrderId(int orderId);
	
	
}
