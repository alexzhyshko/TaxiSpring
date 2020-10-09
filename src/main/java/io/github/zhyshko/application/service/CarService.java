package io.github.zhyshko.application.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.zhyshko.application.entity.Car;
import io.github.zhyshko.application.entity.CarStatus;
import io.github.zhyshko.application.entity.Coordinates;
import io.github.zhyshko.application.entity.Driving;
import io.github.zhyshko.application.exception.NearestCarTooFarException;
import io.github.zhyshko.application.exception.NoCarsFoundException;
import io.github.zhyshko.application.exception.NoCarsNearFoundException;
import io.github.zhyshko.application.repository.CarRepository;
import io.github.zhyshko.application.repository.DrivingRepository;
import io.github.zhyshko.application.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

	private static final int CAR_SEARCH_RADIUS_KM = 100;
	
	private final CarRepository carRepository;
	private final DrivingRepository drivingRepository;
	private final OrderRepository orderRepository;
	
	private List<Car> getAllAvailableCars(){
		return this.drivingRepository.findAllCarsByStatusAndDayOfDriving(1, LocalDate.now()).orElseThrow(()->new NoCarsFoundException("No active cars found")).stream()
				.map(Driving::getCar)
				.collect(Collectors.toList());
	}
	
	public List<Car> getAllAvailableCarsToday(){
		return getAllAvailableCars();
	}
	
	public void setCarBusy(Car car) {
		setCarStatus(car, 2);
	}
	
	public void setCarFree(Car car) {
		setCarStatus(car, 1);
	}
	
	public void setCarStatus(Car car, int statusId) {
		CarStatus status = findCarStatusById(statusId);
		car.setStatus(status);
		this.carRepository.save(car);
	}
	
	public CarStatus findCarStatusById(int statusId) {
		return this.carRepository.findCarStatusById(statusId);
	}
	
	private List<Car> getAllCarsByPassengerCountAndCategoryAndStatusAndDayOfDriving(int passengerCount, String category, int status, LocalDate dayOfDriving){
		return this.drivingRepository.findAllCarsByPassengerCountAndCategoryAndStatusAndDayOfDriving(passengerCount, category, status, dayOfDriving).orElseThrow(()->new NoCarsFoundException("No active cars found"))
				.stream()
				.map(Driving::getCar)
				.collect(Collectors.toList());
	}
	
	public Car getNearestCarByPlacesCountAndCategory(Coordinates customerCoordinates, int passengerCount, String category) {
		List<Car> allCarsByCategoryAndPlacesCountAndDate = getAllCarsByPassengerCountAndCategoryAndStatusAndDayOfDriving(passengerCount, category, 1, LocalDate.now());
		Car nearestCar =  allCarsByCategoryAndPlacesCountAndDate
				.stream()
				.min(getDistanceSortComparator(customerCoordinates))
				.orElseThrow(()->new NoCarsNearFoundException("No active cars nearby found"));
		if(getDistanceBetweenCarAndCoordinate(nearestCar, customerCoordinates)>CAR_SEARCH_RADIUS_KM)
			throw new NearestCarTooFarException("Unfortunately, the neasrest car to you is too far");
		return nearestCar;
	}
	
	public Car getCarByOrderId(int orderid) {
		return this.orderRepository.findCarById(orderid).orElseThrow(()->new NoCarsFoundException("No car found by order"));
	}
	
	
	private Comparator<Car> getDistanceSortComparator(Coordinates customerCoordinates){
		return (car1, car2)->Double.compare(getDistanceBetweenCarAndCoordinate(car1, customerCoordinates), getDistanceBetweenCarAndCoordinate(car2, customerCoordinates));
	}
	
	private double convertCoordinatesStringToDouble(String coodinatesString) {
		return Double.parseDouble(coodinatesString);
	}
	
	private double degreesToRadians(double degrees) {
		return degrees * Math.PI / 180;
	}

	
	private double getDistanceBetweenCarAndCoordinate(Car car, Coordinates coordinate) {
		return getDistanceBetweenTwoCoordinates(car.getCoordinates(), coordinate);
	}
	
	private double getDistanceBetweenTwoCoordinates(Coordinates coordinate1, Coordinates coordinate2) {
		return getDistanceInKmBetweenEarthCoordinates(convertCoordinatesStringToDouble(coordinate1.getLatitude()), 
				convertCoordinatesStringToDouble(coordinate1.getLongitude()), 
				convertCoordinatesStringToDouble(coordinate2.getLatitude()), 
				convertCoordinatesStringToDouble(coordinate2.getLongitude()));
	}
	
	private double getDistanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
		int earthRadiusKm = 6371;

		double dLat = degreesToRadians(lat2 - lat1);
		double dLon = degreesToRadians(lon2 - lon1);

		lat1 = degreesToRadians(lat1);
		lat2 = degreesToRadians(lat2);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return earthRadiusKm * c;
	}
	
}
